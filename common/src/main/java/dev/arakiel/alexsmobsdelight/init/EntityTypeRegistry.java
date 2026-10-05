package dev.arakiel.alexsmobsdelight.init;

import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.entities.SuperMosquito;
import dev.arakiel.alexsmobsdelight.entities.SuperSeagull;
import dev.arakiel.alexsmobsdelight.entities.ThrownBananaEntity;
import dev.arakiel.alexsmobsdelight.entities.ThrownDartEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

/**
 * Entity types.
 *
 * <p>The attributes of the two mobs are registered by the platform projects: Fabric uses
 * {@code FabricDefaultAttributeRegistry}, NeoForge the {@code EntityAttributeCreationEvent}.</p>
 */
public final class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<ThrownDartEntity>> THROWN_DART =
            abstractArrow("thrown_dart", ThrownDartEntity::new);
    public static final RegistrySupplier<EntityType<ThrownBananaEntity>> THROWN_BANANA =
            abstractArrow("thrown_banana", ThrownBananaEntity::new);

    public static final RegistrySupplier<EntityType<SuperSeagull>> SUPER_SEAGULL = register("super_seagull",
            EntityType.Builder.of(SuperSeagull::new, MobCategory.CREATURE).sized(0.9F, 0.9F)
                    .clientTrackingRange(45));

    public static final RegistrySupplier<EntityType<SuperMosquito>> SUPER_MOSQUITO = register("super_mosquito",
            EntityType.Builder.of(SuperMosquito::new, MobCategory.MONSTER).sized(1.5F, 1.3F).fireImmune()
                    .clientTrackingRange(45));

    private EntityTypeRegistry() {
    }

    public static void register() {
        ENTITY_TYPES.register();
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> register(String name,
                                                                               EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(name));
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> abstractArrow(String name,
                                                                                    EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build(name));
    }
}
