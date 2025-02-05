package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.LivingEntity;

@Mod.EventBusSubscriber(
        modid = Wrs.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD)
public class AsignarAtributosEntidad {

    @SubscribeEvent
    public static void onAttributeModification(EntityAttributeModificationEvent event) {
        //ciclo for que recorre a todas las entidades vivas (LivingEntity) y usa la funcion agregar en todas ellas
        for (EntityType<? extends LivingEntity> entity : event.getTypes()) {
            agregar(event, entity);
        }
    }

    //funcion que agrega los atributos nuevos a las entidades vivas (que extienden de LivingEntity)
    public static void agregar(EntityAttributeModificationEvent event, EntityType<? extends LivingEntity> entidad) {
        event.add(entidad, Atributos.SLASH.get());
        event.add(entidad, Atributos.SLASH_RESIST.get());
        event.add(entidad, Atributos.BLUDGEON.get());
        event.add(entidad, Atributos.BLUDGEON_RESIST.get());
        event.add(entidad, Atributos.PIERCE.get());
        event.add(entidad, Atributos.PIERCE_RESIST.get());
        event.add(entidad, Atributos.ARCANE.get());
        event.add(entidad, Atributos.ARCANE_RESIST.get());
        event.add(entidad, Atributos.FIRE.get());
        event.add(entidad, Atributos.FIRE_RESIST.get());
        event.add(entidad, Atributos.ICE.get());
        event.add(entidad, Atributos.ICE_RESIST.get());
        event.add(entidad, Atributos.ELECTRIC.get());
        event.add(entidad, Atributos.ELECTRIC_RESIST.get());
        event.add(entidad, Atributos.HOLY.get());
        event.add(entidad, Atributos.HOLY_RESIST.get());
        event.add(entidad, Atributos.DARK.get());
        event.add(entidad, Atributos.DARK_RESIST.get());
    }
}
