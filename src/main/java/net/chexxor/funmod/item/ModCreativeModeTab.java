package net.chexxor.funmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab FUNMOD_TAB = new CreativeModeTab("funmod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MITHRIL_INGOT.get());
        }
    };
}
