package dev.arakiel.alexsmobsdelight.init;

import java.util.List;
import java.util.function.Supplier;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

/** The mod's creative tab. */
public final class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(AlexsMobsDelight.MOD_ID, Registries.CREATIVE_MODE_TAB);

    private static final List<Supplier<Item>> NO_TAB_ITEMS = List.of(
            ItemRegistry.MIMICANDY,
            ItemRegistry.DIMENSIONAL_FOOD,
            ItemRegistry.DIMENSIONAL_SLICER
    );

    public static final RegistrySupplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(
            AlexsMobsDelight.MOD_ID, () -> CreativeTabRegistry.create(builder -> builder
                    .title(Component.translatable("itemGroup.alexsmobsdelight"))
                    .icon(() -> ItemRegistry.MANTIS_SHRIMP_TAIL_LIME.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        for (RegistrySupplier<Item> item : ItemRegistry.ITEMS) {
                            if (!NO_TAB_ITEMS.contains(item)) {
                                output.accept(item.get());
                            }
                        }
                    })));

    private TabRegistry() {
    }

    public static void register() {
        CREATIVE_MODE_TABS.register();
    }
}
