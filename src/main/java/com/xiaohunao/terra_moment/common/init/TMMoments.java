package com.xiaohunao.terra_moment.common.init;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.xiaohunao.heaven_destiny_moment.common.context.ClientSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.MomentData;
import com.xiaohunao.heaven_destiny_moment.common.context.TipSettings;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmount;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.LocationCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.TimeCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.WorldUniqueMomentCondition;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.TEEntities;

public class TMMoments {
    public static final BiMap<ResourceKey<Moment>, Moment> MOMENTS = HashBiMap.create();


    public static final ResourceKey<Moment> SLIME_RAIN = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "slime_rain");
    public static final ResourceKey<Moment> BLOOD_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "blood_moon");
//    public static final ResourceKey<Moment> SANDSTORM = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "sandstorm");
//    public static final ResourceKey<Moment> GOBLIN_ARMY = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "goblin_army");
//    public static final ResourceKey<Moment> FROST_LEGION = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "frost_legion");
//    public static final ResourceKey<Moment> SOLAR_ECLIPSE = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "solar_eclipse");
//    public static final ResourceKey<Moment> PIRATE_INVASION = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "pirate_invasion");
//    public static final ResourceKey<Moment> PUMPKIN_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "pumpkin_moon");
//    public static final ResourceKey<Moment> FROST_MOON = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "frost_moon");
//    public static final ResourceKey<Moment> MARTIAN_MADNESS = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "martian_madness");
//    public static final ResourceKey<Moment> LUNAR_EVENTS = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "lunar_events");


    public static final ResourceKey<Moment> TORCH_GOD = TerraMoment.asResourceKey(HDMRegistries.Keys.MOMENT, "torch_god");

    public static void bootstrap(BootstrapContext<Moment> context) {
        register(context,BLOOD_MOON,new BloodMoonMoment(false)
                .setMomentData(new MomentData.Builder()
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEEntities.DRIPPLER.get(),20,1,2)))
                                                .spawnCategoryMultiplier(MobCategory.MONSTER,3.0D)
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(true)
                                        .ignoreLightLevel()
                                )
                        )
                        .conditionGroup(conditionGroup -> conditionGroup
                                .create(
                                    new LocationCondition.Builder()
                                        .setValidMoonPhases(0)
                                        .build(),
                                    TimeCondition.between(14000,22000),
                                    WorldUniqueMomentCondition.DEFAULT
                                )
                                .end(
                                     TimeCondition.between(23000,11000)
                                )
                        )
                        .build()
                )
                .setClientSettings(new ClientSettings.Builder()
                        .environmentColor(0xff0000)
                        .clientMoonSettingsContext(clientMoonSettingsContext -> clientMoonSettingsContext
                                .moonSize(25)
                                .moonTexture(TerraMoment.asResource("textures/gui/blood_moon.png"))
                        )
                        .build()
                )
                .setTipSettings(new TipSettings.Builder()
                        .tooltip(MomentState.READY,TerraMoment.asDescriptionId("blood_moon"),0xff0000)
                        .build()
                )
        );



        register(context, SLIME_RAIN, new SlimeRainMoment(150)
                .setMomentData(new MomentData.Builder()
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.BLUE_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.GREEN_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.PINK_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.CORRUPTED_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.DESERT_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.JUNGLE_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.EVIL_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.ICE_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.LAVA_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.LUMINOUS_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.CRIMSON_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.PURPLE_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.RED_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.TROPIC_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.YELLOW_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.HONEY_SLIME.get(), 20, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEEntities.BLACK_SLIME.get(), 20, 1, 1))
                                        )
                                        .spawnCategoryMultiplier(MobCategory.MONSTER, 1.5)
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(false)
                                        .slimesSpawnEverywhere()
                                        .ignoreDistance()
                                )
                        )
                        .conditionGroup(conditionGroup -> conditionGroup
                                .create(
                                        WorldUniqueMomentCondition.DEFAULT
                                )
                        )
                        .build()
                )
                .setTipSettings(
                        new TipSettings.Builder()
                                .tooltip(MomentState.READY, TerraMoment.asDescriptionId("slime_rain"), 0x6d99f9)
                                .tooltip(MomentState.READY, SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2))
                                .build())
        );


        register(context, TORCH_GOD, new TorchGodMoment(50,100,new RandomAmount(2,3))
                .setArea(new LocationArea.Builder().build(builder -> builder
                                .setY(MinMaxBounds.Doubles.between(-64, 0))
                                .build()
                        )
                )
        );
    }

    private static void register(BootstrapContext<Moment> context, ResourceKey<Moment> key, Moment value) {
        context.register(key, value);
        MOMENTS.put(key, value);
    }


}
