package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.google.gson.JsonObject;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.ModMoments;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends LanguageProvider {
    private final Map<String, String> enData = new TreeMap<>();
    private final Map<String, String> zhData = new TreeMap<>();
    private final PackOutput output;
    private final String locale;

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, TerraMoment.MODID, locale);
        this.output = output;
        this.locale = locale;
    }
    @Override
    protected void addTranslations() {
        momentTooltip(ModMoments.SLIME_RAIN,
                Map.of(MomentState.READY,"Slime is falling from the sky!"),
                Map.of(MomentState.READY,"史莱姆从天而降!")
        );
    }

    private void momentTooltip(ResourceKey<Moment> key, Map<MomentState,String> en, Map<MomentState,String> zh){
        Moment moment = ModMoments.MOMENTS.getOrDefault(key, null);
        if (moment != null) {
            TipSettingsContext tipSettingsContext = moment.getTipSettingsContext();
            tipSettingsContext.texts().ifPresent(texts -> {
                texts.forEach(((state, component) -> {
                    add(component.getString(), en.getOrDefault(state, "null"), zh.getOrDefault(state, null));
                }));
            });
        }
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