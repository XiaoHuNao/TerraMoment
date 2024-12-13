package com.xiaohunao.terra_moment.common.network;


import com.xiaohunao.terra_moment.TerraMoment;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record BloodMoonTimeSyncPayload(long time) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BloodMoonTimeSyncPayload> TYPE = new CustomPacketPayload.Type<>(TerraMoment.asResource("blood_moon_time_sync"));
    public static final StreamCodec<ByteBuf, BloodMoonTimeSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG,BloodMoonTimeSyncPayload::time,
            BloodMoonTimeSyncPayload::new
    );

    @Override @NotNull
    public CustomPacketPayload. Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player().isLocalPlayer()) {
                Level level = context.player().level();
                ((ClientLevel)level).setDayTime(time);
            }
        }).exceptionally(e -> {
            context.disconnect(Component.translatable("neoforge.network.invalid_flow", e.getMessage()));
            return null;
        });
    }
}
