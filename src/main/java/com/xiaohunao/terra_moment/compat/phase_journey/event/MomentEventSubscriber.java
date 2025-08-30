package com.xiaohunao.terra_moment.compat.phase_journey.event;

import com.xiaohunao.heaven_destiny_moment.common.event.MomentEvent;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.confluence.phase_journey.common.util.PhaseUtils;

@EventBusSubscriber
public class MomentEventSubscriber {
    @SubscribeEvent
    public static void onMoment(MomentEvent.Victory event) {
        MomentInstance momentInstance = event.getMomentInstance();
        String victoryPath = momentInstance.getRegistryName().getPath() + "_victory";

        if (momentInstance.getLevel() instanceof ServerLevel serverLevel) {
            PhaseUtils.achieveLevelPhase(serverLevel, ResourceLocation.tryBuild("confluence", victoryPath), true);
        }
    }

}

