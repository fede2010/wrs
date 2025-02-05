package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import dev.fede2010.wrs.data.AtributosDataType;
import dev.fede2010.wrs.data.AtributosLoaderEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(
        modid = Wrs.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class AgregarModificadorItem {

    private static final Map<Attribute, UUID> mainHand = new HashMap<>();
    private static final Map<Attribute, UUID> head = new HashMap<>();
    private static final Map<Attribute, UUID> chest = new HashMap<>();
    private static final Map<Attribute, UUID> legs = new HashMap<>();
    private static final Map<Attribute, UUID> feet = new HashMap<>();

    // Evento para agregar modificadores de atributo a los items
    @SubscribeEvent
    public static void agregarModificadores(ItemAttributeModifierEvent event) {
        if (event.getItemStack().isEmpty()) return;

        ItemStack itemStack = event.getItemStack();

        ResourceLocation itemStackId = ForgeRegistries.ITEMS.getKey(itemStack.getItem());

        if (itemStackId == null) {
            return;
        }

        AtributosDataType dataGrupo = AtributosLoaderEvent.GROUPS.getData().get(itemStackId);
        AtributosDataType dataItem = AtributosLoaderEvent.ITEMS.getData().get(itemStackId);

        AtributosDataType resultado;

        if (dataGrupo == null){
            if (dataItem == null){
                return;
            }else resultado = dataItem;
        }else resultado = dataGrupo;

        if (dataGrupo != null && dataItem != null) {
            resultado = AtributosDataType.reemplazarValores(dataGrupo, dataItem);
        }

        mapeo(mainHand);
        mapeo(head);
        mapeo(chest);
        mapeo(legs);
        mapeo(feet);

        // Verifica si el ítem es una armadura
        if (event.getItemStack().getItem() instanceof ArmorItem) {
            // Obtiene el tipo de ranura de equipo de la armadura
            EquipmentSlot armorSlot = ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot();
            // Verifica si el slot actual coincide con el tipo de ranura de equipo de la armadura
            if (event.getSlotType() == armorSlot) {

                if (EquipmentSlot.HEAD == armorSlot) {
                    crearModificador(event, head, resultado);
                }

                if (EquipmentSlot.CHEST == armorSlot) {
                    crearModificador(event, chest, resultado);
                }

                if (EquipmentSlot.LEGS == armorSlot) {
                    crearModificador(event, legs, resultado);
                }

                if (EquipmentSlot.FEET == armorSlot) {
                    crearModificador(event, feet, resultado);
                }
            }
        } else if (event.getSlotType() == EquipmentSlot.MAINHAND) {
            crearModificador(event, mainHand, resultado);
        }
    }

    public static void crearModificador(ItemAttributeModifierEvent event, Map<Attribute, UUID> map, AtributosDataType data){

        event.addModifier(Atributos.SLASH.get(), new AttributeModifier(map.get(Atributos.SLASH.get()), Atributos.SLASH.get() + ":" + event.getSlotType(), data.damage().getSlash(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.BLUDGEON.get(), new AttributeModifier(map.get(Atributos.BLUDGEON.get()), Atributos.BLUDGEON.get() + ":" + event.getSlotType(), data.damage().getBludgeon(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.PIERCE.get(), new AttributeModifier(map.get(Atributos.PIERCE.get()), Atributos.PIERCE.get() + ":" + event.getSlotType(), data.damage().getPierce(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.ARCANE.get(), new AttributeModifier(map.get(Atributos.ARCANE.get()), Atributos.ARCANE.get() + ":" + event.getSlotType(), data.damage().getArcane(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.FIRE.get(), new AttributeModifier(map.get(Atributos.FIRE.get()), Atributos.FIRE.get() + ":" + event.getSlotType(), data.damage().getFire(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.ICE.get(), new AttributeModifier(map.get(Atributos.ICE.get()), Atributos.ICE.get() + ":" + event.getSlotType(), data.damage().getIce(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.ELECTRIC.get(), new AttributeModifier(map.get(Atributos.ELECTRIC.get()), Atributos.ELECTRIC.get() + ":" + event.getSlotType(), data.damage().getElectric(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.HOLY.get(), new AttributeModifier(map.get(Atributos.HOLY.get()), Atributos.HOLY.get() + ":" + event.getSlotType(), data.damage().getHoly(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.DARK.get(), new AttributeModifier(map.get(Atributos.DARK.get()), Atributos.DARK.get() + ":" + event.getSlotType(), data.damage().getDark(), AttributeModifier.Operation.ADDITION));

        event.addModifier(Atributos.SLASH_RESIST.get(), new AttributeModifier(map.get(Atributos.SLASH_RESIST.get()), Atributos.SLASH_RESIST.get() + ":" + event.getSlotType(), data.resistance().getSlash(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.BLUDGEON_RESIST.get(), new AttributeModifier(map.get(Atributos.BLUDGEON_RESIST.get()), Atributos.BLUDGEON_RESIST.get() + ":" + event.getSlotType(), data.resistance().getBludgeon(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.PIERCE_RESIST.get(), new AttributeModifier(map.get(Atributos.PIERCE_RESIST.get()), Atributos.PIERCE_RESIST.get() + ":" + event.getSlotType(), data.resistance().getPierce(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.ARCANE_RESIST.get(), new AttributeModifier(map.get(Atributos.ARCANE_RESIST.get()), Atributos.ARCANE_RESIST.get() + ":" + event.getSlotType(), data.resistance().getArcane(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.FIRE_RESIST.get(), new AttributeModifier(map.get(Atributos.FIRE_RESIST.get()), Atributos.FIRE_RESIST.get() + ":" + event.getSlotType(), data.resistance().getFire(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.ICE_RESIST.get(), new AttributeModifier(map.get(Atributos.ICE_RESIST.get()), Atributos.ICE_RESIST.get() + ":" + event.getSlotType(), data.resistance().getIce(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.ELECTRIC_RESIST.get(), new AttributeModifier(map.get(Atributos.ELECTRIC_RESIST.get()), Atributos.ELECTRIC_RESIST.get() + ":" + event.getSlotType(), data.resistance().getElectric(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.HOLY_RESIST.get(), new AttributeModifier(map.get(Atributos.HOLY_RESIST.get()), Atributos.HOLY_RESIST.get() + ":" + event.getSlotType(), data.resistance().getHoly(), AttributeModifier.Operation.ADDITION));
        event.addModifier(Atributos.DARK_RESIST.get(), new AttributeModifier(map.get(Atributos.DARK_RESIST.get()), Atributos.DARK_RESIST.get() + ":" + event.getSlotType(), data.resistance().getDark(), AttributeModifier.Operation.ADDITION));
    }

    public static void mapeo(Map<Attribute, UUID> map){

        if (map.isEmpty()) {

            map.put(Atributos.SLASH.get(), UUID.fromString("e1f89a2a-4a3e-4d9a-9b78-3a1c9d1b6e72"));
            map.put(Atributos.BLUDGEON.get(), UUID.fromString("cd2d3c15-6a1e-45bb-bc0b-3f1a8b7ab4d1"));
            map.put(Atributos.PIERCE.get(), UUID.fromString("7e93a9d9-0a75-445d-89a1-bc865f787baa"));
            map.put(Atributos.ARCANE.get(), UUID.fromString("4d2b4e14-bb78-41fc-b4f9-1a6f3e59c9f0"));
            map.put(Atributos.FIRE.get(), UUID.fromString("f9a3d5b2-2c68-4b0d-9f31-3b12f7a6b2d7"));
            map.put(Atributos.ICE.get(), UUID.fromString("ba98a3d9-9e1d-4387-8706-8e7d5e4baf55"));
            map.put(Atributos.ELECTRIC.get(), UUID.fromString("1a5bcd32-cc9b-4a58-b456-9ab7e6d9e847"));
            map.put(Atributos.HOLY.get(), UUID.fromString("ff573d2a-9c2d-4a39-91f5-4f9c61b785c3"));
            map.put(Atributos.DARK.get(), UUID.fromString("9c3f2e4d-b1db-40da-8d2a-8d7f58c72f88"));

            map.put(Atributos.SLASH_RESIST.get(), UUID.fromString("2d8b7e6c-9343-41b9-93a6-9d1f3d1a2e21"));
            map.put(Atributos.BLUDGEON_RESIST.get(), UUID.fromString("5eaf3c2f-d5f2-47d2-87d3-2b3f4f2e1b21"));
            map.put(Atributos.PIERCE_RESIST.get(), UUID.fromString("b4f1a9c8-5a3b-41b2-8f4f-0c9e1a9f7899"));
            map.put(Atributos.ARCANE_RESIST.get(), UUID.fromString("cf5b9e12-6c7d-4a12-8f7e-3d8f1c9d7d8b"));
            map.put(Atributos.FIRE_RESIST.get(), UUID.fromString("d6c7e3f5-2f89-452e-81e3-1a7c5d9b9b33"));
            map.put(Atributos.ICE_RESIST.get(), UUID.fromString("3b8f1c9d-6f5e-4a93-b6f7-1d9e7a8b7d44"));
            map.put(Atributos.ELECTRIC_RESIST.get(), UUID.fromString("c9e1b6a7-4f5d-482e-92e7-7d3f4a2f9f10"));
            map.put(Atributos.HOLY_RESIST.get(), UUID.fromString("1f8b7c2a-8d4e-4a3e-93d7-3c7f1b5d2e99"));
            map.put(Atributos.DARK_RESIST.get(), UUID.fromString("9a3d5e2a-7f4b-4a2e-98d7-1f6b3c2d7e22"));

        }

    }
    
}
