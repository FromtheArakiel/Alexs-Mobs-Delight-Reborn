package dev.arakiel.alexsmobsdelight.entities;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.github.alexthe666.alexsmobs.entity.EntityStradpole;
import dev.arakiel.alexsmobsdelight.entities.ai.SuperSeagullAIStealFromPlayers;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;

public class SuperSeagull extends EntitySeagull implements PowerableMob {
    private int shootCooldown;
    private int eatCount;
    private final ServerBossEvent bossEvent;
    private final List<EntityType<?>> entitySummonsEasy = List.of(
            AMEntityRegistry.DROPBEAR.get(),
            AMEntityRegistry.CROCODILE.get(),
            AMEntityRegistry.STRADDLER.get(),
            AMEntityRegistry.GRIZZLY_BEAR.get(),
            AMEntityRegistry.SOUL_VULTURE.get(),
            AMEntityRegistry.MURMUR.get()
    );
    private final List<EntityType<?>> entitySummonsNormal = List.of(
            AMEntityRegistry.DROPBEAR.get(),
            AMEntityRegistry.STRADDLER.get(),
            AMEntityRegistry.GRIZZLY_BEAR.get(),
            AMEntityRegistry.SOUL_VULTURE.get(),
            AMEntityRegistry.MURMUR.get(),
            AMEntityRegistry.MIMICUBE.get()
    );

    private final List<EntityType<?>> entitySummonsHard = List.of(
            AMEntityRegistry.DROPBEAR.get(),
            AMEntityRegistry.STRADDLER.get(),
            AMEntityRegistry.SOUL_VULTURE.get(),
            AMEntityRegistry.MURMUR.get(),
            AMEntityRegistry.MIMICUBE.get(),
            AMEntityRegistry.FARSEER.get()
    );

