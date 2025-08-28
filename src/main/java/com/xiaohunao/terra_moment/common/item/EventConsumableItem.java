package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstanceBuilder;
import com.xiaohunao.xhn_lib.api.register.FlexibleHolder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EventConsumableItem extends Item {
    protected FlexibleHolder<Moment,?> holder;
    public EventConsumableItem(FlexibleHolder<Moment,?> holder) {
        super(new Properties());
        this.holder = holder;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (level instanceof ServerLevel serverLevel){
            MomentInstanceBuilder.create(serverLevel,holder.get(),player.blockPosition());
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
