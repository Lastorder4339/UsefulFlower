package net.squarep.mcmods.ps147.flower.SidedProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ServerProxy implements ISidedProxy {

	@Override
	public void registerBlockRenderer(
			Class<? extends ISimpleBlockRenderingHandler> renderer) {
		// do nothing.
	}

	@Override
	public void registerTexture(String path) {
		
	}

}
