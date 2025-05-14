package dev.fede2010.wrs.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record AtributosData(
        Optional<Double> slash,
        Optional<Double> bludgeon,
        Optional<Double> pierce,
        Optional<Double> arcane,
        Optional<Double> fire,
        Optional<Double> ice,
        Optional<Double> electric,
        Optional<Double> holy,
        Optional<Double> dark
) {
    public static final Codec<AtributosData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.DOUBLE.optionalFieldOf("slash").forGetter(AtributosData::slash),
                    Codec.DOUBLE.optionalFieldOf("bludgeon").forGetter(AtributosData::bludgeon),
                    Codec.DOUBLE.optionalFieldOf("pierce").forGetter(AtributosData::pierce),
                    Codec.DOUBLE.optionalFieldOf("arcane").forGetter(AtributosData::arcane),
                    Codec.DOUBLE.optionalFieldOf("fire").forGetter(AtributosData::fire),
                    Codec.DOUBLE.optionalFieldOf("ice").forGetter(AtributosData::ice),
                    Codec.DOUBLE.optionalFieldOf("electric").forGetter(AtributosData::electric),
                    Codec.DOUBLE.optionalFieldOf("holy").forGetter(AtributosData::holy),
                    Codec.DOUBLE.optionalFieldOf("dark").forGetter(AtributosData::dark)
            ).apply(instance, AtributosData::new)
    );

    public static final AtributosData ATRIBUTOS_VACIOS = new AtributosData(
            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()
    );

    public Double getSlash(){
        return slash().orElse(0.0);
    }

    public Double getBludgeon() {
        return bludgeon.orElse(0.0);
    }


    public Double getPierce() {
        return pierce.orElse(0.0);
    }


    public Double getArcane() {
        return arcane.orElse(0.0);
    }


    public Double getFire() {
        return fire.orElse(0.0);
    }


    public Double getIce() {
        return ice.orElse(0.0);
    }


    public Double getElectric() {
        return electric.orElse(0.0);
    }


    public Double getHoly() {
        return holy.orElse(0.0);
    }


    public Double getDark() {
        return dark.orElse(0.0);
    }

    public AtributosData combinar(AtributosData atributosDataNuevo) {
        return new AtributosData(
                Optional.of(this.getSlash() + atributosDataNuevo.getSlash()),
                Optional.of(this.getBludgeon() + atributosDataNuevo.getBludgeon()),
                Optional.of(this.getPierce() + atributosDataNuevo.getPierce()),
                Optional.of(this.getArcane() + atributosDataNuevo.getArcane()),
                Optional.of(this.getFire() + atributosDataNuevo.getFire()),
                Optional.of(this.getIce() + atributosDataNuevo.getIce()),
                Optional.of(this.getElectric() + atributosDataNuevo.getElectric()),
                Optional.of(this.getHoly() + atributosDataNuevo.getHoly()),
                Optional.of(this.getDark() + atributosDataNuevo.getDark())
        );
    }

}

