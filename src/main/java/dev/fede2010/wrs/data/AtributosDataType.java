package dev.fede2010.wrs.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public record AtributosDataType(
        List<ResourceLocation>ids,
        AtributosData damage,
        AtributosData resistance
) {
    public static final Codec<AtributosDataType> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceLocation.CODEC.listOf()
                            .optionalFieldOf("ids", List.of()) // Hace que "ids" sea opcional con una lista vacía por defecto
                            .forGetter(AtributosDataType::ids),
                    AtributosData.CODEC.optionalFieldOf("damage", AtributosData.ATRIBUTOS_VACIOS).forGetter(AtributosDataType::damage),
                    AtributosData.CODEC.optionalFieldOf("resistance", AtributosData.ATRIBUTOS_VACIOS).forGetter(AtributosDataType::resistance)
            ).apply(instance, AtributosDataType::new)
    );

    public static AtributosDataType reemplazarValores(AtributosDataType grupo, AtributosDataType entidad) {

        Optional<Double> slash     = Optional.of(entidad.damage().slash().orElse(grupo.damage().getSlash()));
        Optional<Double> bludgeon  = Optional.of(entidad.damage().bludgeon().orElse(grupo.damage().getBludgeon()));
        Optional<Double> pierce    = Optional.of(entidad.damage().pierce().orElse(grupo.damage().getPierce()));
        Optional<Double> arcane    = Optional.of(entidad.damage().arcane().orElse(grupo.damage().getArcane()));
        Optional<Double> fire      = Optional.of(entidad.damage().fire().orElse(grupo.damage().getFire()));
        Optional<Double> ice       = Optional.of(entidad.damage().ice().orElse(grupo.damage().getIce()));
        Optional<Double> electric  = Optional.of(entidad.damage().electric().orElse(grupo.damage().getElectric()));
        Optional<Double> holy      = Optional.of(entidad.damage().holy().orElse(grupo.damage().getHoly()));
        Optional<Double> dark      = Optional.of(entidad.damage().dark().orElse(grupo.damage().getDark()));

        Optional<Double> slashResist     = Optional.of(entidad.resistance().slash().orElse(grupo.resistance().getSlash()));
        Optional<Double> bludgeonResist  = Optional.of(entidad.resistance().bludgeon().orElse(grupo.resistance().getBludgeon()));
        Optional<Double> pierceResist    = Optional.of(entidad.resistance().pierce().orElse(grupo.resistance().getPierce()));
        Optional<Double> arcaneResist    = Optional.of(entidad.resistance().arcane().orElse(grupo.resistance().getArcane()));
        Optional<Double> fireResist      = Optional.of(entidad.resistance().fire().orElse(grupo.resistance().getFire()));
        Optional<Double> iceResist       = Optional.of(entidad.resistance().ice().orElse(grupo.resistance().getIce()));
        Optional<Double> electricResist  = Optional.of(entidad.resistance().electric().orElse(grupo.resistance().getElectric()));
        Optional<Double> holyResist      = Optional.of(entidad.resistance().holy().orElse(grupo.resistance().getHoly()));
        Optional<Double> darkResist      = Optional.of(entidad.resistance().dark().orElse(grupo.resistance().getDark()));

        return new AtributosDataType(
                List.of(),
                new AtributosData(slash, bludgeon, pierce, arcane, fire, ice, electric, holy, dark),
                new AtributosData(slashResist, bludgeonResist, pierceResist, arcaneResist, fireResist, iceResist, electricResist, holyResist, darkResist)
        );
    }

}