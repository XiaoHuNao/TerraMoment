package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.common.moment.IMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentBuilder;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.common.moment.Instance.BloodMoonInstance;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class BloodMoonMoment extends DefaultMoment  {
    public static final MapCodec<BloodMoonMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DefaultMoment.CODEC.forGetter(moment -> moment),
            Codec.BOOL.fieldOf("isCanSleep").forGetter(BloodMoonMoment::isCanSleep)
    ).apply(instance, BloodMoonMoment::new));

    private boolean isCanSleep;

    public BloodMoonMoment(DefaultMoment moment, boolean isCanSleep) {
        super(moment.barRenderType, moment.momentData, moment.tipSettings, moment.clientSettings, moment.trackers);
        this.isCanSleep = isCanSleep;
    }

    public boolean isCanSleep() {
        return isCanSleep;
    }


    @Override
    public MapCodec<BloodMoonMoment> codec() {
        return CODEC;
    }

    @Override
    public MomentInstance newMomentInstance(Level level, IMoment momentResourceKey) {
        return new BloodMoonInstance(level, momentResourceKey);
    }

    public static class Builder extends MomentBuilder<BloodMoonMoment> {
        protected boolean isCanSleep = false;

        @Override
        public BloodMoonMoment build() {
            return new BloodMoonMoment(
                    new DefaultMoment(
                            Optional.ofNullable(barRenderType),
                            Optional.ofNullable(momentData),
                            Optional.ofNullable(tipSettings),
                            Optional.ofNullable(clientSettings),
                            Optional.ofNullable(trackers)
                    ),
                    isCanSleep
            );
        }
    }
}
