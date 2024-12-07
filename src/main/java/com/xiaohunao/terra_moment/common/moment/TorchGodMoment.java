package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentDataContext;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmountContext;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.Area;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class TorchGodMoment extends Moment {
    public static final MapCodec<TorchGodMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("mixTorchCount").forGetter(TorchGodMoment::mixTorchCount),
            Codec.INT.fieldOf("totalAttacksNeeded").forGetter(TorchGodMoment::totalAttacksNeeded),
            RandomAmountContext.CODEC.fieldOf("multiAttackBarrage").forGetter(TorchGodMoment::multiAttackBarrage),
            HDMRegistries.BAR_RENDER_TYPE.byNameCodec().optionalFieldOf("bar_render_type").forGetter(Moment::barRenderType),
            Area.CODEC.optionalFieldOf("area").forGetter(Moment::area),
            MomentDataContext.CODEC.optionalFieldOf("moment_data_context").forGetter(Moment::momentDataContext),
            TipSettingsContext.CODEC.optionalFieldOf("tips").forGetter(Moment::tipSettingsContext),
            ClientSettingsContext.CODEC.optionalFieldOf("clientSettingsContext").forGetter(Moment::clientSettingsContext)
    ).apply(instance, (mixTorchCount,totalAttacksNeeded,multiAttackBarrage,renderType, area,
                       momentDataContext, tipSettingsContext, clientSettingsContext)
            -> new TorchGodMoment(mixTorchCount,totalAttacksNeeded,multiAttackBarrage))
    );


    private final int mixTorchCount;
    private final int totalAttacksNeeded;
    private final RandomAmountContext multiAttackBarrage;

    public TorchGodMoment(int mixTorchCount,int totalAttacksNeeded, RandomAmountContext multiAttackBarrage) {
        this.mixTorchCount = mixTorchCount;
        this.totalAttacksNeeded = totalAttacksNeeded;
        this.multiAttackBarrage = multiAttackBarrage;
    }


    @Override
    public MomentInstance<TorchGodMoment> newMomentInstance(Level level, ResourceKey<Moment> momentResourceKey) {
        return new TorchGodInstance(level,momentResourceKey);
    }

    @Override
    public MapCodec<? extends Moment> codec() {
        return TMContextRegister.TORCH_GOD.get();
    }

    public RandomAmountContext multiAttackBarrage() {
        return multiAttackBarrage;
    }

    public int mixTorchCount() {
        return mixTorchCount;
    }

    public int totalAttacksNeeded() {
        return totalAttacksNeeded;
    }
}
