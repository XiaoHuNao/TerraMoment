package com.xiaohunao.terra_moment.common.init;

import com.mojang.serialization.MapCodec;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModContextRegister {
    public static final DeferredRegister<MapCodec<? extends Moment>> MOMENT_CODEC = DeferredRegister.create(HDMRegistries.Keys.MOMENT_CODEC, TerraMoment.MODID);


    public static final DeferredHolder<MapCodec<? extends Moment>, MapCodec<? extends Moment>> SLIME_RAIN = MOMENT_CODEC.register("slime_rain", () -> SlimeRainMoment.CODEC);

}
