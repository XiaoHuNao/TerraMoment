package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentDataContext;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.init.MomentRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.TerraMoment;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.TEEntities;
import org.confluence.terraentity.init.TETags;

public class ModMoments {
    public static final ResourceKey<Moment> SLIME_RAIN = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "slime_rain");

    public static void bootstrap(BootstrapContext<Moment> context) {
        context.register(SLIME_RAIN,new DefaultMoment(
                HeavenDestinyMoment.asResource("terra"),
                new LocationArea.Builder().build(builder -> builder
                        .setDimension(Level.OVERWORLD)
                        .build()
                ),
                new MomentDataContext.Builder()
                        .mobSpawnSettings(mobSpawnSettings -> mobSpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.BLUE_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.GREEN_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.PINK_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.CORRUPTED_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.DESERT_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.JUNGLE_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.EVIL_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.ICE_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.LAVA_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.LUMINOUS_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.CRIMSON_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.PURPLE_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.RED_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.TROPIC_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.YELLOW_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.HONEY_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.BLACK_SLIME.get(),20,4,4))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.KING_SLIME.get(),1,4,4))
                                        )
                                        .spawnCategoryMultiplier(MobCategory.MONSTER,2.0)
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(false)
                                        .slimesSpawnEverywhere()
                                )
                        )
                        .build(),
                new TipSettingsContext.Builder().build(),
                new ClientSettingsContext.Builder().build()
        ));
    }
}
