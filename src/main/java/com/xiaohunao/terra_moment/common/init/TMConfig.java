package com.xiaohunao.terra_moment.common.init;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class TMConfig {
    public static final TMConfig CONFIG;
    public static final ModConfigSpec  CONFIG_SPEC;


    public final ModConfigSpec.ConfigValue<Integer> requiredKillsForSlimeKingSpawn;
//    public final ModConfigSpec.ConfigValue<Integer> afterRequiredForSlimeKingDefeatingKillsSpawn;

    public final ModConfigSpec.ConfigValue<Integer> requiredTorchCount;


    private TMConfig(ModConfigSpec.Builder builder) {
        requiredKillsForSlimeKingSpawn = builder.define("requiredKillsForSlimeKingSpawn",150);
//        requiredKillsForSlimeKingSpawn = builder.define("afterRequiredForSlimeKingDefeatingKillsSpawn",75);


        requiredTorchCount = builder.define("requiredTorchCount",50);
    }

    static {
        Pair<TMConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(TMConfig::new);

        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
