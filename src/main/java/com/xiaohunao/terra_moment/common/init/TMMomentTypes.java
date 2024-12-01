package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentType;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TMMomentTypes {
    public static final DeferredRegister<MomentType<?>> MOMENT_TYPE = DeferredRegister.create(HDMRegistries.MOMENT_TYPE, TerraMoment.MODID);


    public static final DeferredHolder<MomentType<?>, MomentType<SlimeRainInstance>> SLIME_RAIN = MOMENT_TYPE.register("slime_rain",
            () -> MomentType.builder(SlimeRainInstance::new, DefaultMoment.class).build());
}