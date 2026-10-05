package dev.arakiel.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import dev.arakiel.alexsmobsdelight.accessor.IEntityMantisShrimpData;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Remembers the last item a mantis shrimp held so shrimp fried rice turns into shrimp fried egg, and
 * counts how long a crocodile karambit has been held to drop a tail.
 */
@Mixin(EntityMantisShrimp.class)
public class EntityMantisShrimpMixin implements IEntityMantisShrimpData {
    private static final String KNIFE_COUNTER = "AMDCrocodileKnifeCounter";
    /** The 1.20.1 sources used {@code forge:eggs}, the 1.21 common tag is {@code c:eggs}. */
    private static final net.minecraft.tags.TagKey<Item> EGGS =
            net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath("c", "eggs"));

    @Unique
    private ItemStack amd$lastHoldItem = ItemStack.EMPTY;

    @Override
    public ItemStack amd$getLastHoldItem() {
        return amd$lastHoldItem;
    }

    @Override
    public void amd$setLastHoldItem(ItemStack item) {
        amd$lastHoldItem = item;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void amd$checkLastHoldItem(CallbackInfo callback) {
        EntityMantisShrimp shrimp = (EntityMantisShrimp) (Object) this;
        if (amd$lastHoldItem.is(AMTagRegistry.SHRIMP_RICE_FRYABLES)
                && amd$lastHoldItem.is(EGGS)
                && shrimp.getMainHandItem().is(AMItemRegistry.SHRIMP_FRIED_RICE.get())) {
            shrimp.setItemInHand(InteractionHand.MAIN_HAND,
                    new ItemStack(ItemRegistry.SHRIMP_FRIED_EGG.get()));
        }
        if (amd$lastHoldItem != shrimp.getMainHandItem()) {
            amd$lastHoldItem = shrimp.getMainHandItem();
        }
        if (shrimp.getMainHandItem().is(ItemRegistry.CROCODILE_KNIFE.get())) {
            ItemStack stack = shrimp.getMainHandItem();
            CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            int counter = tag.getInt(KNIFE_COUNTER);
            if (counter >= 3000) {
                Item tail = switch (shrimp.getVariant()) {
                    case 1 -> ItemRegistry.MANTIS_SHRIMP_TAIL_RED.get();
                    case 2 -> ItemRegistry.MANTIS_SHRIMP_TAIL_LIME.get();
                    case 3 -> ItemRegistry.MANTIS_SHRIMP_TAIL_WHITE.get();
                    default -> ItemRegistry.MANTIS_SHRIMP_TAIL_GREEN.get();
                };
                shrimp.spawnAtLocation(new ItemStack(tail));
                tag.putInt(KNIFE_COUNTER, 0);
            } else {
                tag.putInt(KNIFE_COUNTER, counter + 1);
            }
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        }
    }
}
