package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.LocationCondition;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SlimeRainInstance extends MomentInstance {
    public SlimeRainInstance(Level level, Moment moment) {
        super(TMMomentTypes.SLIME_RAIN.get(), level, moment);
    }

    public SlimeRainInstance(UUID uuid, Level level, Moment moment) {
        super(TMMomentTypes.SLIME_RAIN.get(), uuid, level, moment);
    }

    @Override
    public void finalizeSpawn(Entity entity) {
        if (entity.level().isClientSide) return;

        BlockPos blockPos = entity.blockPosition();
        LocationCondition locationCondition = LocationCondition.Builder.isCanSeeSky(true).build();

        for (int i = 0; i < 10; i++) {
            BlockPos pos = blockPos.offset(0, 50, 0);
            if (locationCondition.matches((ServerLevel) entity.level(),pos)) {
                entity.setPos(pos.getX(),pos.getY(),pos.getZ());
            }
        }

        mandatoryAttackRandomPlayer(entity);
    }

    @Override
    public boolean canCreate(Map<UUID, MomentInstance> runMoments, Level level, @Nullable BlockPos pos, @Nullable ServerPlayer player) {
        return true;
    }
}
