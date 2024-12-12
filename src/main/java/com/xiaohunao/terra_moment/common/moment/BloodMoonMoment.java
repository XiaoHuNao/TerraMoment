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
import com.xiaohunao.heaven_destiny_moment.common.moment.area.Area;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.instance.DefaultInstance;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class BloodMoonMoment extends Moment {
    public static final MapCodec<BloodMoonMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            HDMRegistries.BAR_RENDER_TYPE.byNameCodec().optionalFieldOf("bar_render_type").forGetter(Moment::barRenderType),
            Area.CODEC.optionalFieldOf("area").forGetter(Moment::area),
            MomentData.CODEC.optionalFieldOf("moment_data_context").forGetter(Moment::momentDataContext),
            TipSettings.CODEC.optionalFieldOf("tips").forGetter(Moment::tipSettingsContext),
            ClientSettings.CODEC.optionalFieldOf("clientSettingsContext").forGetter(Moment::clientSettingsContext),
            Codec.BOOL.fieldOf("isCanSleep").forGetter(BloodMoonMoment::isCanSleep)
    ).apply(instance, BloodMoonMoment::new));

    private final boolean isCanSleep;

    public BloodMoonMoment(boolean isCanSleep) {
        this.isCanSleep = isCanSleep;
    }

    public BloodMoonMoment(Optional<IBarRenderType> renderType, Optional<Area> area, Optional<MomentData> momentDataContext, Optional<TipSettings> tipSettingsContext, Optional<ClientSettings> clientSettingsContext, boolean canSleep) {
        super(renderType, area, momentDataContext, tipSettingsContext, clientSettingsContext);
        this.isCanSleep = canSleep;
    }

    public boolean isCanSleep() {
        return isCanSleep;
    }

    @Override
    public DefaultInstance newMomentInstance(Level level, ResourceKey<Moment> momentResourceKey) {
        return new DefaultInstance(level,momentResourceKey);
    }

    @Override
    public MapCodec<? extends Moment> codec() {
        return TMContextRegister.BLOOD_MOON.get();
    }
}
