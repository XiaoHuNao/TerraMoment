package com.xiaohunao.terra_moment.common.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.xiaohunao.terra_moment.common.event.PatrolSpawnEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PatrolSpawner.class)
public class PatrolSpawnerMixin {
    @Unique
    private Player terra_moment$capturedPlayer;
    @Unique
    private BlockPos terra_moment$capturedPos;

    @Inject(method = "tick", at = @At("HEAD"))
    private void release(ServerLevel level, boolean spawnEnemies, boolean spawnFriendlies, CallbackInfoReturnable<Integer> cir) {
        if (terra_moment$capturedPlayer != null && terra_moment$capturedPlayer.isRemoved()) {
            this.terra_moment$capturedPlayer = null;
            this.terra_moment$capturedPos = null;
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/PatrolSpawner;spawnPatrolMember(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;Z)Z", ordinal = 0))
    private void capturePatrolInfo(ServerLevel level, boolean spawnEnemies, boolean spawnFriendlies, CallbackInfoReturnable<Integer> cir, @Local Player player, @Local BlockPos.MutableBlockPos blockpos$mutableblockpos) {
        this.terra_moment$capturedPlayer = player;
        this.terra_moment$capturedPos = blockpos$mutableblockpos.immutable();
    }

    @Inject(method = "tick", at = @At("RETURN"), cancellable = true)
    private void onPatrolSpawn(ServerLevel level, boolean spawnEnemies, boolean spawnFriendlies, CallbackInfoReturnable<Integer> cir) {
        int spawnedCount = cir.getReturnValue();
        if (spawnedCount > 0 && this.terra_moment$capturedPlayer != null && this.terra_moment$capturedPos != null) {
            PatrolSpawnEvent event = new PatrolSpawnEvent(
                    level,
                    this.terra_moment$capturedPos,
                    this.terra_moment$capturedPlayer,
                    spawnedCount
            );

            PatrolSpawnEvent cancelled = NeoForge.EVENT_BUS.post(event);

            if (cancelled.isCanceled()) {
                cir.setReturnValue(0);
            }

            this.terra_moment$capturedPlayer = null;
            this.terra_moment$capturedPos = null;
        }
    }
}
