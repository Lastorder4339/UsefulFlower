package net.squarep.mcmods.ps147.flower.Event;

import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public class TerrainGenEvent {

	@ForgeSubscribe
	public void gen(Decorate event) {
		if(event.type == EventType.FLOWERS) {
		event.setResult(Result.DENY);
		}
	}
}
