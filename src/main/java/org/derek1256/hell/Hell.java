package org.derek1256.hell;

import net.fabricmc.api.ModInitializer;

public class Hell implements ModInitializer {
    public static ModConfig CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = ModConfig.load();
        System.out.println("Max portal size: " + CONFIG.maxPortalSize);
    }
}
