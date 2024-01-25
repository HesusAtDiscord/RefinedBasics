// package net.chexxor.funmod.block.entity;

// import com.google.common.collect.Lists;
// import com.google.common.collect.Maps;
// import it.unimi.dsi.fastutil.objects.Object2IntMap;
// import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
// import java.util.List;
// import java.util.Map;
// import javax.annotation.Nullable;

// import net.chexxor.funmod.FunMod;
// import net.minecraft.SharedConstants;
// import net.minecraft.Util;
// import net.minecraft.core.BlockPos;
// import net.minecraft.core.Direction;
// import net.minecraft.core.Holder;
// import net.minecraft.core.NonNullList;
// import net.minecraft.core.RegistryAccess;
// import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.nbt.CompoundTag;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.server.level.ServerLevel;
// import net.minecraft.server.level.ServerPlayer;
// import net.minecraft.tags.ItemTags;
// import net.minecraft.tags.TagKey;
// import net.minecraft.util.Mth;
// import net.minecraft.world.Container;
// import net.minecraft.world.ContainerHelper;
// import net.minecraft.world.WorldlyContainer;
// import net.minecraft.world.entity.ExperienceOrb;
// import net.minecraft.world.entity.player.Player;
// import net.minecraft.world.entity.player.StackedContents;
// import net.minecraft.world.inventory.ContainerData;
// import net.minecraft.world.inventory.RecipeCraftingHolder;
// import net.minecraft.world.inventory.StackedContentsCompatible;
// import net.minecraft.world.item.Item;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.Items;
// import net.minecraft.world.item.crafting.AbstractCookingRecipe;
// import net.minecraft.world.item.crafting.Recipe;
// import net.minecraft.world.item.crafting.RecipeHolder;
// import net.minecraft.world.item.crafting.RecipeManager;
// import net.minecraft.world.item.crafting.RecipeType;
// import net.minecraft.world.level.ItemLike;
// import net.minecraft.world.level.Level;
// import net.minecraft.world.level.block.AbstractFurnaceBlock;
// import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
// import net.minecraft.world.level.block.entity.BlockEntityType;
// import net.minecraft.world.level.block.state.BlockState;
// import net.minecraft.world.phys.Vec3;

// public abstract class AbstractFunFurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible
// {
//    protected static final int SLOT_INPUT = 0;
//    protected static final int SLOT_FUEL = 1;
//    protected static final int SLOT_RESULT = 2;
//    public static final int DATA_LIT_TIME = 0;
//    private static final int[] SLOTS_FOR_UP = new int[]{0};
//    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
//    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
//    public static final int DATA_LIT_DURATION = 1;
//    public static final int DATA_COOKING_PROGRESS = 2;
//    public static final int DATA_COOKING_TOTAL_TIME = 3;
//    public static final int NUM_DATA_VALUES = 4;
//    public static final int BURN_TIME_STANDARD = 200;
//    public static final int BURN_COOL_SPEED = 2;
//    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
//    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
//    int litTime;
//    int litDuration;
//    int cookingProgress;
//    int cookingTotalTime;

//    protected final ContainerData dataAccess = new ContainerData()
//    {
//       public int get(int p_58431_) {
//          switch (p_58431_) {
//             case 0:
//                return AbstractFunFurnaceBlockEntity.this.litTime;
//             case 1:
//                return AbstractFunFurnaceBlockEntity.this.litDuration;
//             case 2:
//                return AbstractFunFurnaceBlockEntity.this.cookingProgress;
//             case 3:
//                return AbstractFunFurnaceBlockEntity.this.cookingTotalTime;
//             default:
//                return 0;
//          }
//       }

//       public void set(int p_58433_, int p_58434_) {
//          switch (p_58433_) {
//             case 0:
//                AbstractFunFurnaceBlockEntity.this.litTime = p_58434_;
//                break;
//             case 1:
//                AbstractFunFurnaceBlockEntity.this.litDuration = p_58434_;
//                break;
//             case 2:
//                AbstractFunFurnaceBlockEntity.this.cookingProgress = p_58434_;
//                break;
//             case 3:
//                AbstractFunFurnaceBlockEntity.this.cookingTotalTime = p_58434_;
//          }
//       }

//       public int getCount() {
//          return 4;
//       }
//    };

//    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
//    private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;

