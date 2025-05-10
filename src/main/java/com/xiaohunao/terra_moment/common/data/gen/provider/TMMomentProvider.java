package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.xiaohunao.heaven_destiny_moment.common.context.SpawnCategoryMultiplierModifier;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.LevelCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.WorldUniqueMomentCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.TimeCondition;
import com.xiaohunao.heaven_destiny_moment.common.data.gen.provider.MomentProvider;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.DefaultMoment;
import com.xiaohunao.heaven_destiny_moment.common.trigger.ConditionalTrigger;
import com.xiaohunao.heaven_destiny_moment.common.trigger.triggers.LevelTickTrigger;
import com.xiaohunao.heaven_destiny_moment.common.trigger.triggers.RandomLevelTickTrigger;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.confluence.terraentity.init.entity.TEMonsterEntities;

public class TMMomentProvider extends MomentProvider {
    public TMMomentProvider(PackOutput packOutput) {
        super(packOutput, TerraMoment.MODID);
    }

    @Override
    protected void addMoments() {
        addMoment(TMMoments.GOBLIN_ARMY,new DefaultMoment()
                .setMomentData(momentData -> momentData
                        .stateSettingsGroup(stateSettingsGroup -> stateSettingsGroup
                                .create(RandomLevelTickTrigger.of(0.5f),
                                        WorldUniqueMomentCondition.DEFAULT,
                                        TimeCondition.between(14000,22000)
                                )

                        )
                )
        );


        addMoment(TMMoments.BLOOD_MOON,new BloodMoonMoment(false)
                .setMomentData(momentData -> momentData
                        .entitySpawnSettings(entitySpawnSettings -> entitySpawnSettings
                                .biomeEntitySpawnSettings(biomeEntitySpawnSettings -> biomeEntitySpawnSettings
                                        .biomeMobSpawnSettings(biomeMobSpawnSettings -> biomeMobSpawnSettings
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEMonsterEntities.DRIPPLER.get(),240,1,2))
                                                .addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(TEMonsterEntities.BLOOD_ZOMBIE.get(),240,1,2))
                                        )
                                        .spawnCategoryMultiplier(MobCategory.MONSTER,new SpawnCategoryMultiplierModifier(TerraMoment.asResource("blood_moon"),3.0, SpawnCategoryMultiplierModifier.Operation.ADD_MULTIPLIED_BASE))
                                )
                                .rule(rule -> rule
                                        .allowOriginalBiomeSpawnSettings(true)
                                        .ignoreLightLevel()
                                )
                        )
                        .stateSettingsGroup(stateSettingsGroup -> stateSettingsGroup
                                .create(RandomLevelTickTrigger.of(0.5f),
                                        WorldUniqueMomentCondition.DEFAULT,
                                        TimeCondition.between(14000,22000),
                                        new LevelCondition.Builder()
                                                .setValidMoonPhases(0)
                                                .build()
                                )
                                .state(MomentState.END, LevelTickTrigger.INSTANCE,TimeCondition.between(23000,11000))
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
                        .tooltip(MomentState.READY,TerraMoment.asDescriptionId("blood_moon"),0xff0000)
                        .tooltip(MomentState.READY, SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2))
                ));
    }
}
