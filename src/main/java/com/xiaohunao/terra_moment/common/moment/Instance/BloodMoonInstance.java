package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class BloodMoonInstance extends MomentInstance {
    public BloodMoonInstance(Level level, Moment moment) {
        super(TMMomentTypes.BLOOD_MOON.get(), level, moment);
    }

    public BloodMoonInstance(UUID uuid, Level level, Moment moment) {
        super(TMMomentTypes.BLOOD_MOON.get(), uuid, level, moment);
    }

    @Override
    public void finalizeSpawn(Entity entity) {
        playerListManager.mandatoryAttackRandomPlayer(entity);
    }

    @Override
    public boolean canCreate(Map<UUID, MomentInstance> runMoments, Level level, @Nullable BlockPos pos, @Nullable ServerPlayer player) {
        return super.canCreate(runMoments, level, pos, player);
    }
}
