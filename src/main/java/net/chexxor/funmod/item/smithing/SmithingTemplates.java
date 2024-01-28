package net.chexxor.funmod.item.smithing;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

public class SmithingTemplates
{
    // UI Texts
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component BLACK_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("black_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component BLACK_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.black_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BLACK_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.black_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BLACK_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.black_upgrade.base_slot_description")));
    private static final Component BLACK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.black_upgrade.additions_slot_description")));

    // UI Icons
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");

    public static SmithingTemplateItem createBlackUpgradeTemplate()
    {
        return new SmithingTemplateItem(
            BLACK_UPGRADE_APPLIES_TO,
            BLACK_UPGRADE_INGREDIENTS,
            BLACK_UPGRADE,
            BLACK_UPGRADE_BASE_SLOT_DESCRIPTION,
            BLACK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
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
