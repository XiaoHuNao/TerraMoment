package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.common.init.MomentRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.terra_moment.TerraMoment;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

public class ModMoment {
    public static final ResourceKey<Moment> SLIME_RAIN = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "slime_rain");

    public static void bootstrap(BootstrapContext<Moment> context) {

    }
}