//    protected AbstractFunFurnaceBlockEntity(BlockEntityType<?> p_154991_, BlockPos p_154992_, BlockState p_154993_, RecipeType<? extends AbstractCookingRecipe> p_154994_)
//    {
//       super(p_154991_, p_154992_, p_154993_);
//       this.quickCheck = RecipeManager.createCheck((RecipeType)p_154994_);
//       this.recipeType = p_154994_;
//    }

//    /**@deprecated Forge: get burn times by calling ForgeHooks#getBurnTime(ItemStack)*/ @Deprecated
//    public static Map<Item, Integer> getFuel()
//    {
//       Map<Item, Integer> map = Maps.newLinkedHashMap();
//       add(map, Items.LAVA_BUCKET, 20000);
//       add(map, Blocks.COAL_BLOCK, 16000);
//       add(map, Items.BLAZE_ROD, 2400);
//       add(map, Items.COAL, 1600);
//       add(map, Items.CHARCOAL, 1600);
//       add(map, ItemTags.LOGS, 300);
//       add(map, ItemTags.BAMBOO_BLOCKS, 300);
//       add(map, ItemTags.PLANKS, 300);
//       add(map, Blocks.BAMBOO_MOSAIC, 300);
//       add(map, ItemTags.WOODEN_STAIRS, 300);
//       add(map, Blocks.BAMBOO_MOSAIC_STAIRS, 300);
//       add(map, ItemTags.WOODEN_SLABS, 150);
//       add(map, Blocks.BAMBOO_MOSAIC_SLAB, 150);
//       add(map, ItemTags.WOODEN_TRAPDOORS, 300);
//       add(map, ItemTags.WOODEN_PRESSURE_PLATES, 300);
//       add(map, ItemTags.WOODEN_FENCES, 300);
//       add(map, ItemTags.FENCE_GATES, 300);
//       add(map, Blocks.NOTE_BLOCK, 300);
//       add(map, Blocks.BOOKSHELF, 300);
//       add(map, Blocks.CHISELED_BOOKSHELF, 300);
//       add(map, Blocks.LECTERN, 300);
//       add(map, Blocks.JUKEBOX, 300);
//       add(map, Blocks.CHEST, 300);
//       add(map, Blocks.TRAPPED_CHEST, 300);
//       add(map, Blocks.CRAFTING_TABLE, 300);
//       add(map, Blocks.DAYLIGHT_DETECTOR, 300);
//       add(map, ItemTags.BANNERS, 300);
//       add(map, Items.BOW, 300);
//       add(map, Items.FISHING_ROD, 300);
//       add(map, Blocks.LADDER, 300);
//       add(map, ItemTags.SIGNS, 200);
//       add(map, ItemTags.HANGING_SIGNS, 800);
//       add(map, Items.WOODEN_SHOVEL, 200);
//       add(map, Items.WOODEN_SWORD, 200);
//       add(map, Items.WOODEN_HOE, 200);
//       add(map, Items.WOODEN_AXE, 200);
//       add(map, Items.WOODEN_PICKAXE, 200);
//       add(map, ItemTags.WOODEN_DOORS, 200);
//       add(map, ItemTags.BOATS, 1200);
//       add(map, ItemTags.WOOL, 100);
//       add(map, ItemTags.WOODEN_BUTTONS, 100);
//       add(map, Items.STICK, 100);
//       add(map, ItemTags.SAPLINGS, 100);
//       add(map, Items.BOWL, 100);
//       add(map, ItemTags.WOOL_CARPETS, 67);
//       add(map, Blocks.DRIED_KELP_BLOCK, 4001);
//       add(map, Items.CROSSBOW, 300);
//       add(map, Blocks.BAMBOO, 50);
//       add(map, Blocks.DEAD_BUSH, 100);
//       add(map, Blocks.SCAFFOLDING, 50);
//       add(map, Blocks.LOOM, 300);
//       add(map, Blocks.BARREL, 300);
//       add(map, Blocks.CARTOGRAPHY_TABLE, 300);
//       add(map, Blocks.FLETCHING_TABLE, 300);
//       add(map, Blocks.SMITHING_TABLE, 300);
//       add(map, Blocks.COMPOSTER, 300);
//       add(map, Blocks.AZALEA, 100);
//       add(map, Blocks.FLOWERING_AZALEA, 100);
//       add(map, Blocks.MANGROVE_ROOTS, 300);
//       return map;
//    }

//    private static boolean isNeverAFurnaceFuel(Item p_58398_)
//    {
//       return p_58398_.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
//    }

