# WRS Mod Documentation

## 1. Damage and Resistance Attributes
- **Entities** and **items** can have damage and resistance values. **Damage types** only accept damage values.
- Values are percentages and can be positive or negative.

## 2. Damage Type Priority
- You can define **groups** (multiple entities or items) to assign values, but **individual configurations** override group-defined values.
- If a `damage type` is marked as **special** (e.g. spells), it always ignores the entity’s damage settings and applies only the `damage type` value.

## 3. How Values Work
- **Damage**
    - 100 % = all damage is of that type.
    - \> 100 % = the excess is added as extra damage.
    - Negative values reduce the percentage of that damage type, down to a minimum of 0 %.

- **Resistance**
    - ≥ 100 % = complete invulnerability to that type.
    - < 0 % = takes extra damage.

## 4. Datapack Configuration

Inside your datapack folder (`<your_datapack>/data/wrs/`), the structure should look like this:

> **Note**: The root of your datapack should be:
> ```
> <your_datapack>/
> ├── data/
> │   └── wrs/
> │       ├── damage_types/
> │       │   └── <modid>/
> │       │       └── <damage_type_name>.json
> │       ├── entities/
> │       │   └── <modid>/
> │       │       └── <entity_name>.json
> │       ├── items/
> │       │   └── <modid>/
> │       │       └── <item_name>.json
> │       └── groups/
> │           └── <any_name>.json
> └── pack.mcmeta
> ```

> **Important**: The `.json` filename must exactly match the `item`, `entity`, or `damage type` name (for example, `minecraft:zombie` → `zombie.json`).  
> Also, it must be placed within the corresponding `modid` folder (e.g., `entities/minecraft/zombie.json` or `items/my_mod/magic_sword.json`).

## 5. JSON File Specification

Each JSON file may contain these fields:
- **damage** (optional): an object of `"type": value` pairs, where `value` is the damage percentage (positive or negative).
- **resistance** (optional): an object of `"type": value` pairs, where `value` is the resistance percentage (positive or negative).
- **special** (only in **damage_types**): boolean; if `true`, ignores all entity settings and applies only this damage.
- **ids** (only in **groups**): a list of identifiers `"modid:itemid"` or `"modid:entityid"` to which the group configuration applies.

Available types (for both `damage` and `resistance`):  
`slash`, `bludgeon`, `pierce`, `arcane`, `fire`, `ice`, `electric`, `holy`, `dark`

### Examples

#### Damage Type
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
#### Entities or Items

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
#### Groups
Apply a configuration to multiple entities and/or items.

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
**Tip**: You can omit the `damage` or `resistance` fields if you don’t need them (e.g., if all values would be 0.0).
Also, within `damage` or `resistance`, you can omit specific damage types; any undefined type defaults to 0.0.