package net.squarep.mcmods.ps147.flower.TileEntity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.squarep.mcmods.ps147.flower.ITileEntityPacket;

import com.google.common.io.ByteArrayDataInput;

public class TileEntityFlowerPot extends TileEntity implements
ITileEntityPacket {
	private boolean isDefaultRender;
	private ItemStack is;
	private static final List<Integer> defaultRenderList = Arrays.asList(
			Block.tallGrass.blockID, Block.sapling.blockID,
			Block.deadBush.blockID, Block.cactus.blockID);

	public ItemStack getItemStack() {
		return is;
	}

	public boolean isDefaultRender() {
		return isDefaultRender;
	}

	@Override
	public void readFromNBT(final NBTTagCompound par1NBT) {
		isDefaultRender = par1NBT.getBoolean("isDefaultRender");
		is = new ItemStack(par1NBT.getCompoundTag("is").getInteger("id"),
				par1NBT.getCompoundTag("is").getInteger("stack"), par1NBT
				.getCompoundTag("is").getInteger("damage"));
	}

	@Override
	public void receivePacketData(final ByteArrayDataInput par1Data) {
		isDefaultRender = par1Data.readBoolean();
		is = new ItemStack(par1Data.readInt(), par1Data.readInt(),
				par1Data.readInt());
	}

	@Override
	public void sendPacketData(final DataOutputStream par1Data)
			throws IOException {
		par1Data.writeBoolean(isDefaultRender);
		par1Data.writeInt(is.itemID);
		par1Data.writeInt(is.stackSize);
		par1Data.writeInt(is.getItemDamage());
	}

	public void setItemStack(final ItemStack par1IS) {
		System.out.println(defaultRenderList);
		System.out.printf("%d, %b%n", par1IS.itemID,
				defaultRenderList.contains(par1IS.itemID));
		isDefaultRender = defaultRenderList.contains(par1IS.itemID);
		is = par1IS;
	}

	@Override
	public void writeToNBT(final NBTTagCompound par1NBT) {
		par1NBT.setBoolean("isDefaultRender", isDefaultRender);
		par1NBT.getCompoundTag("is").setInteger("id", is.itemID);
		par1NBT.getCompoundTag("is").setInteger("stack", is.stackSize);
		par1NBT.getCompoundTag("is").setInteger("damage", is.getItemDamage());
	}
}
