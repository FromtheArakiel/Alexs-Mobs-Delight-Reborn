package dev.arakiel.alexsmobsdelight.recipes;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import dev.arakiel.alexsmobsdelight.init.RecipeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * Mimicandy: eight mimicandy around one food copy that food.
 *
 * <p>1.21 recipes no longer carry their own id, use {@link CraftingInput} instead of the old crafting
 * container and store edibility in the food data component.</p>
 */
public class RecipeMimicandyDuplicate extends CustomRecipe {
    private static final int REQUIRED_MIMICANDY = 8;

    public RecipeMimicandyDuplicate(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!AMConfig.mimicreamRepair) {
            return false;
        }
        ItemStack edible = ItemStack.EMPTY;
        int mimicandy = 0;
        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (isEdibleFood(stack)) {
                edible = stack;
            } else if (stack.is(ItemRegistry.MIMICANDY.get())) {
                ++mimicandy;
            }
        }
        return !edible.isEmpty() && mimicandy >= REQUIRED_MIMICANDY;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack edible = ItemStack.EMPTY;
        int mimicandy = 0;
        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (isEdibleFood(stack)) {
                edible = stack;
            } else if (stack.is(ItemRegistry.MIMICANDY.get())) {
                ++mimicandy;
            }
        }
        if (!edible.isEmpty() && mimicandy >= REQUIRED_MIMICANDY) {
            return new ItemStack(edible.getItem(), 1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int slot = 0; slot < remaining.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.has(DataComponents.FOOD) && !stack.is(ItemRegistry.MIMICANDY.get())) {
                ItemStack copy = stack.copy();
                copy.setCount(1);
                remaining.set(slot, copy);
                break;
            }
        }
        return remaining;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.MIMICANDY_RECIPE.get();
    }

    private boolean isEdibleFood(ItemStack stack) {
        return stack.has(DataComponents.FOOD) && !isBlacklisted(stack)
                && !stack.is(ItemRegistry.MIMICANDY.get());
    }

    private boolean isBlacklisted(ItemStack stack) {
        ResourceLocation name = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return name != null && AMConfig.mimicreamBlacklist.contains(name.toString());
    }
}
