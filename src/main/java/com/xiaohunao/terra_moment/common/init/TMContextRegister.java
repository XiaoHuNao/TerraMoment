package com.xiaohunao.terra_moment.common.init;

import com.mojang.serialization.MapCodec;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class TMContextRegister {
    public static final DeferredRegister<MapCodec<? extends Moment<?>>> MOMENT_CODEC = DeferredRegister.create(HDMRegistries.Keys.MOMENT_CODEC, TerraMoment.MODID);


    public static final DeferredHolder<MapCodec<? extends Moment<?>>, MapCodec<SlimeRainMoment>> SLIME_RAIN = MOMENT_CODEC.register("slime_rain", () -> SlimeRainMoment.CODEC);
    public static final DeferredHolder<MapCodec<? extends Moment<?>>, MapCodec<TorchGodMoment>> TORCH_GOD = MOMENT_CODEC.register("torch_god", () -> TorchGodMoment.CODEC);
    public static final DeferredHolder<MapCodec<? extends Moment<?>>, MapCodec<BloodMoonMoment>> BLOOD_MOON = MOMENT_CODEC.register("blood_moon", () -> BloodMoonMoment.CODEC);

}
