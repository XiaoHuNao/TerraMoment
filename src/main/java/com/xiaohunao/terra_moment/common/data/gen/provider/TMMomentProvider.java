package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.xiaohunao.heaven_destiny_moment.common.actuator.SimpleEntitySpawnActuator;
import com.xiaohunao.heaven_destiny_moment.common.attachment.KillEntityRecorderAttachment;
import com.xiaohunao.heaven_destiny_moment.common.context.SpawnCategoryMultiplierModifier;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.LevelCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.MomentHistoryCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.PlayerCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.KillEntityCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.WorldUniqueMomentCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.LevelRunningTimeCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.TimeCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.entity_info.EntityInfo;
import com.xiaohunao.heaven_destiny_moment.common.data.gen.provider.MomentProvider;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMBarRenderTypes;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMScalingFunctions;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.spawn_algorithm.RandomPlayerPosImitationVanillaNaturalSpawner;
import com.xiaohunao.heaven_destiny_moment.common.trigger.triggers.*;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import com.xiaohunao.terra_moment.common.moment.GoblinArmyMoment;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.entity.TEBossEntities;
import org.confluence.terraentity.init.entity.TEMonsterEntities;

public class TMMomentProvider extends MomentProvider {
    public TMMomentProvider(PackOutput packOutput) {
        super(packOutput, TerraMoment.MODID);
    }

