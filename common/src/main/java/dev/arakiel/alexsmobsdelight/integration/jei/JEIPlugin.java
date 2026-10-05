package dev.arakiel.alexsmobsdelight.integration.jei;

import com.google.common.collect.ImmutableList;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.integration.jei.category.KiviakDecompositionRecipeCategory;
import dev.arakiel.alexsmobsdelight.integration.jei.resource.KiviakDecompositionDummy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;

/**
 * JEI plugin.
 *
 * <p>JEI is optional: the plugin is only loaded by JEI itself, and the Fabric entry point and the
 * {@code @JeiPlugin} annotation both point at this class. The API used here is the JEI 19
 * {@code RecipeType} based one.</p>
 */
@JeiPlugin
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MOD_ID, "jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new KiviakDecompositionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(KiviakDecompositionRecipeCategory.RECIPE_TYPE,
                ImmutableList.of(new KiviakDecompositionDummy()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
