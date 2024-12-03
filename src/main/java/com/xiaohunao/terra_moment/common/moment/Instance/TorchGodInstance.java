package com.xiaohunao.terra_moment.common.moment.Instance;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.common.init.TMConfig;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Blocks;

import java.util.*;

public class TorchGodInstance extends MomentInstance {
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

            torchGroup.forEach(pos -> {
                BlockPos pos1 = pos.offset(0, 1, 0);
                level.setBlock(pos1, Blocks.DIAMOND_BLOCK.defaultBlockState(),19);
            });
        }else {
            setState(MomentState.END);
        }
    }
    private boolean checkTorchGroup(){
        UnmodifiableIterator<BlockPos> iterator = torchGroup.iterator();
        if (iterator.hasNext()) {
            BlockPos next = iterator.next();
            Set<BlockPos> group = updateTorchGroup(next, level);
            if (group.size() >= TMConfig.CONFIG.requiredTorchCount.get()){
                this.torchGroup = ImmutableSet.copyOf(group);
                return true;
            }else {
                return false;
            }
        }
        return false;
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
