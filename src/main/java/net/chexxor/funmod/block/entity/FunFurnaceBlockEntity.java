package net.chexxor.funmod.block.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.chexxor.funmod.FunMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FunFurnaceBlockEntity extends AbstractFurnaceBlockEntity
{
    private static final float MIN_SPEED_MODIFIER = 0.1f;
    private static final float DEFAULT_SPEED_MODIFIER = 5f;

    private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;

    private float speedModifier = 1f;

    public FunFurnaceBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.FUN_FURNACE_ENTITY.get(), pos, state, RecipeType.SMELTING);

        this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
        setSpeedModifier(DEFAULT_SPEED_MODIFIER);
    }

    public void setSpeedModifier(float speedModifier)
    {
        this.speedModifier = Math.max(speedModifier, MIN_SPEED_MODIFIER);
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("container.funmod.furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, @Nonnull Inventory inventory)
    {
        return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
    }

    @Override
    public void setItem(int slot, @Nonnull ItemStack itemStack)
    {
        ItemStack existingStack = this.items.get(slot);
        boolean isSameItem = !itemStack.isEmpty() && ItemStack.isSameItemSameTags(itemStack, existingStack);
        this.items.set(slot, itemStack);
        if (itemStack.getCount() > this.getMaxStackSize()) {
            itemStack.setCount(this.getMaxStackSize());
        }

        // If the item in the input slot has changed, reset the cooking progress and
        // total time
        if (slot == 0 && !isSameItem) {
            dataAccess.set(DATA_COOKING_TOTAL_TIME, getTotalCookTime());
            dataAccess.set(DATA_COOKING_PROGRESS, 0);
            this.setChanged();
        }
    }

    private int getTotalCookTime()
    {
        Integer cookTime = quickCheck.getRecipeFor(this, level).map(recipeHolder -> {
            return recipeHolder.value().getCookingTime();
        }).orElse(200);
        int modifiedTime = (int)(cookTime.intValue() * (1 / speedModifier));
        FunMod.Log("Entity - getTotalCookTime: " + cookTime + " * 1/" + speedModifier + " = " + modifiedTime);
        return modifiedTime;
    }

    private boolean isLit()
    {
        return dataAccess.get(DATA_LIT_TIME) > 0;
    }

    private boolean burn(RegistryAccess access, @Nullable RecipeHolder<?> recipeHolder, NonNullList<ItemStack> itemstackList, int containerMaxSize)
    {
        if (recipeHolder != null && this.canBurn(access, recipeHolder, itemstackList, containerMaxSize))
        {
            ItemStack input = itemstackList.get(SLOT_INPUT);
            ItemStack recipeOutput = ((RecipeHolder<Recipe<WorldlyContainer>>) recipeHolder).value().assemble(this, access);
            ItemStack output = itemstackList.get(SLOT_RESULT);

            if (output.isEmpty())
            {
                itemstackList.set(SLOT_RESULT, recipeOutput.copy());
            }
            else if (output.is(recipeOutput.getItem()))
            {
                output.grow(recipeOutput.getCount());
            }

            if (input.is(Blocks.WET_SPONGE.asItem()) && !itemstackList.get(SLOT_FUEL).isEmpty() && itemstackList.get(SLOT_FUEL).is(Items.BUCKET))
            {
                itemstackList.set(SLOT_FUEL, new ItemStack(Items.WATER_BUCKET));
            }

            input.shrink(SLOT_FUEL);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean canBurn(RegistryAccess access, @Nullable RecipeHolder<?> recipeHolder, NonNullList<ItemStack> itemstackList, int containerMaxSize)
    {
        if (!itemstackList.get(SLOT_INPUT).isEmpty() && recipeHolder != null)
        {
            ItemStack recipeOutput = ((RecipeHolder<Recipe<WorldlyContainer>>) recipeHolder).value().assemble(this, access);
            if (recipeOutput.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack outputStack = itemstackList.get(SLOT_RESULT);
                if (outputStack.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(outputStack, recipeOutput)) {
                    return false;
                }
                // Forge fix: make furnace respect stack sizes in furnace recipes
                else if (outputStack.getCount() + recipeOutput.getCount() <= containerMaxSize && outputStack.getCount() + recipeOutput.getCount() <= outputStack.getMaxStackSize())
                {
                    return true;
                }
                // Forge fix: make furnace respect stack sizes in furnace recipes
                else
                {
                    return outputStack.getCount() + recipeOutput.getCount() <= recipeOutput.getMaxStackSize();
                }
            }
        }
        else
        {
            return false;
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, FunFurnaceBlockEntity entity)
    {
        entity.serverTick(level, pos, state);
    }

    private void serverTick(Level level, BlockPos pos, BlockState state)
    {
        boolean wasLit = isLit();
        boolean dirty = false;

        int litTime = dataAccess.get(DATA_LIT_TIME);
        int cookingProgress = dataAccess.get(DATA_COOKING_PROGRESS);
        int cookingTotalTime = dataAccess.get(DATA_COOKING_TOTAL_TIME);

        if (isLit())
        {
            --litTime;
            dataAccess.set(DATA_LIT_TIME, litTime);
        }

        ItemStack itemstack = items.get(SLOT_FUEL);
        boolean hasInput = !items.get(SLOT_INPUT).isEmpty();
        boolean hasFuel = !itemstack.isEmpty();

        // Should burn
        if (isLit() || hasFuel && hasInput)
        {
            RecipeHolder<?> recipeHolder;
            if (hasInput)
            {
                recipeHolder = quickCheck.getRecipeFor(this, level).orElse(null);
            }
            else
            {
                recipeHolder = null;
            }

            // Start burning
            int i = getMaxStackSize();
            boolean canBurn = canBurn(level.registryAccess(), recipeHolder, items, i);
            if (!isLit() && canBurn)
            {
                litTime = getBurnDuration(itemstack);
                dataAccess.set(DATA_LIT_TIME, litTime);
                dataAccess.set(DATA_LIT_DURATION, litTime);
                if (isLit()) {
                    dirty = true;
                    if (itemstack.hasCraftingRemainingItem())
                        items.set(1, itemstack.getCraftingRemainingItem());
                    else
                    if (hasFuel) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            // Burn progress
            if (isLit() && canBurn(level.registryAccess(), recipeHolder, items, i))
            {
                ++cookingProgress;
                if (cookingProgress == cookingTotalTime)
                {
                    cookingProgress = 0;
                    cookingTotalTime = getTotalCookTime();
                    if (burn(level.registryAccess(), recipeHolder, items, i)) {
                        setRecipeUsed(recipeHolder);
                    }

                    dirty = true;
                }
            }
            else
            {
                cookingProgress = 0;
            }
        }
        // Not burning - Progress ticking down to 0
        else if (!isLit() && cookingProgress > 0)
        {
            cookingProgress = Mth.clamp(cookingProgress - 2, 0, cookingTotalTime);
        }

        dataAccess.set(DATA_COOKING_PROGRESS, cookingProgress);
        dataAccess.set(DATA_COOKING_TOTAL_TIME, cookingTotalTime);

        if (wasLit != isLit())
        {
            dirty = true;
            Boolean newLitValue = Boolean.valueOf(isLit());
            state = state.setValue(AbstractFurnaceBlock.LIT, newLitValue);
            level.setBlock(pos, state, 3);
        }

        if (dirty)
        {
            setChanged(level, pos, state);
        }
    }
}