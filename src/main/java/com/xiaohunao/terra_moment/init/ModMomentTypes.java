package com.xiaohunao.terra_moment.init;

import com.xiaohunao.heaven_destiny_moment.common.init.MomentTypeRegistry;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentType;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.moment.instance.BloodMoonInstance;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMomentTypes {
    public static final DeferredRegister<MomentType<?>> MOMENT_TYPE = DeferredRegister.create(MomentTypeRegistry.KEY, TerraMoment.MODID);

    public static final DeferredHolder<MomentType<?>, MomentType<BloodMoonInstance>> BLOOD_MOON = MOMENT_TYPE.register("blood_moon",
            () -> MomentType.Builder.of(BloodMoonInstance::new,ModMoment.BLOOD_MOON.get()).build());
}
