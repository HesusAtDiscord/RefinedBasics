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
    public static final RegistryObject<Item> MITHRIL_INGOT = createItem("mithril_ingot", new Item.Properties());
    public static final RegistryObject<Item> MITHRIL_RAW = createItem("mithril_raw", new Item.Properties());

// food values
    // baseline (melon, apples etc. RTG edible items)

// TODO calculate all of this (already done so kinda K to run for now)
    // baked
    public static final int NUTRITION_BREAD = 5;
    // sliced (uncooked)
    public static final int NUTRITION_BREAD_SLICE = NUTRITION_BREAD/5;
    public static final int NUTRITION_RAW_BACON_SLICE = 0;
    // cooked/smelted
    public static final int NUTRITION_COOKED_BEEF = 8;
    public static final int NUTRITION_COOKED_PORKCHOP = 8;
    public static final int NUTRITION_COOKED_MUTTON = 6;
    public static final int NUTRITION_COOKED_EGG = 3;
    public static final int NUTRITION_COOKED_BACON = NUTRITION_RAW_BACON_SLICE+3;
    public static final int NUTRITION_COOKED_CHICKEN = 6;
    // sliced AND cooked
    public static final int NUTRITION_COOKED_BEEF_SLICE = NUTRITION_COOKED_BEEF/4;
    public static final int NUTRITION_COOKED_PORKCHOP_SLICE = NUTRITION_COOKED_PORKCHOP/4;
    public static final int NUTRITION_COOKED_MUTTON_SLICE = NUTRITION_COOKED_MUTTON/3;
    public static final int NUTRITION_COOKED_CHICKEN_SLICE = NUTRITION_COOKED_CHICKEN/3;

    //combined (6 basic, +2 for every added ingredient
    public static final int NUTRITION_SANDWICH_BEEF = (NUTRITION_BREAD_SLICE+NUTRITION_COOKED_BEEF_SLICE)*2;
    public static final int NUTRITION_SANDWICH_PORKCHOP = (NUTRITION_BREAD_SLICE+NUTRITION_COOKED_PORKCHOP_SLICE)*2;
    public static final int NUTRITION_SANDWICH_MUTTON = (NUTRITION_BREAD_SLICE+NUTRITION_COOKED_MUTTON_SLICE)*2;
    public static final int NUTRITION_SANDWICH_CHICKEN = (NUTRITION_BREAD_SLICE+NUTRITION_COOKED_CHICKEN_SLICE)*2;
    public static final int NUTRITION_SANDWICH_EGG_BACON = (NUTRITION_BREAD_SLICE+NUTRITION_COOKED_EGG+NUTRITION_COOKED_BACON)+1;

    // TODO bubblegum + damage boost



// food
    // baseline items
    public static final FoodProperties BREAD_SLICE_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_BREAD_SLICE).saturationMod(0.5F).build();
    public static final RegistryObject<Item> BREAD_SLICE = createItem("bread_slice", new Item.Properties().food(BREAD_SLICE_PROPERTIES));
    public static final FoodProperties RAW_BACON_SLICE_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_RAW_BACON_SLICE).saturationMod(0.5F).build();
    public static final RegistryObject<Item> RAW_BACON_SLICE = createItem("raw_bacon_slice", new Item.Properties().food(RAW_BACON_SLICE_PROPERTIES));

    // cooked items
    public static final FoodProperties COOKED_BEEF_SLICE_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_COOKED_BEEF_SLICE).saturationMod(0.5F).build();
    public static final RegistryObject<Item> COOKED_BEEF_SLICE = createItem("cooked_beef_slice", new Item.Properties().food(COOKED_BEEF_SLICE_PROPERTIES));
    public static final FoodProperties COOKED_PORKCHOP_SLICE_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_COOKED_PORKCHOP_SLICE).saturationMod(0.5F).build();
    public static final RegistryObject<Item> COOKED_PORKCHOP_SLICE = createItem("cooked_porkchop_slice", new Item.Properties().food(COOKED_PORKCHOP_SLICE_PROPERTIES));
    public static final FoodProperties COOKED_MUTTON_SLICE_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_COOKED_MUTTON_SLICE).saturationMod(0.5F).build();
    public static final RegistryObject<Item> COOKED_MUTTON_SLICE = createItem("cooked_mutton_slice", new Item.Properties().food(COOKED_MUTTON_SLICE_PROPERTIES));
    public static final FoodProperties COOKED_EGG_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_COOKED_EGG).saturationMod(0.5F).build();
    public static final RegistryObject<Item> COOKED_EGG = createItem("cooked_egg", new Item.Properties().food(COOKED_EGG_PROPERTIES));
    public static final FoodProperties COOKED_BACON_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_COOKED_BACON).saturationMod(0.5F).build();
    public static final RegistryObject<Item> COOKED_BACON = createItem("cooked_bacon", new Item.Properties().food(COOKED_BACON_PROPERTIES));
    public static final FoodProperties COOKED_CHICKEN_SLICE_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_COOKED_CHICKEN_SLICE).saturationMod(0.5F).build();
    public static final RegistryObject<Item> COOKED_CHICKEN_SLICE = createItem("cooked_chicken_slice", new Item.Properties().food(COOKED_CHICKEN_SLICE_PROPERTIES));

    // Ur mom made these
    public static final FoodProperties SANDWICH_BEEF_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_SANDWICH_BEEF).saturationMod(0.5F).build();
    public static final RegistryObject<Item> SANDWICH_BEEF = createItem("sandwich_beef", new Item.Properties().food(SANDWICH_BEEF_PROPERTIES));
    public static final FoodProperties SANDWICH_PORKCHOP_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_SANDWICH_PORKCHOP).saturationMod(0.5F).build();
    public static final RegistryObject<Item> SANDWICH_PORKCHOP = createItem("sandwich_porkchop", new Item.Properties().food(SANDWICH_PORKCHOP_PROPERTIES));
    public static final FoodProperties SANDWICH_MUTTON_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_SANDWICH_MUTTON).saturationMod(0.5F).build();
    public static final RegistryObject<Item> SANDWICH_MUTTON = createItem("sandwich_mutton", new Item.Properties().food(SANDWICH_MUTTON_PROPERTIES));
    public static final FoodProperties SANDWICH_CHICKEN_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_SANDWICH_CHICKEN).saturationMod(0.5F).build();
    public static final RegistryObject<Item> SANDWICH_CHICKEN = createItem("sandwich_chicken", new Item.Properties().food(SANDWICH_CHICKEN_PROPERTIES));
    public static final FoodProperties SANDWICH_EGG_BACON_PROPERTIES = (new FoodProperties.Builder()).nutrition(NUTRITION_SANDWICH_EGG_BACON).saturationMod(0.5F).build();
    public static final RegistryObject<Item> SANDWICH_EGG_BACON = createItem("sandwich_egg_bacon", new Item.Properties().food(SANDWICH_EGG_BACON_PROPERTIES));
        // TODO sandwich variations (this enough?)

    // smells fishy
        // TODO sushi

    // Add the example block item to the building blocks tab
    private static RegistryObject<Item> createItem(String name, Item.Properties properties) {
        RegistryObject<Item> item = ITEMS.register(name, () -> new Item(properties));
        ModCreativeModeTab.addToTab(item);
        return item;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}