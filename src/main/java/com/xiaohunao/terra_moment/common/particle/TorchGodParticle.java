package com.xiaohunao.terra_moment.common.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.xiaohunao.terra_moment.common.init.ParticleRenderTypes;
import com.xiaohunao.terra_moment.common.particle.options.TorchGodParticleOptions;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TorchGodParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected TorchGodParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites, TorchGodParticleOptions options) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        this.setSpriteFromAge(sprites);
        this.rCol = options.color().x();
        this.gCol = options.color().y();
        this.bCol = options.color().z();
    }

    public float getQuadSize(float pScaleFactor) {
        return this.quadSize - this.quadSize * (((float)this.age + pScaleFactor) / (float)this.lifetime);
    }


    public void tick() {
        super.tick();
        this.setSpriteFromAge(sprites);
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return LightTexture.FULL_BRIGHT;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderTypes.TORCH_GOD;
    }
    public static class Provider implements ParticleProvider<TorchGodParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }
        @Nullable
        @Override
        public Particle createParticle(@NotNull TorchGodParticleOptions options, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed){
            return new TorchGodParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprites, options);
        }
    }
}
