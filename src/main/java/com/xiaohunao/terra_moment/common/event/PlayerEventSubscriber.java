package com.xiaohunao.terra_moment.common.event;

import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.entity.projectile.TorchGodProjectile;
import com.xiaohunao.terra_moment.common.init.ParticleRenderTypes;
import com.xiaohunao.terra_moment.common.init.TMEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = TerraMoment.MODID)
public class PlayerEventSubscriber {
    @SubscribeEvent
    public static void onPlayerInteractRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        InteractionHand hand = event.getHand();
        Player player = event.getEntity();
        if (hand != InteractionHand.MAIN_HAND) return;

        if (level instanceof ServerLevel serverLevel) {
            if (player.isShiftKeyDown()){
//                TorchGodProjectile torchGodProjectile = TMEntities.TORCH_GOD.get().create(level);
//                if (torchGodProjectile != null) {
//                    torchGodProjectile.setPos(player.getEyePosition());
//                    torchGodProjectile.shootFromRotation(player,player.getXRot(), player.getYRot(),0.0F,1.0F,1.0F);
//                    level.addFreshEntity(torchGodProjectile);
//                }
////
//                BlockPos pos = event.getPos();
//                for (int i = 0; i < 5; i++) {
//                    for (int j = 0; j < 10; j++) {
//                        BlockPos torchPos = pos.offset(i, 0, j); // 使用offset方法根据偏移量计算新的坐标
////                        serverLevel.setBlock(torchPos, Blocks.TORCH.defaultBlockState(),19); // 设置火把方块
//                        serverLevel.setBlock(torchPos, Blocks.AIR.defaultBlockState(),19); // 设置火把方块
//                    }
//                }
            }

        } else {
            if (player.isShiftKeyDown()){

//                level.addParticle(TorchGodProjectile.GLIMMER,eyePosition.x,eyePosition.y,eyePosition.z,0,0.5F,0);
            }
        }
    }
}