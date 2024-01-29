package net.chexxor.funmod.item.smithing;

import java.util.List;

import net.chexxor.funmod.FunMod;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

public class SmithingTemplates
{
    public enum SmithingTemplateType {
        BLACK,
        BLACK_G,
        BLACK_CLEAN,
        PINK,
    };

    // UI Texts
    private static final ChatFormatting TITLE_FORMAT        = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT  = ChatFormatting.BLUE;

    private static final Component UPGRADE_APPLIES_TO               = Component.translatable(Util.makeDescriptionId("item", Resource("smithing_template.diamond.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component UPGRADE_BASE_SLOT_DESCRIPTION    = Component.translatable(Util.makeDescriptionId("item", Resource("smithing_template.diamond.base_slot_description")));

    private static final Component BLACK_UPGRADE                    = Component.translatable(Util.makeDescriptionId("upgrade", Resource("black_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component BLACK_UPGRADE_INGREDIENTS        = Component.translatable(Util.makeDescriptionId("item", Resource("smithing_template.black_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BLACK_UPGRADE_ADD_DESCRIPTION    = Component.translatable(Util.makeDescriptionId("item", Resource("smithing_template.black_upgrade.additions_slot_description")));

    private static final Component PINK_UPGRADE                     = Component.translatable(Util.makeDescriptionId("upgrade", Resource("pink_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component PINK_UPGRADE_INGREDIENTS         = Component.translatable(Util.makeDescriptionId("item", Resource("smithing_template.pink_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component PINK_UPGRADE_ADD_DESCRIPTION     = Component.translatable(Util.makeDescriptionId("item", Resource("smithing_template.pink_upgrade.additions_slot_description")));

    // UI Icons
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");

    private static ResourceLocation Resource(String path) {
        return new ResourceLocation(FunMod.MOD_ID, path);
    }

    public static SmithingTemplateItem createUpgradeTemplate(SmithingTemplateType type)
    {
        switch (type) {
            case BLACK:
                return createBlackUpgradeTemplate();
            // case BLACK_G:
            //     return createBlackGoldUpgradeTemplate();
            // case BLACK_CLEAN:
            //     return createBlackCleanUpgradeTemplate();
            case PINK:
                return createPinkUpgradeTemplate();
            default:
                return createBlackUpgradeTemplate();
        }
    }

    public static SmithingTemplateItem createBlackUpgradeTemplate()
    {
        return new SmithingTemplateItem(
            UPGRADE_APPLIES_TO,
            BLACK_UPGRADE_INGREDIENTS,
            BLACK_UPGRADE,
            UPGRADE_BASE_SLOT_DESCRIPTION,
            BLACK_UPGRADE_ADD_DESCRIPTION,
            createArmorUpgradeIconList(),
            createIngotUpgradeMaterialList());
    }

    public static SmithingTemplateItem createPinkUpgradeTemplate()
    {
        return new SmithingTemplateItem(
            UPGRADE_APPLIES_TO,
            PINK_UPGRADE_INGREDIENTS,
            PINK_UPGRADE,
            UPGRADE_BASE_SLOT_DESCRIPTION,
            PINK_UPGRADE_ADD_DESCRIPTION,
            createArmorUpgradeIconList(),
            createIngotUpgradeMaterialList());
    }

    private static List<ResourceLocation> createArmorUpgradeIconList() {
        return List.of(
            EMPTY_SLOT_HELMET,
            EMPTY_SLOT_CHESTPLATE,
            EMPTY_SLOT_LEGGINGS,
            EMPTY_SLOT_BOOTS
            // EMPTY_SLOT_SWORD,
            // EMPTY_SLOT_PICKAXE,
            // EMPTY_SLOT_AXE,
            // EMPTY_SLOT_HOE,
            // EMPTY_SLOT_SHOVEL
            );
    }

    private static List<ResourceLocation> createIngotUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

}
