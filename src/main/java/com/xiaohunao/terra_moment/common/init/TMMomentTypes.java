package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentType;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.instance.DefaultInstance;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TMMomentTypes {
    public static final DeferredRegister<MomentType<?>> MOMENT_TYPE = DeferredRegister.create(HDMRegistries.MOMENT_TYPE, TerraMoment.MODID);


    public static final DeferredHolder<MomentType<?>, MomentType<SlimeRainInstance>> SLIME_RAIN = MOMENT_TYPE.register("slime_rain",
            () -> MomentType.builder(SlimeRainInstance::new, DefaultMoment.class).build());

    public static final DeferredHolder<MomentType<?>, MomentType<TorchGodInstance>> TORCH_GOD = MOMENT_TYPE.register("torch_god",
            () -> MomentType.builder(TorchGodInstance::new, DefaultMoment.class).build());

    public static final DeferredHolder<MomentType<?>, MomentType<DefaultInstance>> BLOOD_MOON = MOMENT_TYPE.register("blood_moon",
            () -> MomentType.builder(DefaultInstance::new, BloodMoonMoment.class).build());
}