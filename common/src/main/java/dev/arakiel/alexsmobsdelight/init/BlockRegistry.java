package dev.arakiel.alexsmobsdelight.init;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.blocks.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.PushReaction;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import vectorwing.farmersdelight.common.block.PieBlock;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> HONEY_GLAZED_BEAR_MEAT_WITH_SALMON = BLOCKS.register("honey_glazed_bear_meat_with_salmon", () -> {
        return new HoneyGlazedBearMeatWithSalmonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.BOWL_OF_HONEY_GLAZED_BEAR_MEAT_WITH_SALMON, true);
    });
    public static final RegistrySupplier<Block> MOOSE_SAUSAGE_WITH_SALMON = BLOCKS.register("moose_sausage_with_salmon", () -> {
        return new MooseSausageWithSalmonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.BONE_BLOCK), ItemRegistry.BOWL_OF_MOOSE_SAUSAGE_WITH_SALMON, true);
    });
    public static final RegistrySupplier<Block> MOOSE_PIE_BLOCK = BLOCKS.register("moose_pie", () -> {
        return new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.MOOSE_PIE_SLICE);
    });
    public static final RegistrySupplier<Block> MUSHROOM_BUNFUNGUS_EAR_PIE = BLOCKS.register("mushroom_bunfungus_ear_pie", () -> {
        return new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.MUSHROOM_BUNFUNGUS_EAR_PIE_SLICE);
    });
    public static final RegistrySupplier<Block> WILD_STEW = BLOCKS.register("wild_stew", () -> {
        return new WildStewBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.LANTERN), ItemRegistry.BOWL_OF_WILD_STEW, true);
    });
    public static final RegistrySupplier<Block> ALEXS_RICE_ROLL_MEDLEY = BLOCKS.register("alexs_rice_roll_medley", () -> {
        return new AlexsRiceRollMedleyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
    });
    public static final RegistrySupplier<Block> LOBSTER_ROLL_MEDLEY = BLOCKS.register("lobster_roll_medley", () -> {
        return new LobsterRollMedleyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
    });
    public static final RegistrySupplier<Block> STEAMED_STUFFED_CROCODILE = BLOCKS.register("steamed_stuffed_crocodile", () -> {
        return new SteamedStuffedCrocodileBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)
                .pushReaction(PushReaction.DESTROY));
    });
    public static final RegistrySupplier<Block> WHALE_MEAT_STEWED_WITH_PORK = BLOCKS.register("whale_meat_stewed_with_pork", () -> {
        return new WhalePorkStewBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.DECORATED_POT), ItemRegistry.POT_OF_WHALE_MEAT_STEWED_WITH_PORK, true);
    });
    public static final RegistrySupplier<Block> FRIED_TARANTULA_HAWK = BLOCKS.register("fried_tarantula_hawk", () -> {
        return new FriedTarantulaHawkBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.SERVE_OF_FRIED_TARANTULA_HAWK, true);
    });
    public static final RegistrySupplier<Block> STUFFED_GRILLED_ANACONDA = BLOCKS.register("stuffed_grilled_anaconda", () -> {
        return new StuffedGrilledAnacondaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.BONE_BLOCK), ItemRegistry.BOWL_OF_STUFFED_GRILLED_ANACONDA, true);
    });
    public static final RegistrySupplier<Block> BEGGARS_EMU = BLOCKS.register("beggars_emu", () -> {
        return new BeggarsEmuBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.PLATE_OF_BEGGARS_EMU, true);
    });
    public static final RegistrySupplier<Block> MUSHROOMS_BRAISED_WITH_CENTIPEDE = BLOCKS.register("mushrooms_braised_with_centipede", () -> {
        return new MushroomsBraisedWithCentipedeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.COPPER), ItemRegistry.PLATE_OF_MUSHROOMS_BRAISED_WITH_CENTIPEDE, true);
    });
    public static final RegistrySupplier<Block> BEGGARS_EMU_IN_THE_MUD = BLOCKS.register("beggars_emu_in_the_mud", () -> {
        return new BeggarsEmuInTheMudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD).randomTicks());
    });
    public static final RegistrySupplier<Block> BUNFUNGUS_HODGEPODGE = BLOCKS.register("bunfungus_hodgepodge", () -> {
        return new BunfungusHodgepodgeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.BOWL_OF_BUNFUNGUS_HODGEPODGE, true);
    });
    public static final RegistrySupplier<Block> COLD_ROASTED_FROSTSTALKER_MEAT = BLOCKS.register("cold_roasted_froststalker_meat", () -> {
        return new WildStewBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.LANTERN), ItemRegistry.BOWL_OF_COLD_ROASTED_FROSTSTALKER_MEAT, true);
    });
    public static final RegistrySupplier<Block> BANANA_BLOCK = BLOCKS.register("banana_block", () -> {
        return new BananaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).sound(SoundType.BIG_DRIPLEAF).noCollission());
    });
    public static final RegistrySupplier<Block> COASTAL_KIVIAK = BLOCKS.register("coastal_kiviak", () -> {
        return new KiviakBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART_BLOCK));
    });
    public static final RegistrySupplier<Block> POLAR_KIVIAK = BLOCKS.register("polar_kiviak", () -> {
        return new KiviakBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART_BLOCK));
    });
    public static final RegistrySupplier<Block> ACACIA_BLOSSOM_BLOCK = BLOCKS.register("acacia_blossom_block", () -> {
        return new AcaciaBlossomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).sound(SoundType.GRASS).noCollission());
    });
    public static final RegistrySupplier<Block> SEAL_FUR_CARPET_BROWN = BLOCKS.register("seal_fur_carpet_brown", () -> {
        return new SealCarpet(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_CARPET));
    });
    public static final RegistrySupplier<Block> SEAL_FUR_CARPET_GRAY = BLOCKS.register("seal_fur_carpet_gray", () -> {
        return new SealCarpet(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CARPET));
    });
    public static final RegistrySupplier<Block> ACACIA_BLOSSOM_CAKE = BLOCKS.register("acacia_blossom_cake", () -> new CommonCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.ACACIA_BLOSSOM_CAKE_SLICE));
    public static final RegistrySupplier<Block> MUNGAL_SPORES_CAKE = BLOCKS.register("mungal_spores_cake", () -> new CommonCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.MUNGAL_SPORES_CAKE_SLICE));

    public static final RegistrySupplier<Block> MAGGOT_FARM_BLOCK = BLOCKS.register("maggot_farm", () -> new MaggotFarmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COMPOSTER)));
    public static final RegistrySupplier<Block> END_CHEESE_ORE = BLOCKS.register("end_cheese_ore", () -> new DropExperienceBlock(UniformInt.of(1, 3), BlockBehaviour.Properties.of().strength(1.0F, 1.0F)));

    public static void register() {
        BLOCKS.register();
    }

}
