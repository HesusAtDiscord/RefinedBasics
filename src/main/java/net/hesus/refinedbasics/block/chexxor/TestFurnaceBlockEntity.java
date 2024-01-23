package net.hesus.refinedbasics.block.chexxor;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

// Example of a minimal implementation for a custom speed furnace
public class TestFurnaceBlockEntity extends CopyFromMinecraftFurnaceEntity {
    private static final float MIN_SPEED_MODIFIER = 0.1f;
    private static final float DEFAULT_SPEED_MODIFIER = 2f;

    private float speedModifier = 1f;

    public TestFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_FURNACE_ENTITY.get(), pos, state, RecipeType.SMELTING);
        setSpeedModifier(DEFAULT_SPEED_MODIFIER);
    }

    public void setSpeedModifier(float speedModifier)
    {
        this.speedModifier = Math.max(speedModifier, MIN_SPEED_MODIFIER);
    }

    @Override
    protected int getTotalCookTime()
    {
        return (int)(super.getTotalCookTime() / speedModifier);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.refinedbasics.test_furnace");
    }
}
