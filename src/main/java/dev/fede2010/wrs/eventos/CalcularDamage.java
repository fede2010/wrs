package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
import dev.fede2010.wrs.data.DataLoaderEvent;
import dev.fede2010.wrs.data.DamageTypeData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
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

        float damageOriginal = event.getAmount();

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

        boolean especial = false;

        double damageModificado;

        if (victima == null) return;

        ResourceKey<DamageType> damageTypeResourceKey = event.getSource().typeHolder().unwrapKey().orElse(null);
        ResourceLocation damagetype = new ResourceLocation("");
        if (damageTypeResourceKey != null){
            damagetype = damageTypeResourceKey.location();
        }

        DamageTypeData dataDamage = DataLoaderEvent.DAMAGE_TYPES.getData().get(damagetype);
        if (dataDamage != null){
            especial = dataDamage.special();
        }

        if (atacante != null && !especial){

            if (event.getSource().isIndirect()) {
                if (event.getSource().getDirectEntity() instanceof LivingEntity atacanteDirecto) {
                    atacante = atacanteDirecto;
                }
            }

             slashDamage = calcularDamage(atacante.getAttributeValue(Atributos.SLASH.get()), victima.getAttributeValue(Atributos.SLASH_RESIST.get()), damageOriginal);
             bludgeonDamage = calcularDamage(atacante.getAttributeValue(Atributos.BLUDGEON.get()), victima.getAttributeValue(Atributos.BLUDGEON_RESIST.get()), damageOriginal);
             pierceDamage = calcularDamage(atacante.getAttributeValue(Atributos.PIERCE.get()), victima.getAttributeValue(Atributos.PIERCE_RESIST.get()), damageOriginal);
             arcaneDamage = calcularDamage(atacante.getAttributeValue(Atributos.ARCANE.get()), victima.getAttributeValue(Atributos.ARCANE_RESIST.get()), damageOriginal);
             fireDamage = calcularDamage(atacante.getAttributeValue(Atributos.FIRE.get()), victima.getAttributeValue(Atributos.FIRE_RESIST.get()), damageOriginal);
             iceDamage = calcularDamage(atacante.getAttributeValue(Atributos.ICE.get()), victima.getAttributeValue(Atributos.ICE_RESIST.get()), damageOriginal);
             electricDamage = calcularDamage(atacante.getAttributeValue(Atributos.ELECTRIC.get()), victima.getAttributeValue(Atributos.ELECTRIC_RESIST.get()), damageOriginal);
             holyDamage = calcularDamage(atacante.getAttributeValue(Atributos.HOLY.get()), victima.getAttributeValue(Atributos.HOLY_RESIST.get()), damageOriginal);
             darkDamage = calcularDamage(atacante.getAttributeValue(Atributos.DARK.get()), victima.getAttributeValue(Atributos.DARK_RESIST.get()), damageOriginal);

             damageModificado = slashDamage + bludgeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

            atributosMinimos = atributosMinimos - Atributos.atributosTotales(atacante);

            if (atributosMinimos > 0){
                damageModificado = damageModificado + (damageOriginal * (atributosMinimos / 100));
            }

            event.setAmount((float) damageModificado);
        }else {

            if (dataDamage == null)return;

            slashDamage = calcularDamage(dataDamage.damage().getSlash(), victima.getAttributeValue(Atributos.SLASH_RESIST.get()), damageOriginal);
            bludgeonDamage = calcularDamage(dataDamage.damage().getBludgeon(), victima.getAttributeValue(Atributos.BLUDGEON_RESIST.get()), damageOriginal);
            pierceDamage = calcularDamage(dataDamage.damage().getPierce(), victima.getAttributeValue(Atributos.PIERCE_RESIST.get()), damageOriginal);
            arcaneDamage = calcularDamage(dataDamage.damage().getArcane(), victima.getAttributeValue(Atributos.ARCANE_RESIST.get()), damageOriginal);
            fireDamage = calcularDamage(dataDamage.damage().getFire(), victima.getAttributeValue(Atributos.FIRE_RESIST.get()), damageOriginal);
            iceDamage = calcularDamage(dataDamage.damage().getIce(), victima.getAttributeValue(Atributos.ICE_RESIST.get()), damageOriginal);
            electricDamage = calcularDamage(dataDamage.damage().getElectric(), victima.getAttributeValue(Atributos.ELECTRIC_RESIST.get()), damageOriginal);
            holyDamage = calcularDamage(dataDamage.damage().getHoly(), victima.getAttributeValue(Atributos.HOLY_RESIST.get()), damageOriginal);
            darkDamage = calcularDamage(dataDamage.damage().getDark(), victima.getAttributeValue(Atributos.DARK_RESIST.get()), damageOriginal);

            damageModificado = slashDamage + bludgeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

            atributosMinimos = atributosMinimos - Atributos.atributosTotales(dataDamage);

            if (atributosMinimos > 0){
                damageModificado = damageModificado + (damageOriginal * (atributosMinimos / 100));
            }

            event.setAmount((float) damageModificado);

        }

        MostrarCalculo.obtenerDatos(damageOriginal, slashDamage, bludgeonDamage, pierceDamage, arcaneDamage, fireDamage, iceDamage, electricDamage, holyDamage, darkDamage, damageModificado);

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

}