//    private static void add(Map<Item, Integer> p_204303_, TagKey<Item> p_204304_, int p_204305_)
//    {
//       for(Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(p_204304_))
//       {
//          if (!isNeverAFurnaceFuel(holder.value())) {
//             p_204303_.put(holder.value(), p_204305_);
//          }
//       }
//    }

//    private static void add(Map<Item, Integer> p_58375_, ItemLike p_58376_, int p_58377_)
//    {
//       Item item = p_58376_.asItem();
//       if (isNeverAFurnaceFuel(item))
//       {
//          if (SharedConstants.IS_RUNNING_IN_IDE)
//          {
//             throw (IllegalStateException)Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
//          }
//       }
//       else
//       {
//          p_58375_.put(item, p_58377_);
//       }
//    }

//    private boolean isLit()
//    {
//       return this.litTime > 0;
//    }

//    public void load(CompoundTag tag)
//    {
//       super.load(tag);
//       this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
//       ContainerHelper.loadAllItems(tag, this.items);
//       this.litTime = tag.getInt("BurnTime");
//       FunMod.Log("load - litTime: " + this.litTime);
//       this.cookingProgress = tag.getInt("CookTime");
//       this.cookingTotalTime = tag.getInt("CookTimeTotal");
//       this.litDuration = this.getBurnDuration(this.items.get(1));
//       CompoundTag compoundtag = tag.getCompound("RecipesUsed");

//       for(String s : compoundtag.getAllKeys())
//       {
//          this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
//       }
//    }

//    protected void saveAdditional(CompoundTag tag)
//    {
//       super.saveAdditional(tag);
//       tag.putInt("BurnTime", this.litTime);
//       tag.putInt("CookTime", this.cookingProgress);
//       tag.putInt("CookTimeTotal", this.cookingTotalTime);
//       ContainerHelper.saveAllItems(tag, this.items);
//       CompoundTag compoundtag = new CompoundTag();
//       this.recipesUsed.forEach((location, value) -> {
//          compoundtag.putInt(location.toString(), value);
//       });
//       tag.put("RecipesUsed", compoundtag);
//    }

//    public static void serverTick(Level level, BlockPos pos, BlockState state, AbstractFunFurnaceBlockEntity entity)
//    {
//       boolean wasLit = entity.isLit();
//       boolean dirty = false;
//       if (entity.isLit())
//       {
//          --entity.litTime;
//       }

//       ItemStack itemstack = entity.items.get(SLOT_FUEL);
//       boolean hasInput = !entity.items.get(SLOT_INPUT).isEmpty();
//       boolean hasFuel = !itemstack.isEmpty();

//       // FunMod.Log("serverTick - hasInput: " + hasInput + " hasFuel: " + hasFuel + " isLit: " + wasLit);

//       if (entity.isLit() || hasFuel && hasInput)
//       {
//          RecipeHolder<?> recipeHolder;
//          if (hasInput)
//          {
//             recipeHolder = entity.quickCheck.getRecipeFor(entity, level).orElse(null);
//          }
//          else
//          {
//             recipeHolder = null;
//          }

//          // Start burning
//          int i = entity.getMaxStackSize();
//          boolean canBurn = entity.canBurn(level.registryAccess(), recipeHolder, entity.items, i);
//          FunMod.Log("serverTick - canBurn: " + canBurn);
//          if (!entity.isLit() && canBurn)
//          {
//             FunMod.Log("serverTick - Start Burning: " + entity.isLit());
//             entity.litTime = entity.getBurnDuration(itemstack);
//             entity.litDuration = entity.litTime;
//             if (entity.isLit()) {
//                dirty = true;
//                if (itemstack.hasCraftingRemainingItem())
//                   entity.items.set(1, itemstack.getCraftingRemainingItem());
//                else
//                if (hasFuel) {
//                   Item item = itemstack.getItem();
//                   itemstack.shrink(1);
//                   if (itemstack.isEmpty()) {
//                      entity.items.set(1, itemstack.getCraftingRemainingItem());
//                   }
//                }
//             }
//          }

//          // Burn progress
//          if (entity.isLit() && entity.canBurn(level.registryAccess(), recipeHolder, entity.items, i))
//          {
//             ++entity.cookingProgress;
//             FunMod.Log("serverTick - Burn Progress: " + entity.cookingProgress);
//             if (entity.cookingProgress == entity.cookingTotalTime) {
//                entity.cookingProgress = 0;
//                entity.cookingTotalTime = getTotalCookTime(level, entity);
//                if (entity.burn(level.registryAccess(), recipeHolder, entity.items, i)) {
//                   entity.setRecipeUsed(recipeHolder);
//                }

