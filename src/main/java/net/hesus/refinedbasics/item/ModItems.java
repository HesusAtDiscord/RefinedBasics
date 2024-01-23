package net.hesus.refinedbasics.item;

import net.hesus.refinedbasics.RefinedBasics;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
//registers
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RefinedBasics.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RefinedBasics.MOD_ID);
// misc
    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.REFINEDBASICS_TAB)));
    public static final RegistryObject<Item> MITHRIL_RAW = ITEMS.register("mithril_raw", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.REFINEDBASICS_TAB)));

// food values
    public static final int NUTRITION_BREAD = 5;
    public static final int NUTRITION_BREAD_SLICES = 1;
    public static final int NUTRITION_COOKED_BEEF = 8;
    public static final int NUTRITION_COOKED_BEEF_SLICES = NUTRITION_COOKED_BEEF/4;
    public static final int NUTRITION_SANDWICH_BEEF = (NUTRITION_BREAD_SLICES+NUTRITION_COOKED_BEEF_SLICES)*2;
    // TODO bubblegum + damage boost
// food
    public static final FoodProperties SANDWICH_BEEF_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_SANDWICH_BEEF).saturationMod(0.5F).build();
    public static final RegistryObject<Item> SANDWICH_BEEF = ITEMS.register("sandwich_beef", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.REFINEDBASICS_TAB).food(SANDWICH_BEEF_PROPERTIES)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
