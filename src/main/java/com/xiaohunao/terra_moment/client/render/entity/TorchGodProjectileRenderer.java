package com.xiaohunao.terra_moment.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.xiaohunao.terra_moment.common.entity.projectile.TorchGodProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TorchGodProjectileRenderer extends EntityRenderer<TorchGodProjectile> {

	public TorchGodProjectileRenderer(EntityRendererProvider.Context pContext) {
		super(pContext);
	}

	@Override
	public void render(TorchGodProjectile p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

	}

	@Override
	public ResourceLocation getTextureLocation(TorchGodProjectile pEntity) {
		return InventoryMenu.BLOCK_ATLAS;
	}
}