package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.MapCodec;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentData;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettings;
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
    public static final MapCodec<SlimeRainMoment> CODEC = simpleCodec(SlimeRainMoment::new);

    public SlimeRainMoment() {
        super();
    }

    public SlimeRainMoment(Optional<IBarRenderType> renderType, Optional<Area> area, Optional<MomentData> momentDataContext, Optional<TipSettings> tipSettingsContext, Optional<ClientSettings> clientSettings, Optional<List<ITracker>> trackers) {
        super(renderType, area, momentDataContext, tipSettingsContext, clientSettings, trackers);
    }

    @Override
    public MapCodec<? extends SlimeRainMoment> codec() {
        return TMContextRegister.SLIME_RAIN.get();
    }


    @Override
    public MomentInstance newMomentInstance(Level level, Moment momentResourceKey) {
        return new SlimeRainInstance(level, momentResourceKey);
    }
}
