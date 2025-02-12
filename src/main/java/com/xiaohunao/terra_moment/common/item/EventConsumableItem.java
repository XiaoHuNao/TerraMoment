package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentManager;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EventConsumableItem extends Item {
    protected ResourceKey<Moment<?>> momentResourceKey;
    public EventConsumableItem(ResourceKey<Moment<?>> momentResourceKey) {
        super(new Properties());
        this.momentResourceKey = momentResourceKey;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (level instanceof ServerLevel serverLevel){
            MomentManager.of(level).createMomentInstance(momentResourceKey,player.blockPosition(), (ServerPlayer) player);
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
