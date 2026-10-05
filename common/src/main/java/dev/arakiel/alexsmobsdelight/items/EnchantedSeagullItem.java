package dev.arakiel.alexsmobsdelight.items;

import java.util.List;

import dev.arakiel.alexsmobsdelight.helper.EffectListHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/** Food that carries every effect the seagull absorbed from the food it stole. */
public class EnchantedSeagullItem extends Item {
    public EnchantedSeagullItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (stack.has(DataComponents.FOOD)) {
            for (MobEffectInstance instance : EffectListHelper.getEffects(stack)) {
                entity.addEffect(instance);
            }
        }
        return entity.eat(level, stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        for (MobEffectInstance instance : EffectListHelper.getEffects(stack)) {
            Holder<MobEffect> holder = instance.getEffect();
            MobEffect effect = holder.value();
            String levelSuffix = instance.getAmplifier() != 0
                    ? " " + Component.translatable("enchantment.level." + (instance.getAmplifier() + 1)).getString()
                    : "";
            ChatFormatting style = effect.getCategory() == MobEffectCategory.BENEFICIAL ? ChatFormatting.BLUE
                    : effect.getCategory() == MobEffectCategory.HARMFUL ? ChatFormatting.RED : ChatFormatting.GRAY;
            tooltip.add(Component.translatable("tooltip.alexsmobsdelight.effect_desc",
                            effect.getDisplayName().getString(), levelSuffix,
                            MobEffectUtil.formatDuration(instance, 1.0F, 1.0F))
                    .withStyle(style));
        }
    }
}
