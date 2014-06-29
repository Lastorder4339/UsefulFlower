package net.squarep.mcmods.ps147.flower.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.squarep.mcmods.ps147.flower.Flower_Core;
import net.squarep.mcmods.ps147.flower.Renderer.RendererFlowerDouble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDoubleFlowerBase extends BlockFlower implements IShearable {

	public BlockDoubleFlowerBase(int par1Id) {
		super(par1Id);
	}

	public void breakBlock(World world, int x, int y, int z, int side, int meta) {
		if (world.getBlockId(x, y - 1, z) == this.blockID) {
			world.setBlockWithNotify(x, y - 1, z, 0);
		} else if (world.getBlockId(x, y + 1, z) == this.blockID) {
			world.setBlockWithNotify(x, y + 1, z, 0);
		}
		if (meta > 3 && meta % 2 < 1) {
			Random rand = new Random();
			float f = 0.7F;
			double d = (double) (rand.nextFloat() * f) + (double) (1.0F - f)
					* 0.5D;
			double d1 = (double) (rand.nextFloat() * f) + (double) (1.0F - f)
					* 0.5D;
			double d2 = (double) (rand.nextFloat() * f) + (double) (1.0F - f)
					* 0.5D;
			EntityItem entityitem = new EntityItem(world, (double) x + d,
					(double) y + d1, (double) z + d2, new ItemStack(
							this.blockID, 1, meta));
			entityitem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityitem);
		}
		super.breakBlock(world, x, y, z, side, meta);
	}

	@Override
	protected void checkFlowerChange(World par1World, int par2, int par3,
			int par4) {
		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			if (par1World.getBlockMetadata(par2, par3 - 1, par4) == par1World
					.getBlockMetadata(par2, par3, par4) - 1)
				return;
			this.dropBlockAsItem(par1World, par2, par3, par4,
					par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4) {
		if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) > 3)
			return 0xFFFFFF;
		int var5 = 0;
		int var6 = 0;
		int var7 = 0;

		for (int var8 = -1; var8 <= 1; ++var8) {
			for (int var9 = -1; var9 <= 1; ++var9) {
				int var10 = par1IBlockAccess.getBiomeGenForCoords(par2 + var9,
						par4 + var8).getBiomeGrassColor();
				var5 += (var10 & 16711680) >> 16;
				var6 += (var10 & 65280) >> 8;
				var7 += var10 & 255;
			}
		}

		return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return -1;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		return meta + 16;
	}

	@Override
	public int getRenderType() {
		return Flower_Core.getInstance().renderIdMap
				.get(RendererFlowerDouble.class.getName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 4));
		par3List.add(new ItemStack(par1, 1, 6));
		par3List.add(new ItemStack(par1, 1, 8));
		par3List.add(new ItemStack(par1, 1, 12));
	}

	public int onBlockPlaced(World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int meta) {
		world.setBlockAndMetadataWithNotify(x, y + 1, z, blockID, meta + 1);
		return meta;
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
			int z, int meta, int fortune) {
		if (meta < 4) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			if (world.rand.nextInt(8) != 0) {
				return ret;
			}

			ItemStack item = ForgeHooks.getGrassSeed(world);
			if (item != null) {
				ret.add(item);
			}

			item = ForgeHooks.getGrassSeed(world);
			if (item != null) {
				ret.add(item);
			}
			return ret;
		}
		return new ArrayList<ItemStack>();
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) < 4;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x,
			int y, int z, int fortune) {
		int meta = world.getBlockMetadata(x, y, z);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		if (world.getBlockId(x, y - 1, z) == this.blockID) {
			meta = world.getBlockMetadata(x, y - 1, z);
			world.setBlockWithNotify(x, y - 1, z, 0);
		} else if (world.getBlockId(x, y + 1, z) == this.blockID) {
			meta = world.getBlockMetadata(x, y + 1, z) - 1;
			world.setBlockWithNotify(x, y + 1, z, 0);
		}
		ret.add(new ItemStack(this.blockID, 1, meta));

		return ret;
	}
}
