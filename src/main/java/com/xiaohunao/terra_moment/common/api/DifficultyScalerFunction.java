package com.xiaohunao.terra_moment.common.api;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class DifficultyScalerFunction {
    public static BiFunction<Integer, Level, Float> SLIME_RAIN_SCALER = (integer, level) -> 0.5f;

    public static void setSlimeRainScaler(@NotNull BiFunction<Integer, Level, Float> slimeRainScaler) {
        SLIME_RAIN_SCALER = slimeRainScaler;
    }
}
