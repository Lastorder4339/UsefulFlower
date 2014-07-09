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

	public BlockFlower(final int par1Id) {
		super(par1Id, 0, Material.plants);
		setTextureFile("/net/squarep/mcmods/ps147/assets/texture/flower_block.png");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public boolean canBlockStay(final World par1World, final int par2,
			final int par3, final int par4) {
		final Block soil = blocksList[par1World
				.getBlockId(par2, par3 - 1, par4)];
		return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World
				.canBlockSeeTheSky(par2, par3, par4))
				&& (soil != null && soil.canSustainPlant(par1World, par2,
						par3 - 1, par4, ForgeDirection.UP, this));
	}

	@Override
	public boolean canPlaceBlockAt(final World par1World, final int par2,
			final int par3, final int par4) {
		return super.canPlaceBlockAt(par1World, par2, par3, par4)
				&& canBlockStay(par1World, par2, par3, par4);
	}

	protected boolean canThisPlantGrowOnThisBlockID(final int par1) {
		return par1 == Block.grass.blockID || par1 == Block.dirt.blockID
				|| par1 == Block.tilledField.blockID;
	}

	protected void checkFlowerChange(final World par1World, final int par2,
			final int par3, final int par4) {
		if (!canBlockStay(par1World, par2, par3, par4)) {
			dropBlockAsItem(par1World, par2, par3, par4,
					par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	@Override
	public int damageDropped(final int par1) {
		return par1;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(final int side, final int meta) {
		return meta;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World,
			final int par2, final int par3, final int par4) {
		return null;
	}

	@Override
	public int getPlantID(final World world, final int x, final int y,
			final int z) {
		return blockID;
	}

	@Override
	public int getPlantMetadata(final World world, final int x, final int y,
			final int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public EnumPlantType getPlantType(final World world, final int x,
			final int y, final int z) {
		return EnumPlantType.Plains;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final int par1,
			final CreativeTabs par2CreativeTabs, final List par3List) {
		for (int i = 0; i < 10; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(final World par1World, final int par2,
			final int par3, final int par4, final int par5) {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		checkFlowerChange(par1World, par2, par3, par4);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(final World par1World, final int par2,
			final int par3, final int par4, final Random par5Random) {
		checkFlowerChange(par1World, par2, par3, par4);
	}
}
