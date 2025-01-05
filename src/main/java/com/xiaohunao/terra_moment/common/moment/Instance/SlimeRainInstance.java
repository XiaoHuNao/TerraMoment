package com.xiaohunao.terra_moment.common.moment.Instance;

import com.xiaohunao.heaven_destiny_moment.common.context.condition.LocationCondition;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMAttachments;
import com.xiaohunao.heaven_destiny_moment.common.moment.Moment;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentState;
import com.xiaohunao.terra_moment.common.init.TMMomentTypes;
import com.xiaohunao.terra_moment.common.moment.SlimeRainMoment;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import org.confluence.terraentity.entity.boss.KingSlime;
import org.confluence.terraentity.init.TEEntities;

import java.util.UUID;

public class SlimeRainInstance extends MomentInstance<SlimeRainMoment> {
    public boolean canSpawnSlimeKing = false;
    //-1 noExists  0 death  1 Exists
    private boolean isSlimeKingExists = false;

    public SlimeRainInstance(Level level, ResourceKey<Moment<?>> momentKey) {
        super(TMMomentTypes.SLIME_RAIN.get(), level, momentKey);
    }

    public SlimeRainInstance(UUID uuid, Level level, ResourceKey<Moment<?>> momentKey) {
        super(TMMomentTypes.SLIME_RAIN.get(), uuid, level, momentKey);
    }

    @Override
    public void finalizeSpawn(Entity entity) {
        if (entity.level().isClientSide) return;

        BlockPos blockPos = entity.blockPosition();
        LocationCondition locationCondition = LocationCondition.Builder.isCanSeeSky(true).build();

        for (int i = 0; i < 10; i++) {
            BlockPos pos = blockPos.offset(0, 20, 0);
            if (locationCondition.matches((ServerLevel) entity.level(),pos)) {
                entity.setPos(pos.getX(),pos.getY(),pos.getZ());
            }
        }
    }


    @Override
    protected void ongoing() {
        if (canSpawnSlimeKing){
            Player randomPlayer = getRandomPlayer();
            if (randomPlayer != null){
                KingSlime kingSlime = TEEntities.KING_SLIME.get().create(level);
                if (kingSlime != null) {
                    BlockPos pos = NaturalSpawner.getRandomPosWithin(level, level.getChunkAt(randomPlayer.blockPosition()));
                    kingSlime.setPos(pos.getX(),pos.getY(),pos.getZ());
                    kingSlime.setData(HDMAttachments.MOMENT_ENTITY,kingSlime.getData(HDMAttachments.MOMENT_ENTITY).setUid(uuid));
                    level.addFreshEntity(kingSlime);
                    isSlimeKingExists = true;
                    canSpawnSlimeKing = false;
                }
            }
        }

        moment().ifPresent(slimeRainMoment -> {
            if (getData(HDMAttachments.MOMENT_KILL_ENTITY).getCounter() >= slimeRainMoment.requiredKills() && !isSlimeKingExists) {
                canSpawnSlimeKing = true;
            }
        });
    }

    @Override
    public void livingDeath(LivingEntity entity) {
        if (entity instanceof KingSlime) {
            setState(MomentState.VICTORY);
        }
    }
}
