package dev.arakiel.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.entity.util.RainbowUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class SlowEatConsumableItem extends ConsumableItem {

    public SlowEatConsumableItem(Item.Properties properties) {
        super(properties);
    }

    public SlowEatConsumableItem(Item.Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public SlowEatConsumableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        if (stack.has(net.minecraft.core.component.DataComponents.FOOD)) {
            return 64;
        }
        return 0;
    }
}
