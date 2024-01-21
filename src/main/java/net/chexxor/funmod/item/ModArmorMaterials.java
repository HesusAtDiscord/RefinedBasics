package net.chexxor.funmod.item;

import java.util.function.Supplier;

import net.chexxor.funmod.FunMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial
{
    BLACK("black", 15, new int[]{2, 5, 6, 2}, 9, 0.0F, 0.0F,
        () -> { return Ingredient.of(ModItems.BLACK_INGOT.get());
    }),

    MITHRIL("mithril", 33, new int[]{3, 6, 8, 3}, 10, 2.0F, 0.0F,
        () -> { return Ingredient.of(ModItems.MITHRIL_INGOT.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durability;
    private final int[] damageReduction;
    private final int enchantmentValue;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterials(String name, int durability, int[] damageReduction, int enchantmentValue, float toughness, float knockbackResistance, Supplier<Ingredient> supplier)
    {
        this.name = name;
        this.durability = durability;
        this.damageReduction = damageReduction;
        this.enchantmentValue = enchantmentValue;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(supplier);
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * durability;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return damageReduction[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_DIAMOND;
    }

    public String getName() {
        return FunMod.MOD_ID + ":" + name;
    }

    public float getToughness() {
        return toughness;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}