package dev.arakiel.alexsmobsdelight.tier;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

/** Tool tiers of the mod. 1.21 replaced Forge's {@code ForgeTier} with the plain vanilla interface. */
public final class ItemTier {
    public static final Tier CROCODILE = new SimpleTier(2, 250, 8.0F, 3.0F, 10,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.CROCODILE_TOOTH.get()));
    public static final Tier CROCODILE_SCUTE = new SimpleTier(2, 250, 8.0F, 3.0F, 10,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(AMItemRegistry.CROCODILE_SCUTE.get()));
    public static final Tier SHRIMP = new SimpleTier(3, 300, 12.0F, 3.0F, 5,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.LOBSTER_HEAD.get()));
    public static final Tier WHALE_TOOTH = new SimpleTier(2, 400, 7.5F, 2.0F, 5,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(AMItemRegistry.CACHALOT_WHALE_TOOTH.get()));
    public static final Tier SKELEWAG = new SimpleTier(1, 430, 7.5F, 2.0F, 1,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.EMPTY);
    public static final Tier FROSTSTALKER_HORN = new SimpleTier(1, 160, 7.5F, 2.0F, 5,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(AMItemRegistry.FROSTSTALKER_HORN.get()));

    private ItemTier() {
    }

    private record SimpleTier(int level, int uses, float speed, float damage, int enchantment,
                              TagKey<Block> incorrect, Supplier<Ingredient> repair) implements Tier {
        @Override
        public int getUses() {
            return uses;
        }

        @Override
        public float getSpeed() {
            return speed;
        }

        @Override
        public float getAttackDamageBonus() {
            return damage;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return incorrect;
        }

        @Override
        public int getEnchantmentValue() {
            return enchantment;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repair.get();
        }
    }
}
