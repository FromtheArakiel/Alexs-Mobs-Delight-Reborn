package dev.arakiel.alexsmobsdelight.helper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/**
 * Stores the list of effects an enchanted seagull absorbed.
 *
 * <p>The 1.20.1 sources wrote a raw NBT list into the item tag. 1.21 keeps item NBT in a data
 * component and serialises effects through their codec, so this helper does both directions.</p>
 */
public final class EffectListHelper {
    public static final String KEY = "AmdConsumedFoodEffects";

    private EffectListHelper() {
    }

    public static List<MobEffectInstance> getEffects(ItemStack stack) {
        CompoundTag tag = customTag(stack);
        List<MobEffectInstance> effects = new ArrayList<>();
        if (!tag.contains(KEY)) {
            return effects;
        }
        Tag stored = tag.get(KEY);
        MobEffectInstance.CODEC.listOf().parse(NbtOps.INSTANCE, stored).result()
                .ifPresent(effects::addAll);
        return effects;
    }

    public static void setEffects(ItemStack stack, List<MobEffectInstance> effects) {
        if (effects.isEmpty()) {
            return;
        }
        MobEffectInstance.CODEC.listOf().encodeStart(NbtOps.INSTANCE, effects).result().ifPresent(encoded -> {
            CompoundTag tag = customTag(stack);
            tag.put(KEY, encoded);
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        });
    }

    private static CompoundTag customTag(ItemStack stack) {
        return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
    }

    /** Raw form used when copying the list between stacks. */
    public static ListTag copyRaw(ItemStack stack) {
        CompoundTag tag = customTag(stack);
        return tag.contains(KEY) ? tag.getList(KEY, Tag.TAG_COMPOUND) : new ListTag();
    }
}
