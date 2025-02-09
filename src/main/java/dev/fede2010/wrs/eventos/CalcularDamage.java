package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Config;
import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import dev.fede2010.wrs.data.AtributosDataType;
import dev.fede2010.wrs.data.AtributosLoaderEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(
        modid = Wrs.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class CalcularDamage {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        // Obtenemos la entidad que está recibiendo daño
        LivingEntity victima = event.getEntity();
        LivingEntity atacante = (LivingEntity) event.getSource().getEntity();

        double slashDamage;
        double bludgeonDamage;
        double pierceDamage;
        double arcaneDamage;
        double fireDamage;
        double iceDamage;
        double electricDamage;
        double holyDamage;
        double darkDamage;

        double atributosMinimos = 100;

        float damageOriginal = event.getAmount();

        if (victima == null) return;

        if (atacante != null){

             slashDamage = calcularDamage(atacante.getAttributeValue(Atributos.SLASH.get()), victima.getAttributeValue(Atributos.SLASH_RESIST.get()), damageOriginal);
             bludgeonDamage = calcularDamage(atacante.getAttributeValue(Atributos.BLUDGEON.get()), victima.getAttributeValue(Atributos.BLUDGEON_RESIST.get()), damageOriginal);
             pierceDamage = calcularDamage(atacante.getAttributeValue(Atributos.PIERCE.get()), victima.getAttributeValue(Atributos.PIERCE_RESIST.get()), damageOriginal);
             arcaneDamage = calcularDamage(atacante.getAttributeValue(Atributos.ARCANE.get()), victima.getAttributeValue(Atributos.ARCANE_RESIST.get()), damageOriginal);
             fireDamage = calcularDamage(atacante.getAttributeValue(Atributos.FIRE.get()), victima.getAttributeValue(Atributos.FIRE_RESIST.get()), damageOriginal);
             iceDamage = calcularDamage(atacante.getAttributeValue(Atributos.ICE.get()), victima.getAttributeValue(Atributos.ICE_RESIST.get()), damageOriginal);
             electricDamage = calcularDamage(atacante.getAttributeValue(Atributos.ELECTRIC.get()), victima.getAttributeValue(Atributos.ELECTRIC_RESIST.get()), damageOriginal);
             holyDamage = calcularDamage(atacante.getAttributeValue(Atributos.HOLY.get()), victima.getAttributeValue(Atributos.HOLY_RESIST.get()), damageOriginal);
             darkDamage = calcularDamage(atacante.getAttributeValue(Atributos.DARK.get()), victima.getAttributeValue(Atributos.DARK_RESIST.get()), damageOriginal);

            double damageModificado = slashDamage + bludgeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

            atributosMinimos = atributosMinimos - Atributos.atributosTotales(atacante);

            if (atributosMinimos > 0){
                damageModificado = damageModificado + (damageOriginal * (atributosMinimos / 100));
            }

            event.setAmount((float) damageModificado);

            if (Config.debug){
                if (event.getSource().getEntity() instanceof ServerPlayer player) {

                    mensaje(player, Wrs.MODID + ".initial_damage", damageOriginal, ChatFormatting.GREEN);
                    mensaje(player, Wrs.MODID + ".value.slash", slashDamage, ChatFormatting.GRAY);
                    mensaje(player, Wrs.MODID + ".value.bludgeon", bludgeonDamage, ChatFormatting.GRAY);
                    mensaje(player, Wrs.MODID + ".value.pierce", pierceDamage, ChatFormatting.GRAY);
                    mensaje(player, Wrs.MODID + ".value.arcane", arcaneDamage, ChatFormatting.GRAY);
                    mensaje(player, Wrs.MODID + ".value.fire", fireDamage, ChatFormatting.RED);
                    mensaje(player, Wrs.MODID + ".value.ice", iceDamage, ChatFormatting.AQUA);
                    mensaje(player, Wrs.MODID + ".value.electric", electricDamage, ChatFormatting.YELLOW);
                    mensaje(player, Wrs.MODID + ".value.holy", holyDamage, ChatFormatting.WHITE);
                    mensaje(player, Wrs.MODID + ".value.dark", darkDamage, ChatFormatting.DARK_GRAY);
                    mensaje(player, Wrs.MODID + ".final_damage", damageModificado, ChatFormatting.GREEN);

                }
            }
        }else {

            String damageType = new ResourceLocation(event.getSource().getMsgId().toLowerCase(Locale.ROOT)).toString();

            AtributosDataType dataGrupo = AtributosLoaderEvent.GROUPS.getData().get(new ResourceLocation(damageType));
            if (dataGrupo == null)return;

            slashDamage = calcularDamage(dataGrupo.damage().getSlash(), victima.getAttributeValue(Atributos.SLASH_RESIST.get()), damageOriginal);
            bludgeonDamage = calcularDamage(dataGrupo.damage().getBludgeon(), victima.getAttributeValue(Atributos.BLUDGEON_RESIST.get()), damageOriginal);
            pierceDamage = calcularDamage(dataGrupo.damage().getPierce(), victima.getAttributeValue(Atributos.PIERCE_RESIST.get()), damageOriginal);
            arcaneDamage = calcularDamage(dataGrupo.damage().getArcane(), victima.getAttributeValue(Atributos.ARCANE_RESIST.get()), damageOriginal);
            fireDamage = calcularDamage(dataGrupo.damage().getFire(), victima.getAttributeValue(Atributos.FIRE_RESIST.get()), damageOriginal);
            iceDamage = calcularDamage(dataGrupo.damage().getIce(), victima.getAttributeValue(Atributos.ICE_RESIST.get()), damageOriginal);
            electricDamage = calcularDamage(dataGrupo.damage().getElectric(), victima.getAttributeValue(Atributos.ELECTRIC_RESIST.get()), damageOriginal);
            holyDamage = calcularDamage(dataGrupo.damage().getHoly(), victima.getAttributeValue(Atributos.HOLY_RESIST.get()), damageOriginal);
            darkDamage = calcularDamage(dataGrupo.damage().getDark(), victima.getAttributeValue(Atributos.DARK_RESIST.get()), damageOriginal);

            double damageModificado = slashDamage + bludgeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

            atributosMinimos = atributosMinimos - Atributos.atributosTotales(dataGrupo);

            if (atributosMinimos > 0){
                damageModificado = damageModificado + (damageOriginal * (atributosMinimos / 100));
            }

            event.setAmount((float) damageModificado);

        }

    }

    public static double calcularDamage(double ataqueValor, double defensaValor, float damageOriginal){

        double damageFinal = 0.0;

        if (ataqueValor > 0){
            damageFinal = damageOriginal * (ataqueValor / 100);
            damageFinal = calcularResistencia(damageFinal, defensaValor);
        }

        return damageFinal;
    }

    public static double calcularResistencia(double damage, double resistencia){
        if (resistencia > 0 && resistencia <= 100){
            damage *= 1 - (resistencia / 100);
        }else if (resistencia > 0){
            damage = 0;
        }else damage *= 1 + (Math.abs(resistencia) / 100);

        return damage;
    }

    public static void mensaje(ServerPlayer serverPlayer, String translatable, double damage, ChatFormatting color){
        serverPlayer.sendSystemMessage(Component.translatable(translatable, damage)
                .withStyle(color));
    }

}