package com.xiaohunao.terra_moment.common.event;

import com.google.common.collect.Sets;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.TimeConditionContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.init.TMConfig;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


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

    @SubscribeEvent
    public static void torchGod(BlockEvent.EntityPlaceEvent event) {
        LevelAccessor level = event.getLevel();
        Entity entity = event.getEntity();
        if (level instanceof ServerLevel serverLevel && entity instanceof ServerPlayer serverPlayer){
            BlockState placedBlock = event.getPlacedBlock();
            if (placedBlock.getBlock() instanceof BaseTorchBlock) {
                BlockPos startPos = event.getPos();

                Set<BlockPos> torchGroup = TorchGodInstance.updateTorchGroup(startPos, serverLevel);

                if (torchGroup.size() >= TMConfig.CONFIG.requiredTorchCount.get()){
                    MomentInstance.create(TMMoments.TORCH_GOD,serverLevel,startPos,serverPlayer,instance -> {
                        if (instance instanceof TorchGodInstance torchGodInstance) {
                            torchGodInstance.bindTorchGroup(torchGroup);
                        }
                    });
                }
            }
        }
    }


}
