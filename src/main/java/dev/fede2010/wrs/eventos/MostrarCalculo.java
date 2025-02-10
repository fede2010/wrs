package dev.fede2010.wrs.eventos;

import dev.fede2010.wrs.Config;
import dev.fede2010.wrs.Wrs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(
        modid = Wrs.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class MostrarCalculo {

    public static float damageOriginal;
    public static double slashDamage;
    public static double bludgeonDamage;
    public static double pierceDamage;
    public static double arcaneDamage;
    public static double fireDamage;
    public static double iceDamage;
    public static double electricDamage;
    public static double holyDamage;
    public static double darkDamage;
    public static double damageModificado;

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {

        if (Config.debug){
            if (event.getSource().getEntity() instanceof ServerPlayer atacante) {

                mensaje(atacante, Wrs.MODID + ".initial_damage", damageOriginal, ChatFormatting.GREEN);
                mensaje(atacante, Wrs.MODID + ".value.slash", slashDamage, ChatFormatting.GRAY);
                mensaje(atacante, Wrs.MODID + ".value.bludgeon", bludgeonDamage, ChatFormatting.GRAY);
                mensaje(atacante, Wrs.MODID + ".value.pierce", pierceDamage, ChatFormatting.GRAY);
                mensaje(atacante, Wrs.MODID + ".value.arcane", arcaneDamage, ChatFormatting.GRAY);
                mensaje(atacante, Wrs.MODID + ".value.fire", fireDamage, ChatFormatting.RED);
                mensaje(atacante, Wrs.MODID + ".value.ice", iceDamage, ChatFormatting.AQUA);
                mensaje(atacante, Wrs.MODID + ".value.electric", electricDamage, ChatFormatting.YELLOW);
                mensaje(atacante, Wrs.MODID + ".value.holy", holyDamage, ChatFormatting.WHITE);
                mensaje(atacante, Wrs.MODID + ".value.dark", darkDamage, ChatFormatting.DARK_GRAY);
                mensaje(atacante, Wrs.MODID + ".final_damage", damageModificado, ChatFormatting.GREEN);

            }

            if (event.getEntity() instanceof ServerPlayer victima) {

                if (damageOriginal == 0){
                    String damageType = new ResourceLocation(event.getSource().getMsgId().toLowerCase(Locale.ROOT)).toString();

                    victima.sendSystemMessage(Component.translatable(Wrs.MODID + ".no_data", damageType)
                            .withStyle(ChatFormatting.WHITE));
                    return;
                }

                mensaje(victima, Wrs.MODID + ".initial_damage", damageOriginal, ChatFormatting.GREEN);
                mensaje(victima, Wrs.MODID + ".value.slash", slashDamage, ChatFormatting.GRAY);
                mensaje(victima, Wrs.MODID + ".value.bludgeon", bludgeonDamage, ChatFormatting.GRAY);
                mensaje(victima, Wrs.MODID + ".value.pierce", pierceDamage, ChatFormatting.GRAY);
                mensaje(victima, Wrs.MODID + ".value.arcane", arcaneDamage, ChatFormatting.GRAY);
                mensaje(victima, Wrs.MODID + ".value.fire", fireDamage, ChatFormatting.RED);
                mensaje(victima, Wrs.MODID + ".value.ice", iceDamage, ChatFormatting.AQUA);
                mensaje(victima, Wrs.MODID + ".value.electric", electricDamage, ChatFormatting.YELLOW);
                mensaje(victima, Wrs.MODID + ".value.holy", holyDamage, ChatFormatting.WHITE);
                mensaje(victima, Wrs.MODID + ".value.dark", darkDamage, ChatFormatting.DARK_GRAY);
                mensaje(victima, Wrs.MODID + ".final_damage", damageModificado, ChatFormatting.GREEN);
            }

        }

        damageOriginal = 0;
        slashDamage = 0;
        bludgeonDamage = 0;
        pierceDamage = 0;
        arcaneDamage = 0;
        fireDamage = 0;
        iceDamage = 0;
        electricDamage = 0;
        holyDamage = 0;
        darkDamage = 0;
        damageModificado = 0;

    }

    public static void obtenerDatos(float damageOriginal, double slashDamage, double bludgeonDamage, double pierceDamage, double arcaneDamage, double fireDamage, double iceDamage, double electricDamage, double holyDamage, double darkDamage, double damageModificado){
        MostrarCalculo.damageOriginal = damageOriginal;
        MostrarCalculo.slashDamage = slashDamage;
        MostrarCalculo.bludgeonDamage = bludgeonDamage;
        MostrarCalculo.pierceDamage = pierceDamage;
        MostrarCalculo.arcaneDamage = arcaneDamage;
        MostrarCalculo.fireDamage = fireDamage;
        MostrarCalculo.iceDamage = iceDamage;
        MostrarCalculo.electricDamage = electricDamage;
        MostrarCalculo.holyDamage = holyDamage;
        MostrarCalculo.darkDamage = darkDamage;
        MostrarCalculo.damageModificado = damageModificado;
    }

    private static void mensaje(ServerPlayer serverPlayer, String translatable, double damage, ChatFormatting color){
        serverPlayer.sendSystemMessage(Component.translatable(translatable, damage)
                .withStyle(color));
    }

}
