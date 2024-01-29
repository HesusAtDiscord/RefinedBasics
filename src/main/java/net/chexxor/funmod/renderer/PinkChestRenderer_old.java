package net.chexxor.funmod.renderer;

import net.chexxor.funmod.FunMod;
// import net.chexxor.funmod.block.entity.PinkChestBlockEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;

// public class PinkChestRenderer extends ChestRenderer<PinkChestBlockEntity>
// {
//     public static final Material CHEST_LOCATION = chestMaterial("pink.png");
//     public static final Material CHEST_LOCATION_LEFT = chestMaterial("pink_left.png");
//     public static final Material CHEST_LOCATION_RIGHT = chestMaterial("pink_right.png");

//     public PinkChestRenderer(BlockEntityRendererProvider.Context context) {
//         super(context);
//     }

//     @Override
//     protected Material getMaterial(PinkChestBlockEntity chestBlockEntity, ChestType chestType)
//     {
//         FunMod.Log("Getting chest material for " + chestType + " chest");
//         return chooseMaterial(chestType, CHEST_LOCATION, CHEST_LOCATION_LEFT, CHEST_LOCATION_RIGHT);
//     }

//     private static Material chestMaterial(String variant)
//     {
//         FunMod.Log("Loading chest material: " + variant);
//         ResourceLocation resource = new ResourceLocation(FunMod.MOD_ID, "entity/chest/" + variant);
//         return new Material(Sheets.CHEST_SHEET, resource);
//     }

//     private static Material chooseMaterial(ChestType chestType, Material normal, Material left, Material right) {
//         switch (chestType) {
//            case LEFT:
//                 return left;
//             case RIGHT:
//                 return right;
//             case SINGLE:
//             default:
//                 return normal;
//         }
//      }
// }