package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.actuator.StateSettingActuator;
import com.xiaohunao.heaven_destiny_moment.common.automation.AutomationContext;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.KillEntityCondition;
import com.xiaohunao.heaven_destiny_moment.common.function.MomentProbabilityFunction;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMAttachments;
import com.xiaohunao.heaven_destiny_moment.common.moment.*;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


import java.util.Map;
import java.util.UUID;

public class GoblinArmyInstance extends MomentInstance {
    public GoblinArmyInstance(Level level, IMoment moment) {
        super(TMMomentTypes.GOBLIN_ARMY.get(), level, moment);
    }

    public GoblinArmyInstance(UUID uuid, Level level, IMoment moment) {
        super(TMMomentTypes.GOBLIN_ARMY.get(), uuid, level, moment);
    }


    @Override
    public void initMomentBar() {
        super.initMomentBar();
        updateBarProgress(0.0f);
    }

    @Override
    public void addKillCount(LivingEntity livingEntity, DamageSource source) {
        if (source == null) return;
        super.addKillCount(livingEntity, source);

        KillEntityCondition.RequiredKill requiredKill = tryRequiredKill.get(TerraMoment.asResource("goblin_army_victory")).getSecond();
        int totalScore = getData(HDMAttachments.MOMENT_KILL_ENTITY_RECORDER).getTotalScore();
        updateBarProgress((float) totalScore / requiredKill.totalScore());
    }

    @Override
    public void refreshBarAfterPlayerUpdate() {
        KillEntityCondition.RequiredKill requiredKill = tryRequiredKill.get(TerraMoment.asResource("goblin_army_victory")).getSecond();
        int totalScore = getData(HDMAttachments.MOMENT_KILL_ENTITY_RECORDER).getTotalScore();
        updateBarProgress((float) totalScore / requiredKill.totalScore());
    }

    @Override
    public boolean canCreate(AutomationContext context) {
        return true;
    }

    @Override
    public boolean checkGeneralConditions(AutomationContext context) {
        return super.checkGeneralConditions(context);
    }

    @Override
    public void finalizeSpawn(Entity entity) {
        playerListManager.mandatoryAttackRandomPlayer(entity);
    }

    @Override
    protected void victory() {
        super.victory();
    }
}
