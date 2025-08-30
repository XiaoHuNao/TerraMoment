package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentData;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettings;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.IMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentBuilder;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.Area;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.heaven_destiny_moment.common.tracker.ITracker;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SlimeRainMoment extends DefaultMoment {
    public static final ResourceLocation SLIME_RAIN_VICTORY = ResourceLocation.tryBuild("confluence","slime_rain_victory");

    public static final double CHANCE_NORMAL = 1.0f / 90000.0f;
    public static final double CHANCE_NORMAL_DEFEATED = 1.0f / 180000.0f;
    public static final double CHANCE_NORMAL_NOCONDITION = 1.0f / 450000.0f;
    public static final double CHANCE_NORMAL_NOCONDITION_DEFEATED = 1.0f / 900000.0f;

    public static final double CHANCE_HARD = 1.0f / 135000.0f;
    public static final double CHANCE_HARD_DEFEATED = 1.0f / 270000.0f;
    public static final double CHANCE_HARD_NOCONDITION = 1.0f / 675000.0f;
    public static final double CHANCE_HARD_NOCONDITION_DEFEATED = 1.0f / 1350000.0f;


    public static final MapCodec<SlimeRainMoment> CODEC = Moment.simpleCodec(SlimeRainMoment::new);

    public SlimeRainMoment(Optional<IBarRenderType> iBarRenderType, Optional<MomentData> momentData, Optional<TipSettings> tipSettings, Optional<ClientSettings> clientSettings, Optional<List<ITracker>> iTrackers) {
        super(iBarRenderType, momentData, tipSettings, clientSettings, iTrackers);
    }

    @Override
    public MapCodec<SlimeRainMoment> codec() {
        return CODEC;
    }


    @Override
    public MomentInstance newMomentInstance(Level level, IMoment moment) {
        return new SlimeRainInstance(level, moment);
    }

    public static class Builder extends MomentBuilder<SlimeRainMoment> {
        @Override
        public SlimeRainMoment build() {
            return new SlimeRainMoment(
                    Optional.ofNullable(barRenderType),
                    Optional.ofNullable(momentData),
                    Optional.ofNullable(tipSettings),
                    Optional.ofNullable(clientSettings),
                    Optional.ofNullable(trackers)
            );
        }
    }
}
