package com.xiaohunao.terra_moment.common.entity.projectile;

import com.xiaohunao.terra_moment.common.init.TMEntities;
import com.xiaohunao.terra_moment.common.particle.options.TorchGodParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.Random;

public class TorchGodProjectile extends AbstractHurtingProjectile {
    public static final TorchGodParticleOptions GLIMMER = new TorchGodParticleOptions(new Vector3f(255.0F / 255.0F, 128.0F / 255.0F, 16.0F / 255.0F),1.5F);

    public TorchGodProjectile(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public TorchGodProjectile(double x, double y, double z, Level level) {
        super(TMEntities.TORCH_GOD.get(), x, y, z, level);
        this.setNoGravity(true);
    }
    public TorchGodProjectile(BlockPos pos, Level level) {
        super(TMEntities.TORCH_GOD.get(),pos.getX(),pos.getY(),pos.getZ(), level);
        this.setNoGravity(true);
    }

    public TorchGodProjectile(Level level) {
        super(TMEntities.TORCH_GOD.get(), level);
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (tickCount > 5 * 20) {
            discard();
            return;
        }



        Vec3 oldPosition = new Vec3(getX(), getY(), getZ());
        Random random = new Random();

        if (level().isClientSide()) {
            double deltaX = getX() - oldPosition.x;
            double deltaY = getY() - oldPosition.y;
            double deltaZ = getZ() - oldPosition.z;
            for (double i = 0; i < 9; i ++) {
                double coeff = i / 9.0;
                level().addParticle(GLIMMER, oldPosition.x + deltaX * coeff, oldPosition.y + deltaY * coeff, oldPosition.z + deltaZ * coeff, 01.1f*(random.nextFloat()-0.5f), 01.1f*(random.nextFloat()-0.5f), 01.1f*(random.nextFloat()-0.5f));
            }
        }
    }

}
