package net.squarep.mcmods.ps147.flower;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class FlowerPacketHandler implements IPacketHandler {

	public static Packet getPacket(final ITileEntityPacket par1TileEntity) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		int x = ((TileEntity) par1TileEntity).xCoord;
		int y = ((TileEntity) par1TileEntity).yCoord;
		int z = ((TileEntity) par1TileEntity).zCoord;
		try {
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			par1TileEntity.sendPacketData(dos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "TTM";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}

	@Override
	public void onPacketData(final INetworkManager network,
			final Packet250CustomPayload packet, final Player player) {
		ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
		if (packet.channel.equals("TTM")) {
			int x, y, z;
			try {
				x = data.readInt();
				y = data.readInt();
				z = data.readInt();

				World world = ((EntityPlayer) player).worldObj;
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

				if (tileEntity instanceof ITileEntityPacket) {
					((ITileEntityPacket) tileEntity).receivePacketData(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
