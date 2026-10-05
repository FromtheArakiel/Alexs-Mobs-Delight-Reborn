package dev.arakiel.alexsmobsdelight.mixin;

import java.util.List;
import java.util.Map;

import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.google.common.collect.Maps;

import dev.arakiel.alexsmobsdelight.accessor.IEntitySeagullData;
import dev.arakiel.alexsmobsdelight.config.Config;
import dev.arakiel.alexsmobsdelight.helper.EffectListHelper;
import dev.arakiel.alexsmobsdelight.helper.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Lets a seagull collect the effects of the food it steals and turn suspicious sand into treasure.
 */
@Mixin(EntitySeagull.class)
public class EntitySeagullMixin implements IEntitySeagullData {
    private static final ResourceKey<LootTable> SEAGULL_TREASURE_SAND = ResourceKey.create(Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "gameplay/seagull_treasure_sand"));

    @Unique
    private boolean amd$consumedEternalFood;
    @Unique
    private Map<Holder<MobEffect>, MobEffectInstance> amd$consumedFoodEffects = Maps.newHashMap();

    @Override
    public boolean amd$getConsumedEternalFood() {
        return amd$consumedEternalFood;
    }

    @Override
    public void amd$setConsumedEternalFood(boolean consumed) {
        amd$consumedEternalFood = consumed;
    }

    @Override
    public Map<Holder<MobEffect>, MobEffectInstance> amd$getEffects() {
        return amd$consumedFoodEffects;
    }

    @Override
    public void amd$setEffectMap(Map<Holder<MobEffect>, MobEffectInstance> effects) {
        amd$consumedFoodEffects = effects;
    }

    @Inject(method = "eatItemEffect", at = @At("HEAD"), remap = false)
    private void amd$applyEternalTag(ItemStack heldItem, CallbackInfo callback) {
        if (heldItem == null) {
            return;
        }
        EntitySeagull seagull = (EntitySeagull) (Object) this;
        if (Config.ETERNAL_FOODS_ITEMS.contains(heldItem.getItem())) {
            ((IEntitySeagullData) seagull).amd$setConsumedEternalFood(true);
        }

        Map<Holder<MobEffect>, MobEffectInstance> foodEffects = Maps.newHashMap();
        FoodProperties food = heldItem.get(DataComponents.FOOD);
        if (food != null && !Config.ENCHANTED_SEAGULL_BLACKLIST_ITEMS.contains(heldItem.getItem())) {
            for (FoodProperties.PossibleEffect possible : food.effects()) {
                if (seagull.getRandom().nextFloat() <= possible.probability()) {
                    MobEffectInstance instance = possible.effect();
                    foodEffects.put(instance.getEffect(), instance);
                }
            }
        }

        // Effects an enchanted seagull carries in its custom data are merged in as well.
        for (MobEffectInstance carried : EffectListHelper.getEffects(heldItem)) {
            Holder<MobEffect> effect = carried.getEffect();
            MobEffectInstance existing = foodEffects.get(effect);
            if (existing == null) {
                foodEffects.put(effect, carried);
            } else {
                foodEffects.put(effect, new MobEffectInstance(effect,
                        Math.max(carried.getDuration(), existing.getDuration()),
                        Math.max(carried.getAmplifier(), existing.getAmplifier())));
            }
        }
        ((IEntitySeagullData) seagull).amd$setEffectMap(foodEffects);
    }

    @Inject(method = "setTreasurePos", at = @At("TAIL"), remap = false)
    @SuppressWarnings("deprecation") // Legacy full-cube solid check, kept for parity with the 1.20.1 behaviour.
    private void amd$setTreasureSand(BlockPos pos, CallbackInfo callback) {
        EntitySeagull seagull = (EntitySeagull) (Object) this;
        Level level = seagull.level();
        Player player = EntityHelper.getClosestPlayer(level, EntityHelper.getVec3(seagull), 16);
        for (int y = 0; y < 128; ++y) {
            BlockPos current = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState below = level.getBlockState(current.below());
            if (!(level.getBlockState(current).getBlock() instanceof ChestBlock)
                    || !(below.is(Blocks.SAND) || below.is(Blocks.GRAVEL) || below.is(Blocks.STONE)
                    || below.is(Blocks.SANDSTONE))) {
                continue;
            }
            boolean enclosed = true;
            for (Direction direction : Direction.values()) {
                if (!level.getBlockState(current.relative(direction)).isSolid()) {
                    enclosed = false;
                    break;
                }
            }
            if (!enclosed) {
                continue;
            }
            BlockState treasure = below.is(Blocks.GRAVEL) || below.is(Blocks.STONE)
                    ? Blocks.SUSPICIOUS_GRAVEL.defaultBlockState() : Blocks.SUSPICIOUS_SAND.defaultBlockState();
            BlockEntity chest = level.getBlockEntity(current);
            CompoundTag tag = chest == null ? null : chest.saveWithoutMetadata(level.registryAccess());
            if (chest instanceof RandomizableContainerBlockEntity chestEntity && tag != null && tag.contains("LootTable", 8)) {
                if (level.setBlock(current.below(), treasure, Block.UPDATE_ALL)) {
                    if (level.getBlockEntity(current.below()) instanceof BrushableBlockEntity brushable) {
                        if (player != null) {
                            chestEntity.unpackLootTable(player);
                        }
                        brushable.setLootTable(SEAGULL_TREASURE_SAND, tag.getLong("LootTableSeed"));
                        brushable.setChanged();
                        level.sendBlockUpdated(current.below(), treasure, treasure, Block.UPDATE_CLIENTS);
                    }
                }
                break;
            }
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void amd$save(CompoundTag tag, CallbackInfo callback) {
        tag.putBoolean("AmdConsumedEternalFood", this.amd$consumedEternalFood);
        if (!this.amd$consumedFoodEffects.isEmpty()) {
            ListTag effects = new ListTag();
            for (MobEffectInstance instance : this.amd$consumedFoodEffects.values()) {
                effects.add(instance.save());
            }
            tag.put("AmdConsumedFoodEffects", effects);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void amd$load(CompoundTag tag, CallbackInfo callback) {
        if (tag.contains("AmdConsumedEternalFood")) {
            this.amd$consumedEternalFood = tag.getBoolean("AmdConsumedEternalFood");
        }
        if (tag.contains("AmdConsumedFoodEffects", 9)) {
            ListTag effects = tag.getList("AmdConsumedFoodEffects", 10);
            for (int index = 0; index < effects.size(); ++index) {
                int current = index;
                MobEffectInstance.CODEC.parse(net.minecraft.nbt.NbtOps.INSTANCE, effects.getCompound(current))
                        .result()
                        .ifPresent(instance -> this.amd$consumedFoodEffects.put(instance.getEffect(), instance));
            }
        }
    }
}
