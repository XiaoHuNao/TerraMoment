package com.xiaohunao.terra_moment.common.moment;

import com.mojang.serialization.MapCodec;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentData;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettings;
import com.xiaohunao.heaven_destiny_moment.common.moment.IMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentBuilder;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.heaven_destiny_moment.common.tracker.ITracker;
import com.xiaohunao.terra_moment.common.moment.Instance.GoblinArmyInstance;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class GoblinArmyMoment extends DefaultMoment{
    public static final MapCodec<GoblinArmyMoment> CODEC = Moment.simpleCodec(GoblinArmyMoment::new);

    public GoblinArmyMoment(Optional<IBarRenderType> iBarRenderType, Optional<MomentData> momentData, Optional<TipSettings> tipSettings, Optional<ClientSettings> clientSettings, Optional<List<ITracker>> iTrackers) {
        super(iBarRenderType, momentData, tipSettings, clientSettings, iTrackers);
    }


    @Override
    public MomentInstance newMomentInstance(Level level, IMoment moment) {
        return new GoblinArmyInstance(level, moment);
    }

    @Override
    public MapCodec<GoblinArmyMoment> codec() {
        return CODEC;
    }

    public static class Builder extends MomentBuilder<GoblinArmyMoment> {
        @Override
        public GoblinArmyMoment build() {
            return new GoblinArmyMoment(
                    Optional.ofNullable(barRenderType),
                    Optional.ofNullable(momentData),
                    Optional.ofNullable(tipSettings),
                    Optional.ofNullable(clientSettings),
                    Optional.ofNullable(trackers)
            );
        }
    }
}
