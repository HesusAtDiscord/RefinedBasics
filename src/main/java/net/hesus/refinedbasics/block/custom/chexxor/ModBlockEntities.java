package net.hesus.refinedbasics.block.custom.chexxor;

import net.hesus.refinedbasics.RefinedBasics;
import net.hesus.refinedbasics.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RefinedBasics.MOD_ID);

    public static final RegistryObject<BlockEntityType<TestFurnaceBlockEntity>> IRON_FURNACE_ENTITY =
        BLOCK_ENTITIES.register("iron_furnace_entity", () -> BlockEntityType.Builder.of(TestFurnaceBlockEntity::new, ModBlocks.IRON_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}