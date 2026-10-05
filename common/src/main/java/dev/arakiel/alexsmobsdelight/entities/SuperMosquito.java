package dev.arakiel.alexsmobsdelight.entities;

import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.EntityHemolymph;
import com.github.alexthe666.alexsmobs.entity.EntityMosquitoSpit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class SuperMosquito extends EntityCrimsonMosquito implements PowerableMob {
    private final ServerBossEvent bossEvent;

    public SuperMosquito(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.bossEvent = (ServerBossEvent)(new ServerBossEvent(
                Component.translatable("entity.alexsmobsdelight.super_mosquito.display").withStyle(ChatFormatting.BOLD, ChatFormatting.DARK_PURPLE),
                BossEvent.BossBarColor.PURPLE,
                BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 300.0).add(Attributes.FOLLOW_RANGE, 64.0).add(Attributes.ARMOR, 0.0).add(Attributes.ATTACK_DAMAGE, 8.0).add(Attributes.MOVEMENT_SPEED, 0.5);
    }

    private void spit(LivingEntity target) {
        if (!this.isSick()) {
            for(int i = 0; i < 2 + this.random.nextInt(2); ++i) {
                EntityHemolymph llamaspitentity = new EntityHemolymph(this.level(), this, false);
                double d0 = target.getX() - this.getX();
                double d1 = target.getY(0.3333333333333333) - llamaspitentity.getY();
                double d2 = target.getZ() - this.getZ();
                float f = Mth.sqrt((float)(d0 * d0 + d2 * d2)) * 0.2F;
                llamaspitentity.shoot(d0, d1 + (double)f, d2, 1.5F, 5.0F);
                if (!this.isSilent()) {
                    this.gameEvent(GameEvent.PROJECTILE_SHOOT);
                    this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LAVA_POP, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                }

                this.level().addFreshEntity(llamaspitentity);
            }
        }
    }

    public void tick() {
        super.tick();
        if(this.getBloodLevel() > 0){
            if(this.getTarget() != null){
                spit(this.getTarget());
            }
        }
    }

    @Override
    public boolean isPowered() {
        return true;
    }
}
