package dev.arakiel.alexsmobsdelight.helper;

import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

public class ItemHelper {
    /**
     * Tooltip component of one of our items, keyed by its registry path.
     *
     * <p>The 1.20.1 sources concatenated {@code stack.getItem()} into the key, but {@code Item} does not
     * override {@code toString()}, so the key became a class name and never resolved in game.</p>
     */
    public static MutableComponent tooltip(ItemStack stack) {
        return Component.translatable("tooltip.alexsmobsdelight."
                + BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
    }

    public static boolean itemHasSmeltResult(ItemStack itemStack, Level level){
        SingleRecipeInput input = new SingleRecipeInput(itemStack);
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, input, level).isPresent();
    }

    public static ItemStack getItemSmeltResult(ItemStack itemStack, Level level){
        if (itemHasSmeltResult(itemStack, level)) {
            SingleRecipeInput input = new SingleRecipeInput(itemStack);
            return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, input, level)
                    .map(recipe -> recipe.value().getResultItem(level.registryAccess()).copy())
                    .orElse(ItemStack.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    public static List<ItemStack> getBlockDrops(ServerLevel level, BlockPos pos, Player player, ItemStack tool) {
        BlockState state = level.getBlockState(pos);

        LootParams.Builder builder = new LootParams.Builder(level)
                .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                .withParameter(LootContextParams.BLOCK_STATE, state)
                .withParameter(LootContextParams.TOOL, tool);

        if (player != null) {
            builder.withParameter(LootContextParams.THIS_ENTITY, player);
        }
        return state.getDrops(builder);
    }

    public static Component customColor(Component name, int r, int g, int b){
        String text = name.getString();
        int rgb = (r << 16) | (g << 8) | b;
        return Component.literal(text).withStyle(style -> style.withColor(rgb));
    }

    public static boolean hasFlyingFishCan(Player player){
        for(ItemStack s : player.getAllSlots()){
            if(s.is(ItemRegistry.OPENED_SURFLYGFISK.get())){
                return true;
            }
        }
        return false;
    }
}
