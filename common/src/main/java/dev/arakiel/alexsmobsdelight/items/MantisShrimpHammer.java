package dev.arakiel.alexsmobsdelight.items;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

/** Mantis shrimp hammer: auto smelts whatever it harvests. */
public class MantisShrimpHammer extends PickaxeItem {
    public MantisShrimpHammer(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.mantis_shrimp_tools")
                .withStyle(ChatFormatting.GOLD));
    }
}
