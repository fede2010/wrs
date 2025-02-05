package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.atributos.Atributos;
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

    //IDEA podria detectar cuando un atributo viene de un item y la cantidad, luego restarle o sumarle esa cantidad al jugador
    //y aplicar daños y resistencias por separado, es decir podria usar el daño del evento para los calculos de entidades y el atributo de daño de ataque y armadura para los calculos de los items
    //y despues sumar todos los calculos para el daño final

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        // Obtenemos la entidad que está recibiendo daño
        LivingEntity victima = event.getEntity();
        LivingEntity atacante = (LivingEntity) event.getSource().getEntity();

        if (victima == null) return;
        if (atacante == null) return;

        float damageOriginal = event.getAmount();
        double damageModificado;

        double slashDamage = damageFinal(atacante, Atributos.SLASH.get(), victima, Atributos.SLASH_RESIST.get(), damageOriginal);
        double bludeonDamage = damageFinal(atacante, Atributos.BLUDGEON.get(), victima, Atributos.BLUDGEON_RESIST.get(), damageOriginal);
        double pierceDamage = damageFinal(atacante, Atributos.PIERCE.get(), victima, Atributos.PIERCE_RESIST.get(), damageOriginal);
        double arcaneDamage = damageFinal(atacante, Atributos.ARCANE.get(), victima, Atributos.ARCANE_RESIST.get(), damageOriginal);
        double fireDamage = damageFinal(atacante, Atributos.FIRE.get(), victima, Atributos.FIRE_RESIST.get(), damageOriginal);
        double iceDamage = damageFinal(atacante, Atributos.ICE.get(), victima, Atributos.ICE_RESIST.get(), damageOriginal);
        double electricDamage = damageFinal(atacante, Atributos.ELECTRIC.get(), victima, Atributos.ELECTRIC_RESIST.get(), damageOriginal);
        double holyDamage = damageFinal(atacante, Atributos.HOLY.get(), victima, Atributos.HOLY_RESIST.get(), damageOriginal);
        double darkDamage = damageFinal(atacante, Atributos.DARK.get(), victima, Atributos.DARK_RESIST.get(), damageOriginal);

        damageModificado = slashDamage + bludeonDamage + pierceDamage + arcaneDamage + fireDamage + iceDamage + electricDamage + holyDamage + darkDamage;

        if (Atributos.atributosMinimos(atacante)){
            event.setAmount((float) damageModificado);
        }

    }

    //obtiene el valor de los atributos de ataque de la entidad
    public static double damageFinal(LivingEntity atacante, Attribute atributoAtacante, LivingEntity victima, Attribute atributoVictima, float damageOriginal){

        double damageFinal = 0.0;

        double atacanteValor = atacante.getAttributeValue(atributoAtacante);
        double victimaValor = victima.getAttributeValue(atributoVictima);

        if (atacanteValor > 0){
            damageFinal = damageOriginal * (atacanteValor / 100);
            if (victimaValor > 0 && victimaValor <= 100){
                damageFinal *= 1 - (victimaValor / 100);
            }else if (victimaValor > 0){
                damageFinal = 0;
            }else damageFinal *= 1 + (Math.abs(victimaValor) / 100);
        }

        return damageFinal;
    }

}