    @Override
    protected void addMoments() {
        addMoment(TMMoments.GOBLIN_ARMY, new GoblinArmyMoment()
                .setBarRenderType(HDMBarRenderTypes.SLIME_BAR_RENDER_TYPE.get())
                .setMomentData(momentData -> momentData
                        .autoActuatorGroupSettings(stateSettingsGroup -> stateSettingsGroup
                                .create(
                                        PlayerCondition.builder(PlayerCondition.Type.ANY)
                                                .playerPredicate(playerPredicate -> playerPredicate
                                                        .checkAdvancementDone(ResourceLocation.withDefaultNamespace("adventure/hero_of_the_village"), true)
                                                )
                                                .build(),
                                        WorldUniqueMomentCondition.DEFAULT
                                )
                                .state(MomentState.VICTORY,KillEntityTrigger.Moment.INSTANCE,
                                        KillEntityCondition.builder(KillEntityRecorderAttachment.KillType.MOMENT)
                                                .withRequiredTotalScore(150)
                                                .withDifficultyScaling(HDMScalingFunctions.COMMON.get())
                                                .build()
                                )

                        )
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_ARCHER.get(), 360, 2, 4))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_PEON.get(), 480, 2, 3))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_WARRIOR.get(), 360, 2, 3))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_SORCERER.get(), 240, 1, 1))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_THIEF.get(), 480, 2, 4))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.ANGER_GOBLIN.get(), 240, 1, 2))
                                        )
                                        .spawnCategoryMultiplier(MobCategory.MONSTER, new SpawnCategoryMultiplierModifier(TerraMoment.asResource("goblin_army"), 1.5, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(false)
                                )
                                .afterEndClearMonster()
                        )
                )
                .setTipSettings(tipSettings -> tipSettings
                        .tooltip(MomentState.READY, TerraMoment.asDescriptionId("goblin_army_ready"), 0xaf4bff)
                        .tooltip(MomentState.START, TerraMoment.asDescriptionId("goblin_army_start"), 0xaf4bff)
                        .tooltip(MomentState.VICTORY, TerraMoment.asDescriptionId("goblin_army_victory"), 0xaf4bff)
                )
        );


        addMoment(TMMoments.BLOOD_MOON, new BloodMoonMoment(false)
                .setMomentData(momentData -> momentData
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.DRIPPLER.get(), 300, 1, 2))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLOOD_ZOMBIE.get(), 420, 2, 3))
                                        )
                                        .spawnCategoryMultiplier(MobCategory.MONSTER, new SpawnCategoryMultiplierModifier(TerraMoment.asResource("blood_moon"), 1.5, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(true)
                                        .ignoreLightLevel()
                                )
                        )
                        .autoActuatorGroupSettings(autoActuatorGroupSettings -> autoActuatorGroupSettings
                                .create(
                                        TimeProbabilityTrigger.exactly(14000,0.05f),
                                        WorldUniqueMomentCondition.DEFAULT,
                                        TimeCondition.between(14000, 22000),
                                        new LevelCondition.Builder()
                                                .setValidMoonPhases(0)
                                                .build()
                                )
                                .state(MomentState.END, LevelTickTrigger.INSTANCE, TimeCondition.between(23000, 11000))
                        )
                )
                .setClientSettings(clientSettings -> clientSettings
                        .environmentColor(0xff0000)
                        .clientMoonSettings(clientMoonSettings -> clientMoonSettings
                                .moonSize(25)
                                .moonTexture(TerraMoment.asResource("textures/gui/blood_moon.png"))
                        )
                )
                .setTipSettings(tipSettings -> tipSettings
                        .tooltip(MomentState.READY, TerraMoment.asDescriptionId("blood_moon"), 0x32ff82)
                ));


        addMoment(TMMoments.SLIME_RAIN, new SlimeRainMoment()
                .setBarRenderType(HDMBarRenderTypes.SLIME_BAR_RENDER_TYPE.get())
                        .setMomentData(momentData -> momentData
                                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLUE_SLIME.get(), 200, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GREEN_SLIME.get(), 300, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.PINK_SLIME.get(), 1, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.DESERT_SLIME.get(), 20, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.JUNGLE_SLIME.get(), 20, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.PURPLE_SLIME.get(), 100, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.RED_SLIME.get(), 20, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.TROPIC_SLIME.get(), 20, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.YELLOW_SLIME.get(), 20, 1, 1))
                                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLACK_SLIME.get(), 20, 1, 1))
                                                        )
                                                        .spawnCategoryMultiplier(MobCategory.MONSTER, new SpawnCategoryMultiplierModifier(TerraMoment.asResource("slime_rain"),1.5, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
                                                )
                                                .rule(rule -> rule
                                                        .allowOriginalBiomeSpawnSettings(false)
                                                        .slimesSpawnEverywhere()
                                                        .ignoreDistance()
                                                )
                                                .afterEndClearMonster()
                                        )
                                        .autoActuatorGroupSettings(autoActuatorGroupSettings -> autoActuatorGroupSettings
                                                .create(
                                                        TimeProbabilityTrigger.between(1000, 9000,0.0000133f),
                                                        LevelRunningTimeCondition.atLeast(30 * 60 * 20),
                                                        MomentHistoryCondition.randomTicks(85 * 60 * 20,180 * 60 * 20, TMMomentTypes.SLIME_RAIN.get()),
                                                        WorldUniqueMomentCondition.DEFAULT
                                                )
                                                .actuator(SimpleEntitySpawnActuator.of(new EntityInfo.Builder(TEBossEntities.KING_SLIME.get()).build(), RandomPlayerPosImitationVanillaNaturalSpawner.INSTANCE),
                                                        KillEntityTrigger.Moment.INSTANCE,
                                                        KillEntityCondition.builder(KillEntityRecorderAttachment.KillType.MOMENT)
                                                                .withRequiredTotalScore(150)
                                                                .withDifficultyScaling(HDMScalingFunctions.COMMON.get())
                                                                .build()
                                                )
                                                .state(MomentState.VICTORY, KillEntityTrigger.Moment.INSTANCE,
                                                        KillEntityCondition.builder(KillEntityRecorderAttachment.KillType.MOMENT)
                                                                .withRequiredKillCount(TEBossEntities.KING_SLIME.get(),1)
                                                                .build()
                                                )
                                        )
                        )
                        .setTipSettings(tipSettings -> tipSettings
                                .tooltip(MomentState.START, TerraMoment.asDescriptionId("slime_rain_start"), 0x32ff82)
                                .tooltip(MomentState.READY, TerraMoment.asDescriptionId("slime_rain_ready"), 0x32ff82)
                        )
        );

    }


}
