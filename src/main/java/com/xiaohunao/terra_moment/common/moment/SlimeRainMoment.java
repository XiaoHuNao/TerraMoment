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
import com.xiaohunao.heaven_destiny_moment.common.tracker.ITracker;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class SlimeRainMoment extends Moment {
    public static final MapCodec<SlimeRainMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            HDMRegistries.BAR_RENDER_TYPE.byNameCodec().optionalFieldOf("bar_render_type").forGetter(Moment::barRenderType),
            Area.CODEC.optionalFieldOf("area").forGetter(Moment::area),
            MomentData.CODEC.optionalFieldOf("moment_data_context").forGetter(Moment::momentData),
            TipSettings.CODEC.optionalFieldOf("tips").forGetter(Moment::tipSettings),
            ClientSettings.CODEC.optionalFieldOf("clientSettings").forGetter(Moment::clientSettings),
            Codec.list(ITracker.CODEC).optionalFieldOf("trackers").forGetter(Moment::trackers)
    ).apply(instance, SlimeRainMoment::new));



    public SlimeRainMoment() {
        super();
    }

    public SlimeRainMoment(Optional<IBarRenderType> renderType, Optional<Area> area, Optional<MomentData> momentDataContext, Optional<TipSettings> tipSettingsContext, Optional<ClientSettings> clientSettings, Optional<List<ITracker>> trackers) {
        super(renderType, area, momentDataContext, tipSettingsContext, clientSettings,trackers);
    }

    @Override
    public MapCodec<? extends SlimeRainMoment> codec() {
        return TMContextRegister.SLIME_RAIN.get();
    }


    @Override
    public MomentInstance newMomentInstance(Level level, Moment momentResourceKey) {
        return new SlimeRainInstance(level,momentResourceKey);
    }
}
