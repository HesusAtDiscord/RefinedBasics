package net.chexxor.funmod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Calendar;

import net.chexxor.funmod.FunMod;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PinkChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T>{
    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;
    private final ModelPart doubleLeftLid;
    private final ModelPart doubleLeftBottom;
    private final ModelPart doubleLeftLock;
    private final ModelPart doubleRightLid;
    private final ModelPart doubleRightBottom;
    private final ModelPart doubleRightLock;
    private boolean xmasTextures;

    public PinkChestRenderer(BlockEntityRendererProvider.Context p_173607_) {
        FunMod.Log("Creating chest renderer");
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
            this.xmasTextures = true;
        }

        ModelPart modelpart = p_173607_.bakeLayer(ModelLayers.CHEST);
        this.bottom = modelpart.getChild(BOTTOM);
        this.lid = modelpart.getChild(LID);
        this.lock = modelpart.getChild(LOCK);
        ModelPart modelpart1 = p_173607_.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
        this.doubleLeftBottom = modelpart1.getChild(BOTTOM);
        this.doubleLeftLid = modelpart1.getChild(LID);
        this.doubleLeftLock = modelpart1.getChild(LOCK);
        ModelPart modelpart2 = p_173607_.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
        this.doubleRightBottom = modelpart2.getChild(BOTTOM);
        this.doubleRightLid = modelpart2.getChild(LID);
        this.doubleRightLock = modelpart2.getChild(LOCK);
    }

    public void render(T tileEntity, float p_225616_2_, PoseStack ms, MultiBufferSource buffer, int p_225616_5_, int p_225616_6_)
    {
        FunMod.Log("Rendering chest");
        Level world = tileEntity.getLevel();
        boolean flag = world != null;
        BlockState blockstate = flag ? tileEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockstate.getBlock();
        if (block instanceof AbstractChestBlock) {
            FunMod.Log("Rendering: Block is an AbstractChestBlock");
            AbstractChestBlock<?> abstractchestblock = (AbstractChestBlock) block;
            boolean flag1 = chesttype != ChestType.SINGLE;
            ms.pushPose();
            float f = blockstate.getValue(ChestBlock.FACING).toYRot();
            ms.translate(0.5F, 0.5F, 0.5F);
            ms.mulPose(Axis.YP.rotationDegrees(-f));
            ms.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> icallbackwrapper;
            if (flag) {
                icallbackwrapper = abstractchestblock.combine(blockstate, world, tileEntity.getBlockPos(), true);
            } else {
                icallbackwrapper = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = icallbackwrapper.apply(ChestBlock.opennessCombiner(tileEntity)).get(p_225616_2_);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = icallbackwrapper.apply(new BrightnessCombiner<>()).applyAsInt(p_225616_5_);
            Material rendermaterial = this.getMaterial(tileEntity, chesttype);
            VertexConsumer ivertexbuilder = rendermaterial.buffer(buffer, RenderType::entityCutout);
            if (flag1) {
                if (chesttype == ChestType.RIGHT) {
                    this.render(ms, ivertexbuilder, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, p_225616_6_);
                } else {
                    this.render(ms, ivertexbuilder, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, p_225616_6_);
                }
            } else {
                this.render(ms, ivertexbuilder, this.lid, this.lock, this.bottom, f1, i, p_225616_6_);
            }

            ms.popPose();
        }
    }

    private void render(PoseStack p_112370_, VertexConsumer p_112371_, ModelPart p_112372_, ModelPart p_112373_, ModelPart p_112374_, float p_112375_, int p_112376_, int p_112377_) {
        p_112372_.xRot = -(p_112375_ * ((float)Math.PI / 2F));
        p_112373_.xRot = p_112372_.xRot;
        p_112372_.render(p_112370_, p_112371_, p_112376_, p_112377_);
        p_112373_.render(p_112370_, p_112371_, p_112376_, p_112377_);
        p_112374_.render(p_112370_, p_112371_, p_112376_, p_112377_);
    }

    protected Material getMaterial(T tileEntity, ChestType chestType) {
        FunMod.Log("Getting chest material for " + chestType + " chest");
        switch (chestType) {
            case LEFT:
                return new Material(Sheets.CHEST_SHEET, new ResourceLocation(FunMod.MOD_ID, "model/chest/pink_chest_left"));
            case RIGHT:
                return new Material(Sheets.CHEST_SHEET, new ResourceLocation(FunMod.MOD_ID, "model/chest/pink_chest_right"));
            case SINGLE:
            default:
                return new Material(Sheets.CHEST_SHEET, new ResourceLocation(FunMod.MOD_ID, "model/chest/pink_chest"));
        }
    }
}
