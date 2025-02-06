package dev.fede2010.wrs.compatibilidad.jade;

import dev.fede2010.wrs.Config;
import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;


public enum WRSEntityComponentProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {

    INSTANCE;

    int contador = 0;

    public static final String ATAQUE = "Daño";
    public static final String RESISTENCIA = "Resistencia";
    public static final String DEBILIDAD = "Debilidad";

    private final boolean DETALLES = Config.detalles;

    @Override
    public void appendTooltip(ITooltip iTooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {

        //Si la tecla no esta presionada muestra los daños de la entidad, si esta presionada muestra las resistencias de la entidad
        if(!Tecla.isVerMasPresionado()){

            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.slash", "Slash", new ItemStack(Items.DIAMOND_SWORD));
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.bludgeon", "Bludgeon", new ItemStack(Items.DIAMOND_AXE));
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.pierce", "Pierce", new ItemStack(Items.ARROW));
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.arcane", "Arcane", new ItemStack(Items.ENCHANTED_BOOK));
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.fire", "Fire", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:healing");
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.ice", "Ice", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_swiftness");
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.electric", "Electric", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_fire_resistance");
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.holy", "Holy", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:invisibility");
            addTooltipAtributoAtaque(iTooltip, entityAccessor, Wrs.MODID + ".value.dark", "Dark", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_weakness");

            contador = 0;

        }else {

            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.slash_resist", "Slash Resist", new ItemStack(Items.DIAMOND_SWORD));
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.bludgeon_resist", "Bludgeon Resist", new ItemStack(Items.DIAMOND_AXE));
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.pierce_resist", "Pierce Resist", new ItemStack(Items.ARROW));
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.arcane_resist", "Arcane Resist", new ItemStack(Items.ENCHANTED_BOOK));
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.fire_resist", "Fire Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:healing");
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.ice_resist", "Ice Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_swiftness");
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.electric_resist", "Electric Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_fire_resistance");
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.holy_resist", "Holy Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:invisibility");
            addTooltipAtributoResistencia(iTooltip, entityAccessor, Wrs.MODID + ".value.dark_resist", "Dark Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_weakness");

            contador = 0;

            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.slash_resist", "Slash Resist", new ItemStack(Items.DIAMOND_SWORD));
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.bludgeon_resist", "Bludgeon Resist", new ItemStack(Items.DIAMOND_AXE));
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.pierce_resist", "Pierce Resist", new ItemStack(Items.ARROW));
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.arcane_resist", "Arcane Resist", new ItemStack(Items.ENCHANTED_BOOK));
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.fire_resist", "Fire Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:healing");
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.ice_resist", "Ice Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_swiftness");
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.electric_resist", "Electric Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_fire_resistance");
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.holy_resist", "Holy Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:invisibility");
            addTooltipAtributoDebilidad(iTooltip, entityAccessor, Wrs.MODID + ".value.dark_resist", "Dark Resist", new ItemStack(Items.LINGERING_POTION), "Potion", "minecraft:long_weakness");

            contador = 0;

        }

    }

    @Override
    public void appendServerData(CompoundTag compoundTag, EntityAccessor entityAccessor) {
        Object entityObj = entityAccessor.getEntity();
        if (entityObj instanceof LivingEntity entity) {

            AttributeInstance slash = entity.getAttribute(Atributos.SLASH.get());
            AttributeInstance bludgeon = entity.getAttribute(Atributos.BLUDGEON.get());
            AttributeInstance pierce = entity.getAttribute(Atributos.PIERCE.get());
            AttributeInstance arcane = entity.getAttribute(Atributos.ARCANE.get());
            AttributeInstance fire = entity.getAttribute(Atributos.FIRE.get());
            AttributeInstance ice = entity.getAttribute(Atributos.ICE.get());
            AttributeInstance electric = entity.getAttribute(Atributos.ELECTRIC.get());
            AttributeInstance holy = entity.getAttribute(Atributos.HOLY.get());
            AttributeInstance dark = entity.getAttribute(Atributos.DARK.get());

            AttributeInstance slashResist = entity.getAttribute(Atributos.SLASH_RESIST.get());
            AttributeInstance bludgeonResist = entity.getAttribute(Atributos.BLUDGEON_RESIST.get());
            AttributeInstance pierceResist = entity.getAttribute(Atributos.PIERCE_RESIST.get());
            AttributeInstance arcaneResist = entity.getAttribute(Atributos.ARCANE_RESIST.get());
            AttributeInstance fireResist = entity.getAttribute(Atributos.FIRE_RESIST.get());
            AttributeInstance iceResist = entity.getAttribute(Atributos.ICE_RESIST.get());
            AttributeInstance electricResist = entity.getAttribute(Atributos.ELECTRIC_RESIST.get());
            AttributeInstance holyResist = entity.getAttribute(Atributos.HOLY_RESIST.get());
            AttributeInstance darkResist = entity.getAttribute(Atributos.DARK_RESIST.get());

            if(slash != null) compoundTag.putDouble("Slash", slash.getValue());
            if(bludgeon != null) compoundTag.putDouble("Bludgeon", bludgeon.getValue());
            if(pierce != null) compoundTag.putDouble("Pierce", pierce.getValue());
            if(arcane != null) compoundTag.putDouble("Arcane", arcane.getValue());
            if(fire != null) compoundTag.putDouble("Fire", fire.getValue());
            if(ice != null) compoundTag.putDouble("Ice", ice.getValue());
            if(electric != null) compoundTag.putDouble("Electric", electric.getValue());
            if(holy != null) compoundTag.putDouble("Holy", holy.getValue());
            if(dark != null) compoundTag.putDouble("Dark", dark.getValue());

            if(slashResist != null) compoundTag.putDouble("Slash Resist", slashResist.getValue());
            if(bludgeonResist != null) compoundTag.putDouble("Bludgeon Resist", bludgeonResist.getValue());
            if(pierceResist != null) compoundTag.putDouble("Pierce Resist", pierceResist.getValue());
            if(arcaneResist != null) compoundTag.putDouble("Arcane Resist", arcaneResist.getValue());
            if(fireResist != null) compoundTag.putDouble("Fire Resist", fireResist.getValue());
            if(iceResist != null) compoundTag.putDouble("Ice Resist", iceResist.getValue());
            if(electricResist != null) compoundTag.putDouble("Electric Resist", electricResist.getValue());
            if(holyResist != null) compoundTag.putDouble("Holy Resist", holyResist.getValue());
            if(darkResist != null) compoundTag.putDouble("Dark Resist", darkResist.getValue());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(WRSPlugin.ATRIBUTOS);
    }

    private void addTooltipAtributoAtaque(ITooltip iTooltip, EntityAccessor entityAccessor, String translationKey, String serverData, ItemStack itemStack) {
        int valor = (int)entityAccessor.getServerData().getDouble(serverData);

        IElementHelper elements = iTooltip.getElementHelper();//.size aumenta el tamaño el primer valor es eje x y el segundo es eje y
        IElement icon = elements.item(itemStack, 0.75f).size(new Vec2(10, 14)).translate(new Vec2(-3, -3));

        if (valor > 0) {
            if (contador == 0){
                iTooltip.add(Component.literal(" ").append(Component.literal(ATAQUE).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00)))));
            }
            if (contador % 3 == 0) {
                contador++;
                iTooltip.add(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                }
            }else {
                contador++;
                iTooltip.append(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                }
            }
        }
    }

    private void addTooltipAtributoAtaque(ITooltip iTooltip, EntityAccessor entityAccessor, String translationKey, String serverData, ItemStack itemStack, String tagArg1, String tagArg2) {
        int valor = (int)entityAccessor.getServerData().getDouble(serverData);

        IElementHelper elements = iTooltip.getElementHelper();

        CompoundTag potionTag2 = itemStack.getOrCreateTag();
        potionTag2.putString(tagArg1, tagArg2);

        IElement icon = elements.item(itemStack, 0.75f).size(new Vec2(10, 14)).translate(new Vec2(-3, -5));

        if (valor > 0) {
            if (contador == 0){
                iTooltip.add(Component.literal(" ").append(Component.literal(ATAQUE).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00)))));
            }
            if (contador % 3 == 0) {
                contador++;
                iTooltip.add(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                }
            }else {
                contador++;
                iTooltip.append(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                }
            }
        }
    }

    private void addTooltipAtributoResistencia(ITooltip iTooltip, EntityAccessor entityAccessor, String translationKey, String serverData, ItemStack itemStack) {
        int valor = (int)entityAccessor.getServerData().getDouble(serverData);

        IElementHelper elements = iTooltip.getElementHelper();
        IElement icon = elements.item(itemStack, 0.75f).size(new Vec2(10, 14)).translate(new Vec2(-3, -3));

            if (valor > 0) {
                if(contador == 0){
                    iTooltip.add(Component.literal(RESISTENCIA).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00))));
                }
                if (contador % 3 == 0) {
                    contador++;
                    iTooltip.add(icon);
                    if (DETALLES){
                        iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                    }else {
                        iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                    }
                }else {
                    contador++;
                    iTooltip.append(icon);
                    if (DETALLES){
                        iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                    }else {
                        iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                    }
                }
            }
    }

    private void addTooltipAtributoResistencia(ITooltip iTooltip, EntityAccessor entityAccessor, String translationKey, String serverData, ItemStack itemStack, String tagArg1 , String tagArg2) {
        int valor = (int)entityAccessor.getServerData().getDouble(serverData);

        IElementHelper elements = iTooltip.getElementHelper();

        CompoundTag potionTag2 = itemStack.getOrCreateTag();
        potionTag2.putString(tagArg1, tagArg2);

        IElement icon = elements.item(itemStack, 0.75f).size(new Vec2(10, 14)).translate(new Vec2(-3, -5));

        if (valor > 0) {
            if(contador == 0){
                iTooltip.add(Component.literal(RESISTENCIA).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00))));
            }
            if (contador % 3 == 0) {
                contador++;
                iTooltip.add(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                }
            }else {
                contador++;
                iTooltip.append(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))).append(Component.literal(" ")));
                }
            }
        }
    }

    private void addTooltipAtributoDebilidad(ITooltip iTooltip, EntityAccessor entityAccessor, String translationKey, String serverData, ItemStack itemStack) {
        int valor = (int)entityAccessor.getServerData().getDouble(serverData);

        IElementHelper elements = iTooltip.getElementHelper();
        IElement icon = elements.item(itemStack, 0.75f).size(new Vec2(10, 14)).translate(new Vec2(-3, -3));

        if (valor < 0) {
            if(contador == 0){
                iTooltip.add(Component.literal(DEBILIDAD).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00))));
            }
            if (contador % 3 == 0) {
                contador++;
                iTooltip.add(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000)))).append(Component.literal(" ")));
                }
            }else {
                contador++;
                iTooltip.append(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000)))).append(Component.literal(" ")));
                }
            }
        }
    }

    private void addTooltipAtributoDebilidad(ITooltip iTooltip, EntityAccessor entityAccessor, String translationKey, String serverData, ItemStack itemStack, String tagArg1, String tagArg2) {
        int valor = (int)entityAccessor.getServerData().getDouble(serverData);

        IElementHelper elements = iTooltip.getElementHelper();

        CompoundTag potionTag2 = itemStack.getOrCreateTag();
        potionTag2.putString(tagArg1, tagArg2);

        IElement icon = elements.item(itemStack, 0.75f).size(new Vec2(10, 14)).translate(new Vec2(-3, -5));

        if (valor < 0) {
            if(contador == 0){
                iTooltip.add(Component.literal(DEBILIDAD).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00))));
            }
            if (contador % 3 == 0) {
                contador++;
                iTooltip.add(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000)))).append(Component.literal(" ")));
                }
            }else {
                contador++;
                iTooltip.append(icon);
                if (DETALLES){
                    iTooltip.append(Component.literal(" ").append(Component.translatable(translationKey, Component.literal(String.valueOf(valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))))).append(Component.literal(" ")));
                }else {
                    iTooltip.append(Component.literal(" ").append(Component.literal(sinDetalles(translationKey, valor)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000)))).append(Component.literal(" ")));
                }
            }
        }
    }

    private String sinDetalles(String translationKey, int valor){

        Component component = Component.translatable(translationKey, Component.literal(String.valueOf(valor)));
        String numeroString = component.getString().replaceAll("\\D", ""); // Eliminar todos los caracteres no numéricos de la cadena
        numeroString = numeroString.trim(); // Eliminar espacios en blanco alrededor del número

        return numeroString;// devuelve el numero solo como un String

    }

}
