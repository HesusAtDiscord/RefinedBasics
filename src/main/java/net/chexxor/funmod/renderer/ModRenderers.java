// package net.chexxor.funmod.renderer;

// import net.chexxor.funmod.FunMod;
// import net.chexxor.funmod.block.entity.ModBlockEntities;
// import net.minecraft.client.renderer.blockentity.ChestRenderer;
// import net.minecraftforge.api.distmarker.Dist;
// import net.minecraftforge.api.distmarker.OnlyIn;
// import net.minecraftforge.client.event.EntityRenderersEvent;
// import net.minecraftforge.eventbus.api.SubscribeEvent;
// import net.minecraftforge.fml.common.Mod;

// public class ModRenderers
// {
//     @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = FunMod.MOD_ID)
//     public static class ClientRendererEvents
//     {
//         @SubscribeEvent
//         @OnlyIn(Dist.CLIENT)
//         public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
//         {
//             FunMod.Log("Registering renderers");
//             event.registerBlockEntityRenderer(ModBlockEntities.PINK_CHEST_ENTITY.get(), ChestRenderer::new);
//         }
//     }
// }
