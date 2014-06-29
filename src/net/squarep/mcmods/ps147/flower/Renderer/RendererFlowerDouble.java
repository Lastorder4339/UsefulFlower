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
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if (world.getBlockMetadata(x, y, z) == 8) {
			renderer.renderCrossedSquares(block, x, y, z);
		} else if (world.getBlockMetadata(x, y, z) == 9) {
			Tessellator var5 = Tessellator.instance;
			var5.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y, z));
			float lighting = 1.0F;
			int color = block.colorMultiplier(renderer.blockAccess, x, y, z);
			int texture = block.getBlockTextureFromSide(0);
			float r = (float) (color >> 16 & 255) / 255.0F;
			float g = (float) (color >> 8 & 255) / 255.0F;
			float b = (float) (color & 255) / 255.0F;
			float var12;
			float var14;

			if (EntityRenderer.anaglyphEnable) {
				var12 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
				float var13 = (r * 30.0F + g * 70.0F) / 100.0F;
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
			renderEastFace(block, (double) x, (double) y, (double) z + 0.5, 1,
					renderer);
			renderWestFace(block, (double) x, (double) y, (double) z - 0.5, 1,
					renderer);
			renderer.setOverrideBlockTexture(25);
			renderEastFace(block, (double) x, (double) y, (double) z + 0.5, 1,
					renderer);
			renderer.clearOverrideBlockTexture();
		} else {
			renderer.renderCrossedSquares(block, x, y, z);
		}
		return true;
	}

	private void renderEastFace(Block par1Block, double par2, double par4,
			double par6, int par8, RenderBlocks renderer) {
		Tessellator var9 = Tessellator.instance;

		if (renderer.overrideBlockTexture >= 0) {
			par8 = renderer.overrideBlockTexture;
		}

		int var10 = (par8 & 15) << 4;
		int var11 = par8 & 240;
		double var12 = ((double) var10 + renderer.renderMinX * 16.0D) / 256.0D;
		double var14 = ((double) var10 + renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
		double var16 = ((double) (var11 + 16) - renderer.renderMaxY * 16.0D) / 256.0D;
		double var18 = ((double) (var11 + 16) - renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
		double var20;

		if (renderer.flipTexture) {
			var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D) {
			var12 = (double) (((float) var10 + 0.0F) / 256.0F);
			var14 = (double) (((float) var10 + 15.99F) / 256.0F);
		}

		if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D) {
			var16 = (double) (((float) var11 + 0.0F) / 256.0F);
			var18 = (double) (((float) var11 + 15.99F) / 256.0F);
		}

		var20 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;

		if (renderer.uvRotateEast == 2) {
			var12 = ((double) var10 + renderer.renderMinY * 16.0D) / 256.0D;
			var16 = ((double) (var11 + 16) - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = ((double) var10 + renderer.renderMaxY * 16.0D) / 256.0D;
			var18 = ((double) (var11 + 16) - renderer.renderMaxX * 16.0D) / 256.0D;
			var24 = var16;
			var26 = var18;
			var20 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var24;
		} else if (renderer.uvRotateEast == 1) {
			var12 = ((double) (var10 + 16) - renderer.renderMaxY * 16.0D) / 256.0D;
			var16 = ((double) var11 + renderer.renderMaxX * 16.0D) / 256.0D;
			var14 = ((double) (var10 + 16) - renderer.renderMinY * 16.0D) / 256.0D;
			var18 = ((double) var11 + renderer.renderMinX * 16.0D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var22;
			var24 = var18;
			var26 = var16;
		} else if (renderer.uvRotateEast == 3) {
			var12 = ((double) (var10 + 16) - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = ((double) (var10 + 16) - renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
			var16 = ((double) var11 + renderer.renderMaxY * 16.0D) / 256.0D;
			var18 = ((double) var11 + renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = par2 + renderer.renderMinX;
		double var30 = par2 + renderer.renderMaxX;
		double var32 = par4 + renderer.renderMinY;
		double var34 = par4 + renderer.renderMaxY;
		double var36 = par6 + renderer.renderMinZ;

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

	/**
	 * Renders the given texture to the west (z-positive) face of the block.
	 * Args: block, x, y, z, texture
	 */
	private void renderWestFace(Block par1Block, double par2, double par4,
			double par6, int par8, RenderBlocks renderer) {
		Tessellator var9 = Tessellator.instance;

		if (renderer.overrideBlockTexture >= 0) {
			par8 = renderer.overrideBlockTexture;
		}

		int var10 = (par8 & 15) << 4;
		int var11 = par8 & 240;
		double var12 = ((double) var10 + renderer.renderMinX * 16.0D) / 256.0D;
		double var14 = ((double) var10 + renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
		double var16 = ((double) (var11 + 16) - renderer.renderMaxY * 16.0D) / 256.0D;
		double var18 = ((double) (var11 + 16) - renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
		double var20;

		if (renderer.flipTexture) {
			var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D) {
			var12 = (double) (((float) var10 + 0.0F) / 256.0F);
			var14 = (double) (((float) var10 + 15.99F) / 256.0F);
		}

		if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D) {
			var16 = (double) (((float) var11 + 0.0F) / 256.0F);
			var18 = (double) (((float) var11 + 15.99F) / 256.0F);
		}

		var20 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;

		if (renderer.uvRotateWest == 1) {
			var12 = ((double) var10 + renderer.renderMinY * 16.0D) / 256.0D;
			var18 = ((double) (var11 + 16) - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = ((double) var10 + renderer.renderMaxY * 16.0D) / 256.0D;
			var16 = ((double) (var11 + 16) - renderer.renderMaxX * 16.0D) / 256.0D;
			var24 = var16;
			var26 = var18;
			var20 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var24;
		} else if (renderer.uvRotateWest == 2) {
			var12 = ((double) (var10 + 16) - renderer.renderMaxY * 16.0D) / 256.0D;
			var16 = ((double) var11 + renderer.renderMinX * 16.0D) / 256.0D;
			var14 = ((double) (var10 + 16) - renderer.renderMinY * 16.0D) / 256.0D;
			var18 = ((double) var11 + renderer.renderMaxX * 16.0D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var22;
			var24 = var18;
			var26 = var16;
		} else if (renderer.uvRotateWest == 3) {
			var12 = ((double) (var10 + 16) - renderer.renderMinX * 16.0D) / 256.0D;
			var14 = ((double) (var10 + 16) - renderer.renderMaxX * 16.0D - 0.01D) / 256.0D;
			var16 = ((double) var11 + renderer.renderMaxY * 16.0D) / 256.0D;
			var18 = ((double) var11 + renderer.renderMinY * 16.0D - 0.01D) / 256.0D;
			var20 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = par2 + renderer.renderMinX;
		double var30 = par2 + renderer.renderMaxX;
		double var32 = par4 + renderer.renderMinY;
		double var34 = par4 + renderer.renderMaxY;
		double var36 = par6 + renderer.renderMaxZ;

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
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return Flower_Core.getInstance().renderIdMap.get(this.getClass()
				.getName());
	}

}
