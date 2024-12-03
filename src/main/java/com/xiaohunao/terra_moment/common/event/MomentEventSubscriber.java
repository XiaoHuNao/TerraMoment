package com.xiaohunao.terra_moment.common.event;

import com.xiaohunao.heaven_destiny_moment.common.context.condition.TimeConditionContext;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMMomentTypes;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.moment.Instance.TorchGodInstance;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
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


import java.util.Map;
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

                Registry<Moment> moments = level.registryAccess().registryOrThrow(HDMRegistries.Keys.MOMENT);
                moments.entrySet().stream()
                        .map(Map.Entry::getValue)
                        .filter(moment -> moment instanceof TorchGodMoment)
                        .map(moment -> (TorchGodMoment) moment)
                        .filter(torchGodMoment -> torchGodMoment.mixTorchCount() <= torchGroup.size())
                        .findFirst()
                        .ifPresent(torchGodMoment ->  {
                            MomentInstance.create(TMMoments.TORCH_GOD,serverLevel,startPos,serverPlayer,instance -> {
                                if (instance instanceof TorchGodInstance torchGodInstance) {
                                    torchGodInstance.bindTorchGroup(torchGroup);
                                }
                            });
                        });
            }
        }
    }
}
