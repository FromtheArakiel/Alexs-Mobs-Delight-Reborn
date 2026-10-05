package dev.arakiel.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.entity.util.RainbowUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class RainbowConsumableItem extends ConsumableItem {
    public RainbowConsumableItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack st, Level level, LivingEntity e) {
        RainbowUtil.setRainbowType(e, RainbowUtil.getRainbowTypeFromStack(st));
        return super.finishUsingItem(st, level, e);
    }
}
