package com.xiaohunao.terra_moment.common.event;

import com.xiaohunao.heaven_destiny_moment.common.moment.MomentManager;
import com.xiaohunao.terra_moment.TerraMoment;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = TerraMoment.MODID)
public class PlayerEventSubscriber {
    @SubscribeEvent
    public static void onPlayerInteractRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        InteractionHand hand = event.getHand();
        Player player = event.getEntity();
        if (hand != InteractionHand.MAIN_HAND) return;

//        if (level instanceof ServerLevel serverLevel) {
//            if (player.isShiftKeyDown()){
//                MomentManager momentManager = MomentManager.of(serverLevel);
//                System.out.println(momentManager);
//
//            }
//        } else {
//            if (player.isShiftKeyDown()){
//            }
//        }
    }
}