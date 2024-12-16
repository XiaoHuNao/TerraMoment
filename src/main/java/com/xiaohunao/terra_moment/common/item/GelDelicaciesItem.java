package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
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

public class GelDelicaciesItem extends EventConsumableItem{
    public GelDelicaciesItem() {
        super(TMMoments.SLIME_RAIN);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (level instanceof ServerLevel serverLevel){
            long dayTime = serverLevel.getDayTime();
            long nextTime = dayTime + ((1000 - dayTime % 24000 + 24000) % 24000);
            serverLevel.setDayTime(nextTime);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TimeSyncPayload(nextTime));
            MomentInstance.create(momentResourceKey, serverLevel,player.blockPosition(), (ServerPlayer) player);
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
