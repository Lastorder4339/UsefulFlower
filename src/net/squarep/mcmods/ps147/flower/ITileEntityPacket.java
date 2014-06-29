package net.squarep.mcmods.ps147.flower;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;

public interface ITileEntityPacket {
	public void receivePacketData(ByteArrayDataInput par1Data);

	public void sendPacketData(DataOutputStream par1Data) throws IOException;
}
