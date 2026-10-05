package dev.arakiel.alexsmobsdelight.accessor;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public interface IEntityMantisShrimpData {
    ItemStack amd$getLastHoldItem();
    void amd$setLastHoldItem(ItemStack item);
}
