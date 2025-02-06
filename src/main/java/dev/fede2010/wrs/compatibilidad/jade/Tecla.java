package dev.fede2010.wrs.compatibilidad.jade;

import com.mojang.blaze3d.platform.InputConstants;
import dev.fede2010.wrs.Wrs;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Tecla {
    //forma de como obtiene una KEY el mod de epicfight
    private static final KeyMapping KEY_VER_MAS = new KeyMapping("key." + Wrs.MODID + ".show_tooltip", InputConstants.KEY_LSHIFT, "key." + Wrs.MODID + ".gui");


    private static boolean verMas = false;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(KEY_VER_MAS);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        //Verifica cuando se presiona la tecla
        verMas = KEY_VER_MAS.isDown();
    }

    public static boolean isVerMasPresionado() {
        return verMas;
    }

}
