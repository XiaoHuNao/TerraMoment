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
import com.xiaohunao.terra_moment.common.moment.Instance.PartyInstance;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class PartyMoment extends DefaultMoment {
    public static final MapCodec<PartyMoment> CODEC = Moment.simpleCodec(PartyMoment::new);

    public PartyMoment(Optional<IBarRenderType> iBarRenderType, Optional<MomentData> momentData, Optional<TipSettings> tipSettings, Optional<ClientSettings> clientSettings, Optional<List<ITracker>> iTrackers) {
        super(iBarRenderType, momentData, tipSettings, clientSettings, iTrackers);
    }

    @Override
    public MapCodec<PartyMoment> codec() {
        return CODEC;
    }

    @Override
    public MomentInstance newMomentInstance(Level level, IMoment moment) {
        return new PartyInstance(level, moment);
    }

    public static class Builder extends MomentBuilder<PartyMoment> {
        @Override
        public PartyMoment build() {
            return new PartyMoment(
                    Optional.ofNullable(barRenderType),
                    Optional.ofNullable(momentData),
                    Optional.ofNullable(tipSettings),
                    Optional.ofNullable(clientSettings),
                    Optional.ofNullable(trackers)
            );
        }
    }
}
