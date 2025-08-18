package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmount;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import net.minecraft.world.level.Level;

public class TorchGodMoment extends DefaultMoment {
    public static final MapCodec<TorchGodMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DefaultMoment.CODEC.forGetter(moment -> moment),
            Codec.INT.fieldOf("mixTorchCount").forGetter(TorchGodMoment::mixTorchCount),
            Codec.INT.fieldOf("totalAttacksNeeded").forGetter(TorchGodMoment::totalAttacksNeeded),
            RandomAmount.CODEC.fieldOf("multiAttackBarrage").forGetter(TorchGodMoment::multiAttackBarrage)
    ).apply(instance, TorchGodMoment::new));

    private final int mixTorchCount;
    private final int totalAttacksNeeded;
    private final RandomAmount multiAttackBarrage;

    public TorchGodMoment(int mixTorchCount, int totalAttacksNeeded, RandomAmount multiAttackBarrage) {
        super();
        this.mixTorchCount = mixTorchCount;
        this.totalAttacksNeeded = totalAttacksNeeded;
        this.multiAttackBarrage = multiAttackBarrage;
    }

    public TorchGodMoment(DefaultMoment moment, int mixTorchCount, int totalAttacksNeeded, RandomAmount multiAttackBarrage) {
        super(moment.barRenderType, moment.area, moment.momentData, moment.tipSettings, moment.clientSettings, moment.trackers);
        this.mixTorchCount = mixTorchCount;
        this.totalAttacksNeeded = totalAttacksNeeded;
        this.multiAttackBarrage = multiAttackBarrage;
    }

    @Override
    public MapCodec<? extends DefaultMoment> codec() {
        return TMContextRegister.TORCH_GOD.get();
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
    public MomentInstance newMomentInstance(Level level, Moment momentResourceKey) {
        return new TorchGodInstance(level, momentResourceKey);
    }
}
