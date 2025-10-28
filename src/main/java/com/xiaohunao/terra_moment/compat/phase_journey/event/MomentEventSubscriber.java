package com.xiaohunao.terra_moment.compat.phase_journey.event;

import com.xiaohunao.heaven_destiny_moment.common.event.MomentEvent;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.phase_journey.common.phase.PhaseType;
import com.xiaohunao.phase_journey.common.util.PhaseUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;


@EventBusSubscriber
public class MomentEventSubscriber {
    @SubscribeEvent
    public static void onMoment(MomentEvent.Victory event) {
        MomentInstance momentInstance = event.getMomentInstance();
        String victoryPath = momentInstance.getRegistryName().getPath() + "_victory";

        PhaseType.LEVEL.applyOrRevokePhase(momentInstance.getLevel(),ResourceLocation.tryBuild("confluence", victoryPath), true);
    }

}

