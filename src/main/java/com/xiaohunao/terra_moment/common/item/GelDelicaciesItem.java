package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.InvertCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.LocationCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.WorldUniqueMomentCondition;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.DifficultyCondition;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstanceBuilder;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.xhn_lib.common.util.MinecraftTimeUtils;
import com.xiaohunao.xhn_lib.common.util.data.MinecraftTimeNode;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GelDelicaciesItem extends EventConsumableItem{
    public GelDelicaciesItem() {
        super(TMMoments.SLIME_RAIN);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level instanceof ServerLevel serverLevel) {
            MinecraftTimeUtils.jumpToNextTimeNode(serverLevel, MinecraftTimeNode.CMD_DAY);

            MomentInstanceBuilder.skipConditionsExcept(level,holder.get(), player.blockPosition(), (ServerPlayer) player,
                    WorldUniqueMomentCondition.DEFAULT,
                    LocationCondition.Builder.inDimension(Level.OVERWORLD).build(),
                    InvertCondition.of(DifficultyCondition.PEACEFUL)
            );

            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            return InteractionResultHolder.consume(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }
}
