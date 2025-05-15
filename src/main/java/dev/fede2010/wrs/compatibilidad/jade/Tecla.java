package dev.fede2010.wrs.compatibilidad.jade;

import com.mojang.blaze3d.platform.InputConstants;
import dev.fede2010.wrs.Wrs;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class Tecla {
    // Tecla configurable
    public static final KeyMapping KEY_VER_MAS = new KeyMapping(
            Wrs.MODID + ".key.show_defenses",
            InputConstants.KEY_LSHIFT,
            Wrs.MODID + ".key.gui"
    );

    private static boolean verMas = false;

    public static boolean isVerMasPresionado() {
        return verMas;
    }

    // Maneja la lectura de la tecla en cada tick del cliente
    @Mod.EventBusSubscriber(modid = Wrs.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientTick {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                verMas = KEY_VER_MAS.isDown();
            }
        }
    }
}
