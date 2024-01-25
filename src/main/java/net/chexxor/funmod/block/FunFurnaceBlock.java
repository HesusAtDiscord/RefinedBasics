package net.chexxor.funmod.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.chexxor.funmod.FunMod;
import net.chexxor.funmod.block.entity.FunFurnaceBlockEntity;
import net.chexxor.funmod.block.entity.ModBlockEntities;
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

public class FunFurnaceBlock extends AbstractFurnaceBlock
{
    public static final MapCodec<FunFurnaceBlock> CODEC = simpleCodec(FunFurnaceBlock::new);

    private static final double CRACKLE_CHANCE_PER_TICK = 0.1D;

    private float speedModifier = 1f;

    public FunFurnaceBlock(Properties properties)
    {
        this(properties, 1f);
    }

    public FunFurnaceBlock(Properties properties, float speedModifier)
    {
        super(properties);
        this.speedModifier = speedModifier;
    }

    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state)
    {
        FunFurnaceBlockEntity entity = new FunFurnaceBlockEntity(pos, state);
        entity.setSpeedModifier(speedModifier);
        return entity;
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec()
    {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof FunFurnaceBlockEntity)
        {
            player.openMenu((MenuProvider)blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createFunFurnaceTicker(level, type, ModBlockEntities.FUN_FURNACE_ENTITY.get());
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
    {
        if (state.getValue(LIT))
        {
            double x = (double)pos.getX() + 0.5D;
            double y = (double)pos.getY();
            double z = (double)pos.getZ() + 0.5D;

            // Chance of playing the crackle sound
            if (random.nextDouble() < CRACKLE_CHANCE_PER_TICK)
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
    protected static <T extends BlockEntity> BlockEntityTicker<T> createFunFurnaceTicker(Level level, BlockEntityType<T> entityType, BlockEntityType<? extends FunFurnaceBlockEntity> specificType)
    {
        return level.isClientSide ? null : createTickerHelper(entityType, specificType, FunFurnaceBlockEntity::serverTick);
    }
}