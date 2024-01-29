package net.chexxor.funmod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.chexxor.funmod.block.ModBlocks;
import net.chexxor.funmod.item.ModCreativeModeTab;
import net.chexxor.funmod.block.entity.ModBlockEntities;
import net.chexxor.funmod.item.ModItems;
import net.chexxor.funmod.renderer.PinkChestRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FunMod.MOD_ID)
public class FunMod
{
    public static final String MOD_ID = "funmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FunMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModCreativeModeTab.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        Log("FunMod loaded");
    }

    public static void Log(String message)
    {
        LOGGER.info("Chexxor: " + message);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}

        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            FunMod.Log("Registering renderers");
            event.registerBlockEntityRenderer(ModBlockEntities.PINK_CHEST_ENTITY.get(), PinkChestRenderer::new);
        }
    }
}
