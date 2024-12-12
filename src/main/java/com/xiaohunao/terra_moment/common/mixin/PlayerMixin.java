package com.xiaohunao.terra_moment.common.mixin;


import com.mojang.datafixers.util.Either;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentManager;
import com.xiaohunao.terra_moment.common.moment.BloodMoonMoment;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class PlayerMixin {
    @Inject(method = "startSleepInBed",at = @At("HEAD"),cancellable = true)
    public void startSleepInBed(BlockPos bedPos, CallbackInfoReturnable<Either<Player.BedSleepingProblem, Unit>> cir){
        ServerPlayer player = (ServerPlayer) (Object) this;
        MomentManager momentManager = MomentManager.of(player.level());
        boolean isCanSleep = momentManager.getImmutableRunMoments().values().stream()
                .map(momentInstance -> momentInstance.moment().orElse(null))
                .allMatch(moment -> !(moment instanceof BloodMoonMoment) || ((BloodMoonMoment) moment).isCanSleep());

        if (!isCanSleep) {
            cir.setReturnValue(Either.right(Unit.INSTANCE));
        }

    }
}
