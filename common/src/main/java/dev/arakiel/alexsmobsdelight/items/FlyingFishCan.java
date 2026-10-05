package dev.arakiel.alexsmobsdelight.items;

import java.util.List;

import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/**
 * Opened can of flying fish.
 *
 * <p>The stink particles used to come from Alex's Mobs' client particle registry; the particle type
 * is looked up from the registry instead so this common code never touches a client only class.</p>
 */
public class FlyingFishCan extends Item {
    private static final ResourceLocation SMELLY = ResourceLocation.fromNamespaceAndPath("alexsmobs", "smelly");

    public FlyingFishCan(Properties properties) {
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
        ItemStack result = new ItemStack(ItemRegistry.OPENED_SURFLYGFISK.get());
        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
        level.playSound(null, entity, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.5F);

        ParticleType<?> smelly = BuiltInRegistries.PARTICLE_TYPE.get(SMELLY);
        if (smelly instanceof SimpleParticleType particle) {
            for (int i = 0; i < 10 + entity.getRandom().nextInt(6); ++i) {
                level.addParticle(particle, entity.getRandomX(1.0), entity.getRandomY(), entity.getRandomZ(1.0),
                        0.0, 0.0, 0.0);
            }
        }
        for (Mob nearby : level.getEntitiesOfClass(Mob.class, entity.getBoundingBox().inflate(15.0))) {
            if (nearby != entity && !nearby.isAlliedTo(entity) && !entity.isAlliedTo(nearby)
                    && !(entity instanceof IHurtableMultipart)) {
                nearby.setLastHurtByMob(entity);
                nearby.setTarget(entity);
            }
        }
        return result;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.surflygfisk").withStyle(ChatFormatting.RED));
    }
}
