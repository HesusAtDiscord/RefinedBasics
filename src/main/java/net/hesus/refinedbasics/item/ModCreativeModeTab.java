package net.hesus.refinedbasics.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.hesus.refinedbasics.RefinedBasics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RefinedBasics.MOD_ID);

    public static final List<Supplier<? extends ItemLike>> TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> REFINEDBASICS_TAB = TABS.register(
    "refinedbasics",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.refinedbasics"))
            .icon(() -> new ItemStack(ModItems.MITHRIL_INGOT.get()))
            .displayItems((displayParams, output) -> TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
            .build());

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> item) {
        TAB_ITEMS.add(item);
        return item;
    }

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
