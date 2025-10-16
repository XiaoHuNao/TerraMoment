package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.IMoment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import com.xiaohunao.terra_moment.common.moment.GoblinArmyMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import com.xiaohunao.xhn_lib.api.register.register.MapCodecFlexibleRegister;


public class TMMapCodecRegisters {
    public static final MapCodecFlexibleRegister<IMoment> MOMENT_CODEC = MapCodecFlexibleRegister.createMapCodec(HDMRegistries.Keys.MOMENT_CODEC, TerraMoment.MODID)
            .addMapCodec(
                    "slime_rain",SlimeRainMoment.CODEC,
                    "torch_god",TorchGodMoment.CODEC,
                    "blood_moon",BloodMoonMoment.CODEC,
                    "goblin_army",GoblinArmyMoment.CODEC

            );
}