    public SuperSeagull(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.shootCooldown = 0;
        this.eatCount = 0;
        this.bossEvent = (ServerBossEvent)(new ServerBossEvent(
                Component.translatable("entity.alexsmobsdelight.super_seagull.display").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA),
                BossEvent.BossBarColor.BLUE,
                BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new SuperSeagullAIStealFromPlayers(this));
        //this.goalSelector.addGoal(5, new AIWanderIdle());
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, PathfinderMob.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        //this.goalSelector.addGoal(9, new AIScatter());
        //this.targetSelector.addGoal(1, new AITargetItems(this, false, false, 15, 16));
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 200.0).add(Attributes.ATTACK_DAMAGE, 8.0).add(Attributes.MOVEMENT_SPEED, 0.4);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return !stack.isEmpty() && !this.isSitting();
    }

    public void tick() {
        if(this.stealCooldown > 20){
            this.stealCooldown = 20;
        }
        if (this.isAlive() && this.getTarget() != null && !this.getMainHandItem().isEmpty()) {
            int difficulty = this.level().getDifficulty().getId();
            List<EntityType<?>> summonEntities = difficulty == 1 ? entitySummonsEasy : difficulty == 2 ? entitySummonsNormal : entitySummonsHard;
            if(this.getMainHandItem().getCount() == 1){
                if(eatCount==15){
                    this.summonEnemies(summonEntities.get(0));
                }
                if(eatCount==30){
                    this.summonEnemies(summonEntities.get(1));
                }
                if(eatCount==45){
                    this.summonEnemies(summonEntities.get(2));
                }
                if(eatCount==55){
                    this.summonEnemies(summonEntities.get(3));
                }
                if(eatCount==60){
                    this.summonEnemies(summonEntities.get(4));
                }
                if(eatCount==63){
                    EntityType<?> entityType = summonEntities.get(5);
                    if(entityType == AMEntityRegistry.FARSEER.get()){
                        this.summonEnemiesOnTargetPos(entityType);
                    }else{
                        this.summonEnemies(entityType);
                    }

                }
                this.eatCount++;
            }else{
                super.eatItem();
                this.eatCount = 0;
            }
            if(this.shootCooldown <= 0){
                this.shootCooldown = 10;
                EntityStradpole pole = (EntityStradpole)((EntityType<?>) AMEntityRegistry.STRADPOLE.get()).create(this.level());
                pole.setPos(this.getX(), this.getEyeY(), this.getZ());
                pole.setParentId(this.getUUID());
                double d0 = this.getTarget().getEyeY() - 1.1;
                double d1 = this.getTarget().getX() - this.getX();
                double d2 = d0 - pole.getY();
                double d3 = this.getTarget().getZ() - this.getZ();
                float f3 = Mth.sqrt((float)(d1 * d1 + d2 * d2 + d3 * d3)) * 0.2F;
                this.gameEvent(GameEvent.PROJECTILE_SHOOT);
                this.playSound(SoundEvents.CROSSBOW_LOADING_END.value(), 2.0F,
                        1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
                pole.shoot(d1, d2 + (double)f3, d3, 2.0F, 0.0F);
                pole.setYRot(this.getYRot() % 360.0F);
                pole.setXRot(Mth.clamp(this.getYRot(), -90.0F, 90.0F) % 360.0F);
                if (!this.level().isClientSide) {
                    this.level().addFreshEntity(pole);
                }
            }
            this.shootCooldown--;
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return super.isInvulnerableTo(source) || source.getEntity() instanceof EntityStradpole || source.getEntity() instanceof SuperSeagull || shouldImmuneDamage();
    }

    public void summonEnemies(EntityType<?> entitytype){
        if(this.level() instanceof ServerLevel serverLevel){
            entitytype.spawn(serverLevel, this.getOnPos(), MobSpawnType.MOB_SUMMONED);
        }
    }

    public void summonEnemiesOnTargetPos(EntityType<?> entitytype){
        if(this.level() instanceof ServerLevel serverLevel){
            entitytype.spawn(serverLevel, this.getTarget().getOnPos(), MobSpawnType.MOB_SUMMONED);
        }
    }

    @Override
    protected void actuallyHurt(DamageSource source, float damage) {
        if (!this.isInvulnerableTo(source)) {
            damage = this.getDamageAfterArmorAbsorb(source, damage);
            damage = this.getDamageAfterMagicAbsorb(source, damage);
            float f1 = Math.max(damage - this.getAbsorptionAmount(), 0.0F);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - (damage - f1));
            float f = damage - f1;
            if (f > 0.0F && f < 3.4028235E37F) {
                Entity entity = source.getEntity();
                if (entity instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)entity;
                    serverplayer.awardStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(f * 10.0F));
                }
            }

            if (f1 != 0.0F) {
                this.getCombatTracker().recordDamage(source, f1);

                this.setHealth(this.isHalfLife() ? this.getHealth() - f1 : Math.max(this.getMaxHealth() / 2, this.getHealth() - f1));
                this.setAbsorptionAmount(this.getAbsorptionAmount() - f1);
                this.gameEvent(GameEvent.ENTITY_DAMAGE);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Flying", this.isFlying());
        compound.putBoolean("Sitting", this.isSitting());
        compound.putInt("StealCooldown", this.stealCooldown);
        compound.putInt("TreasureSitTime", this.treasureSitTime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFlying(compound.getBoolean("Flying"));
        this.setSitting(compound.getBoolean("Sitting"));
        this.stealCooldown = compound.getInt("StealCooldown");
        this.treasureSitTime = compound.getInt("TreasureSitTime");
    }

    @Override
    public boolean isPowered() {
        return shouldImmuneDamage();
    }

    public boolean isHalfLife(){
        return this.getHealth() * 2 <= this.getMaxHealth();
    }

    public boolean shouldImmuneDamage(){
        return this.getMainHandItem().isEmpty() == isHalfLife();
    }

    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        this.bossEvent.addPlayer(p_31483_);
    }

    public void stopSeenByPlayer(ServerPlayer p_31488_) {
        super.stopSeenByPlayer(p_31488_);
        this.bossEvent.removePlayer(p_31488_);
    }
}
