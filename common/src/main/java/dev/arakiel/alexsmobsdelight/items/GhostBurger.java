package dev.arakiel.alexsmobsdelight.items;

import java.util.List;

import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/** Burger that drains the eater's hunger completely. */
public class GhostBurger extends Item {
    public GhostBurger(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            player.getFoodData().setFoodLevel(0);
            player.getFoodData().setSaturation(0);
        }
        level.playSound(null, entity, AMSoundRegistry.UNDERMINER_IDLE.get(), SoundSource.PLAYERS, 1.0F,
                1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.ghostly_burger")
                .withStyle(ChatFormatting.DARK_AQUA));
    }
}
