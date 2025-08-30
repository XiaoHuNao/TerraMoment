package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.automation.AutomationContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstanceBuilder;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.xhn_lib.common.util.MinecraftTimeUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BloodyTearItem extends EventConsumableItem{
    public BloodyTearItem() {
        super(TMMoments.BLOOD_MOON);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level instanceof ServerLevel serverLevel) {
            MinecraftTimeUtils.jumpToNextFullMoon(serverLevel);


            MomentInstanceBuilder.skipConditionsExceptRun(holder.get(),
                    new AutomationContext.Builder(level)
                            .addPlayer(player)
                            .addBlockPos(player.blockPosition())
                            .build()
            );

            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            return InteractionResultHolder.consume(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }
}
