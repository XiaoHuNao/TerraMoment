package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.xiaohunao.heaven_destiny_moment.common.data.gen.provider.MomentLanguageProvider;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMItems;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TMLanguageProvider extends MomentLanguageProvider {
    public TMLanguageProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String locale) {
        super(output, lookupProvider, TerraMoment.MODID, locale);
    }


    @Override
    protected void addTranslations() {
        this.addTranslation("creative_tab." + TerraMoment.MODID + ".tab", "TerraMoment", "泰拉时刻");

        addItem(TMItems.SLIME_RAIN,"SlimeRain","史莱姆雨");
        addItem(TMItems.BLOOD_TEAR,"BloodTear","血泪");

        addMomentTooltip(TMMoments.BLOOD_MOON,
                Map.of(MomentState.READY,"The Blood Moon is rising..."),
                Map.of(MomentState.READY,"血月正在升起……")
        );
        addMomentTooltip(TMMoments.SLIME_RAIN,
                Map.of(MomentState.READY,"Slime is falling from the sky!"),
                Map.of(MomentState.READY,"史莱姆从天而降!")
        );
    }

    private void addItem(Supplier<? extends Item> key, String en, String cn) {
        this.addTranslation(key.get().getDescriptionId(), en, cn);
    }
}