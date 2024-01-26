package net.chexxor.funmod.item;

import java.util.function.Supplier;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModTiers implements Tier
{
    BLACK(2, 300, 6.0F, 2.5F, 5,
        () -> { return Ingredient.of(ModItems.BLACK_INGOT.get());
    }),

    PINK(2, 300, 6.0F, 2.5F, 5,
        () -> { return Ingredient.of(ModItems.PINK_DIAMOND.get());
    }),

    MITHRIL(3, 2000, 10.0F, 3.0F, 30,
        () -> { return Ingredient.of(ModItems.MITHRIL_INGOT.get());
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> supplier)
    {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(supplier);
    }

    public int getUses() {
        return uses;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAttackDamageBonus() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
