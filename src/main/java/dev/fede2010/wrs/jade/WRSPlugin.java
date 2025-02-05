package dev.fede2010.wrs.jade;


import dev.fede2010.wrs.Wrs;
import net.minecraft.world.entity.LivingEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class WRSPlugin implements IWailaPlugin {

    public static final String ATRIBUTOS = Wrs.MODID + ":atributos";
    @Override
    public void register(IWailaCommonRegistration registration) {
        //TODO register data providers

        registration.registerEntityDataProvider(WRSEntityComponentProvider.INSTANCE, LivingEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        //TODO register component providers, icon providers, callbacks, and config options here

        registration.registerEntityComponent(WRSEntityComponentProvider.INSTANCE, LivingEntity.class);
    }
}
