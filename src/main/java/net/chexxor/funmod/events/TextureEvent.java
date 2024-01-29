// package net.chexxor.funmod.events;

// import net.chexxor.funmod.FunMod;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraftforge.api.distmarker.Dist;
// import net.minecraftforge.api.distmarker.OnlyIn;
// import net.minecraftforge.client.event.TextureStitchEvent;
// import net.minecraftforge.eventbus.api.SubscribeEvent;
// import net.minecraftforge.fml.common.Mod;

// @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FunMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
// @OnlyIn(Dist.CLIENT)
// public class TextureEvent {

//     @SubscribeEvent
//     public static void textEvent(TextureStitchEvent.Pre event) {
//         if (event.getAtlas().location().toString().equals("minecraft:textures/atlas/chest.png")) {
//             ResourceLocation resNormal = new ResourceLocation(FunMod.MOD_ID, "entity/pink_chest");
//             ResourceLocation resLeft = new ResourceLocation(FunMod.MOD_ID, "entity/pink_chest_left");
//             ResourceLocation resRight = new ResourceLocation(FunMod.MOD_ID, "entity/pink_chest_right");
//             event.addSprite(resNormal);
//             event.addSprite(resLeft);
//             event.addSprite(resRight);
//         }
//     }
// }