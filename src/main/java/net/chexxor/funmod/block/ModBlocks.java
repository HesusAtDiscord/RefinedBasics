package net.chexxor.funmod.block;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.chexxor.funmod.FunMod;
import net.chexxor.funmod.block.entity.ModBlockEntities;
import net.chexxor.funmod.item.ModCreativeModeTab;
import net.chexxor.funmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FunMod.MOD_ID);

    public static final RegistryObject<Block> MITHRIL_ORE =
        registerBlock("mithril_ore", () -> new Block(metalProperties(1.5f)));

    public static final RegistryObject<Block> FUN_FURNACE =
        registerBlock("fun_furnace", () -> new FunFurnaceBlock(furnaceProperties(3.5f), 20f));

    @Nonnull
    private static Properties furnaceProperties(float strength)
    {
        return Properties.ofFullCopy(Blocks.FURNACE).requiresCorrectToolForDrops().strength(strength).lightLevel((state) -> 13);
    }

    @Nonnull
    public static Properties metalProperties(Tiers tier)
    {
        return metalProperties(tier.getLevel());
    }

    @Nonnull
    public static Properties metalProperties(float strength)
    {
        return Properties.of().strength(strength).requiresCorrectToolForDrops();
    }

    // Pink chest
    public static final RegistryObject<Block> PINK_CHEST =
        // registerBlock("pink_chest", () -> new ChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD), () -> BlockEntityType.CHEST));
        registerBlock("pink_chest", () -> new ChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD), () -> ModBlockEntities.PINK_CHEST_ENTITY.get()));

    public static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        FunMod.Log("Registering BLOCK: " + name);
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        RegistryObject<Item> item = registerBlockItem(name, toReturn);
        ModCreativeModeTab.addToTab(item);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(
        String name,
        RegistryObject<T> block)
    {
        FunMod.Log("Registering BLOCKITEM: " + name);
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
       BLOCKS.register(eventBus);
    }
}