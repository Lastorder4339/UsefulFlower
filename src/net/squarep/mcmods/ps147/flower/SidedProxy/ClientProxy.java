package net.squarep.mcmods.ps147.flower.SidedProxy;

import net.minecraftforge.client.MinecraftForgeClient;
import net.squarep.mcmods.ps147.flower.Flower_Core;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy implements ISidedProxy {

	@Override
	public void registerBlockRenderer(
			final Class<? extends ISimpleBlockRenderingHandler> renderer) {
		try {
			Flower_Core.getInstance().renderIdMap.put(renderer.getName(),
					RenderingRegistry.getNextAvailableRenderId());
			RenderingRegistry.registerBlockHandler(renderer.newInstance());
		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerTexture(final String path) {
		MinecraftForgeClient.preloadTexture(path);
	}

}
