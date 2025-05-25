package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.xiaohunao.heaven_destiny_moment.common.attachment.KillEntityRecorderAttachment;
import com.xiaohunao.heaven_destiny_moment.common.context.SpawnCategoryMultiplierModifier;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.LevelCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.KillEntityCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.WorldUniqueMomentCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.TimeCondition;
import com.xiaohunao.heaven_destiny_moment.common.data.gen.provider.MomentProvider;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMBarRenderTypes;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMScalingFunctions;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.SimpleKillEntityMoment;
import com.xiaohunao.heaven_destiny_moment.common.trigger.triggers.*;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import org.confluence.terraentity.init.entity.TEMonsterEntities;

public class TMMomentProvider extends MomentProvider {
    public TMMomentProvider(PackOutput packOutput) {
        super(packOutput, TerraMoment.MODID);
    }

    @Override
    protected void addMoments() {
        addMoment(TMMoments.GOBLIN_ARMY, new SimpleKillEntityMoment()
                .setBarRenderType(HDMBarRenderTypes.TERRA_BAR_RENDER_TYPE.get())
                .setMomentData(momentData -> momentData
                        .stateSettingsGroup(stateSettingsGroup -> stateSettingsGroup
                                .state(MomentState.CREATE,RandomLevelTickTrigger.of(0.1f)
                                )
                                .state(MomentState.VICTORY,KillAnyEntityTrigger.Moment.INSTANCE,
                                        KillEntityCondition.builder(KillEntityRecorderAttachment.KillType.MOMENT)
                                                .withRequiredTotalScore(2)
                                                .withDifficultyScaling(HDMScalingFunctions.COMMON.get())
                                                .build()
                                )

                        )
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_ARCHER.get(), 240, 1, 2))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_PEON.get(), 240, 1, 2))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_WARRIOR.get(), 240, 1, 2))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_THIEF.get(), 240, 1, 2))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.GOBLIN_SCOUT.get(), 240, 1, 2))
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

        );


        addMoment(TMMoments.BLOOD_MOON, new BloodMoonMoment(false)
                .setMomentData(momentData -> momentData
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.DRIPPLER.get(), 200, 1, 2))
                                                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLOOD_ZOMBIE.get(), 360, 2, 3))
                                        )
                                        .spawnCategoryMultiplier(MobCategory.MONSTER, new SpawnCategoryMultiplierModifier(TerraMoment.asResource("blood_moon"), 1.5, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(true)
                                        .ignoreLightLevel()
                                )
                        )
                        .stateSettingsGroup(stateSettingsGroup -> stateSettingsGroup
                                .state(MomentState.CREATE,TimeProbabilityTrigger.exactly(14000,0.05f),
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
                        .tooltip(MomentState.READY, TerraMoment.asDescriptionId("blood_moon"), 0xff0000)
                        .tooltip(MomentState.READY, SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2))
                ));
    }
}
