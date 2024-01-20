package net.chexxor.funmod.block.entity;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import net.chexxor.funmod.FunMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
// import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.crafting.AbstractCookingRecipe;
// import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
// import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FunFurnaceBlockEntity extends AbstractFurnaceBlockEntity
{
    // private static final float MIN_SPEED_MODIFIER = 0.1f;
    // private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;

    // private float speedModifier = 1f;

    // public FunFurnaceBlockEntity(BlockPos pos, BlockState state, float speedModifier)
    public FunFurnaceBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.FUN_FURNACE_ENTITY.get(), pos, state, RecipeType.SMELTING);
        // this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
        // this.speedModifier = speedModifier;
        // this.speedModifier = 0.2f;
        // if(speedModifier < MIN_SPEED_MODIFIER)
        //     this.speedModifier = 0.1f;
    }

    // @Override
    // public Component getDisplayName()
    // {
    //     return this.getDefaultName();
    // }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("container.furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, @Nonnull Inventory inventory)
    {
        FunMod.Log("Entity - createMenu");
        return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
    }

    // @Override
    // public void setItem(int slot, @Nonnull ItemStack itemStack)
    // {
    //     ItemStack existingStack = this.items.get(slot);
    //     boolean isSameItem = !itemStack.isEmpty() && itemStack.sameItem(existingStack)
    //             && ItemStack.tagMatches(itemStack, existingStack);
    //     this.items.set(slot, itemStack);
    //     if (itemStack.getCount() > this.getMaxStackSize()) {
    //         itemStack.setCount(this.getMaxStackSize());
    //     }

    //     // If the item in the input slot has changed, reset the cooking progress and
    //     // total time
    //     if (slot == 0 && !isSameItem) {
    //         dataAccess.set(DATA_COOKING_TOTAL_TIME, getTotalCookTime());
    //         dataAccess.set(DATA_COOKING_PROGRESS, 0);
    //         this.setChanged();
    //     }
    // }

    // private int getTotalCookTime()
    // {
    //     Integer cookTime = quickCheck.getRecipeFor(this, level).map(AbstractCookingRecipe::getCookingTime).orElse(200);
    //     return (int)(cookTime.intValue() * (1 / speedModifier));
    // }
}
