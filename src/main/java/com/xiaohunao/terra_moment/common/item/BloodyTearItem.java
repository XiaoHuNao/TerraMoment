package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstanceBuilder;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstanceManager;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.terra_moment.common.network.TimeSyncPayload;
import com.xiaohunao.terra_moment.common.utils.time.MinecraftTimeUtils;
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

        if (level instanceof ServerLevel serverLevel) {
            MinecraftTimeUtils.jumpToNextFullMoon(serverLevel);

            MomentInstanceBuilder.create(level, holder.get(), player.blockPosition(), (ServerPlayer) player);

            return InteractionResultHolder.consume(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }
}
