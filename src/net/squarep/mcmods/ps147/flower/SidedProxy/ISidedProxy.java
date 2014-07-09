package net.squarep.mcmods.ps147.flower.SidedProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public interface ISidedProxy {
	public void registerBlockRenderer(
			Class<? extends ISimpleBlockRenderingHandler> renderer);

	public void registerTexture(String path);
}
