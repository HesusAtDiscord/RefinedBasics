// package net.chexxor.funmod.block;

// import javax.annotation.Nullable;

// import net.chexxor.funmod.block.entity.AbstractFunFurnaceBlockEntity;
// import net.minecraft.core.BlockPos;
// import net.minecraft.core.Direction;
// import net.minecraft.server.level.ServerLevel;
// import net.minecraft.world.Containers;
// import net.minecraft.world.InteractionHand;
// import net.minecraft.world.InteractionResult;
// import net.minecraft.world.entity.LivingEntity;
// import net.minecraft.world.entity.player.Player;
// import net.minecraft.world.inventory.AbstractContainerMenu;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.context.BlockPlaceContext;
// import net.minecraft.world.level.Level;
// import net.minecraft.world.level.block.BaseEntityBlock;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.HorizontalDirectionalBlock;
// import net.minecraft.world.level.block.Mirror;
// import net.minecraft.world.level.block.RenderShape;
// import net.minecraft.world.level.block.Rotation;
// import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
// import net.minecraft.world.level.block.entity.BlockEntity;
// import net.minecraft.world.level.block.entity.BlockEntityTicker;
// import net.minecraft.world.level.block.entity.BlockEntityType;
// import net.minecraft.world.level.block.state.BlockBehaviour;
// import net.minecraft.world.level.block.state.BlockState;
// import net.minecraft.world.level.block.state.StateDefinition;
// import net.minecraft.world.level.block.state.properties.BlockStateProperties;
// import net.minecraft.world.level.block.state.properties.BooleanProperty;
// import net.minecraft.world.level.block.state.properties.DirectionProperty;
// import net.minecraft.world.phys.BlockHitResult;
// import net.minecraft.world.phys.Vec3;

// public abstract class AbstractFunFurnaceBlock extends BaseEntityBlock
// {
//    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
//    public static final BooleanProperty LIT = BlockStateProperties.LIT;

//    protected AbstractFunFurnaceBlock(BlockBehaviour.Properties properties)
//    {
//       super(properties);
//       this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
//    }

//    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
//    {
//       if (level.isClientSide)
//       {
//          return InteractionResult.SUCCESS;
//       }
//       else
//       {
//          this.openContainer(level, pos, player);
//          return InteractionResult.CONSUME;
//       }
//    }

//    protected abstract void openContainer(Level level, BlockPos pos, Player player);

//    public BlockState getStateForPlacement(BlockPlaceContext context)
//    {
//       return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
//    }

//    public void setPlacedBy(Level p_48694_, BlockPos p_48695_, BlockState p_48696_, LivingEntity p_48697_, ItemStack p_48698_)
//    {
//       if (p_48698_.hasCustomHoverName())
//       {
//          BlockEntity blockentity = p_48694_.getBlockEntity(p_48695_);
//          if (blockentity instanceof AbstractFunFurnaceBlockEntity)
//          {
//             ((AbstractFunFurnaceBlockEntity)blockentity).setCustomName(p_48698_.getHoverName());
//          }
//       }
//    }

//    public void onRemove(BlockState p_48713_, Level p_48714_, BlockPos p_48715_, BlockState p_48716_, boolean p_48717_) {
//       if (!p_48713_.is(p_48716_.getBlock())) {
//          BlockEntity blockentity = p_48714_.getBlockEntity(p_48715_);
//          if (blockentity instanceof AbstractFunFurnaceBlockEntity) {
//             if (p_48714_ instanceof ServerLevel) {
//                Containers.dropContents(p_48714_, p_48715_, (AbstractFunFurnaceBlockEntity)blockentity);
//                ((AbstractFunFurnaceBlockEntity)blockentity).getRecipesToAwardAndPopExperience((ServerLevel)p_48714_, Vec3.atCenterOf(p_48715_));
//             }

//             p_48714_.updateNeighbourForOutputSignal(p_48715_, this);
//          }

//          super.onRemove(p_48713_, p_48714_, p_48715_, p_48716_, p_48717_);
//       }
//    }

//    public boolean hasAnalogOutputSignal(BlockState p_48700_) {
//       return true;
//    }

//    public int getAnalogOutputSignal(BlockState p_48702_, Level p_48703_, BlockPos p_48704_)
//    {
//       return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_48703_.getBlockEntity(p_48704_));
//    }

//    public RenderShape getRenderShape(BlockState p_48727_)
//    {
//       return RenderShape.MODEL;
//    }

//    public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
//       return p_48722_.setValue(FACING, p_48723_.rotate(p_48722_.getValue(FACING)));
//    }

//    public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
//       return p_48719_.rotate(p_48720_.getRotation(p_48719_.getValue(FACING)));
//    }

//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
//       p_48725_.add(FACING, LIT);
//    }

//    @Nullable
//    protected static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level level, BlockEntityType<T> entityType, BlockEntityType<? extends AbstractFunFurnaceBlockEntity> specificType)
//    {
//       return level.isClientSide ? null : createTickerHelper(entityType, specificType, AbstractFunFurnaceBlockEntity::serverTick);
//    }
//  }