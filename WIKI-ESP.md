# Documentación del Mod WRS

## 1. Atributos de daño y resistencia
- **Entidades** e **items** pueden tener valores de daño y resistencia. Los **tipos de daño** solo aceptan valores de daño.
- Los valores son porcentajes y pueden ser positivos o negativos.

## 2. Prioridad de los tipos de daño
- Puedes definir **grupos** (varias entidades o ítems) para asignar valores, pero las **configuraciones individuales** reemplazan los valores definidos por los grupos.
- Si un `tipo de daño` se marca como **especial** (p. ej. hechizos), siempre ignora el daño de la entidad y aplica solo el del `tipo de daño`.

## 3. Cómo funcionan los valores
- **Daño**
    - 100 % = todo el daño es de ese tipo.
    - \> 100 % = exceso se añade como daño extra.
    - Los valores negativos reducen el porcentaje de ese tipo de daño, hasta un mínimo de 0 %.

- **Resistencia**
    - ≥ 100 % = invulnerabilidad total a ese tipo.
    - < 0 % = recibe daño extra.

## 4. Configuración mediante datapacks

Dentro de la carpeta de tu datapack (`<tu_datapack>/data/wrs/`), la estructura debe quedar así:


> **Nota**: la carpeta raíz de tu datapack debe ser:
> ```  
> <tu_datapack>/  
> ├── data/  
> │   └── wrs/  
> │       ├── damage_types/  
> │       │   └── <modid>/  
> │       │       └── <damage_type_nombre>.json  
> │       ├── entities/  
> │       │   └── <modid>/  
> │       │       └── <entity_nombre>.json  
> │       ├── items/  
> │       │   └── <modid>/  
> │       │       └── <item_nombre>.json  
> │       └── groups/  
> │           └── <cualquier_nombre>.json  
> └── pack.mcmeta  
> ```  

> **Importante**: El nombre del archivo `.json` debe coincidir exactamente con el nombre del `ítem`, `entidad` o `tipo de daño` (por ejemplo, `minecraft:zombie` → `zombie.json`).  
> Además, deben colocarse dentro de la carpeta del `modid` correspondiente (por ejemplo, `entities/minecraft/zombie.json` o `items/mi_mod/espada_magica.json`).

## 5. Especificación de los archivos JSON

Cada archivo JSON puede contener estos campos:
- **damage** (opcional): objeto con pares `"tipo": valor` donde `valor` es porcentaje de daño (positivo o negativo).
- **resistance** (opcional): objeto con pares `"tipo": valor` donde `valor` es porcentaje de resistencia (positivo o negativo).
- **special** (solo en **damage_types**): booleano; si es `true`, ignora toda configuración de entidad y aplica únicamente este daño.
- **ids** (solo en **groups**): lista de identificadores `"modid:itemid"` o `"modid:entityid"` a los que aplica la configuración de grupo.

Los tipos disponibles (para `damage` y `resistance`) son:  
`slash`, `bludgeon`, `pierce`, `arcane`, `fire`, `ice`, `electric`, `holy`, `dark`

### Ejemplos

#### Tipo De Daño
```json
{
  "damage": {
    "slash":    0.0,
    "bludgeon": 0.0,
    "pierce":  100.0,
    "arcane":   0.0,
    "fire":     0.0,
    "ice":      0.0,
    "electric": 0.0,
    "holy":     0.0,
    "dark":     0.0
  },
  "special": true
}
```
#### Entidades o Items

```json
{
  "damage": {
    "slash":    100.0,
    "bludgeon":   0.0,
    "pierce":     0.0,
    "arcane":     0.0,
    "fire":       0.0,
    "ice":        0.0,
    "electric":   0.0,
    "holy":       0.0,
    "dark":     -30.0
  },
  "resistance": {
    "slash":      0.0,
    "bludgeon":   0.0,
    "pierce":     0.0,
    "arcane":     0.0,
    "fire":       0.0,
    "ice":        0.0,
    "electric":   0.0,
    "holy":       0.0,
    "dark":       0.0
  }
}
```
#### Grupos
Aplica una configuración a múltiples entidades y/o ítems.

```json
{
  "ids": [
    "minecraft:zombie",
    "minecraft:diamond_sword"
  ],
  "damage": {
    "slash":    100.0,
    "bludgeon": 0.0,
    "pierce":  0.0,
    "arcane":   0.0,
    "fire":     0.0,
    "ice":      0.0,
    "electric": 0.0,
    "holy":     0.0,
    "dark":     0.0
  },
  "resistance": {
    "slash":      0.0,
    "bludgeon":   0.0,
    "pierce":     0.0,
    "arcane":     0.0,
    "fire":       0.0,
    "ice":        0.0,
    "electric":   0.0,
    "holy":       0.0,
    "dark":       0.0
  }
}
```
**Tip**: Puedes omitir los campos `damage` o `resistance` si no los necesitas (por ejemplo, si todos sus valores serían 0.0).
Además, dentro de `damage` o `resistance`, puedes omitir tipos de daño específicos; cualquier tipo de daño no definido se asumirá con un valor de 0.0.