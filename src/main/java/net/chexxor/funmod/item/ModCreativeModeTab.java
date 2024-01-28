package net.chexxor.funmod.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.chexxor.funmod.FunMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModeTab
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FunMod.MOD_ID);
    public static final List<Supplier<? extends ItemLike>> TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> FUNMOD_TAB = TABS.register(
    FunMod.MOD_ID,
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + FunMod.MOD_ID))
            .icon(() -> new ItemStack(ModItems.BLACK_INGOT.get()))
            .displayItems((displayParams, output) -> TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
            .build());

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> item)
    {
        TAB_ITEMS.add(item);
        return item;
    }

    public static void register(IEventBus eventBus)
    {
        TABS.register(eventBus);
    }
}
