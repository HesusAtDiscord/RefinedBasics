package net.chexxor.funmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PinkChestBlockEntity extends ChestBlockEntity {
    public PinkChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PINK_CHEST_ENTITY.get(), pos, state);
    }

    public PinkChestBlockEntity(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}