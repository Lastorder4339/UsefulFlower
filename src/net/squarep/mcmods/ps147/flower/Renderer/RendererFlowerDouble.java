package net.squarep.mcmods.ps147.flower.Renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.squarep.mcmods.ps147.flower.Flower_Core;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererFlowerDouble implements ISimpleBlockRenderingHandler {

	@Override
	public int getRenderId() {
		return Flower_Core.getInstance().renderIdMap.get(this.getClass()
				.getName());
	}

	private void renderEastFace(final Block par1Block, final double par2,
			final double par4, final double par6, int par8,
			final RenderBlocks renderer) {
		final Tessellator var9 = Tessellator.instance;

		if (renderer.overrideBlockTexture >= 0) {
			par8 = renderer.overrideBlockTexture;
		}

		final int var10 = (par8 & 15) << 4;
		final int var11 = par8 & 240;
		double var12 = (var10 + renderer.renderMinX * 16.0D) / 256.0D;
		double var14 = (var10 + renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
		double var16 = (var11 + 16 - renderer.renderMaxY * 16.0D) / 256.0D;
		double var18 = (var11 + 16 - renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
		double var20;

		if (renderer.flipTexture) {
			var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D) {
			var12 = (var10 + 0.0F) / 256.0F;
			var14 = (var10 + 15.99F) / 256.0F;
		}

		if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D) {
			var16 = (var11 + 0.0F) / 256.0F;
			var18 = (var11 + 15.99F) / 256.0F;
		}

		var20 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;

		if (renderer.uvRotateEast == 2) {
			var12 = (var10 + renderer.renderMinY * 16.0D) / 256.0D;
			var16 = (var11 + 16 - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = (var10 + renderer.renderMaxY * 16.0D) / 256.0D;
			var18 = (var11 + 16 - renderer.renderMaxX * 16.0D) / 256.0D;
			var24 = var16;
			var26 = var18;
			var20 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var24;
		} else if (renderer.uvRotateEast == 1) {
			var12 = (var10 + 16 - renderer.renderMaxY * 16.0D) / 256.0D;
			var16 = (var11 + renderer.renderMaxX * 16.0D) / 256.0D;
			var14 = (var10 + 16 - renderer.renderMinY * 16.0D) / 256.0D;
			var18 = (var11 + renderer.renderMinX * 16.0D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var22;
			var24 = var18;
			var26 = var16;
		} else if (renderer.uvRotateEast == 3) {
			var12 = (var10 + 16 - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = (var10 + 16 - renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
			var16 = (var11 + renderer.renderMaxY * 16.0D) / 256.0D;
			var18 = (var11 + renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		final double var28 = par2 + renderer.renderMinX;
		final double var30 = par2 + renderer.renderMaxX;
		final double var32 = par4 + renderer.renderMinY;
		final double var34 = par4 + renderer.renderMaxY;
		final double var36 = par6 + renderer.renderMinZ;

		if (renderer.enableAO) {
			var9.setBrightness(renderer.brightnessTopLeft);
			var9.addVertexWithUV(var28, var34, var36 - 0.2, var20, var24);
			var9.setBrightness(renderer.brightnessBottomLeft);
			var9.addVertexWithUV(var30, var34, var36 - 0.2, var12, var16);
			var9.setBrightness(renderer.brightnessBottomRight);
			var9.addVertexWithUV(var30, var32, var36 + 0.2, var22, var26);
			var9.setBrightness(renderer.brightnessTopRight);
			var9.addVertexWithUV(var28, var32, var36 + 0.2, var14, var18);
		} else {
			var9.addVertexWithUV(var28, var34, var36 - 0.2, var20, var24);
			var9.addVertexWithUV(var30, var34, var36 - 0.2, var12, var16);
			var9.addVertexWithUV(var30, var32, var36 + 0.3, var22, var26);
			var9.addVertexWithUV(var28, var32, var36 + 0.3, var14, var18);
		}
	}

	@Override
	public void renderInventoryBlock(final Block block, final int metadata,
			final int modelID, final RenderBlocks renderer) {

	}

	/**
	 * Renders the given texture to the west (z-positive) face of the block.
	 * Args: block, x, y, z, texture
	 */
	private void renderWestFace(final Block par1Block, final double par2,
			final double par4, final double par6, int par8,
			final RenderBlocks renderer) {
		final Tessellator var9 = Tessellator.instance;

		if (renderer.overrideBlockTexture >= 0) {
			par8 = renderer.overrideBlockTexture;
		}

		final int var10 = (par8 & 15) << 4;
		final int var11 = par8 & 240;
		double var12 = (var10 + renderer.renderMinX * 16.0D) / 256.0D;
		double var14 = (var10 + renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
		double var16 = (var11 + 16 - renderer.renderMaxY * 16.0D) / 256.0D;
		double var18 = (var11 + 16 - renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
		double var20;

		if (renderer.flipTexture) {
			var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D) {
			var12 = (var10 + 0.0F) / 256.0F;
			var14 = (var10 + 15.99F) / 256.0F;
		}

		if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D) {
			var16 = (var11 + 0.0F) / 256.0F;
			var18 = (var11 + 15.99F) / 256.0F;
		}

		var20 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;

		if (renderer.uvRotateWest == 1) {
			var12 = (var10 + renderer.renderMinY * 16.0D) / 256.0D;
			var18 = (var11 + 16 - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = (var10 + renderer.renderMaxY * 16.0D) / 256.0D;
			var16 = (var11 + 16 - renderer.renderMaxX * 16.0D) / 256.0D;
			var24 = var16;
			var26 = var18;
			var20 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var24;
		} else if (renderer.uvRotateWest == 2) {
			var12 = (var10 + 16 - renderer.renderMaxY * 16.0D) / 256.0D;
			var16 = (var11 + renderer.renderMinX * 16.0D) / 256.0D;
			var14 = (var10 + 16 - renderer.renderMinY * 16.0D) / 256.0D;
			var18 = (var11 + renderer.renderMaxX * 16.0D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var22;
			var24 = var18;
			var26 = var16;
		} else if (renderer.uvRotateWest == 3) {
			var12 = (var10 + 16 - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = (var10 + 16 - renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
			var16 = (var11 + renderer.renderMaxY * 16.0D) / 256.0D;
			var18 = (var11 + renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		final double var28 = par2 + renderer.renderMinX;
		final double var30 = par2 + renderer.renderMaxX;
		final double var32 = par4 + renderer.renderMinY;
		final double var34 = par4 + renderer.renderMaxY;
		final double var36 = par6 + renderer.renderMaxZ;

		if (renderer.enableAO) {
			var9.setBrightness(renderer.brightnessTopLeft);
			var9.addVertexWithUV(var28, var34, var36 - 0.2, var12, var16);
			var9.setBrightness(renderer.brightnessBottomLeft);
			var9.addVertexWithUV(var28, var32, var36 - 0.2, var22, var26);
			var9.setBrightness(renderer.brightnessBottomRight);
			var9.addVertexWithUV(var30, var32, var36 - 0.2, var14, var18);
			var9.setBrightness(renderer.brightnessTopRight);
			var9.addVertexWithUV(var30, var34, var36 - 0.2, var20, var24);
		} else {
			var9.addVertexWithUV(var28, var34, var36 - 0.2, var12, var16);
			var9.addVertexWithUV(var28, var32, var36 + 0.3, var22, var26);
			var9.addVertexWithUV(var30, var32, var36 + 0.3, var14, var18);
			var9.addVertexWithUV(var30, var34, var36 - 0.2, var20, var24);
		}
	}

	@Override
	public boolean renderWorldBlock(final IBlockAccess world, final int x,
			final int y, final int z, final Block block, final int modelId,
			final RenderBlocks renderer) {
		if (world.getBlockMetadata(x, y, z) == 8) {
			renderer.renderCrossedSquares(block, x, y, z);
		} else if (world.getBlockMetadata(x, y, z) == 9) {
			final Tessellator var5 = Tessellator.instance;
			var5.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y, z));
			final int color = block.colorMultiplier(renderer.blockAccess, x, y,
					z);
			block.getBlockTextureFromSide(0);
			float r = (color >> 16 & 255) / 255.0F;
			float g = (color >> 8 & 255) / 255.0F;
			float b = (color & 255) / 255.0F;
			float var12;
			float var14;

			if (EntityRenderer.anaglyphEnable) {
				var12 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
				final float var13 = (r * 30.0F + g * 70.0F) / 100.0F;
				var14 = (r * 30.0F + b * 70.0F) / 100.0F;
				r = var12;
				g = var13;
				b = var14;
			}

			var5.setColorOpaque_F(r, g, b);
			var12 = 0.1865F;
			renderer.setOverrideBlockTexture(27);
			renderer.drawCrossedSquares(block, 0, x, y, z, 0.7F);
			renderer.setOverrideBlockTexture(26);
			renderEastFace(block, x, y, z + 0.5, 1, renderer);
			renderWestFace(block, x, y, z - 0.5, 1, renderer);
			renderer.setOverrideBlockTexture(25);
			renderEastFace(block, x, y, z + 0.5, 1, renderer);
			renderer.clearOverrideBlockTexture();
		} else {
			renderer.renderCrossedSquares(block, x, y, z);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

}
