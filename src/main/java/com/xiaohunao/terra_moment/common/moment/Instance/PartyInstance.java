package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.moment.IMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class PartyInstance extends MomentInstance {
    public PartyInstance(Level level, IMoment moment) {
        super(TMMomentTypes.PARTY.get(), level, moment);
    }

    public PartyInstance(UUID uuid, Level level, IMoment moment) {
        super(TMMomentTypes.PARTY.get(), uuid, level, moment);
    }
}
