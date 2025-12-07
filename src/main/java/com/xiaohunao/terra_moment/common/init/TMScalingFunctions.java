package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.common.function.MomentKillEntityConditionDifficultyScalingFunction;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.terra_moment.common.api.DifficultyScalerFunction;
import com.xiaohunao.xhn_lib.api.register.holder.FlexibleHolder;
import com.xiaohunao.xhn_lib.api.register.register.FlexibleRegister;


public class TMScalingFunctions {
    public static final FlexibleRegister<MomentKillEntityConditionDifficultyScalingFunction> MOMENT_KILL_ENTITY_CONDITION_DIFFICULTY_SCALING_FUNCTION =
            FlexibleRegister.create(HDMRegistries.MOMENT_KILL_ENTITY_CONDITION_DIFFICULTY_SCALING_FUNCTION, HeavenDestinyMoment.MODID);

    public static final FlexibleHolder<MomentKillEntityConditionDifficultyScalingFunction, ?> SLIME_RAIN =
            MOMENT_KILL_ENTITY_CONDITION_DIFFICULTY_SCALING_FUNCTION.registerStatic("slime_rain", () ->
                    (baseValue, momentInstance) -> {
                        float multipliers = DifficultyScalerFunction.SLIME_RAIN_SCALER.apply(baseValue, momentInstance.getLevel());

                        /*PhaseUtils.getValueBasedOnPhase(ResourceLocation.tryBuild("confluence", "goblin_army_victory"), momentInstance.getLevel(), 0.5F, 1.0F);*/
                        return (int) (baseValue * multipliers);
                    }
            );
}
