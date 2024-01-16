package net.chexxor.funmod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;

public class FunFurnaceBlock extends FurnaceBlock {
    public FunFurnaceBlock() {
        super(Block.Properties.copy(Blocks.FURNACE));
    }
}