package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentData;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettings;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.Area;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class SlimeRainMoment extends Moment<SlimeRainMoment> {
    public static final MapCodec<SlimeRainMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            IBarRenderType.CODEC.optionalFieldOf("bar_render_type").forGetter(Moment::barRenderType),
            Area.CODEC.optionalFieldOf("area").forGetter(Moment::area),
            MomentData.CODEC.optionalFieldOf("moment_data_context").forGetter(Moment::momentData),
            TipSettings.CODEC.optionalFieldOf("tips").forGetter(Moment::tipSettings),
            ClientSettings.CODEC.optionalFieldOf("clientSettingsContext").forGetter(Moment::clientSettings),
            Codec.INT.fieldOf("requiredKills").forGetter(SlimeRainMoment::requiredKills)
    ).apply(instance, SlimeRainMoment::new));


    private final int requiredKills;

    public SlimeRainMoment(int requiredKills) {
        super();
        this.requiredKills = requiredKills;
    }

    public SlimeRainMoment(Optional<IBarRenderType> renderType, Optional<Area> area, Optional<MomentData> momentDataContext, Optional<TipSettings> tipSettingsContext, Optional<ClientSettings> clientSettingsContext, int requiredKills) {
        super(renderType, area, momentDataContext, tipSettingsContext, clientSettingsContext);
        this.requiredKills = requiredKills;
    }

    @Override
    public MapCodec<? extends SlimeRainMoment> codec() {
        return TMContextRegister.SLIME_RAIN.get();
    }

    public int requiredKills() {
        return requiredKills;
    }

    @Override
    public MomentInstance<SlimeRainMoment> newMomentInstance(Level level, ResourceKey<Moment<?>> momentResourceKey) {
        return new SlimeRainInstance(level,momentResourceKey);
    }
}
