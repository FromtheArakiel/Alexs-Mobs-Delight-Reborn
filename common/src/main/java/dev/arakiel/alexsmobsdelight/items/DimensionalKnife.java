package dev.arakiel.alexsmobsdelight.items;

import java.util.List;

import dev.arakiel.alexsmobsdelight.helper.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.item.KnifeItem;

/** Knife that always drops the full loot of whatever it kills. */
public class DimensionalKnife extends KnifeItem {
    public DimensionalKnife(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(ItemHelper.tooltip(stack).withStyle(ChatFormatting.DARK_PURPLE));
    }
}
