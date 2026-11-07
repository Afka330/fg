package com.example.jumpscaremod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpscareMod implements ClientModInitializer {
    public static final String MODID = "jumpscaremod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        ModConfig.load();
        ClientTickEvents.END_CLIENT_TICK.register(JumpscareHandler::onClientTick);
        // addLast: слой поверх всего HUD
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath(MODID, "jumpscare_layer"),
                (graphics, deltaTracker) -> JumpscareHandler.extract(graphics));
    }
}
