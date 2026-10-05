package dev.arakiel.alexsmobsdelight.events;

import java.util.ArrayList;
import java.util.List;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import dev.arakiel.alexsmobsdelight.accessor.IEntitySeagullData;
import dev.arakiel.alexsmobsdelight.config.Config;
import dev.arakiel.alexsmobsdelight.helper.EffectListHelper;
import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import dev.arakiel.alexsmobsdelight.mixin.FroststalkerAccessor;
import dev.arakiel.alexsmobsdelight.mixin.LootItemAccessor;
import dev.arakiel.alexsmobsdelight.mixin.LootPoolAccessor;
import dev.arakiel.alexsmobsdelight.mixin.LootPoolSingletonContainerAccessor;
import dev.arakiel.alexsmobsdelight.mixin.LootTableAccessor;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

/**
 * Combat related behaviour.
 *
 * <p>The 1.20.1 sources used Forge's {@code LivingHurtEvent}, {@code LivingAttackEvent} and
 * {@code LivingDropsEvent}. Damage changes and cancellations happen in {@code LivingHurtMixin}; the
 * extra drops are registered through Architectury's living death event.</p>
 */
public final class CombatEvents {
    private static final TagKey<Item> MANTIS_SHRIMP_TOOLS =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("alexsmobsdelight",
                    "tools/mantis_shrimp_tools"));

    private CombatEvents() {
    }

    public static void register() {
        EntityEvent.LIVING_DEATH.register(CombatEvents::onLivingDeath);
    }

    /** Extra side effects that used to live in the hurt event. Returns true when damage is cancelled. */
    public static boolean applyHitEffects(LivingEntity target, DamageSource source, float amount) {
        Level level = target.level();
        if (!(level instanceof ServerLevel serverLevel) || !(source.getEntity() instanceof LivingEntity attacker)) {
            return false;
        }
        var random = target.getRandom();

        if (attacker.getMainHandItem().is(ItemRegistry.CROCODILE_KNIFE.get())
                && !Config.LOOTING_BLACKLIST.contains(target.getType())) {
            float lootHealth = Config.CROCODILE_KNIFE_HEALTH.get().floatValue();
            if (lootHealth * 2.0F * attacker.getMaxHealth() > target.getMaxHealth()) {
                float chance = Mth.clamp((lootHealth * 2.0F * attacker.getMaxHealth() - target.getMaxHealth())
                        / (lootHealth * attacker.getMaxHealth()), 0.0F, 1.0F);
                if (attacker instanceof Player player && chance < 1.0F) {
                    player.displayClientMessage(Component.translatable(
                            "message.alexsmobsdelight.crocodile_knife_1").withStyle(ChatFormatting.GOLD), true);
                }
                if (random.nextInt(100) <= Config.CROCODILE_KNIFE_LOOT.get() * chance) {
                    giveLootTableItems(serverLevel, target, source, attacker);
                    serverLevel.playSound(null, target.getOnPos(), AMSoundRegistry.CROCODILE_HURT.get(),
                            SoundSource.PLAYERS);
                }
            } else if (attacker instanceof Player player) {
                player.displayClientMessage(Component.translatable(
                        "message.alexsmobsdelight.crocodile_knife").withStyle(ChatFormatting.RED), true);
            }
        }

        if (attacker.getMainHandItem().is(MANTIS_SHRIMP_TOOLS)) {
            target.igniteForSeconds(1.0F);
            serverLevel.playSound(null, target.getOnPos(), AMSoundRegistry.MANTIS_SHRIMP_SNAP.get(),
                    SoundSource.PLAYERS);
        }
        if (attacker instanceof Player player && player.hasEffect(AMDHolders.effect(EffectRegistry.CROCODILE_DEATH_ROLL))
                && player.getFoodData().getFoodLevel() > 0) {
            player.startAutoSpinAttack(20, 8.0F, player.getMainHandItem());
            serverLevel.playSound(null, target.getOnPos(), AMSoundRegistry.CROCODILE_BITE.get(),
                    SoundSource.PLAYERS);
        }
        if (attacker.hasEffect(AMDHolders.effect(EffectRegistry.CROCODILE_SHARPNESS))) {
            int amplifier = attacker.getEffect(AMDHolders.effect(EffectRegistry.CROCODILE_SHARPNESS)).getAmplifier();
            target.addEffect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.EXSANGUINATION.get()),
                    100, amplifier));
        }
        if (attacker.hasEffect(AMDHolders.effect(EffectRegistry.POISON_FANGS))) {
            int amplifier = attacker.getEffect(AMDHolders.effect(EffectRegistry.POISON_FANGS)).getAmplifier();
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 100 * (amplifier + 1), 1));
        }

        // A tamed froststalker attacks whoever its owner attacks with the horn knife.
        if (attacker instanceof EntityFroststalker froststalker) {
            LivingEntity leader = ((FroststalkerAccessor) froststalker).amd$getLeader();
            if (leader != null && leader.isAlive()
                    && leader.getMainHandItem().is(ItemRegistry.FROSTSTALKER_HORN_KNIFE.get())) {
                target.invulnerableTime = 0;
                if (leader instanceof Player player) {
                    target.hurt(serverLevel.damageSources().playerAttack(player), amount);
                } else if (leader instanceof Mob mob) {
                    target.hurt(serverLevel.damageSources().mobAttack(mob), amount);
                }
                return true;
            }
        }
        return false;
    }

    /** Damage multipliers that used to be applied through the Forge hurt event. */
    public static float modifyDamage(LivingEntity target, DamageSource source, float amount) {
        if (!(source.getEntity() instanceof LivingEntity attacker)) {
            return amount;
        }
        Level level = target.level();
        if (attacker.getMainHandItem().is(ItemRegistry.SKELEWAG_KNIFE.get())
                && target.getType().is(EntityTypeTags.UNDEAD)) {
            amount += 10.0F;
        }
        if (attacker.hasEffect(AMDHolders.effect(EffectRegistry.NOCTURNAL)) && level.isNight()) {
            int amplifier = attacker.getEffect(AMDHolders.effect(EffectRegistry.NOCTURNAL)).getAmplifier();
            amount *= 1.0F + 0.2F * (amplifier + 1);
        }
        if (target.hasEffect(AMDHolders.effect(EffectRegistry.NOCTURNAL)) && level.isDay()) {
            int amplifier = target.getEffect(AMDHolders.effect(EffectRegistry.NOCTURNAL)).getAmplifier();
            amount *= Math.max(1.0F - 0.2F * (amplifier + 1), 0.2F);
        }
        return amount;
    }

    /** Dodge: incoming projectiles can be avoided while the effect is active. */
    public static boolean shouldCancelDamage(LivingEntity entity, DamageSource source) {
        if (entity.level().isClientSide || !entity.hasEffect(AMDHolders.effect(EffectRegistry.DODGE))
                || !(source.getDirectEntity() instanceof Projectile)) {
            return false;
        }
        int amplifier = entity.getEffect(AMDHolders.effect(EffectRegistry.DODGE)).getAmplifier();
        if (entity.getRandom().nextInt(10) > Math.pow(0.5, amplifier + 1)) {
            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.WOOL_PLACE, SoundSource.NEUTRAL, 1.0F, 0.3F);
            return true;
        }
        return false;
    }

    private static EventResult onLivingDeath(LivingEntity entity, DamageSource source) {
        if (entity.level() instanceof ServerLevel level) {
            if (entity instanceof IEntitySeagullData seagull && !seagull.amd$getEffects().isEmpty()) {
                ItemStack meal = new ItemStack(seagull.amd$getConsumedEternalFood()
                        ? ItemRegistry.ENCHANTED_ETERNAL_COOKED_SEAGULL.get()
                        : ItemRegistry.ENCHANTED_COOKED_SEAGULL.get());
                EffectListHelper.setEffects(meal, new ArrayList<>(seagull.amd$getEffects().values()));
                addDrop(entity, meal);
            } else if (entity instanceof IEntitySeagullData seagull && seagull.amd$getConsumedEternalFood()) {
                addDrop(entity, new ItemStack(ItemRegistry.ETERNAL_COOKED_SEAGULL.get()));
            }

            if (entity instanceof EntityTarantulaHawk hawk && hawk.isBaby()) {
                addDrop(entity, new ItemStack(hawk.isOnFire()
                        ? ItemRegistry.COOKED_TARANTULA_HAWK_LARVA.get()
                        : ItemRegistry.RAW_TARANTULA_HAWK_LARVA.get()));
            }

            if (source.getEntity() instanceof LivingEntity attacker
                    && attacker.getMainHandItem().is(ItemRegistry.DIMENSIONAL_SLICER.get())) {
                dropWholeLootTable(level, entity, source, attacker);
            }
        }
        return EventResult.pass();
    }

    private static void addDrop(LivingEntity entity, ItemStack stack) {
        ItemEntity drop = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);
        drop.setPickUpDelay(10);
        entity.level().addFreshEntity(drop);
    }

    private static void giveLootTableItems(ServerLevel level, LivingEntity target, DamageSource source,
                                           LivingEntity attacker) {
        LootTable table = level.getServer().reloadableRegistries().getLootTable(target.getLootTable());
        List<ItemStack> drops = table.getRandomItems(lootParams(level, target, source, attacker));
        if (drops.isEmpty()) {
            return;
        }
        if (Config.CROCODILE_KNIFE_FULL_DROP.get()) {
            for (ItemStack drop : drops) {
                target.spawnAtLocation(drop);
            }
        } else {
            ItemStack drop = drops.get(attacker.getRandom().nextInt(drops.size())).copy();
            drop.setCount(1);
            target.spawnAtLocation(drop);
        }
    }

    /** The dimensional slicer drops everything the loot table could have produced. */
    private static void dropWholeLootTable(ServerLevel level, LivingEntity target, DamageSource source,
                                           LivingEntity attacker) {
        LootTable table = level.getServer().reloadableRegistries().getLootTable(target.getLootTable());
        LootParams params = lootParams(level, target, source, attacker);
        LootContext context = new LootContext.Builder(params).create(null);

        List<Item> alreadyDropped = new ArrayList<>();
        for (ItemEntity existing : level.getEntitiesOfClass(ItemEntity.class,
                target.getBoundingBox().inflate(3.0))) {
            alreadyDropped.add(existing.getItem().getItem());
        }

        for (LootPool pool : ((LootTableAccessor) table).amd$getLootTablePools()) {
            for (LootPoolEntryContainer entry : ((LootPoolAccessor) pool).amd$getEntries()) {
                if (!(entry instanceof LootItem lootItem)
                        || !(entry instanceof LootPoolSingletonContainer singleton)) {
                    continue;
                }
                Item item = ((LootItemAccessor) lootItem).amd$getItem().value();
                ItemStack base = new ItemStack(item);
                for (LootItemFunction function : ((LootPoolSingletonContainerAccessor) singleton).amd$getFunctions()) {
                    base = function.apply(base, context);
                }
                if (base.getCount() <= 0) {
                    base = new ItemStack(item);
                }
                base.setCount(1);
                if (!alreadyDropped.contains(base.getItem())) {
                    addDrop(target, base.copy());
                }
            }
        }
    }

    private static LootParams lootParams(ServerLevel level, LivingEntity target, DamageSource source,
                                         LivingEntity attacker) {
        return new LootParams.Builder(level)
                .withParameter(LootContextParams.THIS_ENTITY, target)
                .withParameter(LootContextParams.ORIGIN, target.position())
                .withParameter(LootContextParams.DAMAGE_SOURCE, source)
                .withOptionalParameter(LootContextParams.ATTACKING_ENTITY, attacker)
                .create(LootContextParamSets.ENTITY);
    }
}
