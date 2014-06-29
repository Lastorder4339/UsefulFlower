package net.squarep.mcmods.ps147.flower.Item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFlower extends ItemBlock {

	public ItemBlockFlower(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getIconFromDamage(int par1) {
		return par1;
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
		return String.format("sq.ps147.flower.%d",
				par1ItemStack.getItemDamage());
	}
}
