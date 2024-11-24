package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentDataContext;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.init.MomentRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.terra_moment.TerraMoment;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.TEEntities;
import org.confluence.terraentity.init.TETags;

public class ModMoments {
    public static final ResourceKey<Moment> SLIME_RAIN = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "slime_rain");
    public static final ResourceKey<Moment> SANDSTORM = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "sandstorm");
    public static final ResourceKey<Moment> BLOOD_MOON = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "blood_moon");
    public static final ResourceKey<Moment> GOBLIN_ARMY = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "goblin_army");
    public static final ResourceKey<Moment> FROST_LEGION = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "frost_legion");
    public static final ResourceKey<Moment> SOLAR_ECLIPSE = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "solar_eclipse");
    public static final ResourceKey<Moment> PIRATE_INVASION = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "pirate_invasion");
    public static final ResourceKey<Moment> PUMPKIN_MOON = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "pumpkin_moon");
    public static final ResourceKey<Moment> FROST_MOON = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "frost_moon");
    public static final ResourceKey<Moment> MARTIAN_MADNESS = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "martian_madness");
    public static final ResourceKey<Moment> LUNAR_EVENTS = TerraMoment.asResourceKey(MomentRegistries.Keys.MOMENT, "lunar_events");

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
                                        .spawnCategoryMultiplier(MobCategory.MONSTER,1.5)
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(false)
                                        .slimesSpawnEverywhere()
                                )
                        )
                        .build(),
                new TipSettingsContext.Builder()
                        .tooltip(MomentState.READY, "slime_rain")
                        .tooltip(MomentState.READY, SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2))
                        .build(),
                new ClientSettingsContext.Builder().build()
        ));
    }
}
