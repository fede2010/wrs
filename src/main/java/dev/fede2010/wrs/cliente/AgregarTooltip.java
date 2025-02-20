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

        agregarAtributo(event, Atributos.SLASH.get().getDescriptionId(), ChatFormatting.GRAY, resultado.damage().getSlash());
        agregarAtributo(event, Atributos.BLUDGEON.get().getDescriptionId(), ChatFormatting.GRAY, resultado.damage().getBludgeon());
        agregarAtributo(event, Atributos.PIERCE.get().getDescriptionId(), ChatFormatting.GRAY, resultado.damage().getPierce());
        agregarAtributo(event, Atributos.ARCANE.get().getDescriptionId(), ChatFormatting.GRAY, resultado.damage().getArcane());
        agregarAtributo(event, Atributos.FIRE.get().getDescriptionId(), ChatFormatting.RED, resultado.damage().getFire());
        agregarAtributo(event, Atributos.ICE.get().getDescriptionId(), ChatFormatting.AQUA, resultado.damage().getIce());
        agregarAtributo(event, Atributos.ELECTRIC.get().getDescriptionId(), ChatFormatting.YELLOW, resultado.damage().getElectric());
        agregarAtributo(event, Atributos.HOLY.get().getDescriptionId(), ChatFormatting.WHITE, resultado.damage().getHoly());
        agregarAtributo(event, Atributos.DARK.get().getDescriptionId(), ChatFormatting.DARK_GRAY, resultado.damage().getDark());

        agregarAtributo(event, Atributos.SLASH_RESIST.get().getDescriptionId(), ChatFormatting.GRAY, resultado.resistance().getSlash());
        agregarAtributo(event, Atributos.BLUDGEON_RESIST.get().getDescriptionId(), ChatFormatting.GRAY, resultado.resistance().getBludgeon());
        agregarAtributo(event, Atributos.PIERCE_RESIST.get().getDescriptionId(), ChatFormatting.GRAY, resultado.resistance().getPierce());
        agregarAtributo(event, Atributos.ARCANE_RESIST.get().getDescriptionId(), ChatFormatting.GRAY, resultado.resistance().getArcane());
        agregarAtributo(event, Atributos.FIRE_RESIST.get().getDescriptionId(), ChatFormatting.RED, resultado.resistance().getFire());
        agregarAtributo(event, Atributos.ICE_RESIST.get().getDescriptionId(), ChatFormatting.AQUA, resultado.resistance().getIce());
        agregarAtributo(event, Atributos.ELECTRIC_RESIST.get().getDescriptionId(), ChatFormatting.YELLOW, resultado.resistance().getElectric());
        agregarAtributo(event, Atributos.HOLY_RESIST.get().getDescriptionId(), ChatFormatting.WHITE, resultado.resistance().getHoly());
        agregarAtributo(event, Atributos.DARK_RESIST.get().getDescriptionId(), ChatFormatting.DARK_GRAY, resultado.resistance().getDark());

    }

    // Función genérica para agregar atributos al tooltip
    private static void agregarAtributo(ItemTooltipEvent event, String atributoId, ChatFormatting color, double valorAtributo) {
        if (valorAtributo != 0) {
            String attributeName = I18n.get(atributoId);
            ChatFormatting colorValor = (valorAtributo > 0) ? ChatFormatting.BLUE : ChatFormatting.RED;

            // Remover cualquier línea que contenga el atributo antes de agregarlo
            event.getToolTip().removeIf(component -> component.getString().contains(attributeName));

            event.getToolTip().add(Component.literal(attributeName + ": ").withStyle(color).append(Component.literal("" + valorAtributo).withStyle(colorValor)));
        }
    }

}
