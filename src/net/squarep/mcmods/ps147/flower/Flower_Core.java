package net.squarep.mcmods.ps147.flower;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.squarep.mcmods.ps147.flower.Block.BlockDoubleFlower;
import net.squarep.mcmods.ps147.flower.Block.BlockFlower;
import net.squarep.mcmods.ps147.flower.Event.TerrainGenEvent;
import net.squarep.mcmods.ps147.flower.Item.ItemBlockFlower;
import net.squarep.mcmods.ps147.flower.Item.ItemBlockFlowerDouble;
import net.squarep.mcmods.ps147.flower.Renderer.RendererFlowerDouble;
import net.squarep.mcmods.ps147.flower.SidedProxy.ISidedProxy;
import net.squarep.mcmods.ps147.flower.World.FlowerGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "ps147|flower", name = "FlowerMod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = "ps147|Flower", packetHandler = FlowerPacketHandler.class)
public class Flower_Core {
	@SidedProxy(clientSide = "net.squarep.mcmods.ps147.flower.SidedProxy.ClientProxy", serverSide = "net.squarep.mcmods.ps147.flower.SidedProxy.ServerProxy")
	public static ISidedProxy proxy;
	@Mod.Instance("ps147|flower")
	private static Flower_Core instance;

	public static Flower_Core getInstance() {
		return instance;
	}

	public Block flower, flowerPot, flowerDouble;

	public Map<String, Integer> renderIdMap = new HashMap<String, Integer>();

	public Flower_Core() {
		instance = this;
	}

	@Mod.Init
	public void init(final FMLInitializationEvent event) {
		proxy.registerTexture("/net/squarep/mcmods/ps147/assets/texture/flower_block.png");
		Block.blocksList[37] = null;
		Block.blocksList[38] = null;
		// Block.blocksList[140] = null;
		flowerDouble = new BlockDoubleFlower(37).setBlockName("Flower_Double");
		flower = new BlockFlower(38).setBlockName("Flower_Single");
		// flowerPot = new BlockFlowerPot(140).setBlockName("FlowerPot");
		GameRegistry.registerBlock(flower, ItemBlockFlower.class,
				"ps147_flower");
		GameRegistry.registerBlock(flowerDouble, ItemBlockFlowerDouble.class,
				"ps147_flowerDouble");
		registerTranslation();
		proxy.registerBlockRenderer(RendererFlowerDouble.class);
		// proxy.registerBlockRenderer(RendererFlowerPot.class);
		// GameRegistry.registerTileEntity(TileEntityFlowerPot.class,
		// "ps147|flowerPot");
		MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainGenEvent());
		GameRegistry.registerWorldGenerator(new FlowerGenerator());
	}

	public void registerTranslation() {
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 0),
				"en_US", "Allium");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 1),
				"en_US", "Blue Orchid");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 2),
				"en_US", "Dandelion");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 3),
				"en_US", "Azure Bluet");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 4),
				"en_US", "Oxeye Daisy");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 5),
				"en_US", "Poppy");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 6),
				"en_US", "Orange Tulip");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 7),
				"en_US", "Pink Tulip");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 8),
				"en_US", "Red Tulip");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 9),
				"en_US", "White Tulip");

		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 0),
				"ja_JP", "アリウム");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 1),
				"ja_JP", "ヒスイラン");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 2),
				"ja_JP", "たんぽぽ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 3),
				"ja_JP", "ヒナソウ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 4),
				"ja_JP", "フランスギク");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 5),
				"ja_JP", "ポピー");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 6),
				"ja_JP", "橙色のチューリップ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 7),
				"ja_JP", "桃色のチューリップ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 8),
				"ja_JP", "赤色のチューリップ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(38, 1, 9),
				"ja_JP", "白色のチューリップ");

		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 0),
				"en_US", "Large Fern");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 2),
				"en_US", "Double Tallgrass");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 4),
				"en_US", "Peony");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 6),
				"en_US", "Rose Bush");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 8),
				"en_US", "Sunflower");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 12),
				"en_US", "Lilac");

		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 0),
				"ja_JP", "大きなシダ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 2),
				"ja_JP", "高い草");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 4),
				"ja_JP", "ボタン");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 6),
				"ja_JP", "バラの低木");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 8),
				"ja_JP", "ひまわり");
		LanguageRegistry.instance().addNameForObject(new ItemStack(37, 1, 12),
				"ja_JP", "ライラック");
		// LanguageRegistry.instance().addNameForObject(flowerPot, "en_US",
		// "FlowerPot");
		// LanguageRegistry.instance().addNameForObject(flowerPot, "ja_JP",
		// "鉢植え");
	}
}
