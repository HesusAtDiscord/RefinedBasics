package net.hesus.refinedbasics.block.chexxor;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

// Example of a minimal implementation for a custom speed furnace
// Takes in a speed modifier and passes it on to the block entity when it is created
public class TestFurnaceBlock extends AbstractFurnaceBlock {
    private float speedModifier = 1f;

    // Constructor takes in a speed modifier
    public TestFurnaceBlock(Properties properties, float speedModifier)
    {
        super(properties);
        this.speedModifier = speedModifier;
    }

    // Passes the speed modifier to the block entity when it is created
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        TestFurnaceBlockEntity entity = new TestFurnaceBlockEntity(pos, state);
        entity.setSpeedModifier(speedModifier);
        return entity;
    }

    // Copied from Minecraft's Furnace, but adapted for this mods entity
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof TestFurnaceBlockEntity)
        {
            player.openMenu((MenuProvider)blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    // Copied from Minecraft's Furnace, spawns particles when lit
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
    {
        if (state.getValue(LIT))
        {
            double x = (double)pos.getX() + 0.5D;
            double y = (double)pos.getY();
            double z = (double)pos.getZ() + 0.5D;

            // Chance of playing the crackle sound
            if (random.nextDouble() < 0.1D)
            {
                level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            // Spawn particles
            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double forwardOffset = 0.52D;
            double sidewaysOffset = random.nextDouble() * 0.6D - 0.3D;
            double xOffset = axis == Direction.Axis.X ? (double)direction.getStepX() * forwardOffset : sidewaysOffset;
            double yOffset = random.nextDouble() * 6.0D / 16.0D;
            double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * forwardOffset : sidewaysOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTestFurnaceTicker(level, type, ModBlockEntities.IRON_FURNACE_ENTITY.get());
    }

    // Uses the `serverTick` method from this mod's version of AbstractFurnaceBlockEntity (which uses the updated cooking time)
    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTestFurnaceTicker(Level level, BlockEntityType<T> entityType, BlockEntityType<? extends CopyFromMinecraftFurnaceEntity> specificType)
    {
        return level.isClientSide ? null : createTickerHelper(entityType, specificType, CopyFromMinecraftFurnaceEntity::serverTick);
    }
}
