package net.chexxor.funmod.block.entity;

import net.chexxor.funmod.FunMod;
import net.chexxor.funmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FunMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FunFurnaceBlockEntity>> FUN_FURNACE_ENTITY =
            BLOCK_ENTITIES.register(
                "fun_furnace_entity",
                () -> BlockEntityType.Builder.of(FunFurnaceBlockEntity::new, ModBlocks.FUN_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}