package net.chexxor.funmod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.chexxor.funmod.FunMod;
import net.chexxor.funmod.item.smithing.SmithingTemplates;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
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
    public static final Properties DefaultProperties(){ return new Item.Properties(); }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FunMod.MOD_ID);

    // Materials
    public static final RegistryObject<Item> BLACK_INGOT = createItem("black_ingot");
    public static final RegistryObject<Item> MITHRIL_INGOT = createItem("mithril_ingot");
    public static final RegistryObject<Item> MITHRIL_NUGGET = createItem("mithril_nugget");
    public static final RegistryObject<Item> PINK_DIAMOND = createItem("pink_diamond");

    // Tools
    public static final RegistryObject<Item> BLACK_SWORD        = createItem("black_sword",     getToolSupplier(ModTiers.BLACK, ToolType.SWORD));
    public static final RegistryObject<Item> BLACK_PICKAXE      = createItem("black_pickaxe",   getToolSupplier(ModTiers.BLACK, ToolType.PICKAXE));
    public static final RegistryObject<Item> BLACK_AXE          = createItem("black_axe",       getToolSupplier(ModTiers.BLACK, ToolType.AXE));
    public static final RegistryObject<Item> BLACK_SHOVEL       = createItem("black_shovel",    getToolSupplier(ModTiers.BLACK, ToolType.SHOVEL));
    public static final RegistryObject<Item> BLACK_HOE          = createItem("black_hoe",       getToolSupplier(ModTiers.BLACK, ToolType.HOE));

    public static final RegistryObject<Item> MITHRIL_SWORD      = createItem("mithril_sword",   getToolSupplier(ModTiers.MITHRIL, ToolType.SWORD));
    public static final RegistryObject<Item> MITHRIL_PICKAXE    = createItem("mithril_pickaxe", getToolSupplier(ModTiers.MITHRIL, ToolType.PICKAXE));
    public static final RegistryObject<Item> MITHRIL_AXE        = createItem("mithril_axe",     getToolSupplier(ModTiers.MITHRIL, ToolType.AXE));
    public static final RegistryObject<Item> MITHRIL_SHOVEL     = createItem("mithril_shovel",  getToolSupplier(ModTiers.MITHRIL, ToolType.SHOVEL));
    public static final RegistryObject<Item> MITHRIL_HOE        = createItem("mithril_hoe",     getToolSupplier(ModTiers.MITHRIL, ToolType.HOE));

    // Armor
    public static final RegistryObject<Item> BLACK_HELMET       = createItem("black_helmet",        getArmorSupplier(ModArmorMaterials.BLACK, Type.HELMET));
    public static final RegistryObject<Item> BLACK_CHESTPLATE   = createItem("black_chestplate",    getArmorSupplier(ModArmorMaterials.BLACK, Type.CHESTPLATE));
    public static final RegistryObject<Item> BLACK_LEGGINGS     = createItem("black_leggings",      getArmorSupplier(ModArmorMaterials.BLACK, Type.LEGGINGS));
    public static final RegistryObject<Item> BLACK_BOOTS        = createItem("black_boots",         getArmorSupplier(ModArmorMaterials.BLACK, Type.BOOTS));

    public static final RegistryObject<Item> BLACK_HELMET_G     = createItem("black_g_helmet",      getArmorSupplier(ModArmorMaterials.BLACK_GOLD, Type.HELMET));
    public static final RegistryObject<Item> BLACK_CHESTPLATE_G = createItem("black_g_chestplate",  getArmorSupplier(ModArmorMaterials.BLACK_GOLD, Type.CHESTPLATE));
    public static final RegistryObject<Item> BLACK_LEGGINGS_G   = createItem("black_g_leggings",    getArmorSupplier(ModArmorMaterials.BLACK_GOLD, Type.LEGGINGS));
    public static final RegistryObject<Item> BLACK_BOOTS_G      = createItem("black_g_boots",       getArmorSupplier(ModArmorMaterials.BLACK_GOLD, Type.BOOTS));

    public static final RegistryObject<Item> PINK_HELMET        = createItem("pink_helmet",         getArmorSupplier(ModArmorMaterials.PINK, Type.HELMET));
    public static final RegistryObject<Item> PINK_CHESTPLATE    = createItem("pink_chestplate",     getArmorSupplier(ModArmorMaterials.PINK, Type.CHESTPLATE));
    public static final RegistryObject<Item> PINK_LEGGINGS      = createItem("pink_leggings",       getArmorSupplier(ModArmorMaterials.PINK, Type.LEGGINGS));
    public static final RegistryObject<Item> PINK_BOOTS         = createItem("pink_boots",          getArmorSupplier(ModArmorMaterials.PINK, Type.BOOTS));

    public static final RegistryObject<Item> MITHRIL_HELMET     = createItem("mithril_helmet",      getArmorSupplier(ModArmorMaterials.MITHRIL, Type.HELMET));
    public static final RegistryObject<Item> MITHRIL_CHESTPLATE = createItem("mithril_chestplate",  getArmorSupplier(ModArmorMaterials.MITHRIL, Type.CHESTPLATE));
    public static final RegistryObject<Item> MITHRIL_LEGGINGS   = createItem("mithril_leggings",    getArmorSupplier(ModArmorMaterials.MITHRIL, Type.LEGGINGS));
    public static final RegistryObject<Item> MITHRIL_BOOTS      = createItem("mithril_boots",       getArmorSupplier(ModArmorMaterials.MITHRIL, Type.BOOTS));

    // Smithing Upgrades
    public static final RegistryObject<Item> BLACK_UPGRADE      = createSmithingUpgrade("smithing_template_black_upgrade", SmithingTemplates.SmithingTemplateType.BLACK);
    public static final RegistryObject<Item> BLACK_G_TRIM       = createSmithingUpgrade("smithing_template_black_g_trim", SmithingTemplates.SmithingTemplateType.BLACK_G);
    public static final RegistryObject<Item> BLACK_G_UNTRIM     = createSmithingUpgrade("smithing_template_black_untrim", SmithingTemplates.SmithingTemplateType.BLACK_CLEAN);

    public static final RegistryObject<Item> PINK_UPGRADE       = createSmithingUpgrade("smithing_template_pink_upgrade", SmithingTemplates.SmithingTemplateType.PINK);

    // TODO: Add override for custom strength and speed values
    public static Supplier<Item> getToolSupplier(@Nonnull ModTiers tier, ToolType type)
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

    public static Supplier<Item> getArmorSupplier(@Nonnull ArmorMaterial tier, ArmorItem.Type type)
    {
        return () -> new ArmorItem(tier, type, DefaultProperties());
    }

    public static RegistryObject<Item> createSmithingUpgrade(String name, SmithingTemplates.SmithingTemplateType type)
    {
        return createItem(name, () -> SmithingTemplates.createUpgradeTemplate(type));
    }

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
