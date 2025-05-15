package dev.fede2010.wrs.compatibilidad.jade;

import dev.fede2010.wrs.Wrs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Registra la tecla en  el lado del cliente
@Mod.EventBusSubscriber(modid = Wrs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TeclaRegistrar {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(Tecla.KEY_VER_MAS);
    }
}
