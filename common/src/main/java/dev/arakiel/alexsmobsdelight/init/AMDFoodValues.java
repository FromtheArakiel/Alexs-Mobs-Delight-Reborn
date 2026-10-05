package dev.arakiel.alexsmobsdelight.init;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import dev.arakiel.alexsmobsdelight.effects.AMDMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class AMDFoodValues {
    public static final FoodProperties RAW_BEAR_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties RAW_BEAR_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).fast().build();
    public static final FoodProperties COOKED_BEAR_MEAT = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_BEAR_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).fast().build();

    public static final FoodProperties RAW_BISON_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.3F).build();
    public static final FoodProperties RAW_BISON_MEAT_CUBES = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_BISON_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_BISON_MEAT_CUBES = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();

    public static final FoodProperties RAW_TUSKLIN_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.3F).build();
    public static final FoodProperties RAW_TUSKLIN_MEAT_PIECE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).fast().build();
    public static final FoodProperties COOKED_TUSKLIN_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_TUSKLIN_MEAT_PIECE = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).fast().build();
    public static final FoodProperties RAW_TUSKLIN_SAUSAGE = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.4F).build();
    public static final FoodProperties SMOKED_TUSKLIN_SAUSAGE = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).build();
    public static final FoodProperties SLICE_OF_SMOKED_TUSKLIN_SAUSAGE = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.85F).build();
    public static final FoodProperties FRIED_TUSKLIN_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).build();

    public static final FoodProperties RAW_KANGAROO_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.6F).fast().build();
    public static final FoodProperties COOKED_KANGAROO_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.85F).fast().build();

    public static final FoodProperties RAW_MOOSE_RIB_PIECE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.6F).fast().build();
    public static final FoodProperties COOKED_MOOSE_RIB_PIECE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.85F).fast().build();

    public static final FoodProperties RAW_SEAGULL = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_SEAGULL = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).build();
    public static final FoodProperties ALWAYS_EAT_COOKED_SEAGULL = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).alwaysEdible().build();
    public static final FoodProperties KIVIAK = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.SEAGULL_ANOREXIA), 24000, 0), 1.0F).build();

    public static final FoodProperties MANTIS_SHRIMP_TAIL = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_MANTIS_SHRIMP_TAIL = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).build();

    public static final FoodProperties BANANA_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).fast().build();
    public static final FoodProperties BANANA_ROLL = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).build();
    public static final FoodProperties BANANA_ROLL_SLICE = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.5F).build();
    public static final FoodProperties LOBSTER_ROLL = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();

    public static final FoodProperties RAW_CROCODILE_CLAW = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties RAW_CROCODILE_TAIL = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.3F).build();
    public static final FoodProperties RAW_CROCODILE_MEAT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_CROCODILE_CLAW = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_CROCODILE_TAIL = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_CROCODILE_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties CROCODILE_BARBECUE_STICK = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.9F).build();

    public static final FoodProperties RAW_TENTACLE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_TENTACLE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_LOST_TENTACLE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_LOST_TENTACLE_CUT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties TENTACLE_MEDLEY = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).build();
    public static final FoodProperties TENTACLE_SANDWICH = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).build();
    public static final FoodProperties DRIED_KELP_TENTACLE_SANDWICH = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.7F).build();

    public static final FoodProperties MOOSE_PIE = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.3F).build();
    public static final FoodProperties MUSHROOM_BUNFUNGUS_EAR_PIE = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOCTURNAL), 3600, 0), 1.0F).build();

    public static final FoodProperties MIMICANDY = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.5F).build();

    public static final FoodProperties WHALE_MEAT = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_WHALE_MEAT = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.6F).build();
    public static final FoodProperties SEAL_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_SEAL_MEAT = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties CHEESE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();

    public static final FoodProperties CENTIPEDE_MEAT = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).effect(new MobEffectInstance(MobEffects.POISON, 200, 1), 0.3F).build();
    public static final FoodProperties COOKED_CENTIPEDE_MEAT = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_CENTIPEDE_LEG = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_MAGGOT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.6F).build();
    public static final FoodProperties TARANTULA_HAWK_ABDOMEN = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).effect(new MobEffectInstance(MobEffects.POISON, 300, 0), 0.3F).build();
    public static final FoodProperties COOKED_TARANTULA_HAWK_ABDOMEN = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties TARANTULA_HAWK_LARVA = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).effect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.5F).build();
    public static final FoodProperties COOKED_TARANTULA_HAWK_LARVA = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties COCKROACH = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1F).build();
    public static final FoodProperties ANT = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).effect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.2F).build();
    public static final FoodProperties RAW_TURTLE_MEAT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_TURTLE_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_ANACONDA = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_ANACONDA = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_EMU_MEAT = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_EMU_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_HUMMINGBIRD = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_HUMMINGBIRD = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_EMU_LEG = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_EMU_LEG = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();

    public static final FoodProperties RAW_BUNFUNGUS_LEG = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_BUNFUNGUS_LEG = (new FoodProperties.Builder()).nutrition(9).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_FROSTSTALKER_LEG = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_FROSTSTALKER_LEG = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_FROSTSTALKER_MEAT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_FROSTSTALKER_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties RAW_BUNFUNGUS_EAR = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_BUNFUNGUS_EAR = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties MUSHROOM_HAT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_MUSHROOM_HAT = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();

    public static final FoodProperties SKELEWAG_EYE = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0), 0.7F).build();

    public static final FoodProperties PROTEIN_BLOCK = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.5F).alwaysEdible().effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.BUG_PHEROMONES.get()), 3600, 0), 1.0F).build();
    public static final FoodProperties ANT_COOKIE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties CENTIPEDE_LEG_STICK = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.POISON_FANGS), 1800, 0), 1.0F).build();
    public static final FoodProperties COOKED_MAGGOT_STICK = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties LARVA_ROLL = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 3600, 0), 1.0F).build();
    public static final FoodProperties STUFFED_TARANTULA_HAWK = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.5F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FLUTTERING), 6000, 0), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0F).build();

    public static final FoodProperties SEAL_STICK = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(MobEffects.LUCK, 200, 4), 1.0F).build();
    public static final FoodProperties SEAL_SANDWICH = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.LUCK, 3000, 0), 1.0F).build();
    public static final FoodProperties GONGYLIDIA_SANDWICH = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 1), 1.0F).build();

    public static final FoodProperties BANANA_CUSTARD = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CRYSTALLIZE_WALKER), 3600, 0), 1.0F).build();

    public static final FoodProperties BOWL_OF_WILD_STEW = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties CROCODILE_STEW_WITH_CATFISH = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CROCODILE_TOUGHNESS), 1800, 0), 1.0F).build();
    public static final FoodProperties FRONTIER_SOUP = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3000, 0), 1.0F).build();
    public static final FoodProperties KANGAROO_MEAT_STEW = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties SEAL_STEW = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 1800, 0), 1.0F).effect(new MobEffectInstance(MobEffects.LUCK, 3000, 0), 1.0F).build();
    public static final FoodProperties BUNFUNGUS_EAR_STEW = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOCTURNAL), 1200, 1), 1.0F).build();
    public static final FoodProperties SKELEWAG_EYE_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 1800, 0), 1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0), 1.0F).build();
    public static final FoodProperties FISH_BONE_ALGAE_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 1800, 0), 1.0F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1800, 0), 1.0F).build();
    public static final FoodProperties FROSTSTALKER_MEAT_STEW = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FROST_RESISTANCE), 3600, 0), 1.0F).build();
    public static final FoodProperties MUSHROOM_CREAM_SOUP = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties MUSHROOM_FRESH_MEAT_STEW = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOCTURNAL), 1800, 0), 1.0F).build();
    public static final FoodProperties MOOSE_STEW = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties SEA_BEAR_STEW = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties SEAGULL_SOUP = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties LOBSTER_HEAD_STEW = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties CENTIPEDE_SOUP = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.POISON_FANGS), 1200, 0), 1.0F).build();
    public static final FoodProperties CENTIPEDE_SOUP_CUP = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.POISON_FANGS), 1200, 0), 1.0F).build();
    public static final FoodProperties SOPA_DE_MACACO_CUP = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.8F).build();
    public static final FoodProperties MOSQUITO_REPELLENT_STEW_CUP = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.8F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.MOSQUITO_REPELLENT.get()), 24000), 1.0F).build();
    public static final FoodProperties CHEESE_EMU_LEG = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.DODGE), 3000, 0), 1.0F).build();
    public static final FoodProperties STICK_HUMMINGBIRD = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.8F).build();

    public static final FoodProperties PROTEIN_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.BUG_PHEROMONES.get()), 3000, 0), 1.0F).build();
    public static final FoodProperties PROTEIN_SOUP_CUP = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.BUG_PHEROMONES.get()), 3000, 0), 1.0F).build();
    public static final FoodProperties GONGYLIDIA_SOUP = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 1), 1.0F).build();
    public static final FoodProperties MAGGOT_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties DETOXIFY_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 3600, 0), 1.0F).build();
    public static final FoodProperties DETOXIFY_SOUP_CUP = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 3600, 0), 1.0F).build();
    public static final FoodProperties DETOXIFY_JELLY = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 9600, 0), 1.0F).build();
    public static final FoodProperties DETOXIFY_TEA = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).alwaysEdible().effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 1800, 0), 1.0F).build();
    public static final FoodProperties SKELEWAG_EYE_DROPS = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).alwaysEdible().effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1.0F).build();
    public static final FoodProperties BISON_TARTARE = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.5F).effect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.2F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties BREASTED_KANGAROO_MEATBALLS = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties GRAVY_KANGAROO_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.7F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties BOWL_OF_HONEY_GLAZED_BEAR_MEAT_WITH_SALMON = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.KNOCKBACK_RESISTANCE.get()), 1800, 0), 1.0F).build();
    public static final FoodProperties HONEY_GLAZED_MOOSE_RIBS = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties ROAST_SEAGULL = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties CANNED_BEAR_MEAT = (new FoodProperties.Builder()).nutrition(20).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.KNOCKBACK_RESISTANCE.get()), 1800, 0), 1.0F).build();
    public static final FoodProperties FRIED_SEAGULL_WITH_FRIES = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.SATURATION, 200, 0), 1.0F).build();
    public static final FoodProperties ANTS_CLIMBING_A_TREE = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties BRAISED_TARANTULA_HAWK_LARVA = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 9600, 0), 1.0F).build();
    public static final FoodProperties CRISPY_COCKROACH_WING = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.BUG_PHEROMONES.get()), 3600, 0), 1.0F).build();
    public static final FoodProperties CRISPY_TARANTULA_HAWK_WING = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FLUTTERING), 9600, 0), 1.0F).build();
    public static final FoodProperties FRIED_ANTS = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1.0F).build();
    public static final FoodProperties FRIED_CENTIPEDE_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.POISON_FANGS), 1800, 0), 1.0F).build();
    public static final FoodProperties FRIED_TARANTULA_HAWK = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FLUTTERING), 6000, 0), 1.0F).build();
    public static final FoodProperties GAZING_INTO_THE_ABYSS = (new FoodProperties.Builder()).nutrition(13).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.LAVA_VISION.get()), 3600, 0), 1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0), 1.0F).build();
    public static final FoodProperties MAGGOT_SALAD = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.MOSQUITO_REPELLENT.get()), 3600, 0), 1F).build();
    public static final FoodProperties PICKLED_MAGGOTS = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.MOSQUITO_REPELLENT.get()), 24000, 0), 1F).build();
    public static final FoodProperties MUSHROOMS_BRAISED_WITH_CENTIPEDE = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.POISON_FANGS), 1800, 2), 1F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1F).build();
    public static final FoodProperties TARANTULA_HAWK_SASHIMI = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FLUTTERING), 3600, 0), 1F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1F).build();
    public static final FoodProperties TRUE_ANTS_CLIMBING_A_TREE = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 1), 1F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1F).build();

    public static final FoodProperties BIG_MAC = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 0), 1.0F).build();
    public static final FoodProperties WHALE_BURGER = (new FoodProperties.Builder()).nutrition(24).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1.0F).build();
    public static final FoodProperties MUSHROOM_BIG_MAX = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 0), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 0), 1.0F).build();
    public static final FoodProperties FROSTSTALKER_ROUGAMO = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FROST_RESISTANCE), 1800, 1), 1.0F).build();
    public static final FoodProperties CHEESE_SEAL_BURGER = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0F).effect(new MobEffectInstance(MobEffects.LUCK, 1800, 2), 1.0F).build();
    public static final FoodProperties MANTIS_SHRIMP_TEMPURA = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0F).build();
    public static final FoodProperties TEMPURA = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties SHRIMP_FRIED_EGG = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.3F).build();
    public static final FoodProperties SHRIMP_POKE_BOWL = (new FoodProperties.Builder()).nutrition(20).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties BOWL_OF_MOOSE_SAUSAGE_WITH_SALMON = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties LOBSTER_PASTA = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties SPORES_BUNFUNGUS_LEG_PASTA = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOCTURNAL), 6000, 0), 1.0F).build();
    public static final FoodProperties BOWL_OF_BUNFUNGUS_HODGEPODGE = (new FoodProperties.Builder()).nutrition(15).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOCTURNAL), 3600, 1), 1.0F).build();
    public static final FoodProperties BOWL_OF_COLD_ROASTED_FROSTSTALKER_MEAT = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FROST_RESISTANCE), 6000, 0), 1.0F).build();
    public static final FoodProperties TUSKLIN_HOTDOG = (new FoodProperties.Builder()).nutrition(20).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 0), 1.0F).build();
    public static final FoodProperties SMOKED_TUSKLIN_SANDWICH = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).build();
    public static final FoodProperties KATSUDON = (new FoodProperties.Builder()).nutrition(18).saturationModifier(0.85F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE_CLAW = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CROCODILE_CRUSH), 1800, 0), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE_TAIL = (new FoodProperties.Builder()).nutrition(18).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CROCODILE_HACKSAW), 1800, 0), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE_HEAD = (new FoodProperties.Builder()).nutrition(20).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CROCODILE_SHARPNESS), 1800, 0), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CROCODILE_TOUGHNESS), 1800, 0), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties TAKOYAKI = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties CTHULHUS_BREAKFAST = (new FoodProperties.Builder()).nutrition(13).saturationModifier(0.85F).alwaysEdible().effect(new MobEffectInstance(MobEffects.DARKNESS, 1200, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.FEAR.get()), 100, 0), 1.0F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 4), 1.0F).build();
    public static final FoodProperties WHALE_PORK_STEW = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOURISH_SUPPLEMENT), 1200, 0), 1.0F).build();
    public static final FoodProperties TENTACLE_SALAD = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.EXTENDED_TOUCH), 3600, 0), 1.0F).build();
    public static final FoodProperties TENTACLE_STICK = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.EXTENDED_TOUCH), 1800, 1), 1.0F).build();
    public static final FoodProperties FROSTSTALKER_MEAT_SKEWERS = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FROST_RESISTANCE), 1800, 0), 1.0F).build();
    public static final FoodProperties TRIPLE_KILL = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 2), 1.0F).build();
    public static final FoodProperties WHALE_SOUP = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.ORCAS_MIGHT.get()), 900, 0), 1.0F).build();
    public static final FoodProperties WHALE_TENTACLE = (new FoodProperties.Builder()).nutrition(24).saturationModifier(0.85F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.EXTENDED_TOUCH), 6000, 0), 1.0F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOURISH_SUPPLEMENT), 600, 0), 1.0F).build();
    public static final FoodProperties WHALE_SKIN_STEW = (new FoodProperties.Builder()).nutrition(20).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOURISH_SUPPLEMENT), 600, 0), 1.0F).build();
    public static final FoodProperties FLYING_FISH_CAN = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(MobEffects.HUNGER, 600, 1), 1.0F).build();
    public static final FoodProperties ANACONDA_CATFISH_STICK = (new FoodProperties.Builder()).nutrition(11).saturationModifier(0.85F).build();
    public static final FoodProperties SNAKE_SKIN_SALAD = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.65F).build();
    public static final FoodProperties ANACONDA_CONGEE = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties COD_AND_TURTLE_MEAT_CONGEE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties TURTLE_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties SWAMP_SOUP = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.SATURATION, 60, 0), 1.0F).build();
    public static final FoodProperties RATTLE_TAIL_CONGEE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 1200, 0), 1.0F).build();
    public static final FoodProperties STUFFED_ANACONDA = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1.0F).build();
    public static final FoodProperties GRILLED_TURTLE_AND_ANACONDA = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1.0F).build();
    public static final FoodProperties TURTLE_JELLY = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.5F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 9600, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties CHEESE_MASHED_POTATOES_WITH_ANACONDA = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties TURTLE_MEAT_FRIED_RICE = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties BRAISED_ALLIGATOR_SNAPPING_TURTLE = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 9600, 0), 1.0F).build();
    public static final FoodProperties GRILLED_RATTLE_TAIL = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.POISON_RESISTANCE.get()), 3600, 0), 1.0F).build();
    public static final FoodProperties TIGER_CAKE_ROLL = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(AMEffectRegistry.TIGERS_BLESSING.get()), 3600, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties POT_OF_HUMMINGBIRD_STEW = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3000, 0), 1.0F).build();
    public static final FoodProperties HANDTORN_EMU_MEAT_WITH_RICE = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.DODGE), 3600, 0), 1.0F).build();
    public static final FoodProperties PLATE_OF_BEGGARS_EMU = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.DODGE), 1200, 1), 1.0F).build();
    public static final FoodProperties SALVIA_POPSICLE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.2F).effect(new MobEffectInstance(MobEffects.POISON, 100, 0), 1.0F).fast().alwaysEdible().build();
    public static final FoodProperties SALVIA_CUSTARD = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(MobEffects.POISON, 100, 0), 1.0F).build();
    public static final FoodProperties EMU_CUSTARD = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.7F).alwaysEdible().effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.DODGE), 1200, 0), 1.0F).build();
    public static final FoodProperties EMU_FRIED_RICE = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.DODGE), 1200, 0), 1.0F).build();
    public static final FoodProperties BUNFUNGUS_EAR_ROLL = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.75F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.NOCTURNAL), 1200, 0), 1.0F).build();
    public static final FoodProperties CRISPY_FISH_BONE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.75F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 3600, 0), 1.0F).build();
    public static final FoodProperties FROSTSTALKER_MEAT_JELLY = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.6F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FROST_RESISTANCE), 9600, 0), 1.0F).build();
    public static final FoodProperties SLICED_FROSTSTALKER_MEAT_JELLY = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.85F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0F).effect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.FROST_RESISTANCE), 3600, 0), 1.0F).build();

    public static final FoodProperties COMMON_MILKSHAKE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).build();
    public static final FoodProperties COMMON_CAKE_SLICE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5F).build();
    public static final FoodProperties COMMON_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.4F).build();

    //public static final Map<Item, FoodProperties> VANILLA_SOUP_EFFECTS;


    public AMDFoodValues() {
    }

    static {
        /*
        VANILLA_SOUP_EFFECTS = (new ImmutableMap.Builder()).put(Items.MUSHROOM_STEW, (new FoodProperties.Builder()).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0F).build()).put(Items.BEETROOT_SOUP, (new FoodProperties.Builder()).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0F).build()).put(Items.RABBIT_STEW, (new FoodProperties.Builder()).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build()).build();

         */
    }
}
