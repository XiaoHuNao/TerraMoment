package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.network.TimeSyncPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TerraMoment.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TMNetworkRegister {
    public static final String VERSION = "0.0.1";

    @SubscribeEvent
    public static void registerPayload(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(VERSION);
        registrar.playBidirectional(TimeSyncPayload.TYPE, TimeSyncPayload.STREAM_CODEC, new DirectionalPayloadHandler<>(TimeSyncPayload::clientHandle,TimeSyncPayload::serverHandle));
    }
}