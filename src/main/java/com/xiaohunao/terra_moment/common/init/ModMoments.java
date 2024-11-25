package com.xiaohunao.terra_moment.common.init;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentDataContext;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettingsContext;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.TEEntities;

public class ModMoments {
    public static final BiMap<ResourceKey<Moment>,Moment> MOMENTS = HashBiMap.create();


    public static final ResourceKey<Moment> SLIME_RAIN = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "slime_rain");
    public static final ResourceKey<Moment> SANDSTORM = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "sandstorm");
    public static final ResourceKey<Moment> BLOOD_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "blood_moon");
    public static final ResourceKey<Moment> GOBLIN_ARMY = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "goblin_army");
    public static final ResourceKey<Moment> FROST_LEGION = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "frost_legion");
    public static final ResourceKey<Moment> SOLAR_ECLIPSE = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "solar_eclipse");
    public static final ResourceKey<Moment> PIRATE_INVASION = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "pirate_invasion");
    public static final ResourceKey<Moment> PUMPKIN_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "pumpkin_moon");
    public static final ResourceKey<Moment> FROST_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "frost_moon");
    public static final ResourceKey<Moment> MARTIAN_MADNESS = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "martian_madness");
    public static final ResourceKey<Moment> LUNAR_EVENTS = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "lunar_events");

    public static void bootstrap(BootstrapContext<Moment> context) {
        register(context,SLIME_RAIN,new SlimeRainMoment(
                HeavenDestinyMoment.asResource("terra"),
                new LocationArea.Builder().build(builder -> builder
                        .setDimension(Level.OVERWORLD)
                        .build()
                ),
                new MomentDataContext.Builder()
                        .mobSpawnSettings(mobSpawnSettings -> mobSpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.BLUE_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.GREEN_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.PINK_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.CORRUPTED_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.DESERT_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.JUNGLE_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.EVIL_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.ICE_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.LAVA_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.LUMINOUS_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.CRIMSON_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.PURPLE_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.RED_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.TROPIC_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.YELLOW_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.HONEY_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.BLACK_SLIME.get(),20,1,1))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.KING_SLIME.get(),1,1,1))
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
                        .tooltip(MomentState.READY, TerraMoment.asDescriptionId("slime_rain"),0x6d99f9)
                        .tooltip(MomentState.READY, SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2))
                        .build(),
                new ClientSettingsContext.Builder().build()
        ));
    }

    private static void register(BootstrapContext<Moment> context, ResourceKey<Moment> key, Moment value){
        context.register(key,value);
        MOMENTS.put(key,value);
    }


}