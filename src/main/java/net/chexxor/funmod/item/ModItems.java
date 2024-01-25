package net.chexxor.funmod.item;

import java.util.function.Supplier;

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

public class ModItems
{
    public static final Properties DefaultProperties(){ return new Item.Properties(); }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FunMod.MOD_ID);

    public static final RegistryObject<Item> MITHRIL_INGOT = createItem("mithril_ingot");
    public static final RegistryObject<Item> MITHRIL_NUGGET = createItem("mithril_nugget");

    public static final RegistryObject<Item> MITHRIL_SWORD      = createItem("mithril_sword",   () -> new SwordItem(ModTiers.MITHRIL, 3, -2.4f, DefaultProperties()));
    public static final RegistryObject<Item> MITHRIL_PICKAXE    = createItem("mithril_pickaxe", () -> new PickaxeItem(ModTiers.MITHRIL, 1, -2.8f, DefaultProperties()));
    public static final RegistryObject<Item> MITHRIL_AXE        = createItem("mithril_axe",     () -> new AxeItem(ModTiers.MITHRIL, 6, -3.2f, DefaultProperties()));
    public static final RegistryObject<Item> MITHRIL_SHOVEL     = createItem("mithril_shovel",  () -> new ShovelItem(ModTiers.MITHRIL, 1, -3.0f, DefaultProperties()));
    public static final RegistryObject<Item> MITHRIL_HOE        = createItem("mithril_hoe",     () -> new HoeItem(ModTiers.MITHRIL, 0, -1.0f, DefaultProperties()));

    public static RegistryObject<Item> createItem(String name)
    {
        return createItem(name, DefaultProperties());
    }

    private static RegistryObject<Item> createItem(String name, Properties properties)
    {
        return createItem(name, () -> new Item(properties));
    }

    public static <T extends Item> RegistryObject<T> createItem(String name, Supplier<T> item)
    {
        RegistryObject<T> toReturn = ITEMS.register(name, item);
        ModCreativeModeTab.addToTab(toReturn);
        return toReturn;
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
