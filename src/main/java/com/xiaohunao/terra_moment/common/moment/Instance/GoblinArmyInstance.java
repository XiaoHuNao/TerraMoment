package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.actuator.StateSettingActuator;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMAttachments;
import com.xiaohunao.heaven_destiny_moment.common.moment.*;
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
    public GoblinArmyInstance(Level level, Moment moment) {
        super(TMMomentTypes.GOBLIN_ARMY.get(), level, moment);
    }

    public GoblinArmyInstance(UUID uuid, Level level, Moment moment) {
        super(TMMomentTypes.GOBLIN_ARMY.get(), uuid, level, moment);
    }


    @Override
    public void initMomentBar() {
        super.initMomentBar();
        updateBarProgress(0.0f);
    }

    @Override
    public void addKillCount(LivingEntity livingEntity, DamageSource source) {
        super.addKillCount(livingEntity, source);
        tryRequiredKill.forEach((actuator, requiredKill) -> {
            if (actuator instanceof StateSettingActuator(MomentState momentState) && momentState == MomentState.VICTORY){
                int totalScore = getData(HDMAttachments.MOMENT_KILL_ENTITY_RECORDER).getTotalScore();
                updateBarProgress((float) totalScore / requiredKill.totalScore());
            }
        });
    }

    @Override
    public boolean canCreate(Map<UUID, MomentInstance> runMoments, Level level, @Nullable BlockPos pos, @Nullable ServerPlayer player) {
        return true;
    }

    @Override
    public boolean checkGeneralConditions(@Nullable BlockPos pos, @Nullable ServerPlayer serverPlayer) {
        return super.checkGeneralConditions(pos, serverPlayer);
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
