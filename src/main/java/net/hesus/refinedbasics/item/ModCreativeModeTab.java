package net.hesus.refinedbasics.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab REFINEDBASICS_TAB = new CreativeModeTab("refinedbasics") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MITHRIL_INGOT.get());
        }
    };
}
