package com.xiaohunao.terra_moment.common.data.gen;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.data.gen.provider.TMLanguageProvider;
import com.xiaohunao.terra_moment.common.data.gen.provider.TMRegistryProvider;
import com.xiaohunao.terra_moment.common.data.gen.provider.TMItemModelProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TerraMoment.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        boolean server = event.includeServer();
        boolean client = event.includeClient();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(client, new TMItemModelProvider(output, existingFileHelper));
        generator.addProvider(server, new TMRegistryProvider(output, lookupProvider));
        generator.addProvider(server, new TMLanguageProvider(output, "en_us"));
        generator.addProvider(server, new TMLanguageProvider(output, "zh_cn"));
    }

}