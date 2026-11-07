package com.example.jumpscaremod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import java.util.Random;

public final class JumpscareHandler {
    private static final int TOTAL_FRAMES = 13;       // foxy_0 .. foxy_12
    private static final int TICKS_PER_FRAME = 2;
    private static final int JUMPSCARE_DURATION = TOTAL_FRAMES * TICKS_PER_FRAME; // 26 тиков
    private static final int TEX_W = 1024, TEX_H = 768;

    private static final Random RANDOM = new Random();
    private static final Identifier SCREAM_SOUND =
            Identifier.fromNamespaceAndPath(JumpscareMod.MODID, "withered_foxy_scream");
    private static final Identifier[] ANIMATION_FRAMES = new Identifier[TOTAL_FRAMES];
    static {
        for (int i = 0; i < TOTAL_FRAMES; i++) {
            ANIMATION_FRAMES[i] = Identifier.fromNamespaceAndPath(
                    JumpscareMod.MODID, "textures/gui/foxy_" + i + ".png");
        }
    }

    private static boolean isJumpscareActive = false;
    private static int jumpscareTimer = 0;
    private static int ticksSinceLastCheck = 0;

    public static void onClientTick(Minecraft mc) {
        if (mc.player == null || mc.level == null) return;

        if (isJumpscareActive) {
            if (++jumpscareTimer >= JUMPSCARE_DURATION) {
                isJumpscareActive = false;
                jumpscareTimer = 0;
            }
            return;
        }

        if (++ticksSinceLastCheck >= 20) { // раз в секунду
            ticksSinceLastCheck = 0;
            if (RANDOM.nextInt(ModConfig.jumpscareChance) == 0) {
                triggerJumpscare(mc);
            }
        }
    }

    private static void triggerJumpscare(Minecraft mc) {
        isJumpscareActive = true;
        jumpscareTimer = 0;
        SoundEvent sound = SoundEvent.createVariableRangeEvent(SCREAM_SOUND);
        mc.getSoundManager().play(SimpleSoundInstance.forUI(sound, 1.0F, 1.0F));
    }

    public static void extract(GuiGraphicsExtractor graphics) {
        if (!isJumpscareActive) return;
        int width = graphics.guiWidth();
        int height = graphics.guiHeight();
        int frame = Math.min(jumpscareTimer / TICKS_PER_FRAME, TOTAL_FRAMES - 1);
        // pipeline, texture, x, y, u, v, width, height, regionW, regionH, texW, texH
        graphics.blit(RenderPipelines.GUI_TEXTURED, ANIMATION_FRAMES[frame],
                0, 0, 0f, 0f, width, height, TEX_W, TEX_H, TEX_W, TEX_H);
    }

    private JumpscareHandler() {}
}
