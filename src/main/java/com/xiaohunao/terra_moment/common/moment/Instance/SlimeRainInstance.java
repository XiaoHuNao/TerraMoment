package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.context.condition.LocationConditionContext;
import com.xiaohunao.heaven_destiny_moment.common.init.MomentTypes;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentType;
import com.xiaohunao.heaven_destiny_moment.common.moment.moment.instance.DefaultInstance;
import com.xiaohunao.terra_moment.common.init.ModMomentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class SlimeRainInstance extends MomentInstance {
    public SlimeRainInstance(Level level, ResourceKey<Moment> momentKey) {
        super(ModMomentTypes.SLIME_RAIN.get(), level, momentKey);
    }

    public SlimeRainInstance(UUID uuid, Level level, ResourceKey<Moment> momentKey) {
        super(ModMomentTypes.SLIME_RAIN.get(), uuid, level, momentKey);
    }

    @Override
    public void finalizeSpawn(Entity entity) {
        if (entity.level().isClientSide) return;

        BlockPos blockPos = entity.blockPosition();
        LocationConditionContext locationCondition = LocationConditionContext.Builder.isCanSeeSky(true).build();

        for (int i = 0; i < 10; i++) {
            BlockPos pos = blockPos.offset(0, 20, 0);
            if (locationCondition.matches((ServerLevel) entity.level(),pos)) {
                entity.setPos(pos.getX(),pos.getY(),pos.getZ());
            }
        }
    }
}
