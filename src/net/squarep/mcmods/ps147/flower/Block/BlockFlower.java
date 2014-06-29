package net.squarep.mcmods.ps147.flower.Block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlower extends Block implements IPlantable {

	public BlockFlower(int par1Id) {
		super(par1Id, 0, Material.plants);
		this.setTextureFile("/net/squarep/mcmods/ps147/assets/texture/flower_block.png");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return super.canPlaceBlockAt(par1World, par2, par3, par4)
				&& canBlockStay(par1World, par2, par3, par4);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int par1) {
		return par1 == Block.grass.blockID || par1 == Block.dirt.blockID
				|| par1 == Block.tilledField.blockID;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		this.checkFlowerChange(par1World, par2, par3, par4);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		this.checkFlowerChange(par1World, par2, par3, par4);
	}

	protected void checkFlowerChange(World par1World, int par2, int par3,
			int par4) {
		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4,
					par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
		return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World
				.canBlockSeeTheSky(par2, par3, par4))
				&& (soil != null && soil.canSustainPlant(par1World, par2,
						par3 - 1, par4, ForgeDirection.UP, this));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		return meta;
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return this.blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int i = 0; i < 10; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
}
