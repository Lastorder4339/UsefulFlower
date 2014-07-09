package net.squarep.mcmods.ps147.flower.Block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.squarep.mcmods.ps147.flower.Flower_Core;
import net.squarep.mcmods.ps147.flower.Renderer.RendererFlowerPot;
import net.squarep.mcmods.ps147.flower.TileEntity.TileEntityFlowerPot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlowerPot extends Block {
	public BlockFlowerPot(final int par1) {
		super(par1, Material.circuits);
		blockIndexInTexture = 186;
		setBlockBoundsForItemRender();
		setRequiresSelfNotify();
	}

	@Override
	public boolean canPlaceBlockAt(final World par1World, final int par2,
			final int par3, final int par4) {
		return super.canPlaceBlockAt(par1World, par2, par3, par4)
				&& par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
	}

	@Override
	public TileEntity createTileEntity(final World world, final int metadata) {
		return new TileEntityFlowerPot();
	}

	@Override
	public void dropBlockAsItemWithChance(final World par1World,
			final int par2, final int par3, final int par4, final int par5,
			final float par6, final int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5,
				par6, par7);

		if (par5 > 0) {

			/*
			 * if (var8 != null) { this.dropBlockAsItem_do(par1World, par2,
			 * par3, par4, var8); }
			 */
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean func_82505_u_() {
		return true;
	}

	@Override
	public int getRenderType() {
		return Flower_Core.getInstance().renderIdMap
				.get(RendererFlowerPot.class.getName());
	}

	@Override
	public boolean hasTileEntity(final int metadata) {
		return true;
	}

	@Override
	public int idDropped(final int par1, final Random par2Random, final int par3) {
		return Item.flowerPot.itemID;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(final World par1World, final int x,
			final int y, final int z, final EntityPlayer par5EntityPlayer,
			final int par6, final float par7, final float par8, final float par9) {
		final ItemStack var10 = par5EntityPlayer.inventory.getCurrentItem();

		if (var10 == null) {
			return false;
		} else if (Block.blocksList[var10.itemID] instanceof IPlantable) {
			final TileEntity te = par1World.getBlockTileEntity(x, y, z);
			if (te == null || !(te instanceof TileEntityFlowerPot)) {
				return false;
			} else {
				if (((TileEntityFlowerPot) te).getItemStack() == null) {
					((TileEntityFlowerPot) te).setItemStack(var10);

					if (!par5EntityPlayer.capabilities.isCreativeMode
							&& --var10.stackSize <= 0) {
						par5EntityPlayer.inventory.setInventorySlotContents(
								par5EntityPlayer.inventory.currentItem,
								(ItemStack) null);
					}

					par1World.scheduleBlockUpdate(x, y, z, 1, 1);

					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(final World par1World, final int par2,
			final int par3, final int par4, final int par5) {
		if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)) {
			dropBlockAsItem(par1World, par2, par3, par4,
					par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		final float var1 = 0.375F;
		final float var2 = var1 / 2.0F;
		setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1,
				0.5F + var2);
	}

}
