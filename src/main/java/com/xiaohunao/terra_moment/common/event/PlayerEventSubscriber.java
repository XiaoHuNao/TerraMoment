package com.xiaohunao.terra_moment.common.event;

import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.xhn_lib.api.register.FlexibleHolder;
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

        if (level instanceof ServerLevel serverLevel) {
            if (player.isShiftKeyDown()){
                FlexibleHolder<Moment, ?> bloodMoon = TMMoments.BLOOD_MOON;
                FlexibleHolder<Moment, ?> slimeRain = TMMoments.SLIME_RAIN;
                FlexibleHolder<Moment, ?> torchGod = TMMoments.TORCH_GOD;
                System.out.println(bloodMoon.get());
                System.out.println(slimeRain.get());
                System.out.println(torchGod.get());

            }
        } else {
            if (player.isShiftKeyDown()){
            }
        }
    }
}