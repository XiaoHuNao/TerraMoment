package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.terra_moment.TerraMoment;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TMCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TerraMoment.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab",
            () -> CreativeModeTab.builder().icon(() -> TMItems.BLOOD_TEAR.get().getDefaultInstance())
                    .title(Component.translatable("creative_tab." + TerraMoment.MODID + ".tab"))
                    .displayItems((parameters, output) -> {
                        TMItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                    }).build());
}