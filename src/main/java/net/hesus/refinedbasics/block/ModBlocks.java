package net.hesus.refinedbasics.block;

import net.hesus.refinedbasics.RefinedBasics;
import net.hesus.refinedbasics.block.custom.chexxor.TestFurnaceBlock;
import net.hesus.refinedbasics.item.ModCreativeModeTab;
import net.hesus.refinedbasics.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RefinedBasics.MOD_ID);

    public static final RegistryObject<Block> MITHRIL_ORE = registerBlock("mithril_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MITHRIL_BLOCK = registerBlock("mithril_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1).requiresCorrectToolForDrops()));

                    public static final RegistryObject<Block> IRON_FURNACE = registerBlock("iron_furnace",
            () -> new TestFurnaceBlock(BlockBehaviour.Properties.of()
                    .strength(3), 2f));


    public static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, ModCreativeModeTab.REFINEDBASICS_TAB);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus){
       BLOCKS.register(eventBus);
    }
}
