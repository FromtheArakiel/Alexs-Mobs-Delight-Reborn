package dev.arakiel.alexsmobsdelight.init;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.effects.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.MOB_EFFECT);
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.POTION);
    public static final RegistrySupplier<MobEffect> CROCODILE_CRUSH = MOB_EFFECTS.register("crocodile_crush", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0x5D5B42).addAttributeModifier(Attributes.ATTACK_DAMAGE, modifierId("90185901-376B-4498-935B-2F7F79060635"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistrySupplier<MobEffect> CROCODILE_TOUGHNESS = MOB_EFFECTS.register("crocodile_toughness", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0x5D5B42).addAttributeModifier(Attributes.ARMOR_TOUGHNESS, modifierId("90185901-1310-4498-935B-2F7F79060635"), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistrySupplier<MobEffect> CROCODILE_SHARPNESS = MOB_EFFECTS.register("crocodile_sharpness", () -> new CrocodileSharpnessEffect(MobEffectCategory.BENEFICIAL, 0x5D5B42));
    public static final RegistrySupplier<MobEffect> CROCODILE_HACKSAW = MOB_EFFECTS.register("crocodile_hacksaw", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0x5D5B42).addAttributeModifier(Attributes.ATTACK_SPEED, modifierId("90185901-3461-4498-935B-2F7F79060635"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistrySupplier<MobEffect> CROCODILE_DEATH_ROLL = MOB_EFFECTS.register("crocodile_death_roll", () -> new CrocodileDeathRollEffect(MobEffectCategory.BENEFICIAL, 0xAB9C1F));
    public static final RegistrySupplier<MobEffect> SEAGULL_ANOREXIA = MOB_EFFECTS.register("seagull_anorexia", () -> new SeagullAnorexiaEffect(MobEffectCategory.BENEFICIAL, 0xFFB4F9));
    public static final RegistrySupplier<MobEffect> CRYSTALLIZE_WALKER = MOB_EFFECTS.register("crystallize_walker", () -> new CrystallizeWalkerEffect(MobEffectCategory.BENEFICIAL, 0xD7D1A5));
    public static final RegistrySupplier<MobEffect> POISON_FANGS = MOB_EFFECTS.register("poison_fangs", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0xCE664C));
    public static final RegistrySupplier<MobEffect> FLUTTERING = MOB_EFFECTS.register("fluttering", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0xCE5127));
    public static final RegistrySupplier<MobEffect> DODGE = MOB_EFFECTS.register("dodge", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0x886628));
    public static final RegistrySupplier<MobEffect> NOURISH_SUPPLEMENT = MOB_EFFECTS.register("nourish_supplement", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0xDAA11B));
    public static final RegistrySupplier<MobEffect> FROST_RESISTANCE = MOB_EFFECTS.register("frost_resistance", () -> new FrostResistanceEffect(MobEffectCategory.BENEFICIAL, 0xA6D5FF));
    public static final RegistrySupplier<MobEffect> NOCTURNAL = MOB_EFFECTS.register("nocturnal", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0x767CA0));

    public static final RegistrySupplier<MobEffect> EXTENDED_TOUCH = MOB_EFFECTS.register("extended_touch", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, 0xA03F4E).addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, modifierId("93018673-1364-4498-6341-634563113235"), 1, AttributeModifier.Operation.ADD_VALUE));
    public static final RegistrySupplier<MobEffect> EXTENDED_SCARE = MOB_EFFECTS.register("extended_scare", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -4184).addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, modifierId("93018673-1364-4498-6341-902490321540"), 1, AttributeModifier.Operation.ADD_VALUE));

    private EffectRegistry() {
    }

    /** 1.21 attribute modifiers are keyed by resource location instead of a UUID string. */
    private static ResourceLocation modifierId(String uuidLike) {
        return ResourceLocation.parse(uuidLike.toLowerCase(java.util.Locale.ROOT));
    }

    public static void register() {
        MOB_EFFECTS.register();
        POTION.register();
    }
}
