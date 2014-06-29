package net.squarep.mcmods.ps147.flower.Renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.squarep.mcmods.ps147.flower.Flower_Core;
import net.squarep.mcmods.ps147.flower.TileEntity.TileEntityFlowerPot;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererFlowerPot extends RenderBlocks implements
		ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		renderer.renderStandardBlock(block, x, y, z);
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

		var5.setColorOpaque_F(lighting * r, lighting * g, lighting * b);
		var12 = 0.1865F;
		renderer.renderSouthFace(block, (double) ((float) x - 0.5F + var12),
				(double) y, (double) z, texture);
		renderer.renderNorthFace(block, (double) ((float) x + 0.5F - var12),
				(double) y, (double) z, texture);
		renderer.renderWestFace(block, (double) x, (double) y,
				(double) ((float) z - 0.5F + var12), texture);
		renderer.renderEastFace(block, (double) x, (double) y,
				(double) ((float) z + 0.5F - var12), texture);
		renderer.renderTopFace(block, (double) x, (double) ((float) y - 0.5F
				+ var12 + 0.1875F), (double) z, Block.dirt.blockIndexInTexture);

		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityFlowerPot) {
			TileEntityFlowerPot tef = (TileEntityFlowerPot) te;
			System.out.println(tef.getItemStack());
			if (tef.getItemStack() != null) {
				renderFlower(world, x, y, z, block, renderer,
						tef.isDefaultRender(), tef.getItemStack());
			}
		}

		return true;
	}

	public void renderFlower(IBlockAccess world, int x, int y, int z,
			Block block, RenderBlocks renderer, boolean isDefaultRender,
			ItemStack is) {
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

		var14 = 0.0F;
		float var15 = 4.0F;
		float var16 = 0.0F;
		Block var17 = null;

		var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);

		if (!isDefaultRender) {
			renderer.renderBlockByRenderType(Block.blocksList[is.itemID], x, y,
					z);
		} else if (is.itemID == Block.cactus.blockID) {
			renderer.renderAllFaces = true;
			float var18 = 0.125F;
			renderer.setRenderBounds((double) (0.5F - var18), 0.0D,
					(double) (0.5F - var18), (double) (0.5F + var18), 0.25D,
					(double) (0.5F + var18));
			renderer.renderStandardBlock(Block.cactus, x, y, z);
			renderer.setRenderBounds((double) (0.5F - var18), 0.25D,
					(double) (0.5F - var18), (double) (0.5F + var18), 0.5D,
					(double) (0.5F + var18));
			renderer.renderStandardBlock(Block.cactus, x, y, z);
			renderer.setRenderBounds((double) (0.5F - var18), 0.5D,
					(double) (0.5F - var18), (double) (0.5F + var18), 0.75D,
					(double) (0.5F + var18));
			renderer.renderStandardBlock(Block.cactus, x, y, z);
			renderer.renderAllFaces = false;
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		} else if (is.itemID == Block.sapling.blockID) {
			renderer.drawCrossedSquares(Block.sapling, is.getItemDamage(),
					(double) x, (double) y, (double) z, 0.75F);
		} else if (is.itemID == Block.tallGrass.blockID) {
			color = Block.tallGrass.colorMultiplier(renderer.blockAccess, x, y,
					z);
			r = (float) (color >> 16 & 255) / 255.0F;
			g = (float) (color >> 8 & 255) / 255.0F;
			b = (float) (color & 255) / 255.0F;
			var5.setColorOpaque_F(lighting * r, lighting * g, lighting * b);
			renderer.drawCrossedSquares(Block.tallGrass, 2, (double) x,
					(double) y, (double) z, 0.75F);
		} else if (is.itemID == Block.deadBush.blockID) {
			renderer.drawCrossedSquares(Block.deadBush, 2, (double) x,
					(double) y, (double) z, 0.75F);
		}

		var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);

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
