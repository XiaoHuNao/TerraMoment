package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.network.BloodMoonTimeSyncPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TerraMoment.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TMNetworkRegister {
    public static final String VERSION = "0.0.1";

    @SubscribeEvent
    public static void registerPayload(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(VERSION);
        registrar.playToClient(BloodMoonTimeSyncPayload.TYPE, BloodMoonTimeSyncPayload.STREAM_CODEC, BloodMoonTimeSyncPayload::handle);
    }
}