package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentData;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmount;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.Area;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class TorchGodMoment extends Moment<TorchGodMoment> {
    public static final MapCodec<TorchGodMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            IBarRenderType.CODEC.optionalFieldOf("bar_render_type").forGetter(Moment::barRenderType),
            Area.CODEC.optionalFieldOf("area").forGetter(Moment::area),
            MomentData.CODEC.optionalFieldOf("moment_data_context").forGetter(Moment::momentData),
            TipSettings.CODEC.optionalFieldOf("tips").forGetter(Moment::tipSettings),
            ClientSettings.CODEC.optionalFieldOf("clientSettings").forGetter(Moment::clientSettings),
            Codec.INT.fieldOf("mixTorchCount").forGetter(TorchGodMoment::mixTorchCount),
            Codec.INT.fieldOf("totalAttacksNeeded").forGetter(TorchGodMoment::totalAttacksNeeded),
            RandomAmount.CODEC.fieldOf("multiAttackBarrage").forGetter(TorchGodMoment::multiAttackBarrage)
    ).apply(instance, TorchGodMoment::new));


    private final int mixTorchCount;
    private final int totalAttacksNeeded;
    private final RandomAmount multiAttackBarrage;

    public TorchGodMoment(int mixTorchCount,int totalAttacksNeeded, RandomAmount multiAttackBarrage) {
        super();
        this.mixTorchCount = mixTorchCount;
        this.totalAttacksNeeded = totalAttacksNeeded;
        this.multiAttackBarrage = multiAttackBarrage;
    }

    public TorchGodMoment(Optional<IBarRenderType> renderType, Optional<Area> area, Optional<MomentData> momentDataContext, Optional<TipSettings> tipSettingsContext, Optional<ClientSettings> clientSettings, int mixTorchCount, int totalAttacksNeeded, RandomAmount multiAttackBarrage) {
        super(renderType, area, momentDataContext, tipSettingsContext, clientSettings);
        this.mixTorchCount = mixTorchCount;
        this.totalAttacksNeeded = totalAttacksNeeded;
        this.multiAttackBarrage = multiAttackBarrage;
    }

    @Override
    public MapCodec<? extends Moment<TorchGodMoment>> codec() {
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
    public MomentInstance<?> newMomentInstance(Level level, ResourceKey<Moment<?>> momentResourceKey) {
        return new TorchGodInstance(level,momentResourceKey);
    }
}
