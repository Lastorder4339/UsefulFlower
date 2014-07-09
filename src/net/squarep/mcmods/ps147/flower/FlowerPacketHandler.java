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
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final DataOutputStream dos = new DataOutputStream(bos);

		final int x = ((TileEntity) par1TileEntity).xCoord;
		final int y = ((TileEntity) par1TileEntity).yCoord;
		final int z = ((TileEntity) par1TileEntity).zCoord;
		try {
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			par1TileEntity.sendPacketData(dos);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "TTM";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}

	@Override
	public void onPacketData(final INetworkManager network,
			final Packet250CustomPayload packet, final Player player) {
		final ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
		if (packet.channel.equals("TTM")) {
			int x, y, z;
			try {
				x = data.readInt();
				y = data.readInt();
				z = data.readInt();

				final World world = ((EntityPlayer) player).worldObj;
				final TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

				if (tileEntity instanceof ITileEntityPacket) {
					((ITileEntityPacket) tileEntity).receivePacketData(data);
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
}
