package com.xiaohunao.terra_moment;

import com.mojang.logging.LogUtils;
import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.terra_moment.common.init.ModContextRegister;
import com.xiaohunao.terra_moment.common.init.ModItems;
import com.xiaohunao.terra_moment.common.init.ModMomentTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod(TerraMoment.MODID)
public class TerraMoment {
    public static final String MODID = "terra_moment";
    public static final Logger LOGGER = LogUtils.getLogger();
    public TerraMoment(IEventBus modEventBus, ModContainer modContainer) {
//        NeoForge.EVENT_BUS.register(this);
        ModItems.ITEMS.register(modEventBus);
        ModMomentTypes.MOMENT_TYPE.register(modEventBus);
        ModContextRegister.MOMENT_CODEC.register(modEventBus);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return ResourceKey.create(registryKey, TerraMoment.asResource(path));
    }
    public static <T> ResourceKey<Registry<T>> asResourceKey(String path) {
        return ResourceKey.createRegistryKey(HeavenDestinyMoment.asResource(path));
    }
    public static String asDescriptionId(String path) {
        return MODID + "." + path;
    }



    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
