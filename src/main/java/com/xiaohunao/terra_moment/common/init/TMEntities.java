package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.entity.projectile.TorchGodProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(Registries.ENTITY_TYPE, TerraMoment.MODID);


    public static final DeferredHolder<EntityType<?>, EntityType<TorchGodProjectile>> TORCH_GOD = register(
            "torch_god", EntityType.Builder.<TorchGodProjectile>of(TorchGodProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).eyeHeight(0.13F)
                    .clientTrackingRange(4).updateInterval(20)
    );


    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String key, EntityType.Builder<T> builder) {
        return ENTITY_TYPE.register(key, () -> builder.build(key));
    }
}
