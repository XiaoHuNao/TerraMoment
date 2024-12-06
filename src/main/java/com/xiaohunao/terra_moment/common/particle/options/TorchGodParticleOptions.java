package com.xiaohunao.terra_moment.common.particle.options;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.terra_moment.common.init.TMParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;



public record TorchGodParticleOptions(Vector3f color,float scale) implements ParticleOptions {
    public static final MapCodec<TorchGodParticleOptions> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(TorchGodParticleOptions::color),
            Codec.FLOAT.fieldOf("scale").forGetter(TorchGodParticleOptions::scale)
    ).apply(instance,TorchGodParticleOptions::new));


    public static final StreamCodec<RegistryFriendlyByteBuf, TorchGodParticleOptions> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(ExtraCodecs.VECTOR3F),TorchGodParticleOptions::color,
            ByteBufCodecs.FLOAT,TorchGodParticleOptions::scale,
            TorchGodParticleOptions::new
    );

    @Override
    public @NotNull ParticleType<?> getType() {
        return TMParticleTypes.TORCH_GOD.get();
    }
}
