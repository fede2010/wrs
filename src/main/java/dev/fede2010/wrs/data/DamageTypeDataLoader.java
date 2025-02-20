package dev.fede2010.wrs.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import dev.fede2010.wrs.Wrs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DamageTypeDataLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new Gson();
    private Map<ResourceLocation, DamageTypeData> data = new HashMap<>();

    public DamageTypeDataLoader(String ruta) {
        super(GSON, ruta);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager manager, ProfilerFiller profiler) {
        this.data.clear();

        jsons.forEach((fileId, json) -> {

            DamageTypeData.CODEC.parse(JsonOps.INSTANCE, json)
                    .resultOrPartial(error -> {
                        Wrs.LOGGER.error("Error al cargar {}: {}", fileId, error);
                    })
                    .ifPresent(d -> {

                        {
                            // Lógica nueva para archivos individuales
                            String path = fileId.getPath();
                            String[] pathParts = path.split("/");

                            if (pathParts.length >= 2) {
                                String targetNamespace = pathParts[0];
                                String targetPath = String.join("/", Arrays.copyOfRange(pathParts, 1, pathParts.length))
                                        .replace(".json", ""); // Eliminar extensión si existe

                                ResourceLocation targetId = new ResourceLocation(targetNamespace, targetPath);

                                this.data.put(targetId, d);
                            } else {
                                Wrs.LOGGER.error("Ruta inválida para archivo individual: {}", fileId);
                            }
                        }
                    });
        });

    }

    public Map<ResourceLocation, DamageTypeData> getData() {
        return data;
    }
}
