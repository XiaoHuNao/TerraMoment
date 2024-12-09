package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.google.gson.JsonObject;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMItems;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TMLanguageProvider extends LanguageProvider {
    private final Map<String, String> enData = new TreeMap<>();
    private final Map<String, String> zhData = new TreeMap<>();
    private final PackOutput output;
    private final String locale;

    public TMLanguageProvider(PackOutput output, String locale) {
        super(output, TerraMoment.MODID, locale);
        this.output = output;
        this.locale = locale;
    }
    @Override
    protected void addTranslations() {
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

    private void addMomentTooltip(ResourceKey<Moment> key, Map<MomentState,String> en, Map<MomentState,String> zh){
        Moment moment = TMMoments.MOMENTS.getOrDefault(key, null);
        if (moment != null) {
            moment.tipSettingsContext()
                    .flatMap(TipSettingsContext::texts)
                    .ifPresent(texts ->{
                        texts.forEach(((state, component) -> {
                            add(component.getString(), en.getOrDefault(state, "null"), zh.getOrDefault(state, null));
                        }));
                    });
        }
    }
    private void addItem(Supplier<? extends Item> key, String en, String cn) {
        this.add(key.get().getDescriptionId(), en, cn);
    }

    private void add(String key, String en, String zh) {
        if (locale.equals("en_us") && !enData.containsKey(key)) {
            enData.put(key, en);
        } else if (locale.equals("zh_cn") && !zhData.containsKey(key)) {
            zhData.put(key, zh);
        }
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        addTranslations();
        Path path = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(TerraMoment.MODID).resolve("lang");
        if (locale.equals("en_us") && !enData.isEmpty()) {
            return save(enData, cache, path.resolve("en_us.json"));
        }
        if (locale.equals("zh_cn") && !zhData.isEmpty()) {
            return save(zhData, cache, path.resolve("zh_cn.json"));
        }
        return CompletableFuture.allOf();
    }

    private CompletableFuture<?> save(Map<String, String> data, CachedOutput cache, Path target) {
        JsonObject json = new JsonObject();
        data.forEach(json::addProperty);
        return DataProvider.saveStable(cache, json, target);
    }

}