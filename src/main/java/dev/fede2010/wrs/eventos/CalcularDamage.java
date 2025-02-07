package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Config;
import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
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

        double slashDamage = 0;
        double bludgeonDamage = 0;
        double pierceDamage = 0;
        double arcaneDamage = 0;
        double fireDamage = 0;
        double iceDamage = 0;
        double electricDamage = 0;
        double holyDamage = 0;
        double darkDamage = 0;

        float damageOriginal = event.getAmount();

        if (victima == null) return;

        if (atacante != null){

            double atributosMinimos = 100;

             slashDamage = calcularDamage(atacante, Atributos.SLASH.get(), victima, Atributos.SLASH_RESIST.get(), damageOriginal);
             bludgeonDamage = calcularDamage(atacante, Atributos.BLUDGEON.get(), victima, Atributos.BLUDGEON_RESIST.get(), damageOriginal);
             pierceDamage = calcularDamage(atacante, Atributos.PIERCE.get(), victima, Atributos.PIERCE_RESIST.get(), damageOriginal);
             arcaneDamage = calcularDamage(atacante, Atributos.ARCANE.get(), victima, Atributos.ARCANE_RESIST.get(), damageOriginal);
             iceDamage = calcularDamage(atacante, Atributos.ICE.get(), victima, Atributos.ICE_RESIST.get(), damageOriginal);
             electricDamage = calcularDamage(atacante, Atributos.ELECTRIC.get(), victima, Atributos.ELECTRIC_RESIST.get(), damageOriginal);
             holyDamage = calcularDamage(atacante, Atributos.HOLY.get(), victima, Atributos.HOLY_RESIST.get(), damageOriginal);
             darkDamage = calcularDamage(atacante, Atributos.DARK.get(), victima, Atributos.DARK_RESIST.get(), damageOriginal);

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
            // Arrays de IDs para cada atributo. Más adelante hacer que se implementen desde un datapack.
            String[] slashDamageIds  = {"minecraft:cactus", "minecraft:sweetberrybush"};  // Ejemplo
            String[] bludgeonDamageIds  = {"minecraft:falling_block", "minecraft:falling_anvil", "minecraft:fall", "minecraft:thrown", "minecraft:fireworks", "minecraft:explosion"};  // Ejemplo
            String[] pierceDamageIds  = {"minecraft:arrow", "minecraft:trident", "minecraft:falling_stalactite", "minecraft:stalagmite", "minecraft:thorns"};  // Ejemplo
            String[] arcaneDamageIds  = {"minecraft:magic"};  // Ejemplo
            String[] fireDamageIds   = {"minecraft:infire", "minecraft:onfire", "minecraft:hotfloor", "minecraft:lava", "minecraft:unattributed_fireball"};
            String[] iceDamageIds    = {"minecraft:freeze", "minecraft:drown"}; // Ejemplo
            String[] electricIds     = {"minecraft:lightningBolt"}; // Ejemplo
            String[] holyIds         = {"minecraft:indirect_magic"}; // Ejemplo
            String[] darkIds         = {"minecraft:wither", "minecraft:poison", "minecraft:sonic_boom", "minecraft:wither_skull"}; // Ejemplo

            boolean damageEncontrado = false;

            if (isMatchingDamageType(event, slashDamageIds)){
                slashDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.SLASH_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, bludgeonDamageIds)){
                bludgeonDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.BLUDGEON_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, pierceDamageIds)){
                pierceDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.PIERCE_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, arcaneDamageIds)){
                arcaneDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.ARCANE_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, fireDamageIds)){
                fireDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.FIRE_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, iceDamageIds)){
                iceDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.ICE_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, electricIds)){
                electricDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.ELECTRIC_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, holyIds)){
                holyDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.HOLY_RESIST.get()));
                damageEncontrado = true;
            }

            if (isMatchingDamageType(event, darkIds)){
                darkDamage = calcularResistencia(damageOriginal, victima.getAttributeValue(Atributos.DARK_RESIST.get()));
                damageEncontrado = true;
            }

            double damageModificado = slashDamage + bludgeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

            if (damageEncontrado){
                event.setAmount((float) damageModificado);
            }else {
                event.setAmount(damageOriginal);
            }

        }

    }

    // Metodo auxiliar que verifica si el ID del daño recibido coincide con alguno de los IDs del array.
    private static boolean isMatchingDamageType(LivingHurtEvent event, String[] damageIds) {
        String msgId = new ResourceLocation(event.getSource().getMsgId().toLowerCase(Locale.ROOT)).toString();
        for (String id : damageIds) {
            if (msgId.equals(id)) {
                return true;
            }
        }
        return false;
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