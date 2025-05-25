package com.xiaohunao.terra_moment.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class PatrolSpawnEvent extends Event implements ICancellableEvent {
    private final ServerLevel level;
    private final BlockPos initialSpawnPos;
    private final Player targetPlayer;
    private final int patrolSize;


    public PatrolSpawnEvent(ServerLevel level, BlockPos spawnPos, Player player, int patrolSize) {
        this.level = level;
        this.initialSpawnPos = spawnPos;
        this.targetPlayer = player;
        this.patrolSize = patrolSize;
    }

    public ServerLevel getLevel() {
        return level;
    }


    public BlockPos getInitialSpawnPos() {
        return initialSpawnPos;
    }


    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public int getPatrolSize() {
        return patrolSize;
    }
}