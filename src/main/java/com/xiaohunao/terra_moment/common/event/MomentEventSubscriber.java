package com.xiaohunao.terra_moment.common.event;

import com.xiaohunao.heaven_destiny_moment.common.context.condition.TimeConditionContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;


@EventBusSubscriber
public class MomentEventSubscriber {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        if (!level.isClientSide){
            TimeConditionContext between = TimeConditionContext.between(1000, 9000);
            if (between.matches(level.getDayTime()) && level.random.nextInt(225000) == 0) {
                MomentInstance.create(TMMoments.SLIME_RAIN, (ServerLevel) level, BlockPos.ZERO,null);
            }
        }
    }

}
