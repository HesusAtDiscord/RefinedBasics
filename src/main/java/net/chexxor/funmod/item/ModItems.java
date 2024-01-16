package net.chexxor.funmod.item;

import net.chexxor.funmod.FunMod;
import net.chexxor.funmod.material.ModTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final Properties DefaultProperties = new Item.Properties().tab(ModCreativeModeTab.FUNMOD_TAB);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FunMod.MOD_ID);

    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(DefaultProperties));
    public static final RegistryObject<Item> MITHRIL_NUGGET = ITEMS.register("mithril_nugget",  () -> new Item(DefaultProperties));

    public static final RegistryObject<Item> MITHRIL_SWORD      = ITEMS.register("mithril_sword",   () -> new SwordItem(ModTiers.MITHRIL, 3, -2.4f, DefaultProperties));
    public static final RegistryObject<Item> MITHRIL_PICKAXE    = ITEMS.register("mithril_pickaxe", () -> new PickaxeItem(ModTiers.MITHRIL, 1, -2.8f, DefaultProperties));
    public static final RegistryObject<Item> MITHRIL_AXE        = ITEMS.register("mithril_axe",     () -> new AxeItem(ModTiers.MITHRIL, 6, -3.2f, DefaultProperties));
    public static final RegistryObject<Item> MITHRIL_SHOVEL     = ITEMS.register("mithril_shovel",  () -> new ShovelItem(ModTiers.MITHRIL, 1, -3.0f, DefaultProperties));
    public static final RegistryObject<Item> MITHRIL_HOE        = ITEMS.register("mithril_hoe",     () -> new HoeItem(ModTiers.MITHRIL, 0, -1.0f, DefaultProperties));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