//                dirty = true;
//             }
//          }
//          else
//          {
//             entity.cookingProgress = 0;
//          }
//       }
//       else if (!entity.isLit() && entity.cookingProgress > 0)
//       {
//          entity.cookingProgress = Mth.clamp(entity.cookingProgress - 2, 0, entity.cookingTotalTime);
//       }

//       if (wasLit != entity.isLit())
//       {
//          dirty = true;
//          Boolean newLitValue = Boolean.valueOf(entity.isLit());
//          FunMod.Log("serverTick - newLitValue: " + newLitValue);
//          state = state.setValue(AbstractFurnaceBlock.LIT, newLitValue);
//          level.setBlock(pos, state, 3);
//       }

//       if (dirty)
//       {
//          setChanged(level, pos, state);
//       }

//    }

//    private boolean canBurn(RegistryAccess access, @Nullable RecipeHolder<?> recipeHolder, NonNullList<ItemStack> itemStacks, int p_155008_)
//    {
//       if (!itemStacks.get(SLOT_INPUT).isEmpty() && recipeHolder != null)
//       {
//          ItemStack itemstack = ((RecipeHolder<Recipe<WorldlyContainer>>) recipeHolder).value().assemble(this, access);
//          if (itemstack.isEmpty()) {
//             return false;
//          } else {
//             ItemStack resultStack = itemStacks.get(SLOT_RESULT);
//             if (resultStack.isEmpty()) {
//                return true;
//             } else if (!ItemStack.isSameItem(resultStack, itemstack)) {
//                return false;
//             } else if (resultStack.getCount() + itemstack.getCount() <= p_155008_ && resultStack.getCount() + itemstack.getCount() <= resultStack.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
//                return true;
//             } else {
//                return resultStack.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
//             }
//          }
//       } else {
//          return false;
//       }
//    }

//    private boolean burn(RegistryAccess access, @Nullable RecipeHolder<?> recipeHolder, NonNullList<ItemStack> itemStack, int p_155029_)
//    {
//       if (recipeHolder != null && this.canBurn(access, recipeHolder, itemStack, p_155029_)) {
//          ItemStack itemstack = itemStack.get(0);
//          ItemStack itemstack1 = ((RecipeHolder<Recipe<WorldlyContainer>>) recipeHolder).value().assemble(this, access);
//          ItemStack itemstack2 = itemStack.get(2);
//          if (itemstack2.isEmpty()) {
//             itemStack.set(2, itemstack1.copy());
//          } else if (itemstack2.is(itemstack1.getItem())) {
//             itemstack2.grow(itemstack1.getCount());
//          }

//          if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !itemStack.get(1).isEmpty() && itemStack.get(1).is(Items.BUCKET)) {
//             itemStack.set(1, new ItemStack(Items.WATER_BUCKET));
//          }

//          itemstack.shrink(1);
//          return true;
//       } else {
//          return false;
//       }
//    }

//    protected int getBurnDuration(ItemStack p_58343_)
//    {
//       if (p_58343_.isEmpty()) {
//          return 0;
//       } else {
//          Item item = p_58343_.getItem();
//          return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58343_, this.recipeType);
//       }
//    }

//    private static int getTotalCookTime(Level level, AbstractFunFurnaceBlockEntity entity)
//    {
//       return entity.quickCheck.getRecipeFor(entity, level).map((recipe) -> {
//          return recipe.value().getCookingTime();
//          }).orElse(200);
//    }

//    public static boolean isFuel(ItemStack p_58400_)
//    {
//       return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58400_, null) > 0;
//    }

//    public int[] getSlotsForFace(Direction p_58363_)
//    {
//       if (p_58363_ == Direction.DOWN) {
//          return SLOTS_FOR_DOWN;
//       } else {
//          return p_58363_ == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
//       }
//    }

//    public boolean canPlaceItemThroughFace(int p_58336_, ItemStack p_58337_, @Nullable Direction p_58338_)
//    {
//       return this.canPlaceItem(p_58336_, p_58337_);
//    }

//    public boolean canTakeItemThroughFace(int p_58392_, ItemStack p_58393_, Direction p_58394_)
//    {
//       if (p_58394_ == Direction.DOWN && p_58392_ == 1) {
//          return p_58393_.is(Items.WATER_BUCKET) || p_58393_.is(Items.BUCKET);
//       } else {
//          return true;
//       }
//    }

