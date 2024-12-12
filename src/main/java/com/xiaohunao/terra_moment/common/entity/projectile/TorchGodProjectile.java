package com.xiaohunao.terra_moment.common.entity.projectile;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMEntities;
import com.xiaohunao.terra_moment.common.particle.options.TorchGodParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.mesdag.particlestorm.PSGameClient;
import org.mesdag.particlestorm.particle.ParticleEmitter;

public class TorchGodProjectile extends AbstractHurtingProjectile {
    public static final TorchGodParticleOptions GLIMMER = new TorchGodParticleOptions(new Vector3f(255.0F / 255.0F, 128.0F / 255.0F, 16.0F / 255.0F), 1.5F);

    private ParticleEmitter emitter;

    public TorchGodProjectile(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public TorchGodProjectile(double x, double y, double z, Level level) {
        super(TMEntities.TORCH_GOD.get(), x, y, z, level);
        this.setNoGravity(true);
    }

    public TorchGodProjectile(Vec3 vec3, Level level) {
        super(TMEntities.TORCH_GOD.get(), vec3.x(), vec3.y(), vec3.z(), level);
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
    protected @Nullable ParticleOptions getTrailParticle() {
        return null;
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

        if (level().isClientSide && emitter == null) {
            ParticleEmitter particleEmitter = new ParticleEmitter(level(), position(), TerraMoment.asResource("torch_god"));
            particleEmitter.attached = this;
            this.emitter = particleEmitter;
            PSGameClient.LOADER.addEmitter(emitter, false);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(this.damageSources().magic(), 2.0F);
    }
}
