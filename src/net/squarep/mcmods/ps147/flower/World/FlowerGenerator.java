package net.squarep.mcmods.ps147.flower.World;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class FlowerGenerator implements IWorldGenerator {

	private static WorldGenFlower flower = new WorldGenFlower();
	private static WorldGenDoubleFlower flowerDouble = new WorldGenDoubleFlower();

	@Override
	public void generate(final Random random, final int chunkX,
			final int chunkZ, final World world,
			final IChunkProvider chunkGenerator,
			final IChunkProvider chunkProvider) {
		final int BlockX = chunkX * 16;
		final int BlockZ = chunkZ * 16;
		final int Xcoord = BlockX + random.nextInt(16);
		final int Zcoord = BlockZ + random.nextInt(16);
		int Ycoord = random.nextInt(80);
		for (int i = 0; i < 7; i++) {
			flower.generate(world, random, Xcoord, Ycoord, Zcoord);
			Ycoord = random.nextInt(80);
		}

		for (int i = 0; i < 5; i++) {
			flowerDouble.generate(world, random, Xcoord, Ycoord, Zcoord);
			Ycoord = random.nextInt(80);
		}
	}
}
