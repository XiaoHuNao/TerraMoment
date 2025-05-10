package com.xiaohunao.terra_moment.common.init;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.xiaohunao.heaven_destiny_moment.api.MomentManager;
import com.xiaohunao.heaven_destiny_moment.common.context.SpawnCategoryMultiplierModifier;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmount;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.LevelCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.WorldUniqueMomentCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.TimeCondition;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.moment.area.LocationArea;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import com.xiaohunao.xhn_lib.api.register.FlexibleHolder;
import com.xiaohunao.xhn_lib.api.register.FlexibleRegister;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.entity.TEMonsterEntities;

public class TMMoments {
    public static final FlexibleRegister<Moment> MOMENT = FlexibleRegister.create(HDMRegistries.MOMENT,TerraMoment.MODID, MomentManager.getInstance());

    public static final FlexibleHolder<Moment, ?> SLIME_RAIN = MOMENT.registerDynamic("slime_rain");
    public static final FlexibleHolder<Moment, ?> BLOOD_MOON = MOMENT.registerDynamic("blood_moon");
    public static final FlexibleHolder<Moment, ?> TORCH_GOD = MOMENT.registerDynamic( "torch_god");
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



//    public static void bootstrap(BootstrapContext<Moment> context) {
//        register(context,BLOOD_MOON,new BloodMoonMoment(false)
//                .setMomentData(momentData -> momentData
//                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
//                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
//                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
//                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEMonsterEntities.DRIPPLER.get(),240,1,2))
//                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLOOD_ZOMBIE.get(),240,1,2))
//                                        )
//                                        .spawnCategoryMultiplier(MobCategory.MONSTER,new SpawnCategoryMultiplierModifier(TerraMoment.asResource("blood_moon"),3.0, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
//                                )
//                                .rule(rule -> rule
//                                        .allowOriginalBiomeSpawnSettings(true)
//                                        .ignoreLightLevel()
//                                )
//                        )
////                        .conditionGroup(conditionGroup -> conditionGroup
////                                .create(true,
////                                    TimeCondition.between(14000,22000),
////                                    new LevelCondition.Builder()
////                                            .setValidMoonPhases(0)
////                                            .build(),
////                                    WorldUniqueMomentCondition.DEFAULT
////                                )
////                                .end(
////                                     TimeCondition.between(23000,11000)
////                                )
////                        )
//                )
//                .setClientSettings(clientSettings -> clientSettings
//                        .environmentColor(0xff0000)
//                        .clientMoonSettings(clientMoonSettings -> clientMoonSettings
//                                .moonSize(25)
//                                .moonTexture(TerraMoment.asResource("textures/gui/blood_moon.png"))
//                        )
//                )
//                .setTipSettings(tipSettings -> tipSettings
//                        .tooltip(MomentState.READY,TerraMoment.asDescriptionId("blood_moon"),0xff0000)
//                        .tooltip(MomentState.READY, SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2))
//                )
//        );
//
//
//
//        register(context, SLIME_RAIN, new SlimeRainMoment(150)
//                .setMomentData(momentData -> momentData
//                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
//                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
//                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLUE_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GREEN_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.PINK_SLIME.get(), 1, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.DESERT_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.JUNGLE_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.PURPLE_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.RED_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.TROPIC_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.YELLOW_SLIME.get(), 20, 1, 1))
//                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLACK_SLIME.get(), 20, 1, 1))
//                                        )
//                                        .spawnCategoryMultiplier(MobCategory.MONSTER, new SpawnCategoryMultiplierModifier(TerraMoment.asResource("slime_rain"),1.5, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
//                                )
//                                .rule(rule -> rule
//                                        .allowOriginalBiomeSpawnSettings(false)
//                                        .slimesSpawnEverywhere()
//                                        .ignoreDistance()
//                                )
//                        )
////                        .conditionGroup(conditionGroup -> conditionGroup
////                                .create(true,
////                                        TimeCondition.between(1000, 9000),
////                                        WorldUniqueMomentCondition.DEFAULT
////                                )
////                        )
//                )
//                .setTipSettings(tipSettings -> tipSettings
//                                .tooltip(MomentState.READY, TerraMoment.asDescriptionId("slime_rain"), 0x6d99f9)
//                )
//        );
//
//
//        register(context, TORCH_GOD, new TorchGodMoment(50,100,new RandomAmount(2,3))
//                .setArea(new LocationArea.Builder().build(builder -> builder
//                                .setY(MinMaxBounds.Doubles.between(-64, 0))
//                                .build()
//                        )
//                )
//        );
//
////        register(context,TEST,new DefaultMoment()
////                .setMomentData(momentData -> momentData
////                        .addReward(
////                                new EffectReward.Builder()
////                                        .add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED))
////                                        .build(),
////                                new AttributeReward.Builder()
////                                        .add(Attributes.MOVEMENT_SPEED, new AttributeModifier(TerraMoment.asResource("test"), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
////                                        .build(),
////                                new ItemReward.Builder()
////                                        .add(Items.DIAMOND.getDefaultInstance())
////                                        .build(),
////                                new XpReward(100)
////                        )
////                )
////        );
//    }


}
