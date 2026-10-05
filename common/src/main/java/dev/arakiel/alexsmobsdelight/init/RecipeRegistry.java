package dev.arakiel.alexsmobsdelight.init;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.recipes.RecipeMimicandyDuplicate;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

/** Recipe serializers. */
public final class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> DEF_REG =
            DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.RECIPE_SERIALIZER);

    public static final RegistrySupplier<RecipeSerializer<?>> MIMICANDY_RECIPE = DEF_REG.register(
            "mimicandy_duplicate", () -> new SimpleCraftingRecipeSerializer<>(RecipeMimicandyDuplicate::new));

    private RecipeRegistry() {
    }

    public static void register() {
        DEF_REG.register();
    }
}
