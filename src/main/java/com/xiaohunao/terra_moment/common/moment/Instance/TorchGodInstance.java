package com.xiaohunao.terra_moment.common.moment.Instance;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmountContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.compress.utils.Lists;

import java.util.*;

public class TorchGodInstance extends MomentInstance<TorchGodMoment> {
    public ImmutableSet<BlockPos> torchGroup;

    public TorchGodInstance(Level level, ResourceKey<Moment> momentKey) {
        super(TMMomentTypes.TORCH_GOD.get(), level, momentKey);
    }

    public TorchGodInstance(UUID uuid, Level level, ResourceKey<Moment> momentKey) {
        super(TMMomentTypes.TORCH_GOD.get(), uuid, level, momentKey);
    }

    @Override
    public void tick() {
        if (torchGroup != null) {
            if (!checkTorchGroup()) setState(MomentState.LOSE);
            Player randomPlayer = getRandomPlayer();

            attackPlayer(randomPlayer);



        }else {
            setState(MomentState.END);
        }
    }

    private boolean checkTorchGroup(){
        UnmodifiableIterator<BlockPos> iterator = torchGroup.iterator();
        if (iterator.hasNext()) {
            BlockPos next = iterator.next();
            Set<BlockPos> group = updateTorchGroup(next, level);

            return moment().map(torchGodMoment -> {
                        if (torchGodMoment.mixTorchCount() <= group.size()) {
                            this.torchGroup = ImmutableSet.copyOf(group);
                            return true;
                        } else {
                            return false;
                        }
                    }).orElse(false);

        }
        return false;
    }

    public void attackPlayer(Player player){
        moment().ifPresent(moment -> {
            int amount = moment.multiAttackBarrage().getAmount();
            //TODO 火把神弹幕攻击玩家,这里临时用箭代替

            ImmutableList<BlockPos> posList = ImmutableList.copyOf(torchGroup);
            for (int i = 0; i < amount; i++) {
                Vec3 blockPos = posList.get(level.random.nextInt(posList.size())).getCenter();

                Arrow arrow = new Arrow(level, blockPos.x, blockPos.y, blockPos.z, Items.ARROW.getDefaultInstance(), null);
                level.addFreshEntity(arrow);
                arrow.shoot(player.getX(),player.getY(),player.getZ(),1.0F,1.0F);
            }
        });
    }


    public static Set<BlockPos> updateTorchGroup(BlockPos startPos,Level level){
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = Sets.newHashSet();
        Set<BlockPos> torchGroup = Sets.newHashSet();

        queue.add(startPos);
        visited.add(startPos);

        if (level.getBlockState(startPos).getBlock() instanceof BaseTorchBlock) {
            torchGroup.add(startPos);
        }

        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();

            if (level.getBlockState(currentPos).getBlock() instanceof BaseTorchBlock) {
                torchGroup.add(currentPos);

                for (Direction dir : Direction.values()) {
                    BlockPos neighborPos = currentPos.relative(dir);

                    if (!visited.contains(neighborPos)) {
                        visited.add(neighborPos);
                        queue.add(neighborPos);
                    }
                }
            }
        }
        return torchGroup;
    }

    public void bindTorchGroup(Set<BlockPos> group){
        this.torchGroup = ImmutableSet.copyOf(group);
    }
}
