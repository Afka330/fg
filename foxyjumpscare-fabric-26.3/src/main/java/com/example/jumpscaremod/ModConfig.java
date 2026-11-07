package com.example.jumpscaremod;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** config/jumpscaremod.properties, ключ "chance" = 1 к X в секунду. */
public final class ModConfig {
    public static int jumpscareChance = 10000;

    public static void load() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve("jumpscaremod.properties");
        try {
            if (Files.exists(file)) {
                Properties p = new Properties();
                try (var in = Files.newInputStream(file)) { p.load(in); }
                jumpscareChance = Math.max(1, Math.min(1_000_000,
                        Integer.parseInt(p.getProperty("chance", "10000").trim())));
            } else {
                Files.writeString(file,
                        "# One in X chance per second for the jumpscare to trigger (1..1000000)\nchance=10000\n");
            }
        } catch (Exception e) {
            JumpscareMod.LOGGER.warn("Could not read config, using default", e);
        }
    }

    private ModConfig() {}
}