//    public int getContainerSize() {
//       return this.items.size();
//    }

//    public boolean isEmpty() {
//       for(ItemStack itemstack : this.items) {
//          if (!itemstack.isEmpty()) {
//             return false;
//          }
//       }

//       return true;
//    }

//    public ItemStack getItem(int p_58328_) {
//       return this.items.get(p_58328_);
//    }

//    public ItemStack removeItem(int p_58330_, int p_58331_) {
//       return ContainerHelper.removeItem(this.items, p_58330_, p_58331_);
//    }

//    public ItemStack removeItemNoUpdate(int p_58387_) {
//       return ContainerHelper.takeItem(this.items, p_58387_);
//    }

//    public void setItem(int slot, ItemStack itemsToSet) {
//       ItemStack currentItems = this.items.get(slot);
//       boolean flag = !itemsToSet.isEmpty() && ItemStack.isSameItemSameTags(itemsToSet, currentItems);
//       this.items.set(slot, itemsToSet);
//       if (itemsToSet.getCount() > this.getMaxStackSize()) {
//          itemsToSet.setCount(this.getMaxStackSize());
//       }

//       if (slot == 0 && !flag) {
//          this.cookingTotalTime = getTotalCookTime(this.level, this);
//          this.cookingProgress = 0;
//          this.setChanged();
//       }

//    }

//    public boolean stillValid(Player player) {
//       return Container.stillValidBlockEntity(this, player);
//    }

//    public boolean canPlaceItem(int p_58389_, ItemStack p_58390_) {
//       if (p_58389_ == 2) {
//          return false;
//       } else if (p_58389_ != 1) {
//          return true;
//       } else {
//          ItemStack itemstack = this.items.get(1);
//          return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58390_, this.recipeType) > 0 || p_58390_.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
//       }
//    }

//    public void clearContent() {
//       this.items.clear();
//    }

//    public void setRecipeUsed(@Nullable RecipeHolder<?> p_297739_) {
//       if (p_297739_ != null) {
//          ResourceLocation resourcelocation = p_297739_.id();
//          this.recipesUsed.addTo(resourcelocation, 1);
//       }

//    }

//    @Nullable
//    public RecipeHolder<?> getRecipeUsed() {
//       return null;
//    }

//    public void awardUsedRecipes(Player p_58396_, List<ItemStack> p_282202_) {
//    }

//    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
//       List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
//       player.awardRecipes(list);

//       for(RecipeHolder<?> recipeholder : list) {
//          if (recipeholder != null) {
//             player.triggerRecipeCrafted(recipeholder, this.items);
//          }
//       }

//       this.recipesUsed.clear();
//    }

//    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 p_154997_) {
//       List<RecipeHolder<?>> list = Lists.newArrayList();

//       for(Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
//          level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipeHolder) -> {
//             list.add(recipeHolder);
//             createExperience(level, p_154997_, entry.getIntValue(), ((AbstractCookingRecipe)recipeHolder.value()).getExperience());
//          });
//       }

//       return list;
//    }

//    private static void createExperience(ServerLevel p_154999_, Vec3 p_155000_, int p_155001_, float p_155002_) {
//       int i = Mth.floor((float)p_155001_ * p_155002_);
//       float f = Mth.frac((float)p_155001_ * p_155002_);
//       if (f != 0.0F && Math.random() < (double)f) {
//          ++i;
//       }

//       ExperienceOrb.award(p_154999_, p_155000_, i);
//    }

//    public void fillStackedContents(StackedContents p_58342_) {
//       for(ItemStack itemstack : this.items) {
//          p_58342_.accountStack(itemstack);
//       }

//    }

//    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
//            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

//    @Override
//    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
//       if (!this.remove && facing != null && capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER) {
//          if (facing == Direction.UP)
//             return handlers[0].cast();
//          else if (facing == Direction.DOWN)
//             return handlers[1].cast();
//          else
//             return handlers[2].cast();
//       }
//       return super.getCapability(capability, facing);
//    }

//    @Override
//    public void invalidateCaps() {
//       super.invalidateCaps();
//       for (int x = 0; x < handlers.length; x++)
//         handlers[x].invalidate();
//    }

//    @Override
//    public void reviveCaps() {
//       super.reviveCaps();
//       this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
//    }
// }
