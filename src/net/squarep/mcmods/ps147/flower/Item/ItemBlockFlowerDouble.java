package net.squarep.mcmods.ps147.flower.Item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlowerDouble extends ItemBlock {

	public ItemBlockFlowerDouble(final int par1) {
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(final ItemStack par1ItemStack,
			final int par2) {
		if (par1ItemStack.getItemDamage() <= 3) {
			final double var1 = 0.5D;
			final double var3 = 1.0D;
			return ColorizerGrass.getGrassColor(var1, var3);
		}
		return 0xFFFFFF;
	}

	@Override
	public int getIconFromDamage(final int par1) {
		return par1 == 8 ? 26 : par1 + 16;
	}

	@Override
	public String getItemName() {
		return null;
	}

	@Override
	public String getItemNameIS(final ItemStack par1ItemStack) {
		return String.format("sq.ps147.flowerDouble.%d",
				par1ItemStack.getItemDamage());
	}

	@Override
	public int getMetadata(final int par1) {
		return par1;
	}
}
