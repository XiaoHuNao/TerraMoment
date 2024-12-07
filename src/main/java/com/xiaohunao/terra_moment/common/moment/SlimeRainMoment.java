package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentDataContext;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.Area;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.common.init.TMContextRegister;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class SlimeRainMoment extends Moment {
    public static final MapCodec<SlimeRainMoment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            HDMRegistries.BAR_RENDER_TYPE.byNameCodec().optionalFieldOf("bar_render_type").forGetter(Moment::barRenderType),
            Area.CODEC.optionalFieldOf("area").forGetter(Moment::area),
            MomentDataContext.CODEC.optionalFieldOf("moment_data_context").forGetter(Moment::momentDataContext),
            TipSettingsContext.CODEC.optionalFieldOf("tips").forGetter(Moment::tipSettingsContext),
            ClientSettingsContext.CODEC.optionalFieldOf("clientSettingsContext").forGetter(Moment::clientSettingsContext),
            Codec.INT.fieldOf("requiredKills").forGetter(SlimeRainMoment::requiredKills)
    ).apply(instance, SlimeRainMoment::new));


    private final int requiredKills;

    public SlimeRainMoment(int requiredKills) {
        super();
        this.requiredKills = requiredKills;
    }

    public SlimeRainMoment(Optional<IBarRenderType> renderType, Optional<Area> area, Optional<MomentDataContext> momentDataContext, Optional<TipSettingsContext> tipSettingsContext, Optional<ClientSettingsContext> clientSettingsContext, int requiredKills) {
        super(renderType, area, momentDataContext, tipSettingsContext, clientSettingsContext);
        this.requiredKills = requiredKills;
    }

    @Override
    public MomentInstance<SlimeRainMoment> newMomentInstance(Level level, ResourceKey<Moment> momentResourceKey) {
        return new SlimeRainInstance(level,momentResourceKey);
    }

    @Override
    public MapCodec<? extends Moment> codec() {
        return TMContextRegister.SLIME_RAIN.get();
    }

    public int requiredKills() {
        return requiredKills;
    }
}
