package com.xiaohunao.terra_moment.common.moment.Instance;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.xiaohunao.heaven_destiny_moment.common.automation.AutomationContext;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMMomentTypes;
import com.xiaohunao.heaven_destiny_moment.common.moment.*;
import com.xiaohunao.terra_moment.common.entity.projectile.TorchGodProjectile;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import com.xiaohunao.terra_moment.common.moment.TorchGodMoment;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TorchGodInstance extends MomentInstance {
    public final Map<BlockPos,Integer> attackMap = Maps.newHashMap();

    public int totalAttacksNeeded = 0;

    public List<BlockPos> unlit = Lists.newArrayList();
    public List<BlockPos> lit = Lists.newArrayList();
    public ImmutableSet<BlockPos> torchGroup;




    public TorchGodInstance(Level level, IMoment moment) {
        super(TMMomentTypes.TORCH_GOD.get(), level, moment);
    }

    public TorchGodInstance(UUID uuid, Level level, IMoment moment) {
        super(TMMomentTypes.TORCH_GOD.get(), uuid, level, moment);
    }

    @Override
    public void ongoing() {
        if (torchGroup != null) {
            if (!checkTorchGroup()) {
                setState(MomentState.LOSE);
            }
            Player randomPlayer = getPlayerListManager().getRandomPlayer();
            if (randomPlayer != null && tick % 20 == 0){
                attackPlayer(randomPlayer);
            }

        }else {
            setState(MomentState.END);
        }
    }

    private boolean checkTorchGroup(){
        for (BlockPos pos : torchGroup) {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.isAir()) {
                boolean contains = unlit.contains(pos);
                if (contains) {
                    continue;
                }else {
                    return false;
                }
            }

            return blockState.getBlock() instanceof TorchBlock;
        }
        return true;
    }


    public void attackPlayer(Player player){
        TorchGodMoment torchGodMoment = (TorchGodMoment)moment;

        int multiAttack = torchGodMoment.multiAttackBarrage().getAmount();

        int totalAttacksNeeded = torchGodMoment.totalAttacksNeeded();

        if (this.totalAttacksNeeded >= totalAttacksNeeded && unlit.size() == torchGroup.size()) {
            setState(MomentState.VICTORY);
            return;
        }

        int maxAttacksPerPos = totalAttacksNeeded / torchGroup.size();
        int extraAttacksForLastPos = totalAttacksNeeded % torchGroup.size();


        for (int i = 0; i < multiAttack; i++) {
            if (!lit.isEmpty()){
                BlockPos blockPos = lit.get(level.random.nextInt(lit.size()));

                int attacksCount = attackMap.getOrDefault(blockPos, 0);
                attackMap.put(blockPos, ++attacksCount);

                int maxAttacksForThisPos = maxAttacksPerPos;
                if (extraAttacksForLastPos > 0 && lit.indexOf(blockPos) == lit.size() - 1) {
                    maxAttacksForThisPos += extraAttacksForLastPos;
                }

                TorchGodProjectile torchGodProjectile = new TorchGodProjectile(blockPos.getCenter(), level);
                Vec3 direction = player.getEyePosition().subtract(blockPos.getCenter()).normalize();
                torchGodProjectile.setDeltaMovement(direction);
                level.addFreshEntity(torchGodProjectile);
                this.totalAttacksNeeded++;

                if (attacksCount >= maxAttacksForThisPos) {
                    level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
                    unlit.add(blockPos);
                    lit.remove(blockPos);
                }
            }
        }

    }


    @Override
    protected void victory() {
        super.victory();
        unlit.forEach(pos -> {
            level.setBlock(pos,Blocks.TORCH.defaultBlockState(),3);
        });
    }

    public static Set<BlockPos> updateTorchGroup(BlockPos startPos, Level level, int count) {
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = Sets.newHashSet();
        Set<BlockPos> torchGroup = Sets.newHashSet();


        queue.add(startPos);
        visited.add(startPos);

        if (level.getBlockState(startPos).getBlock() instanceof BaseTorchBlock) {
            torchGroup.add(startPos);
        }

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();

            if (level.getBlockState(currentPos).getBlock() instanceof BaseTorchBlock) {
                torchGroup.add(currentPos);

                if (torchGroup.size() >= count) {
                    return torchGroup;
                }

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            if (dx == 0 && dy == 0 && dz == 0) continue;

                            mutablePos.set(currentPos.getX() + dx, currentPos.getY() + dy, currentPos.getZ() + dz);

                            if (!visited.contains(mutablePos)) {
                                visited.add(mutablePos.immutable());
                                queue.add(mutablePos.immutable());
                            }
                        }
                    }
                }
            }
        }
        return torchGroup;
    }

    public void bindTorchGroup(Set<BlockPos> group){
        this.torchGroup = ImmutableSet.copyOf(group);
        this.lit =Lists.newArrayList(group);
    }

    @Override
    public boolean canCreate(AutomationContext context) {
        Level level = context.getLevel();

        MomentInstanceManager momentInstanceManager = MomentInstanceManager.of(level);
        Collection<MomentInstance> momentInstances = momentInstanceManager.getMomentInstances(TMMomentTypes.TORCH_GOD.get());
        return momentInstances.stream().allMatch(instance -> {
            if (instance instanceof TorchGodInstance torchGodInstance) {
                if (context.getBlockPos().isEmpty()){
                    return false;
                }

                return !torchGodInstance.torchGroup.contains(context.getBlockPos().get());
            }
            return true;
        });
    }
}
