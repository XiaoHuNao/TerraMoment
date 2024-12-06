package com.xiaohunao.terra_moment.common.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.particle.TorchGodParticle;
import com.xiaohunao.terra_moment.common.particle.options.TorchGodParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class TMParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, TerraMoment.MODID);

    public static final DeferredHolder<ParticleType<?>,ParticleType<TorchGodParticleOptions>> TORCH_GOD = register("torch_god", false, TorchGodParticleOptions.CODEC, TorchGodParticleOptions.STREAM_CODEC);


    private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> register(String id,boolean overrideLimiter,MapCodec<T> mapCodec, StreamCodec<? super RegistryFriendlyByteBuf, T>streamCodec){
        return PARTICLE_TYPE.register(id, () -> new ParticleType<>(overrideLimiter) {
            @Override
            @NotNull
            public MapCodec<T> codec(){
                return mapCodec;
            }

            @Override
            @NotNull
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec(){
                return streamCodec;
            }
        });
    }
}
