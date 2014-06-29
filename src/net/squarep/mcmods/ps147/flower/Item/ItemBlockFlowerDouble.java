package net.squarep.mcmods.ps147.flower.Item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlowerDouble extends ItemBlock {

	public ItemBlockFlowerDouble(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if (par1ItemStack.getItemDamage() <= 3) {
			double var1 = 0.5D;
			double var3 = 1.0D;
			return ColorizerGrass.getGrassColor(var1, var3);
		}
		return 0xFFFFFF;
	}

	@Override
	public int getIconFromDamage(int par1) {
		return par1 == 8 ? 26 : par1 + 16;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getItemName() {
		return null;
	}

	@Override
	public String getItemNameIS(ItemStack par1ItemStack) {
		return String.format("sq.ps147.flowerDouble.%d",
				par1ItemStack.getItemDamage());
	}
}
