package dev.arakiel.alexsmobsdelight.integration.jei.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import dev.arakiel.alexsmobsdelight.integration.jei.TooltipCallback;
import dev.arakiel.alexsmobsdelight.integration.jei.resource.KiviakDecompositionDummy;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

/** JEI page showing how kiviak ferments. */
public class KiviakDecompositionRecipeCategory implements IRecipeCategory<KiviakDecompositionDummy> {
    public static final RecipeType<KiviakDecompositionDummy> RECIPE_TYPE = RecipeType.create(
            AlexsMobsDelight.MOD_ID, "kiviak_decomposition", KiviakDecompositionDummy.class);

    private static final int SLOT_SIZE = 22;

    private final Component title;
    private final IDrawable background;
    private final IDrawable slotIcon;
    private final IDrawable icon;

    public KiviakDecompositionRecipeCategory(IGuiHelper helper) {
        this.title = Component.translatable(AlexsMobsDelight.MOD_ID + ".jei.kiviak_decomposition");
        ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MOD_ID,
                "textures/gui/jei/kiviak_decomposition.png");
        this.background = helper.createDrawable(backgroundImage, 0, 0, 118, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ItemRegistry.POLAR_KIVIAK.get()));
        this.slotIcon = helper.createDrawable(backgroundImage, 119, 0, SLOT_SIZE, SLOT_SIZE);
    }

    @Override
    public RecipeType<KiviakDecompositionDummy> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    @SuppressWarnings("removal")
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    @SuppressWarnings("removal")
    public void setRecipe(IRecipeLayoutBuilder builder, KiviakDecompositionDummy recipe, IFocusGroup focusGroup) {
        List<ItemStack> accelerators = new ArrayList<>();
        for (Holder<Block> holder : BuiltInRegistries.BLOCK.getTagOrEmpty(BlockTags.ICE)) {
            accelerators.add(new ItemStack(holder.value()));
        }
        List<ItemStack> knives = new ArrayList<>();
        for (Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(ModTags.Items.KNIVES)) {
            knives.add(new ItemStack(holder.value()));
        }

        List<ItemStack> inputs = List.of(new ItemStack(ItemRegistry.COASTAL_KIVIAK.get()),
                new ItemStack(ItemRegistry.POLAR_KIVIAK.get()));
        ItemStack result = new ItemStack(ItemRegistry.KIVIAK.get(), 6);
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 26).addItemStacks(inputs);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 26).addItemStack(result)
                .addTooltipCallback(new TooltipCallback());
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 93, 2).addItemStacks(knives);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 64, 54).addItemStacks(accelerators);
    }

    @Override
    public void draw(KiviakDecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
                     double mouseX, double mouseY) {
        this.slotIcon.draw(guiGraphics, 63, 53);
    }

    @Override
    @SuppressWarnings("removal")
    public List<Component> getTooltipStrings(KiviakDecompositionDummy recipe, IRecipeSlotsView recipeSlotsView,
                                             double mouseX, double mouseY) {
        if (ClientRenderUtils.isCursorInsideBounds(40, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".light"));
        }
        if (ClientRenderUtils.isCursorInsideBounds(53, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".biome"));
        }
        if (ClientRenderUtils.isCursorInsideBounds(67, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".accelerators"));
        }
        return Collections.emptyList();
    }

    private static MutableComponent translateKey(String suffix) {
        return Component.translatable(AlexsMobsDelight.MOD_ID + ".jei.kiviak_decomposition" + suffix);
    }
}
