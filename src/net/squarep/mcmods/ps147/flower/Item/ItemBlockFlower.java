package net.squarep.mcmods.ps147.flower.Item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFlower extends ItemBlock {

	public ItemBlockFlower(final int par1) {
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getIconFromDamage(final int par1) {
		return par1;
	}

	@Override
	public String getItemName() {
		return null;
	}

	@Override
	public String getItemNameIS(final ItemStack par1ItemStack) {
		return String.format("sq.ps147.flower.%d",
				par1ItemStack.getItemDamage());
	}

	@Override
	public int getMetadata(final int par1) {
		return par1;
	}
}
