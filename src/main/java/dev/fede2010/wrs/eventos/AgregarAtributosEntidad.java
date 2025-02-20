package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import dev.fede2010.wrs.data.AtributosDataType;
import dev.fede2010.wrs.data.DataLoaderEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(
        modid = Wrs.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class AgregarAtributosEntidad {

    public AgregarAtributosEntidad(){

    }
    @SubscribeEvent
    public static void agregarAtributos(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;

        Entity entidad = event.getEntity();
        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entidad.getType());

        if (entityId == null) {
            return;
        }

        AtributosDataType dataGrupo = DataLoaderEvent.GROUPS.getData().get(entityId);
        AtributosDataType dataEntidad = DataLoaderEvent.ENTITIES.getData().get(entityId);

        AtributosDataType resultado;

        if (dataGrupo == null){
            if (dataEntidad == null){
                return;
            }else resultado = dataEntidad;
        }else resultado = dataGrupo;

        if (dataGrupo != null && dataEntidad != null) {
            resultado = AtributosDataType.reemplazarValores(dataGrupo, dataEntidad);
        }

        Map<Attribute, Double> atributosMap = new HashMap<>();
        Atributos.atributosMap(atributosMap, resultado);

        if (entidad instanceof LivingEntity livingEntity) {
            for (Map.Entry<Attribute, Double> entry : atributosMap.entrySet()) {
                AttributeInstance attributeInstance = livingEntity.getAttribute(entry.getKey());
                if (attributeInstance != null) {
                    attributeInstance.setBaseValue(entry.getValue());
                } else {
                    Wrs.LOGGER.warn("Atributo no encontrado: {}", entry.getKey());
                }
            }
        }
    }

}
