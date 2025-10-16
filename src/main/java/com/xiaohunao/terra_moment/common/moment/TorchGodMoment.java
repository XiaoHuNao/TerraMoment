package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmount;
import com.xiaohunao.heaven_destiny_moment.common.moment.IMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentBuilder;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import net.minecraft.world.level.Level;

import java.util.Optional;

public final class TorchGodMoment extends DefaultMoment {
    public static final MapCodec<TorchGodMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DefaultMoment.CODEC.forGetter(raidMoment -> raidMoment),
            Codec.INT.fieldOf("mixTorchCount").forGetter(TorchGodMoment::mixTorchCount),
            Codec.INT.fieldOf("totalAttacksNeeded").forGetter(TorchGodMoment::totalAttacksNeeded),
            RandomAmount.CODEC.fieldOf("multiAttackBarrage").forGetter(TorchGodMoment::multiAttackBarrage)
    ).apply(instance, TorchGodMoment::new));

    private final int mixTorchCount;
    private final int totalAttacksNeeded;
    private final RandomAmount multiAttackBarrage;

    public TorchGodMoment(DefaultMoment moment, int mixTorchCount, int totalAttacksNeeded, RandomAmount multiAttackBarrage) {
        super(moment.barRenderType, moment.momentData, moment.tipSettings, moment.clientSettings, moment.trackers);
        this.mixTorchCount = mixTorchCount;
        this.totalAttacksNeeded = totalAttacksNeeded;
        this.multiAttackBarrage = multiAttackBarrage;
    }


    @Override
    public MapCodec<TorchGodMoment> codec() {
        return CODEC;
    }

    public RandomAmount multiAttackBarrage() {
        return multiAttackBarrage;
    }

    public int mixTorchCount() {
        return mixTorchCount;
    }

    public int totalAttacksNeeded() {
        return totalAttacksNeeded;
    }

    @Override
    public MomentInstance newMomentInstance(Level level, IMoment momentResourceKey) {
        return new TorchGodInstance(level, momentResourceKey);
    }

    public static class Builder extends MomentBuilder<TorchGodMoment> {
        protected int mixTorchCount;
        protected int totalAttacksNeeded;
        protected RandomAmount multiAttackBarrage;

        public Builder mixTorchCount(int mixTorchCount) {
            this.mixTorchCount = mixTorchCount;
            return this;
        }

        public Builder totalAttacksNeeded(int totalAttacksNeeded) {
            this.totalAttacksNeeded = totalAttacksNeeded;
            return this;
        }

        public Builder multiAttackBarrage(RandomAmount multiAttackBarrage) {
            this.multiAttackBarrage = multiAttackBarrage;
            return this;
        }

        @Override
        public TorchGodMoment build() {
            return new TorchGodMoment(
                    new DefaultMoment(
                            Optional.ofNullable(barRenderType),
                            Optional.ofNullable(momentData),
                            Optional.ofNullable(tipSettings),
                            Optional.ofNullable(clientSettings),
                            Optional.ofNullable(trackers)
                    ),
                    mixTorchCount,
                    totalAttacksNeeded,
                    multiAttackBarrage
            );
        }
    }
}
