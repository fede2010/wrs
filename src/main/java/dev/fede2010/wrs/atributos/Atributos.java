package dev.fede2010.wrs.atributos;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.data.AtributosDataType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class Atributos {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Wrs.MODID);

    public static final RegistryObject<Attribute> SLASH = ATTRIBUTES.register("slash", () -> (new RangedAttribute(Wrs.MODID + ".slash", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> BLUDGEON = ATTRIBUTES.register("bludgeon", () -> (new RangedAttribute(Wrs.MODID + ".bludgeon", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> PIERCE = ATTRIBUTES.register("pierce", () -> (new RangedAttribute(Wrs.MODID + ".pierce", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> ARCANE = ATTRIBUTES.register("arcane", () -> (new RangedAttribute(Wrs.MODID + ".arcane", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> FIRE = ATTRIBUTES.register("fire", () -> (new RangedAttribute(Wrs.MODID + ".fire", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> ICE = ATTRIBUTES.register("ice", () -> (new RangedAttribute(Wrs.MODID + ".ice", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> ELECTRIC = ATTRIBUTES.register("electric", () -> (new RangedAttribute(Wrs.MODID + ".electric", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> HOLY = ATTRIBUTES.register("holy", () -> (new RangedAttribute(Wrs.MODID + ".holy", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> DARK = ATTRIBUTES.register("dark", () -> (new RangedAttribute(Wrs.MODID + ".dark", 0.0, 0.0, Double.MAX_VALUE)).setSyncable(true));

    public static final RegistryObject<Attribute> SLASH_RESIST = ATTRIBUTES.register("slash_resist", () -> (new RangedAttribute(Wrs.MODID + ".slash_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> BLUDGEON_RESIST = ATTRIBUTES.register("bludgeon_resist", () -> (new RangedAttribute(Wrs.MODID + ".bludgeon_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> PIERCE_RESIST = ATTRIBUTES.register("pierce_resist", () -> (new RangedAttribute(Wrs.MODID + ".pierce_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> ARCANE_RESIST = ATTRIBUTES.register("arcane_resist", () -> (new RangedAttribute(Wrs.MODID + ".arcane_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> FIRE_RESIST = ATTRIBUTES.register("fire_resist", () -> (new RangedAttribute(Wrs.MODID + ".fire_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> ICE_RESIST = ATTRIBUTES.register("ice_resist", () -> (new RangedAttribute(Wrs.MODID + ".ice_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> ELECTRIC_RESIST = ATTRIBUTES.register("electric_resist", () -> (new RangedAttribute(Wrs.MODID + ".electric_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> HOLY_RESIST = ATTRIBUTES.register("holy_resist", () -> (new RangedAttribute(Wrs.MODID + ".holy_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));
    public static final RegistryObject<Attribute> DARK_RESIST = ATTRIBUTES.register("dark_resist", () -> (new RangedAttribute(Wrs.MODID + ".dark_resist", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setSyncable(true));


    public Atributos() {
    }

    public static void atributosMap(Map<Attribute, Double> atributosMap, AtributosDataType data){
        atributosMap.put(Atributos.SLASH.get(), data.damage().getSlash());
        atributosMap.put(Atributos.BLUDGEON.get(), data.damage().getBludgeon());
        atributosMap.put(Atributos.PIERCE.get(), data.damage().getPierce());
        atributosMap.put(Atributos.ARCANE.get(), data.damage().getArcane());
        atributosMap.put(Atributos.FIRE.get(), data.damage().getFire());
        atributosMap.put(Atributos.ICE.get(), data.damage().getIce());
        atributosMap.put(Atributos.ELECTRIC.get(), data.damage().getElectric());
        atributosMap.put(Atributos.HOLY.get(), data.damage().getHoly());
        atributosMap.put(Atributos.DARK.get(), data.damage().getDark());

        atributosMap.put(Atributos.SLASH_RESIST.get(), data.resistance().getSlash());
        atributosMap.put(Atributos.BLUDGEON_RESIST.get(), data.resistance().getBludgeon());
        atributosMap.put(Atributos.PIERCE_RESIST.get(), data.resistance().getPierce());
        atributosMap.put(Atributos.ARCANE_RESIST.get(), data.resistance().getArcane());
        atributosMap.put(Atributos.FIRE_RESIST.get(), data.resistance().getFire());
        atributosMap.put(Atributos.ICE_RESIST.get(), data.resistance().getIce());
        atributosMap.put(Atributos.ELECTRIC_RESIST.get(), data.resistance().getElectric());
        atributosMap.put(Atributos.HOLY_RESIST.get(), data.resistance().getHoly());
        atributosMap.put(Atributos.DARK_RESIST.get(), data.resistance().getDark());

    }

    public static double atributosTotales(LivingEntity entity) {
        Attribute[] damageAttributes = new Attribute[]{
                Atributos.SLASH.get(),
                Atributos.BLUDGEON.get(),
                Atributos.PIERCE.get(),
                Atributos.ARCANE.get(),
                Atributos.FIRE.get(),
                Atributos.ICE.get(),
                Atributos.ELECTRIC.get(),
                Atributos.HOLY.get(),
                Atributos.DARK.get()
        };

        double atributosTotales = 0.0;
        for (Attribute attr : damageAttributes) {
            atributosTotales += entity.getAttributeValue(attr);
        }

        return atributosTotales;
    }


    public static void register(IEventBus eventBus){
        ATTRIBUTES.register(eventBus);
    }
}
