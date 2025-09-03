package com.xiaohunao.terra_moment.common.item;

import com.xiaohunao.heaven_destiny_moment.common.automation.AutomationContext;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.common.*;
import com.xiaohunao.heaven_destiny_moment.common.context.condition.level.DifficultyCondition;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstanceBuilder;
import com.xiaohunao.terra_moment.common.init.TMMoments;
import com.xiaohunao.xhn_lib.common.util.MinecraftTimeUtils;
import com.xiaohunao.xhn_lib.common.util.data.MinecraftTimeNode;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class GoblinBattleStandard extends EventConsumableItem{
    public GoblinBattleStandard() {
        super(TMMoments.GOBLIN_ARMY);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level instanceof ServerLevel serverLevel) {
            MinecraftTimeUtils.jumpToNextTimeNode(serverLevel, MinecraftTimeNode.CMD_DAY);

            MomentInstanceBuilder.skipConditionsExceptRun(holder.get(),
                    new AutomationContext.Builder(level)
                            .addPlayer(player)
                            .addBlockPos(player.blockPosition())
                            .build(),
                    OrCondition.of(ModLoadedCondition.of("confluence_dimension_patch"),
                            List.of(LocationCondition.Builder.inDimension(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("confluence_dimension_patch:otherworld"))).build()),
                            List.of(LocationCondition.Builder.inDimension(Level.OVERWORLD).build())
                    ),
                    LocationCondition.Builder.inDimension(Level.OVERWORLD).build(),
                    InvertCondition.of(DifficultyCondition.PEACEFUL),
                    WorldUniqueMomentCondition.DEFAULT
            );

            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            return InteractionResultHolder.consume(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }
}
