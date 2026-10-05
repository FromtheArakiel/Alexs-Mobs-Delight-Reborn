package dev.arakiel.alexsmobsdelight.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.architectury.platform.Platform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

/**
 * Common configuration.
 *
 * <p>The 1.20.1 sources used {@code ForgeConfigSpec}. Both loaders now read the same json file so a
 * pack behaves identically everywhere. The field names mirror the old spec, including the
 * {@code .get()} accessor the call sites already use.</p>
 */
public final class Config {
    // COMMON
    public static final Value<Integer> ETERNAL_SEAGULL_COOLDOWN = new Value<>("eternal_seagull.eat_cooldown", 160);
    public static final Value<Integer> CROCODILE_KNIFE_LOOT = new Value<>("crocodile_karambit.looting_chance", 20);
    public static final Value<Double> CROCODILE_KNIFE_HEALTH = new Value<>("crocodile_karambit.looting_health", 2.0D);
    public static final Value<Boolean> CROCODILE_KNIFE_FULL_DROP = new Value<>("crocodile_karambit.looting_all_items", false);
    public static final Value<Integer> ORCA_GIFT_CHANCE = new Value<>("orcas_leap_stew.give_chance", 30);
    public static final Value<Boolean> STACKABLE_SOUP_ITEMS = new Value<>("misc.stackable_soup_items", true);
    public static final Value<Boolean> PUPFISH_BREAK = new Value<>("misc.pupfish_break_food_blocks", true);
    public static final Value<Integer> TIGER_SUMMON_RANGE = new Value<>("tiger_cake_roll.summon_range", 16);

    public static final Value<List<String>> ETERNAL_FOODS = new Value<>("eternal_seagull.food_items",
            List.of("alexsmobsdelight:eternal_cooked_seagull",
                    "alexsmobsdelight:enchanted_eternal_cooked_seagull",
                    "artifacts:eternal_steak",
                    "artifacts:everlasting_beef"));
    public static final Value<List<String>> ENCHANTED_SEAGULL_BLACKLIST = new Value<>("eternal_seagull.blacklist",
            List.of("someassemblyrequired:sandwich"));
    public static final Value<List<String>> LOOTING_ENTITIES_BLACKLIST = new Value<>("crocodile_karambit.blacklist",
            List.of("alexsmobs:void_worm"));

    public static List<Item> ETERNAL_FOODS_ITEMS = List.of();
    public static List<Item> ENCHANTED_SEAGULL_BLACKLIST_ITEMS = List.of();
    public static List<EntityType<?>> LOOTING_BLACKLIST = List.of();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private Config() {
    }

    public static void load() {
        Path file = Platform.getConfigFolder().resolve(AlexsMobsDelight.MOD_ID + "-common.json");
        JsonObject root = new JsonObject();
        if (Files.exists(file)) {
            try {
                JsonObject parsed = GSON.fromJson(Files.readString(file, StandardCharsets.UTF_8), JsonObject.class);
                if (parsed != null) {
                    root = parsed;
                }
            } catch (IOException | RuntimeException exception) {
                AlexsMobsDelight.LOGGER.warn("Could not read {}, falling back to defaults", file, exception);
            }
        }
        JsonObject written = new JsonObject();
        for (Value<?> value : Value.ALL) {
            value.read(root, written);
        }
        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, GSON.toJson(written), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            AlexsMobsDelight.LOGGER.warn("Could not write {}", file, exception);
        }

        ETERNAL_FOODS_ITEMS = resolveItems(ETERNAL_FOODS.get());
        ENCHANTED_SEAGULL_BLACKLIST_ITEMS = resolveItems(ENCHANTED_SEAGULL_BLACKLIST.get());
        List<EntityType<?>> blacklist = new ArrayList<>();
        for (String name : LOOTING_ENTITIES_BLACKLIST.get()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(ResourceLocation.parse(name)).ifPresent(blacklist::add);
        }
        LOOTING_BLACKLIST = List.copyOf(blacklist);
    }

    private static List<Item> resolveItems(List<String> names) {
        List<Item> items = new ArrayList<>();
        for (String name : names) {
            BuiltInRegistries.ITEM.getOptional(ResourceLocation.parse(name)).ifPresent(items::add);
        }
        return List.copyOf(items);
    }

    /**
     * A single configuration entry. {@code .get()} keeps the old {@code ForgeConfigSpec} call sites
     * working unchanged.
     */
    public static final class Value<T> implements Supplier<T> {
        private static final List<Value<?>> ALL = new ArrayList<>();

        private final String path;
        private final T fallback;
        private T value;

        private Value(String path, T fallback) {
            this.path = path;
            this.fallback = fallback;
            this.value = fallback;
            ALL.add(this);
        }

        @Override
        public T get() {
            return value;
        }

        private void read(JsonObject root, JsonObject written) {
            value = coerce(readPath(root, path));
            writePath(written, path, value);
        }

        @SuppressWarnings("unchecked")
        private T coerce(Object stored) {
            if (stored == null) {
                return fallback;
            }
            try {
                if (fallback instanceof Integer) {
                    return (T) Integer.valueOf(((Number) stored).intValue());
                }
                if (fallback instanceof Double) {
                    return (T) Double.valueOf(((Number) stored).doubleValue());
                }
                if (fallback instanceof Boolean) {
                    return (T) Boolean.valueOf(String.valueOf(stored));
                }
                if (fallback instanceof List<?>) {
                    List<String> list = new ArrayList<>();
                    for (Object entry : (Iterable<?>) stored) {
                        list.add(String.valueOf(entry));
                    }
                    return (T) List.copyOf(list);
                }
            } catch (RuntimeException exception) {
                AlexsMobsDelight.LOGGER.warn("Invalid value for {}, using {}", path, fallback);
            }
            return fallback;
        }

        private static Object readPath(JsonObject root, String path) {
            JsonObject node = root;
            String[] parts = path.split("\\.");
            for (int index = 0; index < parts.length - 1; index++) {
                if (!node.has(parts[index]) || !node.get(parts[index]).isJsonObject()) {
                    return null;
                }
                node = node.getAsJsonObject(parts[index]);
            }
            return node.has(parts[parts.length - 1])
                    ? GSON.fromJson(node.get(parts[parts.length - 1]), Object.class) : null;
        }

        private static void writePath(JsonObject root, String path, Object value) {
            JsonObject node = root;
            String[] parts = path.split("\\.");
            for (int index = 0; index < parts.length - 1; index++) {
                if (!node.has(parts[index]) || !node.get(parts[index]).isJsonObject()) {
                    node.add(parts[index], new JsonObject());
                }
                node = node.getAsJsonObject(parts[index]);
            }
            node.add(parts[parts.length - 1], GSON.toJsonTree(value));
        }
    }
}
