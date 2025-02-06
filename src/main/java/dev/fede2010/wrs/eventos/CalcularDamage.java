package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Config;
import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

        if (victima == null) return;
        if (atacante == null) return;

        float damageOriginal = event.getAmount();
        double damageModificado;

        double atributosMinimos = 100;

        double slashDamage = calcularDamage(atacante, Atributos.SLASH.get(), victima, Atributos.SLASH_RESIST.get(), damageOriginal);
        double bludeonDamage = calcularDamage(atacante, Atributos.BLUDGEON.get(), victima, Atributos.BLUDGEON_RESIST.get(), damageOriginal);
        double pierceDamage = calcularDamage(atacante, Atributos.PIERCE.get(), victima, Atributos.PIERCE_RESIST.get(), damageOriginal);
        double arcaneDamage = calcularDamage(atacante, Atributos.ARCANE.get(), victima, Atributos.ARCANE_RESIST.get(), damageOriginal);
        double fireDamage = calcularDamage(atacante, Atributos.FIRE.get(), victima, Atributos.FIRE_RESIST.get(), damageOriginal);
        double iceDamage = calcularDamage(atacante, Atributos.ICE.get(), victima, Atributos.ICE_RESIST.get(), damageOriginal);
        double electricDamage = calcularDamage(atacante, Atributos.ELECTRIC.get(), victima, Atributos.ELECTRIC_RESIST.get(), damageOriginal);
        double holyDamage = calcularDamage(atacante, Atributos.HOLY.get(), victima, Atributos.HOLY_RESIST.get(), damageOriginal);
        double darkDamage = calcularDamage(atacante, Atributos.DARK.get(), victima, Atributos.DARK_RESIST.get(), damageOriginal);

        damageModificado = slashDamage + bludeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

        atributosMinimos = atributosMinimos - Atributos.atributosTotales(atacante);

        if (atributosMinimos > 0){
            damageModificado = damageModificado + (damageOriginal * (atributosMinimos / 100));
        }

        event.setAmount((float) damageModificado);

        if (Config.debug){
            if (event.getSource().getEntity() instanceof ServerPlayer player) {

                mensaje(player, Wrs.MODID + ".initial_damage", damageOriginal, ChatFormatting.GREEN);
                mensaje(player, Wrs.MODID + ".value.slash", slashDamage, ChatFormatting.GRAY);
                mensaje(player, Wrs.MODID + ".value.bludgeon", bludeonDamage, ChatFormatting.GRAY);
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

    }

    public static double calcularDamage(LivingEntity atacante, Attribute atributoAtacante, LivingEntity victima, Attribute atributoVictima, float damageOriginal){

        double damageFinal = 0.0;

        double atacanteValor = atacante.getAttributeValue(atributoAtacante);
        double victimaValor = victima.getAttributeValue(atributoVictima);

        if (atacanteValor > 0){
            damageFinal = damageOriginal * (atacanteValor / 100);
            damageFinal = calcularResistencia(damageFinal, victimaValor);
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