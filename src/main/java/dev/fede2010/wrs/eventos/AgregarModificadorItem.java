package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import dev.fede2010.wrs.data.AtributosDataType;
import dev.fede2010.wrs.data.DataLoaderEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
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
    private static final Map<Attribute, UUID> offHand = new HashMap<>();
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

        AtributosDataType dataGrupo = DataLoaderEvent.GROUPS.getData().get(itemStackId);
        AtributosDataType dataItem = DataLoaderEvent.ITEMS.getData().get(itemStackId);

        AtributosDataType resultado;

        if (dataGrupo == null){
            if (dataItem == null){
                return;
            }else resultado = dataItem;
        }else resultado = dataGrupo;

        if (dataGrupo != null && dataItem != null) {
            resultado = AtributosDataType.reemplazarValores(dataGrupo, dataItem);
        }

        mainHand.clear();
        offHand.clear();
        head.clear();
        chest.clear();
        legs.clear();
        feet.clear();

        mapeo(mainHand, event);
        mapeo(offHand, event);
        mapeo(head, event);
        mapeo(chest, event);
        mapeo(legs, event);
        mapeo(feet, event);

        // Verifica si el ítem es una armadura
        if (itemStack.getItem() instanceof ArmorItem) {
            // Obtiene el tipo de ranura de equipo de la armadura
            EquipmentSlot armorSlot = ((ArmorItem) itemStack.getItem()).getEquipmentSlot();
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
        } else if (itemStack.getItem() instanceof ShieldItem && event.getSlotType() == EquipmentSlot.OFFHAND) {
            crearModificador(event, offHand, resultado);
        }
        else if (!(itemStack.getItem() instanceof ShieldItem) && event.getSlotType() == EquipmentSlot.MAINHAND) {
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

    public static void mapeo(Map<Attribute, UUID> map, ItemAttributeModifierEvent event) {
        if (map.isEmpty()) {

            String evento = event.getItemStack().getItem().getDescriptionId() + ":";

            map.put(Atributos.SLASH.get(), UUID.nameUUIDFromBytes((evento + "SLASH").getBytes()));
            map.put(Atributos.BLUDGEON.get(), UUID.nameUUIDFromBytes((evento + "BLUDGEON").getBytes()));
            map.put(Atributos.PIERCE.get(), UUID.nameUUIDFromBytes((evento + "PIERCE").getBytes()));
            map.put(Atributos.ARCANE.get(), UUID.nameUUIDFromBytes((evento + "ARCANE").getBytes()));
            map.put(Atributos.FIRE.get(), UUID.nameUUIDFromBytes((evento + "FIRE").getBytes()));
            map.put(Atributos.ICE.get(), UUID.nameUUIDFromBytes((evento + "ICE").getBytes()));
            map.put(Atributos.ELECTRIC.get(), UUID.nameUUIDFromBytes((evento + "ELECTRIC").getBytes()));
            map.put(Atributos.HOLY.get(), UUID.nameUUIDFromBytes((evento + "HOLY").getBytes()));
            map.put(Atributos.DARK.get(), UUID.nameUUIDFromBytes((evento + "DARK").getBytes()));

            map.put(Atributos.SLASH_RESIST.get(), UUID.nameUUIDFromBytes((evento + "SLASH_RESIST").getBytes()));
            map.put(Atributos.BLUDGEON_RESIST.get(), UUID.nameUUIDFromBytes((evento + "BLUDGEON_RESIST").getBytes()));
            map.put(Atributos.PIERCE_RESIST.get(), UUID.nameUUIDFromBytes((evento + "PIERCE_RESIST").getBytes()));
            map.put(Atributos.ARCANE_RESIST.get(), UUID.nameUUIDFromBytes((evento + "ARCANE_RESIST").getBytes()));
            map.put(Atributos.FIRE_RESIST.get(), UUID.nameUUIDFromBytes((evento + "FIRE_RESIST").getBytes()));
            map.put(Atributos.ICE_RESIST.get(), UUID.nameUUIDFromBytes((evento + "ICE_RESIST").getBytes()));
            map.put(Atributos.ELECTRIC_RESIST.get(), UUID.nameUUIDFromBytes((evento + "ELECTRIC_RESIST").getBytes()));
            map.put(Atributos.HOLY_RESIST.get(), UUID.nameUUIDFromBytes((evento + "HOLY_RESIST").getBytes()));
            map.put(Atributos.DARK_RESIST.get(), UUID.nameUUIDFromBytes((evento + "DARK_RESIST").getBytes()));

        }

    }
}
