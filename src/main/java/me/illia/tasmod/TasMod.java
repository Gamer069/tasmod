package me.illia.tasmod;

import net.fabricmc.api.ModInitializer;

public class TasMod implements ModInitializer {
	public static final String MODID = "tasmod";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Legacy Fabric world!");
	}
}
