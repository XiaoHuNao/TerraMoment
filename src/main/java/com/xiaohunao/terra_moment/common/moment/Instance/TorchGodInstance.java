package com.xiaohunao.terra_moment.common.moment.Instance;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.xiaohunao.heaven_destiny_moment.common.context.amount.RandomAmountContext;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.common.entity.projectile.TorchGodProjectile;
import com.xiaohunao.terra_moment.common.init.TMEntities;
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

            if(tick % 20 == 0) {
                attackPlayer(randomPlayer);
            }

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
            ImmutableList<BlockPos> posList = ImmutableList.copyOf(torchGroup);
            for (int i = 0; i < amount; i++) {
                BlockPos blockPos = posList.get(level.random.nextInt(posList.size()));

                TorchGodProjectile torchGodProjectile = new TorchGodProjectile(blockPos,level);
                Vec3 playerPosition = player.position();
                Vec3 direction = playerPosition.subtract(Vec3.atCenterOf(blockPos)).normalize();
                torchGodProjectile.setDeltaMovement(direction);
                level.addFreshEntity(torchGodProjectile);
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
