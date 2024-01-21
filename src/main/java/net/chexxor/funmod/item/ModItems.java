package net.chexxor.funmod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.chexxor.funmod.FunMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
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
    public static Properties DefaultProperties(){ return new Item.Properties().tab(ModCreativeModeTab.FUNMOD_TAB); }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FunMod.MOD_ID);

    // Materials
    public static final RegistryObject<Item> BLACK_INGOT = ITEMS.register("black_ingot", () -> new Item(DefaultProperties()));

    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(DefaultProperties()));
    public static final RegistryObject<Item> MITHRIL_NUGGET = ITEMS.register("mithril_nugget",  () -> new Item(DefaultProperties()));

    // Tools
    public static final RegistryObject<Item> BLACK_SWORD        = ITEMS.register("black_sword",         getToolSupplier(ModTiers.BLACK, ToolType.SWORD));
    public static final RegistryObject<Item> BLACK_PICKAXE      = ITEMS.register("black_pickaxe",       getToolSupplier(ModTiers.BLACK, ToolType.PICKAXE));
    public static final RegistryObject<Item> BLACK_AXE          = ITEMS.register("black_axe",           getToolSupplier(ModTiers.BLACK, ToolType.AXE));
    public static final RegistryObject<Item> BLACK_SHOVEL       = ITEMS.register("black_shovel",        getToolSupplier(ModTiers.BLACK, ToolType.SHOVEL));
    public static final RegistryObject<Item> BLACK_HOE          = ITEMS.register("black_hoe",           getToolSupplier(ModTiers.BLACK, ToolType.HOE));

    public static final RegistryObject<Item> MITHRIL_SWORD      = ITEMS.register("mithril_sword",       getToolSupplier(ModTiers.MITHRIL, ToolType.SWORD));
    public static final RegistryObject<Item> MITHRIL_PICKAXE    = ITEMS.register("mithril_pickaxe",     getToolSupplier(ModTiers.MITHRIL, ToolType.PICKAXE));
    public static final RegistryObject<Item> MITHRIL_AXE        = ITEMS.register("mithril_axe",         getToolSupplier(ModTiers.MITHRIL, ToolType.AXE));
    public static final RegistryObject<Item> MITHRIL_SHOVEL     = ITEMS.register("mithril_shovel",      getToolSupplier(ModTiers.MITHRIL, ToolType.SHOVEL));
    public static final RegistryObject<Item> MITHRIL_HOE        = ITEMS.register("mithril_hoe",         getToolSupplier(ModTiers.MITHRIL, ToolType.HOE));

    // Armor
    public static final RegistryObject<ArmorItem> BLACK_HELMET       = ITEMS.register("black_helmet",        getArmorSupplier(ModArmorMaterials.BLACK, EquipmentSlot.HEAD));
    public static final RegistryObject<ArmorItem> BLACK_CHESTPLATE   = ITEMS.register("black_chestplate",    getArmorSupplier(ModArmorMaterials.BLACK, EquipmentSlot.CHEST));
    public static final RegistryObject<ArmorItem> BLACK_LEGGINGS     = ITEMS.register("black_leggings",      getArmorSupplier(ModArmorMaterials.BLACK, EquipmentSlot.LEGS));
    public static final RegistryObject<ArmorItem> BLACK_BOOTS        = ITEMS.register("black_boots",         getArmorSupplier(ModArmorMaterials.BLACK, EquipmentSlot.FEET));

    public static final RegistryObject<ArmorItem> MITHRIL_HELMET     = ITEMS.register("mithril_helmet",      getArmorSupplier(ModArmorMaterials.MITHRIL, EquipmentSlot.HEAD));
    public static final RegistryObject<ArmorItem> MITHRIL_CHESTPLATE = ITEMS.register("mithril_chestplate",  getArmorSupplier(ModArmorMaterials.MITHRIL, EquipmentSlot.CHEST));
    public static final RegistryObject<ArmorItem> MITHRIL_LEGGINGS   = ITEMS.register("mithril_leggings",    getArmorSupplier(ModArmorMaterials.MITHRIL, EquipmentSlot.LEGS));
    public static final RegistryObject<ArmorItem> MITHRIL_BOOTS      = ITEMS.register("mithril_boots",       getArmorSupplier(ModArmorMaterials.MITHRIL, EquipmentSlot.FEET));

    // Old way of doing it:
    // public static final RegistryObject<Item> MITHRIL_SWORD      = ITEMS.register("mithril_sword",   () -> new SwordItem(ModTiers.MITHRIL, 3, -2.4f, DefaultProperties()));
    // public static final RegistryObject<Item> MITHRIL_PICKAXE    = ITEMS.register("mithril_pickaxe", () -> new PickaxeItem(ModTiers.MITHRIL, 1, -2.8f, DefaultProperties()));
    // public static final RegistryObject<Item> MITHRIL_AXE        = ITEMS.register("mithril_axe",     () -> new AxeItem(ModTiers.MITHRIL, 6, -3.2f, DefaultProperties()));
    // public static final RegistryObject<Item> MITHRIL_SHOVEL     = ITEMS.register("mithril_shovel",  () -> new ShovelItem(ModTiers.MITHRIL, 1, -3.0f, DefaultProperties()));
    // public static final RegistryObject<Item> MITHRIL_HOE        = ITEMS.register("mithril_hoe",     () -> new HoeItem(ModTiers.MITHRIL, 0, -1.0f, DefaultProperties()));

    // TODO: Add override for custom strength and speed values
    public static Supplier<? extends Item> getToolSupplier(@Nonnull ModTiers tier, ToolType type)
    {
        switch (type)
        {
            case SWORD:
                return () -> new SwordItem(tier, 3, -2.4f, DefaultProperties());
            case PICKAXE:
                return () -> new PickaxeItem(tier, 1, -2.8f, DefaultProperties());
            case AXE:
                return () -> new AxeItem(tier, 6, -3.2f, DefaultProperties());
            case SHOVEL:
                return () -> new ShovelItem(tier, 1, -3.0f, DefaultProperties());
            case HOE:
                return () -> new HoeItem(tier, 0, -1.0f, DefaultProperties());
            default:
                FunMod.Log("Error: getToolItem: Unknown ToolType: " + type.toString());
                return null;
        }
    }

    public static Supplier<? extends ArmorItem> getArmorSupplier(@Nonnull ArmorMaterial tier, EquipmentSlot type)
    {
        return () -> new ArmorItem(tier, type, DefaultProperties());
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
