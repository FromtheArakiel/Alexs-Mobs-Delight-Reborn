package dev.arakiel.alexsmobsdelight.init;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.items.*;
import dev.arakiel.alexsmobsdelight.tier.ItemTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.core.registries.Registries;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.ITEM);
    //食材
    public static final RegistrySupplier<Item> RAW_BEAR_MEAT = ITEMS.register("raw_bear_meat", () -> new Item(foodBuilder(AMDFoodValues.RAW_BEAR_MEAT)));
    public static final RegistrySupplier<Item> RAW_BEAR_MEAT_SLICE = ITEMS.register("raw_bear_meat_slice", () -> new Item(foodBuilder(AMDFoodValues.RAW_BEAR_MEAT_SLICE)));
    public static final RegistrySupplier<Item> COOKED_BEAR_MEAT = ITEMS.register("cooked_bear_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_BEAR_MEAT)));
    public static final RegistrySupplier<Item> COOKED_BEAR_MEAT_SLICE = ITEMS.register("cooked_bear_meat_slice", () -> new Item(foodBuilder(AMDFoodValues.COOKED_BEAR_MEAT_SLICE)));
    public static final RegistrySupplier<Item> RAW_BISON_MEAT = ITEMS.register("raw_bison_meat", () -> new Item(foodBuilder(AMDFoodValues.RAW_BISON_MEAT)));
    public static final RegistrySupplier<Item> RAW_BISON_MEAT_CUBES = ITEMS.register("raw_bison_meat_cubes", () -> new Item(foodBuilder(AMDFoodValues.RAW_BISON_MEAT_CUBES)));
    public static final RegistrySupplier<Item> COOKED_BISON_MEAT = ITEMS.register("cooked_bison_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_BISON_MEAT)));
    public static final RegistrySupplier<Item> COOKED_BISON_MEAT_CUBES = ITEMS.register("cooked_bison_meat_cubes", () -> new Item(foodBuilder(AMDFoodValues.COOKED_BISON_MEAT_CUBES)));
    public static final RegistrySupplier<Item> RAW_KANGAROO_MEAT_SLICE = ITEMS.register("raw_kangaroo_meat_slice", () -> new Item(foodBuilder(AMDFoodValues.RAW_KANGAROO_MEAT_SLICE)));
    public static final RegistrySupplier<Item> COOKED_KANGAROO_MEAT_SLICE = ITEMS.register("cooked_kangaroo_meat_slice", () -> new Item(foodBuilder(AMDFoodValues.COOKED_KANGAROO_MEAT_SLICE)));
    public static final RegistrySupplier<Item> RAW_MOOSE_RIB_PIECE = ITEMS.register("raw_moose_rib_piece", () -> new Item(foodBuilder(AMDFoodValues.RAW_MOOSE_RIB_PIECE)));
    public static final RegistrySupplier<Item> COOKED_MOOSE_RIB_PIECE = ITEMS.register("cooked_moose_rib_piece", () -> new Item(foodBuilder(AMDFoodValues.COOKED_MOOSE_RIB_PIECE)));
    public static final RegistrySupplier<Item> RAW_SEAGULL = ITEMS.register("raw_seagull", () -> new Item(foodBuilder(AMDFoodValues.RAW_SEAGULL)));
    public static final RegistrySupplier<Item> COOKED_SEAGULL = ITEMS.register("cooked_seagull", () -> new Item(foodBuilder(AMDFoodValues.COOKED_SEAGULL)));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_TAIL_RED = ITEMS.register("mantis_shrimp_tail_red", () -> new Item(foodBuilder(AMDFoodValues.MANTIS_SHRIMP_TAIL)));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_TAIL_GREEN = ITEMS.register("mantis_shrimp_tail_green", () -> new Item(foodBuilder(AMDFoodValues.MANTIS_SHRIMP_TAIL)));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_TAIL_LIME = ITEMS.register("mantis_shrimp_tail_lime", () -> new Item(foodBuilder(AMDFoodValues.MANTIS_SHRIMP_TAIL)));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_TAIL_WHITE = ITEMS.register("mantis_shrimp_tail_white", () -> new Item(foodBuilder(AMDFoodValues.MANTIS_SHRIMP_TAIL)));
    public static final RegistrySupplier<Item> COOKED_MANTIS_SHRIMP_TAIL = ITEMS.register("cooked_mantis_shrimp_tail", () -> new Item(foodBuilder(AMDFoodValues.COOKED_MANTIS_SHRIMP_TAIL)));
    public static final RegistrySupplier<Item> RAW_TUSKLIN_MEAT = ITEMS.register("raw_tusklin_meat", () -> new Item(foodBuilder(AMDFoodValues.RAW_TUSKLIN_MEAT)));
    public static final RegistrySupplier<Item> RAW_TUSKLIN_MEAT_PIECE = ITEMS.register("raw_tusklin_meat_piece", () -> new Item(foodBuilder(AMDFoodValues.RAW_TUSKLIN_MEAT_PIECE)));
    public static final RegistrySupplier<Item> COOKED_TUSKLIN_MEAT = ITEMS.register("cooked_tusklin_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_TUSKLIN_MEAT)));
    public static final RegistrySupplier<Item> COOKED_TUSKLIN_MEAT_PIECE = ITEMS.register("cooked_tusklin_meat_piece", () -> new Item(foodBuilder(AMDFoodValues.COOKED_TUSKLIN_MEAT_PIECE)));
    public static final RegistrySupplier<Item> RAW_DEVILS_HOLE_PUPFISH = ITEMS.register("raw_devils_hole_pupfish", () -> new Item(foodBuilder(Foods.COD)));
    public static final RegistrySupplier<Item> RAW_DEVILS_HOLE_PUPFISH_SLICE = ITEMS.register("raw_devils_hole_pupfish_slice", () -> new Item(foodBuilder(FoodValues.COD_SLICE)));
    public static final RegistrySupplier<Item> COOKED_DEVILS_HOLE_PUPFISH = ITEMS.register("cooked_devils_hole_pupfish", () -> new Item(foodBuilder(Foods.COOKED_COD)));
    public static final RegistrySupplier<Item> COOKED_DEVILS_HOLE_PUPFISH_SLICE = ITEMS.register("cooked_devils_hole_pupfish_slice", () -> new Item(foodBuilder(FoodValues.COOKED_COD_SLICE)));
    public static final RegistrySupplier<Item> RAW_CATFISH_SLICE = ITEMS.register("raw_catfish_slice", () -> new Item(foodBuilder(FoodValues.COD_SLICE)));
    public static final RegistrySupplier<Item> COOKED_CATFISH_SLICE = ITEMS.register("cooked_catfish_slice", () -> new Item(foodBuilder(FoodValues.COOKED_COD_SLICE)));
    public static final RegistrySupplier<Item> RAW_FLYING_FISH_SLICE = ITEMS.register("raw_flying_fish_slice", () -> new Item(foodBuilder(FoodValues.COD_SLICE)));
    public static final RegistrySupplier<Item> COOKED_FLYING_FISH = ITEMS.register("cooked_flying_fish", () -> new Item(foodBuilder(Foods.COOKED_COD)));
    public static final RegistrySupplier<Item> COOKED_FLYING_FISH_SLICE = ITEMS.register("cooked_flying_fish_slice", () -> new Item(foodBuilder(FoodValues.COOKED_COD_SLICE)));
    public static final RegistrySupplier<Item> RAW_MUDSKIPPER = ITEMS.register("raw_mudskipper", () -> new Item(foodBuilder(Foods.COD)));
    public static final RegistrySupplier<Item> RAW_MUDSKIPPER_SLICE = ITEMS.register("raw_mudskipper_slice", () -> new Item(foodBuilder(FoodValues.COD_SLICE)));
    public static final RegistrySupplier<Item> COOKED_MUDSKIPPER = ITEMS.register("cooked_mudskipper", () -> new Item(foodBuilder(Foods.COOKED_COD)));
    public static final RegistrySupplier<Item> COOKED_MUDSKIPPER_SLICE = ITEMS.register("cooked_mudskipper_slice", () -> new Item(foodBuilder(FoodValues.COOKED_COD_SLICE)));
    public static final RegistrySupplier<Item> BANANA_SLICE = ITEMS.register("banana_slice", () -> new Item(foodBuilder(AMDFoodValues.BANANA_SLICE)));
    public static final RegistrySupplier<Item> RAW_CROCODILE_CLAW = ITEMS.register("raw_crocodile_claw", () -> new Item(foodBuilder(AMDFoodValues.RAW_CROCODILE_CLAW)));
    public static final RegistrySupplier<Item> RAW_CROCODILE_TAIL = ITEMS.register("raw_crocodile_tail", () -> new Item(foodBuilder(AMDFoodValues.RAW_CROCODILE_TAIL)));
    public static final RegistrySupplier<Item> RAW_CROCODILE_MEAT = ITEMS.register("raw_crocodile_meat", () -> new Item(foodBuilder(AMDFoodValues.RAW_CROCODILE_MEAT)));
    public static final RegistrySupplier<Item> COOKED_CROCODILE_CLAW = ITEMS.register("cooked_crocodile_claw", () -> new Item(foodBuilder(AMDFoodValues.COOKED_CROCODILE_CLAW)));
    public static final RegistrySupplier<Item> COOKED_CROCODILE_TAIL = ITEMS.register("cooked_crocodile_tail", () -> new Item(foodBuilder(AMDFoodValues.COOKED_CROCODILE_TAIL)));
    public static final RegistrySupplier<Item> COOKED_CROCODILE_MEAT = ITEMS.register("cooked_crocodile_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_CROCODILE_MEAT)));
    public static final RegistrySupplier<Item> RAW_WHOLE_CROCODILE = ITEMS.register("raw_whole_crocodile", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> MIMIC_OCTOPUS_TENTACLE = ITEMS.register("mimic_octopus_tentacle", () -> new Item(foodBuilder(AMDFoodValues.RAW_TENTACLE)));
    public static final RegistrySupplier<Item> COOKED_OCTOPUS_TENTACLE = ITEMS.register("cooked_octopus_tentacle", () -> new Item(foodBuilder(AMDFoodValues.COOKED_TENTACLE)));
    public static final RegistrySupplier<Item> WHALE_MEAT = ITEMS.register("whale_meat", () -> new Item(foodBuilder(AMDFoodValues.WHALE_MEAT)));
    public static final RegistrySupplier<Item> COOKED_WHALE_MEAT = ITEMS.register("cooked_whale_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_WHALE_MEAT)));
    public static final RegistrySupplier<Item> COOKED_LOST_TENTACLE = ITEMS.register("cooked_lost_tentacle", () -> new Item(foodBuilder(AMDFoodValues.COOKED_LOST_TENTACLE)));
    public static final RegistrySupplier<Item> DRIED_SHREDDED_SQUID = ITEMS.register("dried_shredded_squid", () -> new Item(foodBuilder(AMDFoodValues.COOKED_LOST_TENTACLE_CUT)));
    public static final RegistrySupplier<Item> SEAL_MEAT = ITEMS.register("seal_meat", () -> new ConsumableItem(foodBuilder(AMDFoodValues.SEAL_MEAT), false, true));
    public static final RegistrySupplier<Item> COOKED_SEAL_MEAT = ITEMS.register("cooked_seal_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_SEAL_MEAT)));
    public static final RegistrySupplier<Item> CHEESE = ITEMS.register("cheese", () -> new CheeseItem(foodBuilder(AMDFoodValues.CHEESE),false, true));
    public static final RegistrySupplier<Item> RAW_CENTIPEDE_BODY = ITEMS.register("raw_centipede_body", () -> new ConsumableItem(foodBuilder(AMDFoodValues.CENTIPEDE_MEAT),true));
    public static final RegistrySupplier<Item> COOKED_CENTIPEDE_BODY = ITEMS.register("cooked_centipede_body", () -> new Item(foodBuilder(AMDFoodValues.COOKED_CENTIPEDE_MEAT)));
    public static final RegistrySupplier<Item> COOKED_CENTIPEDE_LEG = ITEMS.register("cooked_centipede_leg", () -> new Item(foodBuilder(AMDFoodValues.COOKED_CENTIPEDE_LEG)));
    public static final RegistrySupplier<Item> COOKED_MAGGOT = ITEMS.register("cooked_maggot", () -> new Item(foodBuilder(AMDFoodValues.COOKED_MAGGOT)));
    public static final RegistrySupplier<Item> RAW_TARANTULA_HAWK_ABDOMEN = ITEMS.register("raw_tarantula_hawk_abdomen", () -> new ConsumableItem(foodBuilder(AMDFoodValues.TARANTULA_HAWK_ABDOMEN), true));
    public static final RegistrySupplier<Item> COOKED_TARANTULA_HAWK_ABDOMEN = ITEMS.register("cooked_tarantula_hawk_abdomen", () -> new Item(foodBuilder(AMDFoodValues.COOKED_TARANTULA_HAWK_ABDOMEN)));
    public static final RegistrySupplier<Item> RAW_TARANTULA_HAWK_LARVA = ITEMS.register("raw_tarantula_hawk_larva", () -> new Item(foodBuilder(AMDFoodValues.TARANTULA_HAWK_LARVA)));
    public static final RegistrySupplier<Item> COOKED_TARANTULA_HAWK_LARVA = ITEMS.register("cooked_tarantula_hawk_larva", () -> new Item(foodBuilder(AMDFoodValues.COOKED_TARANTULA_HAWK_LARVA)));
    public static final RegistrySupplier<Item> COCKROACH = ITEMS.register("cockroach", () -> new ConsumableItem(foodBuilder(AMDFoodValues.COCKROACH), true));
    public static final RegistrySupplier<Item> LEAFCUTTER_ANT = ITEMS.register("leafcutter_ant", () -> new ConsumableItem(foodBuilder(AMDFoodValues.ANT), true));
    public static final RegistrySupplier<Item> RAW_ANACONDA = ITEMS.register("raw_anaconda", () -> new Item(foodBuilder(AMDFoodValues.RAW_ANACONDA)));
    public static final RegistrySupplier<Item> COOKED_ANACONDA = ITEMS.register("cooked_anaconda", () -> new Item(foodBuilder(AMDFoodValues.COOKED_ANACONDA)));
    public static final RegistrySupplier<Item> RAW_TURTLE_MEAT = ITEMS.register("raw_turtle_meat", () -> new Item(foodBuilder(AMDFoodValues.RAW_TURTLE_MEAT)));
    public static final RegistrySupplier<Item> COOKED_TURTLE_MEAT = ITEMS.register("cooked_turtle_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_TURTLE_MEAT)));
    public static final RegistrySupplier<Item> RAW_EMU = ITEMS.register("raw_emu", () -> new Item(foodBuilder(AMDFoodValues.RAW_EMU_MEAT)));
    public static final RegistrySupplier<Item> COOKED_EMU = ITEMS.register("cooked_emu", () -> new Item(foodBuilder(AMDFoodValues.COOKED_EMU_MEAT)));
    public static final RegistrySupplier<Item> RAW_EMU_LEG = ITEMS.register("raw_emu_leg", () -> new Item(foodBuilder(AMDFoodValues.RAW_EMU_LEG)));
    public static final RegistrySupplier<Item> COOKED_EMU_LEG = ITEMS.register("cooked_emu_leg", () -> new Item(foodBuilder(AMDFoodValues.COOKED_EMU_LEG)));
    public static final RegistrySupplier<Item> RAW_HUMMINGBIRD = ITEMS.register("raw_hummingbird", () -> new Item(foodBuilder(AMDFoodValues.RAW_HUMMINGBIRD)));
    public static final RegistrySupplier<Item> COOKED_HUMMINGBIRD = ITEMS.register("cooked_hummingbird", () -> new Item(foodBuilder(AMDFoodValues.COOKED_HUMMINGBIRD)));
    public static final RegistrySupplier<Item> RAW_BUNFUNGUS_EAR = ITEMS.register("raw_bunfungus_ear", () -> new Item(foodBuilder(AMDFoodValues.RAW_BUNFUNGUS_EAR)));
    public static final RegistrySupplier<Item> COOKED_BUNFUNGUS_EAR = ITEMS.register("cooked_bunfungus_ear", () -> new Item(foodBuilder(AMDFoodValues.COOKED_BUNFUNGUS_EAR)));
    public static final RegistrySupplier<Item> RAW_BUNFUNGUS_LEG = ITEMS.register("raw_bunfungus_leg", () -> new Item(foodBuilder(AMDFoodValues.RAW_BUNFUNGUS_LEG)));
    public static final RegistrySupplier<Item> COOKED_BUNFUNGUS_LEG = ITEMS.register("cooked_bunfungus_leg", () -> new Item(foodBuilder(AMDFoodValues.COOKED_BUNFUNGUS_LEG)));
    public static final RegistrySupplier<Item> MUSHROOM_HAT = ITEMS.register("mushroom_hat", () -> new Item(foodBuilder(AMDFoodValues.MUSHROOM_HAT)));
    public static final RegistrySupplier<Item> COOKED_MUSHROOM_HAT = ITEMS.register("cooked_mushroom_hat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_MUSHROOM_HAT)));
    public static final RegistrySupplier<Item> RAW_FROSTSTALKER_LEG = ITEMS.register("raw_froststalker_leg", () -> new Item(foodBuilder(AMDFoodValues.RAW_FROSTSTALKER_LEG)));
    public static final RegistrySupplier<Item> COOKED_FROSTSTALKER_LEG = ITEMS.register("cooked_froststalker_leg", () -> new Item(foodBuilder(AMDFoodValues.COOKED_FROSTSTALKER_LEG)));
    public static final RegistrySupplier<Item> RAW_FROSTSTALKER_MEAT = ITEMS.register("raw_froststalker_meat", () -> new Item(foodBuilder(AMDFoodValues.RAW_FROSTSTALKER_MEAT)));
    public static final RegistrySupplier<Item> COOKED_FROSTSTALKER_MEAT = ITEMS.register("cooked_froststalker_meat", () -> new Item(foodBuilder(AMDFoodValues.COOKED_FROSTSTALKER_MEAT)));
    public static final RegistrySupplier<Item> SKELEWAG_EYE = ITEMS.register("skelewag_eye", () -> new ConsumableItem(foodBuilder(AMDFoodValues.SKELEWAG_EYE), true));

    //材料
    public static final RegistrySupplier<Item> CROCODILE_TOOTH = ITEMS.register("crocodile_tooth", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> LOBSTER_HEAD = ITEMS.register("lobster_head", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> SEAL_LEATHER_BROWN = ITEMS.register("seal_leather_brown", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> SEAL_LEATHER_GRAY = ITEMS.register("seal_leather_gray", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> ALLIGATOR_SNAPPING_TURTLE_SHELL = ITEMS.register("alligator_snapping_turtle_shell", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> TERRAPIN_SHELL = ITEMS.register("terrapin_shell", () -> new Item(defaultBuilder()));
    public static final RegistrySupplier<Item> TIGER_FUR = ITEMS.register("tiger_fur", () -> new Item(defaultBuilder()));

    //工具？
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_SCYTHE = ITEMS.register("mantis_shrimp_scythe", () -> new MantisShrimpKnife(ItemTier.SHRIMP, 3, -2.4F, defaultBuilder()));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_SHOVEL = ITEMS.register("mantis_shrimp_shovel", () -> new MantisShrimpShovel(ItemTier.SHRIMP, 1.5F, -3.0F, defaultBuilder()));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_HAMMER = ITEMS.register("mantis_shrimp_hammer", () -> new MantisShrimpHammer(ItemTier.SHRIMP, 1, -2.8F, defaultBuilder()));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_AXE = ITEMS.register("mantis_shrimp_axe", () -> new MantisShrimpAxe(ItemTier.SHRIMP, 6.0F, -3.1F, defaultBuilder()));
    public static final RegistrySupplier<Item> LOBSTER_KNIFE = ITEMS.register("lobster_knife", () -> new LobsterKnife(ItemTier.SHRIMP, 0, -2F, defaultBuilder()));
    public static final RegistrySupplier<Item> LOBSTER_DART = ITEMS.register("lobster_dart", () -> new LobsterDartItem(defaultBuilder().durability(64)));
    public static final RegistrySupplier<Item> CROCODILE_KNIFE = ITEMS.register("crocodile_knife", () -> new CrocodileKnife(ItemTier.CROCODILE, -1, -2F, defaultBuilder()));
    public static final RegistrySupplier<Item> CROCODILE_SCUTE_SWORD = ITEMS.register("crocodile_scute_sword", () -> new CrocodileSword(ItemTier.CROCODILE_SCUTE, 3, -2.4F, defaultBuilder()));
    public static final RegistrySupplier<Item> BANANA_BOW = ITEMS.register("banana_bow", () -> new BananaBowItem(defaultBuilder().durability(64)));
    public static final RegistrySupplier<Item> WHALE_TOOTH_PICKAXE = ITEMS.register("whale_tooth_pickaxe", () -> new WhaleToothPickaxe(ItemTier.WHALE_TOOTH, 1, -2.8F, defaultBuilder()));
    public static final RegistrySupplier<Item> SEAGULL_WAND = ITEMS.register("seagull_wand", () -> new SeagullWand(Tiers.WOOD, 3, -2.8F, defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> FROSTSTALKER_HORN_KNIFE = ITEMS.register("froststalker_horn_knife", () -> new FroststalkerHornKnife(ItemTier.FROSTSTALKER_HORN, 0.5F, -2F, defaultBuilder()));
    public static final RegistrySupplier<Item> SKELEWAG_KNIFE = ITEMS.register("skelewag_knife",
            () -> new KnifeItem(ItemTier.SKELEWAG,
                    defaultBuilder().attributes(DiggerItem.createAttributes(ItemTier.SKELEWAG, 0.5F, 0F))));

    public static final RegistrySupplier<Item> DIMENSIONAL_SLICER = ITEMS.register("dimensional_slicer", () -> new DimensionalKnife(Tiers.NETHERITE, 3, -2F, defaultBuilder()));

    //无容器食物
    public static final RegistrySupplier<Item> RAW_TUSKLIN_SAUSAGE = ITEMS.register("raw_tusklin_sausage", () -> new Item(foodBuilder(AMDFoodValues.RAW_TUSKLIN_SAUSAGE)));
    public static final RegistrySupplier<Item> SMOKED_TUSKLIN_SAUSAGE = ITEMS.register("smoked_tusklin_sausage", () -> new Item(foodBuilder(AMDFoodValues.SMOKED_TUSKLIN_SAUSAGE)));
    public static final RegistrySupplier<Item> SLICE_OF_SMOKED_TUSKLIN_SAUSAGE = ITEMS.register("slice_of_smoked_tusklin_sausage", () -> new Item(foodBuilder(AMDFoodValues.SLICE_OF_SMOKED_TUSKLIN_SAUSAGE)));
    public static final RegistrySupplier<Item> MANTIS_SHRIMP_TEMPURA = ITEMS.register("mantis_shrimp_tempura", () -> new ConsumableItem(foodBuilder(AMDFoodValues.MANTIS_SHRIMP_TEMPURA), true));
    public static final RegistrySupplier<Item> SHRIMP_FRIED_EGG = ITEMS.register("shrimp_fried_egg", () -> new Item(foodBuilder(AMDFoodValues.SHRIMP_FRIED_EGG)));
    public static final RegistrySupplier<Item> SHRIMP_POKE_BOWL = ITEMS.register("shrimp_poke_bowl", () -> new ConsumableItem(foodBuilder(AMDFoodValues.SHRIMP_POKE_BOWL), true));
    public static final RegistrySupplier<Item> FRIED_TUSKLIN_MEAT = ITEMS.register("fried_tusklin_meat", () -> new Item(foodBuilder(AMDFoodValues.FRIED_TUSKLIN_MEAT)));
    public static final RegistrySupplier<Item> CROCODILE_RICE = ITEMS.register("crocodile_rice", () -> new ConsumableItem(foodBuilder(FoodValues.COOKED_RICE), true));
    public static final RegistrySupplier<Item> TAKOYAKI = ITEMS.register("takoyaki", () -> new ConsumableItem(foodBuilder(AMDFoodValues.TAKOYAKI), true));
    public static final RegistrySupplier<Item> TEMPURA = ITEMS.register("tempura", () -> new Item(foodBuilder(AMDFoodValues.TEMPURA)));
    public static final RegistrySupplier<Item> ANT_COOKIE = ITEMS.register("ant_cookie", () -> new Item(foodBuilder(AMDFoodValues.ANT_COOKIE)));
    public static final RegistrySupplier<Item> PROTEIN_BLOCK = ITEMS.register("protein_block", () -> new ConsumableItem(foodBuilder(AMDFoodValues.PROTEIN_BLOCK), true));
    public static final RegistrySupplier<Item> STUFFED_TARANTULA_HAWK = ITEMS.register("stuffed_tarantula_hawk", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.STUFFED_TARANTULA_HAWK), true));
    public static final RegistrySupplier<Item> CHEESE_FRIED_EMU_LEG = ITEMS.register("cheese_fried_emu_leg", () -> new Item(foodBuilder(AMDFoodValues.CHEESE_EMU_LEG)));
    public static final RegistrySupplier<Item> EMU_CUSTARD = ITEMS.register("emu_custard", () -> new ConsumableItem(foodBuilder(AMDFoodValues.EMU_CUSTARD), true));
    public static final RegistrySupplier<Item> SAVANNA_FRIED_RICE = ITEMS.register("savanna_fried_rice", () -> new ConsumableItem(foodBuilder(AMDFoodValues.EMU_FRIED_RICE), true));
    public static final RegistrySupplier<Item> BUNFUNGUS_EAR_ROLL = ITEMS.register("bunfungus_ear_roll", () -> new ConsumableItem(foodBuilder(AMDFoodValues.BUNFUNGUS_EAR_ROLL), true));
    public static final RegistrySupplier<Item> CRISPY_FISH_BONE = ITEMS.register("crispy_fish_bone", () -> new ConsumableItem(foodBuilder(AMDFoodValues.CRISPY_FISH_BONE), true));
    public static final RegistrySupplier<Item> FROSTSTALKER_MEAT_JELLY = ITEMS.register("froststalker_meat_jelly", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.FROSTSTALKER_MEAT_JELLY), true));

    //寿司
    public static final RegistrySupplier<Item> CATFISH_ROLL = ITEMS.register("catfish_roll", () -> new Item(foodBuilder(FoodValues.COD_ROLL)));
    public static final RegistrySupplier<Item> DEVILS_HOLE_PUPFISH_ROLL = ITEMS.register("devils_hole_pupfish_roll", () -> new Item(foodBuilder(FoodValues.COD_ROLL)));
    public static final RegistrySupplier<Item> FLYING_FISH_ROLL = ITEMS.register("flying_fish_roll", () -> new Item(foodBuilder(FoodValues.COD_ROLL)));
    public static final RegistrySupplier<Item> MUDSKIPPER_ROLL = ITEMS.register("mudskipper_roll", () -> new Item(foodBuilder(FoodValues.COD_ROLL)));
    public static final RegistrySupplier<Item> BANANA_ROLL = ITEMS.register("banana_roll", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.BANANA_ROLL)));
    public static final RegistrySupplier<Item> BANANA_ROLL_SLICE = ITEMS.register("banana_roll_slice", () -> new Item(foodBuilder(AMDFoodValues.BANANA_ROLL_SLICE)));
    public static final RegistrySupplier<Item> TRIOPS_EGGS_ROLL = ITEMS.register("triops_eggs_roll", () -> new Item(foodBuilder(FoodValues.COD_ROLL)));
    public static final RegistrySupplier<Item> LOBSTER_ROLL = ITEMS.register("lobster_roll", () -> new Item(foodBuilder(AMDFoodValues.LOBSTER_ROLL)));
    public static final RegistrySupplier<Item> DRIED_KELP_ROLLED_TARANTULA_HAWK_LARVA = ITEMS.register("dried_kelp_rolled_tarantula_hawk_larva", () -> new ConsumableItem(foodBuilder(AMDFoodValues.LARVA_ROLL), true));

    //烤串
    public static final RegistrySupplier<Item> CROCODILE_BARBECUE_STICK = ITEMS.register("crocodile_barbecue_stick", () -> new Item(foodBuilder(AMDFoodValues.CROCODILE_BARBECUE_STICK)));
    public static final RegistrySupplier<Item> FRIED_TENTACLES_STICK = ITEMS.register("fried_tentacles_stick", () -> new ConsumableItem(foodBuilder(AMDFoodValues.TENTACLE_STICK), true));
    public static final RegistrySupplier<Item> CHEESE_SEAL_STICK = ITEMS.register("cheese_seal_stick", () -> new ConsumableItem(foodBuilder(AMDFoodValues.SEAL_STICK), true));
    public static final RegistrySupplier<Item> CENTIPEDE_LEG_WITH_CARROT_STICK = ITEMS.register("centipede_leg_with_carrot_stick", () -> new ConsumableItem(foodBuilder(AMDFoodValues.CENTIPEDE_LEG_STICK), true));
    public static final RegistrySupplier<Item> COOKED_MAGGOT_STICK = ITEMS.register("cooked_maggot_stick", () -> new Item(foodBuilder(AMDFoodValues.COOKED_MAGGOT_STICK)));
    public static final RegistrySupplier<Item> ANACONDA_CATFISH_STICK = ITEMS.register("anaconda_catfish_stick", () -> new Item(foodBuilder(AMDFoodValues.ANACONDA_CATFISH_STICK)));
    public static final RegistrySupplier<Item> COOKED_HUMMINGBIRD_STICK = ITEMS.register("cooked_hummingbird_stick", () -> new Item(foodBuilder(AMDFoodValues.STICK_HUMMINGBIRD)));
    public static final RegistrySupplier<Item> FROSTSTALKER_MEAT_SKEWERS = ITEMS.register("froststalker_meat_skewers", () -> new ConsumableItem(foodBuilder(AMDFoodValues.FROSTSTALKER_MEAT_SKEWERS), true));
    public static final RegistrySupplier<Item> TRIPLE_KILL = ITEMS.register("triple_kill", () -> new ConsumableItem(foodBuilder(AMDFoodValues.TRIPLE_KILL), true));

    //汉堡三明治
    public static final RegistrySupplier<Item> BIG_MAC = ITEMS.register("big_mac", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.BIG_MAC), true));
    public static final RegistrySupplier<Item> TUSKLIN_HOT_DOG = ITEMS.register("tusklin_hot_dog", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.TUSKLIN_HOTDOG), true));
    public static final RegistrySupplier<Item> WHALE_BURGER = ITEMS.register("whale_burger", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.WHALE_BURGER), true));
    public static final RegistrySupplier<Item> CHEESE_SEAL_BURGER = ITEMS.register("cheese_seal_burger", () -> new ConsumableItem(foodBuilder(AMDFoodValues.CHEESE_SEAL_BURGER), true));
    public static final RegistrySupplier<Item> SMOKED_TUSKLIN_SANDWICH = ITEMS.register("smoked_tusklin_sandwich", () -> new Item(foodBuilder(AMDFoodValues.SMOKED_TUSKLIN_SANDWICH)));
    public static final RegistrySupplier<Item> TENTACLE_SANDWICH = ITEMS.register("tentacle_sandwich", () -> new Item(foodBuilder(AMDFoodValues.TENTACLE_SANDWICH)));
    public static final RegistrySupplier<Item> DRIED_KELP_TENTACLES_SANDWICH = ITEMS.register("dried_kelp_tentacles_sandwich", () -> new Item(foodBuilder(AMDFoodValues.DRIED_KELP_TENTACLE_SANDWICH)));
    public static final RegistrySupplier<Item> SEAL_SANDWICH = ITEMS.register("seal_sandwich", () -> new ConsumableItem(foodBuilder(AMDFoodValues.SEAL_SANDWICH), true));
    public static final RegistrySupplier<Item> GONGYLIDIA_SANDWICH = ITEMS.register("gongylidia_sandwich", () -> new ConsumableItem(foodBuilder(AMDFoodValues.GONGYLIDIA_SANDWICH), true));
    public static final RegistrySupplier<Item> FROSTSTALKER_ROUGAMO = ITEMS.register("froststalker_rougamo", () -> new ConsumableItem(foodBuilder(AMDFoodValues.FROSTSTALKER_ROUGAMO), true));
    public static final RegistrySupplier<Item> MUSHROOM_BIG_MAX = ITEMS.register("mushroom_big_max", () -> new SlowEatConsumableItem(foodBuilder(AMDFoodValues.MUSHROOM_BIG_MAX), true));

    //派
    public static final RegistrySupplier<Item> MOOSE_PIE = ITEMS.register("moose_pie", () -> new BlockItem(BlockRegistry.MOOSE_PIE_BLOCK.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> MOOSE_PIE_SLICE = ITEMS.register("slice_of_moose_pie", () -> new Item(foodBuilder(AMDFoodValues.MOOSE_PIE)));
    public static final RegistrySupplier<Item> MUSHROOM_BUNFUNGUS_EAR_PIE = ITEMS.register("mushroom_bunfungus_ear_pie", () -> new BlockItem(BlockRegistry.MUSHROOM_BUNFUNGUS_EAR_PIE.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> MUSHROOM_BUNFUNGUS_EAR_PIE_SLICE = ITEMS.register("mushroom_bunfungus_ear_pie_slice", () -> new Item(foodBuilder(AMDFoodValues.MUSHROOM_BUNFUNGUS_EAR_PIE)));

    //特殊
    public static final RegistrySupplier<Item> ENCHANTED_COOKED_SEAGULL = ITEMS.register("enchanted_cooked_seagull", () -> new EnchantedSeagullItem(foodBuilder(AMDFoodValues.ALWAYS_EAT_COOKED_SEAGULL).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> ETERNAL_COOKED_SEAGULL = ITEMS.register("eternal_cooked_seagull", () -> new EternalFoodItem(foodBuilder(AMDFoodValues.COOKED_SEAGULL).rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> ENCHANTED_ETERNAL_COOKED_SEAGULL = ITEMS.register("enchanted_eternal_cooked_seagull", () -> new EnchantedEternalSeagullItem(foodBuilder(AMDFoodValues.ALWAYS_EAT_COOKED_SEAGULL).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> RAINBOW_CUSTARD = ITEMS.register("rainbow_custard", () -> new RainbowConsumableItem(foodBuilder(FoodValues.GLOW_BERRY_CUSTARD).craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistrySupplier<Item> RAINBOW_POPSICLE = ITEMS.register("rainbow_popsicle", () -> new RainbowPopsicleItem(foodBuilder(FoodValues.POPSICLE)));
    public static final RegistrySupplier<Item> BANANA_SLUG_CUSTARD = ITEMS.register("banana_slug_slime_custard", () -> new ConsumableItem(foodBuilder(AMDFoodValues.BANANA_CUSTARD).craftRemainder(Items.GLASS_BOTTLE), true));
    public static final RegistrySupplier<Item> BANANA_SLUG_POPSICLE = ITEMS.register("banana_slug_slime_popsicle", () -> new BananaPopsicleItem(foodBuilder(FoodValues.POPSICLE)));
    public static final RegistrySupplier<Item> SPIT_CUSTARD = ITEMS.register("spit_custard", () -> new ConsumableItem(foodBuilder(AMDFoodValues.SALVIA_CUSTARD).craftRemainder(Items.GLASS_BOTTLE), true));
    public static final RegistrySupplier<Item> SPIT_POPSICLE = ITEMS.register("spit_popsicle", () -> new BananaPopsicleItem(foodBuilder(AMDFoodValues.SALVIA_POPSICLE)));
    public static final RegistrySupplier<Item> KIVIAK = ITEMS.register("kiviak", () -> new KiviakItem(foodBuilder(AMDFoodValues.KIVIAK), true));
    public static final RegistrySupplier<Item> ORCAS_LEAP_SOUP = ITEMS.register("orcas_leap_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.WHALE_SOUP), true, true));
    public static final RegistrySupplier<Item> SURFLYGFISK = ITEMS.register("surflygfisk", () -> new FlyingFishCan(defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> OPENED_SURFLYGFISK = ITEMS.register("surflygfisk_open", () -> new FlyingFishCanOpened(bucketFoodBuilder(AMDFoodValues.FLYING_FISH_CAN), true));
    public static final RegistrySupplier<Item> TIGER_CAKE_ROLL = ITEMS.register("tiger_cake_roll", () -> new TigerCakeRoll(foodBuilder(AMDFoodValues.TIGER_CAKE_ROLL)));
    public static final RegistrySupplier<Item> DETOXIFY_JELLY = ITEMS.register("detoxify_jelly", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.DETOXIFY_JELLY), true));
    public static final RegistrySupplier<Item> DETOXIFY_SOUP = ITEMS.register("detoxify_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.DETOXIFY_SOUP), true));
    public static final RegistrySupplier<Item> DETOXIFY_TEA = ITEMS.register("detoxify_tea", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.DETOXIFY_TEA), true));
    public static final RegistrySupplier<Item> SKELEWAG_EYE_DROPS = ITEMS.register("skelewag_eye_drops", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SKELEWAG_EYE_DROPS), true));

    public static final RegistrySupplier<Item> GHOSTLY_BURGER = ITEMS.register("ghostly_burger", () -> new GhostBurger(defaultBuilder().rarity(Rarity.UNCOMMON)));

    public static final RegistrySupplier<Item> DIMENSIONAL_FOOD = ITEMS.register("dimensional_food", () -> new DimensionalFoods(defaultBuilder().rarity(Rarity.EPIC)));

    public static final RegistrySupplier<Item> MIMICANDY = ITEMS.register("mimicandy", () -> new ConsumableItem(foodBuilder(AMDFoodValues.MIMICANDY), false, true));

    //风味
    public static final RegistrySupplier<Item> ACACIA_BLOSSOM_CAKE = ITEMS.register("acacia_blossom_cake", () -> new ItemNameBlockItem(BlockRegistry.ACACIA_BLOSSOM_CAKE.get(), defaultBuilder()));
    public static final RegistrySupplier<Item> ACACIA_BLOSSOM_CAKE_SLICE = ITEMS.register("acacia_blossom_cake_slice", () -> new ConsumableItem(foodBuilder(AMDFoodValues.COMMON_CAKE_SLICE)));
    public static final RegistrySupplier<Item> ACACIA_BLOSSOM_ICE_CREAM = ITEMS.register("acacia_blossom_ice_cream", () -> new IceCreamItem(bowlFoodBuilder(AMDFoodValues.COMMON_ICE_CREAM)));
    public static final RegistrySupplier<Item> ACACIA_BLOSSOM_MILKSHAKE = ITEMS.register("acacia_blossom_milkshake", () -> new MilkShakeItem(drinkBuilder(AMDFoodValues.COMMON_MILKSHAKE)));
    public static final RegistrySupplier<Item> MUNGAL_SPORES_CAKE = ITEMS.register("mungal_spores_cake", () -> new ItemNameBlockItem(BlockRegistry.MUNGAL_SPORES_CAKE.get(), defaultBuilder()));
    public static final RegistrySupplier<Item> MUNGAL_SPORES_CAKE_SLICE = ITEMS.register("mungal_spores_cake_slice", () -> new ConsumableItem(foodBuilder(AMDFoodValues.COMMON_CAKE_SLICE)));
    public static final RegistrySupplier<Item> MUNGAL_SPORES_ICE_CREAM = ITEMS.register("mungal_spores_ice_cream", () -> new IceCreamItem(bowlFoodBuilder(AMDFoodValues.COMMON_ICE_CREAM)));
    public static final RegistrySupplier<Item> MUNGAL_SPORES_MILKSHAKE = ITEMS.register("mungal_spores_milkshake", () -> new MilkShakeItem(drinkBuilder(AMDFoodValues.COMMON_MILKSHAKE)));

    //汤
    public static final RegistrySupplier<Item> BOWL_OF_WILD_STEW = ITEMS.register("bowl_of_wild_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BOWL_OF_WILD_STEW), true));
    public static final RegistrySupplier<Item> FRONTIER_SOUP = ITEMS.register("frontier_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.FRONTIER_SOUP), true));
    public static final RegistrySupplier<Item> KANGAROO_MEAT_STEW = ITEMS.register("kangaroo_meat_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.KANGAROO_MEAT_STEW), true));
    public static final RegistrySupplier<Item> MOOSE_STEW = ITEMS.register("moose_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.MOOSE_STEW), true));
    public static final RegistrySupplier<Item> SEA_BEAR_STEW = ITEMS.register("sea_bear_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SEA_BEAR_STEW), true, true));
    public static final RegistrySupplier<Item> SEAGULL_SOUP = ITEMS.register("seagull_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SEAGULL_SOUP), true));
    public static final RegistrySupplier<Item> CATFISH_STEW = ITEMS.register("catfish_stew", () -> new ConsumableItem(bowlFoodBuilder(FoodValues.FISH_STEW), true));
    public static final RegistrySupplier<Item> DEVILS_HOLE_PUPFISH_STEW = ITEMS.register("devils_hole_pupfish_stew", () -> new ConsumableItem(bowlFoodBuilder(FoodValues.FISH_STEW), true));
    public static final RegistrySupplier<Item> FLYING_FISH_STEW = ITEMS.register("flying_fish_stew", () -> new ConsumableItem(bowlFoodBuilder(FoodValues.FISH_STEW), true));
    public static final RegistrySupplier<Item> MUDSKIPPER_STEW = ITEMS.register("mudskipper_stew", () -> new ConsumableItem(bowlFoodBuilder(FoodValues.FISH_STEW), true));
    public static final RegistrySupplier<Item> SEAL_STEW = ITEMS.register("seal_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SEAL_STEW), true));
    public static final RegistrySupplier<Item> CROCODILE_STEW_WITH_CATFISH = ITEMS.register("crocodile_stewed_with_catfish", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.CROCODILE_STEW_WITH_CATFISH), true));
    public static final RegistrySupplier<Item> WHALE_SKIN_STEW = ITEMS.register("whale_skin_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.WHALE_SKIN_STEW), true));
    public static final RegistrySupplier<Item> CAVE_CENTIPEDE_SOUP = ITEMS.register("cave_centipede_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.CENTIPEDE_SOUP), true));
    public static final RegistrySupplier<Item> COCKROACH_PROTEIN_SOUP = ITEMS.register("cockroach_protein_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.PROTEIN_SOUP), true));
    public static final RegistrySupplier<Item> MAGGOT_CREAM_SOUP = ITEMS.register("maggot_cream_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.MAGGOT_SOUP), true));
    public static final RegistrySupplier<Item> ANACONDA_CONGEE = ITEMS.register("anaconda_congee", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.MAGGOT_SOUP), true));
    public static final RegistrySupplier<Item> RATTLE_TAIL_CONGEE = ITEMS.register("rattle_tail_congee", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.RATTLE_TAIL_CONGEE), true));
    public static final RegistrySupplier<Item> GONGYLIDIA_CREAM_SOUP = ITEMS.register("gongylidia_cream_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.GONGYLIDIA_SOUP), true));
    public static final RegistrySupplier<Item> COD_AND_TURTLE_MEAT_CONGEE = ITEMS.register("cod_and_turtle_meat_congee", () -> new TurtleShellFoods(defaultBuilder().stacksTo(16).food(AMDFoodValues.COD_AND_TURTLE_MEAT_CONGEE), true));
    public static final RegistrySupplier<Item> TURTLE_SOUP = ITEMS.register("turtle_soup", () -> new TurtleShellFoods(defaultBuilder().stacksTo(16).food(AMDFoodValues.TURTLE_SOUP), true));
    public static final RegistrySupplier<Item> TURTLE_MEAT_FRIED_RICE = ITEMS.register("turtle_meat_fried_rice", () -> new TurtleShellFoods(defaultBuilder().stacksTo(16).food(AMDFoodValues.TURTLE_MEAT_FRIED_RICE), true));
    public static final RegistrySupplier<Item> BRAISED_ALLIGATOR_SNAPPING_TURTLE = ITEMS.register("braised_alligator_snapping_turtle", () -> new SnappingTurtleShellFoods(defaultBuilder().stacksTo(16).food(AMDFoodValues.BRAISED_ALLIGATOR_SNAPPING_TURTLE), true));
    public static final RegistrySupplier<Item> GRILLED_TURTLE_AND_ANACONDA = ITEMS.register("grilled_turtle_and_anaconda", () -> new SnappingTurtleShellFoods(defaultBuilder().stacksTo(16).food(AMDFoodValues.GRILLED_TURTLE_AND_ANACONDA), true));
    public static final RegistrySupplier<Item> SWAMP_SOUP = ITEMS.register("swamp_soup", () -> new SnappingTurtleShellFoods(defaultBuilder().stacksTo(16).food(AMDFoodValues.SWAMP_SOUP), true));
    public static final RegistrySupplier<Item> BUNFUNGUS_EAR_STEW = ITEMS.register("bunfungus_ear_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BUNFUNGUS_EAR_STEW), true));
    public static final RegistrySupplier<Item> FISH_BONE_ALGAE_SOUP = ITEMS.register("fish_bone_algae_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.FISH_BONE_ALGAE_SOUP), true));
    public static final RegistrySupplier<Item> SKELEWAG_EYE_SOUP = ITEMS.register("skelewag_eye_soup", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SKELEWAG_EYE_SOUP), true));
    public static final RegistrySupplier<Item> FROSTSTALKER_MEAT_STEW = ITEMS.register("froststalker_meat_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.FROSTSTALKER_MEAT_STEW), true));
    public static final RegistrySupplier<Item> MUSHROOM_CREAM_SOUP = ITEMS.register("mushroom_cream_soup", () -> new ConsumableItem(foodBuilder(AMDFoodValues.MUSHROOM_CREAM_SOUP), true));
    public static final RegistrySupplier<Item> MUSHROOM_FRESH_MEAT_STEW = ITEMS.register("mushroom_fresh_meat_stew", () -> new ConsumableItem(foodBuilder(AMDFoodValues.MUSHROOM_FRESH_MEAT_STEW), true));

    //矿工乐事联动
    public static final RegistrySupplier<Item> CAVE_CENTIPEDE_SOUP_CUP = ITEMS.register("cave_centipede_soup_cup", () -> new ConsumableItem(cupFoodBuilder(AMDFoodValues.CENTIPEDE_SOUP_CUP), true));
    public static final RegistrySupplier<Item> COCKROACH_PROTEIN_SOUP_CUP = ITEMS.register("cockroach_protein_soup_cup", () -> new ConsumableItem(cupFoodBuilder(AMDFoodValues.PROTEIN_SOUP_CUP), true));
    public static final RegistrySupplier<Item> DETOXIFY_SOUP_CUP = ITEMS.register("detoxify_soup_cup", () -> new ConsumableItem(cupFoodBuilder(AMDFoodValues.DETOXIFY_SOUP_CUP), true));
    public static final RegistrySupplier<Item> MOSQUITO_REPELLENT_STEW_CUP = ITEMS.register("mosquito_repellent_stew_cup", () -> new ConsumableItem(cupFoodBuilder(AMDFoodValues.MOSQUITO_REPELLENT_STEW_CUP), true));
    public static final RegistrySupplier<Item> SOPA_DE_MACACO_CUP = ITEMS.register("sopa_de_macaco_cup", () -> new ConsumableItem(cupFoodBuilder(AMDFoodValues.SOPA_DE_MACACO_CUP), true));

    //碗装食物
    public static final RegistrySupplier<Item> BISON_TARTARE = ITEMS.register("bison_tartare", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BISON_TARTARE), true));
    public static final RegistrySupplier<Item> BOWL_OF_HONEY_GLAZED_BEAR_MEAT_WITH_SALMON = ITEMS.register("bowl_of_honey_glazed_bear_meat_with_salmon", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BOWL_OF_HONEY_GLAZED_BEAR_MEAT_WITH_SALMON), true));
    public static final RegistrySupplier<Item> BRAISED_KANGAROO_MEATBALLS = ITEMS.register("braised_kangaroo_meatballs", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BREASTED_KANGAROO_MEATBALLS), true));
    public static final RegistrySupplier<Item> GRAVY_KANGAROO_MEAT = ITEMS.register("gravy_kangaroo_meat", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.GRAVY_KANGAROO_MEAT), true));
    public static final RegistrySupplier<Item> HONEY_GLAZED_MOOSE_RIBS = ITEMS.register("honey_glazed_moose_ribs", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.HONEY_GLAZED_MOOSE_RIBS), true));
    public static final RegistrySupplier<Item> ROAST_SEAGULL = ITEMS.register("roast_seagull", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.ROAST_SEAGULL), true));
    public static final RegistrySupplier<Item> CANNED_BEAR_MEAT = ITEMS.register("canned_bear_meat", () -> new SlowEatConsumableItem(bucketFoodBuilder(AMDFoodValues.CANNED_BEAR_MEAT), true));
    public static final RegistrySupplier<Item> FRIED_SEAGULL_WITH_FRIES = ITEMS.register("fried_seagull_with_fries", () -> new ConsumableItem(bucketFoodBuilder(AMDFoodValues.FRIED_SEAGULL_WITH_FRIES), true));
    public static final RegistrySupplier<Item> KATSUDON = ITEMS.register("katsudon", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.KATSUDON), true));
    public static final RegistrySupplier<Item> BOWL_OF_MOOSE_SAUSAGE_WITH_SALMON = ITEMS.register("bowl_of_moose_sausage_with_salmon", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BOWL_OF_MOOSE_SAUSAGE_WITH_SALMON), true));
    public static final RegistrySupplier<Item> BOWL_OF_STUFFED_CROCODILE_HEAD = ITEMS.register("bowl_of_stuffed_crocodile_head", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.STUFFED_CROCODILE_HEAD), true));
    public static final RegistrySupplier<Item> BOWL_OF_STUFFED_CROCODILE_TAIL = ITEMS.register("bowl_of_stuffed_crocodile_tail", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.STUFFED_CROCODILE_TAIL), true));
    public static final RegistrySupplier<Item> BOWL_OF_STUFFED_CROCODILE_LEG = ITEMS.register("bowl_of_stuffed_crocodile_leg", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.STUFFED_CROCODILE_CLAW), true));
    public static final RegistrySupplier<Item> BOWL_OF_STUFFED_CROCODILE = ITEMS.register("bowl_of_stuffed_crocodile", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.STUFFED_CROCODILE), true));
    public static final RegistrySupplier<Item> TENTACLE_MEDLEY = ITEMS.register("tentacle_medley", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.TENTACLE_MEDLEY), false));
    public static final RegistrySupplier<Item> CTHULHUS_BREAKFAST = ITEMS.register("cthulhus_breakfast", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.CTHULHUS_BREAKFAST), true));
    public static final RegistrySupplier<Item> LOBSTER_HEAD_STEW = ITEMS.register("lobster_head_stew", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.LOBSTER_HEAD_STEW), true));
    public static final RegistrySupplier<Item> LOBSTER_PASTA = ITEMS.register("lobster_pasta", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.LOBSTER_PASTA), true));
    public static final RegistrySupplier<Item> ACACIA_BLOSSOM_TENTACLE_SALAD = ITEMS.register("acacia_blossom_tentacle_salad", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.TENTACLE_SALAD), true));
    public static final RegistrySupplier<Item> WHALE_MEAT_WITH_TENTACLES = ITEMS.register("whale_meat_with_tentacles", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.WHALE_TENTACLE), true));
    public static final RegistrySupplier<Item> POT_OF_WHALE_MEAT_STEWED_WITH_PORK = ITEMS.register("pot_of_whale_meat_stewed_with_pork", () -> new ConsumableItem(customContainerFoodBuilder(AMDFoodValues.WHALE_PORK_STEW, Items.FLOWER_POT), true));
    public static final RegistrySupplier<Item> ANTS_CLIMBING_A_TREE = ITEMS.register("ants_climbing_a_tree", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.ANTS_CLIMBING_A_TREE), true));
    public static final RegistrySupplier<Item> BRAISED_TARANTULA_HAWK_LARVA = ITEMS.register("braised_tarantula_hawk_larva", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BRAISED_TARANTULA_HAWK_LARVA), true));
    public static final RegistrySupplier<Item> CRISPY_COCKROACH_WING = ITEMS.register("crispy_cockroach_wing", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.CRISPY_COCKROACH_WING), true));
    public static final RegistrySupplier<Item> CRISPY_TARANTULA_HAWK_WING = ITEMS.register("crispy_tarantula_hawk_wing", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.CRISPY_TARANTULA_HAWK_WING), true));
    public static final RegistrySupplier<Item> FRIED_ANTS = ITEMS.register("fried_ants", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.FRIED_ANTS), true));
    public static final RegistrySupplier<Item> FRIED_CENTIPEDE_MEAT = ITEMS.register("fried_centipede_meat", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.FRIED_CENTIPEDE_MEAT), true));
    public static final RegistrySupplier<Item> SERVE_OF_FRIED_TARANTULA_HAWK = ITEMS.register("serve_of_fried_tarantula_hawk", () -> new ConsumableItem(foodBuilder(AMDFoodValues.FRIED_TARANTULA_HAWK), true));
    public static final RegistrySupplier<Item> GAZING_INTO_THE_ABYSS = ITEMS.register("gazing_into_the_abyss", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.GAZING_INTO_THE_ABYSS), true));
    public static final RegistrySupplier<Item> MAGGOT_SALAD = ITEMS.register("maggot_salad", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.MAGGOT_SALAD), true));
    public static final RegistrySupplier<Item> PICKLED_MAGGOTS = ITEMS.register("pickled_maggots", () -> new ConsumableItem(foodBuilder(AMDFoodValues.PICKLED_MAGGOTS).craftRemainder(Items.GLASS_BOTTLE), true));
    public static final RegistrySupplier<Item> PLATE_OF_MUSHROOMS_BRAISED_WITH_CENTIPEDE = ITEMS.register("plate_of_mushrooms_braised_with_centipede", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.MUSHROOMS_BRAISED_WITH_CENTIPEDE), true));
    public static final RegistrySupplier<Item> TARANTULA_HAWK_SASHIMI = ITEMS.register("tarantula_hawk_sashimi", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.TARANTULA_HAWK_SASHIMI), true));
    public static final RegistrySupplier<Item> TRUE_ANTS_CLIMBING_A_TREE = ITEMS.register("true_ants_climbing_a_tree", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.TRUE_ANTS_CLIMBING_A_TREE), true));
    public static final RegistrySupplier<Item> GRILLED_RATTLE_TAIL = ITEMS.register("grilled_rattle_tail", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.GRILLED_RATTLE_TAIL), true));
    public static final RegistrySupplier<Item> TURTLE_JELLY = ITEMS.register("turtle_jelly", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.TURTLE_JELLY), true));
    public static final RegistrySupplier<Item> SNAKE_SKIN_SALAD = ITEMS.register("snake_skin_salad", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SNAKE_SKIN_SALAD)));
    public static final RegistrySupplier<Item> CHEESE_MASHED_POTATOES_WITH_ANACONDA = ITEMS.register("cheese_mashed_potatoes_with_anaconda", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.CHEESE_MASHED_POTATOES_WITH_ANACONDA), true));
    public static final RegistrySupplier<Item> BOWL_OF_STUFFED_GRILLED_ANACONDA = ITEMS.register("bowl_of_stuffed_grilled_anaconda", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.STUFFED_ANACONDA), true));
    public static final RegistrySupplier<Item> POT_OF_HUMMINGBIRD_STEW = ITEMS.register("pot_of_hummingbird_stew", () -> new ConsumableItem(customContainerFoodBuilder(AMDFoodValues.POT_OF_HUMMINGBIRD_STEW, Items.FLOWER_POT), true));
    public static final RegistrySupplier<Item> HANDTORN_EMU_MEAT_WITH_RICE = ITEMS.register("handtorn_emu_meat_with_rice", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.HANDTORN_EMU_MEAT_WITH_RICE), true));
    public static final RegistrySupplier<Item> PLATE_OF_BEGGARS_EMU = ITEMS.register("plate_of_beggars_emu", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.PLATE_OF_BEGGARS_EMU), true));
    public static final RegistrySupplier<Item> SPORES_BUNFUNGUS_LEG_PASTA = ITEMS.register("spores_bunfungus_leg_pasta", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SPORES_BUNFUNGUS_LEG_PASTA), true));
    public static final RegistrySupplier<Item> SLICED_FROSTSTALKER_MEAT_JELLY = ITEMS.register("sliced_froststalker_meat_jelly", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.SLICED_FROSTSTALKER_MEAT_JELLY), true));
    public static final RegistrySupplier<Item> BOWL_OF_BUNFUNGUS_HODGEPODGE = ITEMS.register("bowl_of_bunfungus_hodgepodge", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BOWL_OF_BUNFUNGUS_HODGEPODGE), true));
    public static final RegistrySupplier<Item> BOWL_OF_COLD_ROASTED_FROSTSTALKER_MEAT = ITEMS.register("bowl_of_cold_roasted_froststalker_meat", () -> new ConsumableItem(bowlFoodBuilder(AMDFoodValues.BOWL_OF_COLD_ROASTED_FROSTSTALKER_MEAT), true));

    //大菜
    public static final RegistrySupplier<Item> MOOSE_SAUSAGE_WITH_SALMON = ITEMS.register("moose_sausage_with_salmon", () -> new FeastBlockItems(BlockRegistry.MOOSE_SAUSAGE_WITH_SALMON.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> HONEY_GLAZED_BEAR_MEAT_WITH_SALMON = ITEMS.register("honey_glazed_bear_meat_with_salmon", () -> new FeastBlockItems(BlockRegistry.HONEY_GLAZED_BEAR_MEAT_WITH_SALMON.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> WILD_STEW = ITEMS.register("wild_stew", () -> new FeastBlockItems(BlockRegistry.WILD_STEW.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> ALEXS_RICE_ROLL_MEDLEY = ITEMS.register("alexs_rice_roll_medley", () -> new PlaceOnWaterBlockItem(BlockRegistry.ALEXS_RICE_ROLL_MEDLEY.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> STEAMED_STUFFED_CROCODILE = ITEMS.register("steamed_stuffed_crocodile", () -> new FeastBlockItems(BlockRegistry.STEAMED_STUFFED_CROCODILE.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> LOBSTER_ROLL_MEDLEY = ITEMS.register("lobster_roll_medley", () -> new FeastBlockItems(BlockRegistry.LOBSTER_ROLL_MEDLEY.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> WHALE_MEAT_STEWED_WITH_PORK = ITEMS.register("whale_meat_stewed_with_pork", () -> new FeastBlockItems(BlockRegistry.WHALE_MEAT_STEWED_WITH_PORK.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> FRIED_TARANTULA_HAWK = ITEMS.register("fried_tarantula_hawk", () -> new FeastBlockItems(BlockRegistry.FRIED_TARANTULA_HAWK.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> MUSHROOMS_BRAISED_WITH_CENTIPEDE = ITEMS.register("mushrooms_braised_with_centipede", () -> new FeastBlockItems(BlockRegistry.MUSHROOMS_BRAISED_WITH_CENTIPEDE.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> STUFFED_GRILLED_ANACONDA = ITEMS.register("stuffed_grilled_anaconda", () -> new FeastBlockItems(BlockRegistry.STUFFED_GRILLED_ANACONDA.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> BEGGARS_EMU = ITEMS.register("beggars_emu", () -> new FeastBlockItems(BlockRegistry.BEGGARS_EMU.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> BEGGARS_EMU_IN_THE_MUD = ITEMS.register("beggars_emu_in_the_mud", () -> new FeastBlockItems(BlockRegistry.BEGGARS_EMU_IN_THE_MUD.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> BUNFUNGUS_HODGEPODGE = ITEMS.register("bunfungus_hodgepodge", () -> new FeastBlockItems(BlockRegistry.BUNFUNGUS_HODGEPODGE.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> COLD_ROASTED_FROSTSTALKER_MEAT = ITEMS.register("cold_roasted_froststalker_meat", () -> new FeastBlockItems(BlockRegistry.COLD_ROASTED_FROSTSTALKER_MEAT.get(), defaultBuilder().stacksTo(1)));

    //方块
    public static final RegistrySupplier<Item> COASTAL_KIVIAK = ITEMS.register("coastal_kiviak", () -> new BlockItem(BlockRegistry.COASTAL_KIVIAK.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> POLAR_KIVIAK = ITEMS.register("polar_kiviak", () -> new BlockItem(BlockRegistry.POLAR_KIVIAK.get(), defaultBuilder().stacksTo(1)));
    public static final RegistrySupplier<Item> SEAL_FUR_CARPET_BROWN = ITEMS.register("seal_fur_carpet_brown", () -> new BlockItem(BlockRegistry.SEAL_FUR_CARPET_BROWN.get(), defaultBuilder()));
    public static final RegistrySupplier<Item> SEAL_FUR_CARPET_GRAY = ITEMS.register("seal_fur_carpet_gray", () -> new BlockItem(BlockRegistry.SEAL_FUR_CARPET_GRAY.get(), defaultBuilder()));
    public static final RegistrySupplier<Item> MAGGOT_FARM = ITEMS.register("maggot_farm", () -> new BlockItem(BlockRegistry.MAGGOT_FARM_BLOCK.get(), defaultBuilder()));
    public static final RegistrySupplier<Item> END_CHEESE_ORE = ITEMS.register("end_cheese_ore", () -> new BlockItem(BlockRegistry.END_CHEESE_ORE.get(), defaultBuilder()));


    private static Item.Properties defaultBuilder() {
        return new Item.Properties();
    }

    public static Item.Properties foodBuilder(FoodProperties food) {
        return defaultBuilder().food(food);
    }

    private static Item.Properties bowlFoodBuilder(FoodProperties food) {
        return defaultBuilder().craftRemainder(Items.BOWL).stacksTo(16).food(food);
    }

    private static Item.Properties drinkBuilder(FoodProperties food) {
        return defaultBuilder().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(food);
    }

    /** The Miner's Delight cups are no longer part of the port, so soups always return a bowl. */
    private static Item.Properties cupFoodBuilder(FoodProperties food) {
        return defaultBuilder().craftRemainder(Items.BOWL).stacksTo(16).food(food);
    }

    public static void register() {
        ITEMS.register();
    }

    private static Item.Properties bucketFoodBuilder(FoodProperties food) {
        return defaultBuilder().craftRemainder(Items.BUCKET).stacksTo(1).food(food);
    }

    private static Item.Properties customContainerFoodBuilder(FoodProperties food, Item item) {
        return defaultBuilder().craftRemainder(item).stacksTo(16).food(food);
    }
}
