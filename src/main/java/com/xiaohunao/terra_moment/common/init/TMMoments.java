package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.api.MomentManager;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.xhn_lib.api.register.FlexibleHolder;
import com.xiaohunao.xhn_lib.api.register.FlexibleRegister;

public class TMMoments {
    public static final FlexibleRegister<Moment> MOMENT = FlexibleRegister.create(HDMRegistries.MOMENT,TerraMoment.MODID, MomentManager.getInstance());

    public static final FlexibleHolder<Moment, ?> SLIME_RAIN = MOMENT.registerDynamic("slime_rain");
    public static final FlexibleHolder<Moment, ?> BLOOD_MOON = MOMENT.registerDynamic("blood_moon");
//    public static final FlexibleHolder<Moment, ?> TORCH_GOD = MOMENT.registerDynamic( "torch_god");
    public static final FlexibleHolder<Moment, ?> GOBLIN_ARMY = MOMENT.registerDynamic("goblin_army");

//    public static final ResourceKey<Moment> SANDSTORM = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "sandstorm");
//    public static final ResourceKey<Moment> GOBLIN_ARMY = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "goblin_army");
//    public static final ResourceKey<Moment> FROST_LEGION = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "frost_legion");
//    public static final ResourceKey<Moment> SOLAR_ECLIPSE = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "solar_eclipse");
//    public static final ResourceKey<Moment> PIRATE_INVASION = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "pirate_invasion");
//    public static final ResourceKey<Moment> PUMPKIN_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "pumpkin_moon");
//    public static final ResourceKey<Moment> FROST_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "frost_moon");
//    public static final ResourceKey<Moment> MARTIAN_MADNESS = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "martian_madness");
//    public static final ResourceKey<Moment> LUNAR_EVENTS = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "lunar_events");
}
