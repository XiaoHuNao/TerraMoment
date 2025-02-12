package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.moment.MomentManager;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.network.TimeSyncPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class BloodyTearItem extends EventConsumableItem{
    public BloodyTearItem() {
        super(TMMoments.BLOOD_MOON);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (level instanceof ServerLevel serverLevel){
            long dayTime = serverLevel.getDayTime();
            int currentMoonPhase = level.getMoonPhase();
            long cyclesToNextFullMoon = (8 - currentMoonPhase) % 8;
            long nextFullMoonTime = (dayTime / 24000L) * 24000L + cyclesToNextFullMoon * 24000L + 14000L;
            serverLevel.setDayTime(nextFullMoonTime);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TimeSyncPayload(nextFullMoonTime));
            MomentManager.of(level).createMomentInstance(momentResourceKey,player.blockPosition(), (ServerPlayer) player);
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
