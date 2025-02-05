package dev.fede2010.wrs.cliente;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import dev.fede2010.wrs.data.AtributosDataType;
import dev.fede2010.wrs.network.ClientDataHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;


@Mod.EventBusSubscriber(
        modid = Wrs.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class AgregarTooltip {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {

        ItemStack itemStack = event.getItemStack();

        if (itemStack.isEmpty()) return;

        ResourceLocation itemStackId = ForgeRegistries.ITEMS.getKey(itemStack.getItem());

        if (itemStackId == null) {
            // Manejo de error: el item no tiene un ResourceLocation válido.
            return;
        }

        AtributosDataType dataGrupo = ClientDataHolder.getGroupsData(itemStackId);
        AtributosDataType dataItem = ClientDataHolder.getItemsData(itemStackId);

        AtributosDataType resultado;

        if (dataGrupo == null){
            if (dataItem == null){
                return;
            }else resultado = dataItem;
        }else resultado = dataGrupo;

        if (dataGrupo != null && dataItem != null) {
            resultado = AtributosDataType.reemplazarValores(dataGrupo, dataItem);
        }

        agregarAtributo(event, resultado.damage().getSlash(), Atributos.SLASH.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getBludgeon(), Atributos.BLUDGEON.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getPierce(), Atributos.PIERCE.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getArcane(), Atributos.ARCANE.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getFire(), Atributos.FIRE.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getIce(), Atributos.ICE.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getElectric(), Atributos.ELECTRIC.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getHoly(), Atributos.HOLY.get().getDescriptionId());
        agregarAtributo(event, resultado.damage().getDark(), Atributos.DARK.get().getDescriptionId());

        agregarAtributo(event, resultado.resistance().getSlash(), Atributos.SLASH_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getBludgeon(), Atributos.BLUDGEON_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getPierce(), Atributos.PIERCE_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getArcane(), Atributos.ARCANE_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getFire(), Atributos.FIRE_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getIce(), Atributos.ICE_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getElectric(), Atributos.ELECTRIC_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getHoly(), Atributos.HOLY_RESIST.get().getDescriptionId());
        agregarAtributo(event, resultado.resistance().getDark(), Atributos.DARK_RESIST.get().getDescriptionId());

    }

    // Función genérica para agregar atributos al tooltip
    private static void agregarAtributo(ItemTooltipEvent event, double valorAtributo, String descriptionId) {
        if (valorAtributo != 0) {
            String attributeName = I18n.get(descriptionId);
            ChatFormatting color = (valorAtributo > 0) ? ChatFormatting.GREEN : ChatFormatting.RED;

            // Remover cualquier línea que contenga el atributo antes de agregarlo
            event.getToolTip().removeIf(component -> component.getString().startsWith(attributeName + ":"));

            event.getToolTip().add(Component.literal(attributeName + ": " + valorAtributo).withStyle(color));
        }
    }

}
