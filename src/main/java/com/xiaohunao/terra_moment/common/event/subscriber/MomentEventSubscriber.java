package com.xiaohunao.terra_moment.common.event.subscriber;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;


@EventBusSubscriber
public class MomentEventSubscriber {

    @SubscribeEvent
    public static void torchGod(BlockEvent.EntityPlaceEvent event) {
//        LevelAccessor level = event.getLevel();
//        Entity entity = event.getEntity();
//        if (level instanceof ServerLevel serverLevel && entity instanceof ServerPlayer serverPlayer){
//            BlockState placedBlock = event.getPlacedBlock();
//            if (placedBlock.getBlock() instanceof BaseTorchBlock) {
//                BlockPos startPos = event.getPos();
//
//                Set<BlockPos> torchGroup = TorchGodInstance.updateTorchGroup(startPos, serverLevel,50);
//
//                Registry<Moment> moments = level.registryAccess().registryOrThrow(HDMRegistries.Keys.MOMENT);
//                moments.entrySet().stream()
//                        .map(Map.Entry::getValue)
//                        .filter(moment -> moment instanceof TorchGodMoment)
//                        .map(moment -> (TorchGodMoment) moment)
//                        .filter(torchGodMoment -> torchGodMoment.mixTorchCount() <= torchGroup.size())
//                        .findFirst()
//                        .ifPresent(torchGodMoment ->  {
//                            MomentInstanceBuilder.builder((Level) level, TMMoments.TORCH_GOD.get())
//                                    .pos(startPos)
//                                    .player(serverPlayer)
//                                    .modifier(instance -> {
//                                        if (instance instanceof TorchGodInstance torchGodInstance) {
//                                            torchGodInstance.bindTorchGroup(torchGroup);
//                                        }
//                                    })
//                                    .build();
//                        });
//            }
//        }
    }
}
