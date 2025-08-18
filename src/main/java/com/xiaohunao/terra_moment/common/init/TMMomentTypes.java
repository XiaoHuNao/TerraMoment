package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentType;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.Instance.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TMMomentTypes {
    public static final DeferredRegister<MomentType<?>> MOMENT_TYPE = DeferredRegister.create(HDMRegistries.MOMENT_TYPE, TerraMoment.MODID);


    public static final DeferredHolder<MomentType<?>, MomentType<SlimeRainInstance>> SLIME_RAIN = MOMENT_TYPE.register("slime_rain",
            () -> new MomentType.Builder<>(SlimeRainInstance::new).build());

    public static final DeferredHolder<MomentType<?>, MomentType<TorchGodInstance>> TORCH_GOD = MOMENT_TYPE.register("torch_god",
            () -> new MomentType.Builder<>(TorchGodInstance::new).build());

    public static final DeferredHolder<MomentType<?>, MomentType<BloodMoonInstance>> BLOOD_MOON = MOMENT_TYPE.register("blood_moon",
            () -> new MomentType.Builder<>(BloodMoonInstance::new).build());

    public static final DeferredHolder<MomentType<?>, MomentType<GoblinArmyInstance>> GOBLIN_ARMY = MOMENT_TYPE.register("goblin_army",
            () -> new MomentType.Builder<>(GoblinArmyInstance::new).build());

    public static final DeferredHolder<MomentType<?>, MomentType<PartyInstance>> PARTY = MOMENT_TYPE.register("party",
            () -> new MomentType.Builder<>(PartyInstance::new).build());
}