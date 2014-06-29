package net.squarep.mcmods.ps147.flower.World;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFlower extends WorldGenerator {

	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {
		for (int var6 = 0; var6 < 64; ++var6) {
			int var7 = par3 + par2Random.nextInt(10) - par2Random.nextInt(10);
			int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var9 = par5 + par2Random.nextInt(10) - par2Random.nextInt(10);
			int meta = par2Random.nextInt(10);

			if (par1World.isAirBlock(var7, var8, var9)
					&& (!par1World.provider.hasNoSky || var8 < 127)
					&& Block.blocksList[38].canBlockStay(par1World, var7, var8,
							var9)) {
				par1World.setBlockAndMetadataWithNotify(var7, var8, var9, 37,
						meta);
			}
		}

		return true;
	}
}