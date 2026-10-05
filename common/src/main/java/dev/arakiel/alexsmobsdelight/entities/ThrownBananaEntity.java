package dev.arakiel.alexsmobsdelight.entities;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import dev.arakiel.alexsmobsdelight.init.EntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownBananaEntity extends ThrowableItemProjectile {
    public ThrownBananaEntity(EntityType<? extends ThrownBananaEntity> p_37473_, Level p_37474_) {
        super(p_37473_, p_37474_);
    }

    public ThrownBananaEntity(Level p_37481_, LivingEntity p_37482_) {
        super(EntityTypeRegistry.THROWN_BANANA.get(), p_37482_, p_37481_);
    }

    public ThrownBananaEntity(Level p_37476_, double p_37477_, double p_37478_, double p_37479_) {
        super(EntityTypeRegistry.THROWN_BANANA.get(), p_37477_, p_37478_, p_37479_, p_37476_);
    }

    public void handleEntityEvent(byte p_37484_) {
        if (p_37484_ == 3) {
            for(int $$2 = 0; $$2 < 8; ++$$2) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08);
            }
        }

    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (!this.level().isClientSide) {
            p_37486_.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 2.0F);
            if(p_37486_.getEntity() instanceof LivingEntity living){
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 60));
            }
            if (this.random.nextInt(8) == 0 && p_37486_.getEntity().onGround()) {
                BlockPos pos = p_37486_.getEntity().getOnPos().above();
                if(this.level().getBlockState(pos).canBeReplaced()){
                    this.level().setBlock(pos, AMBlockRegistry.BANANA_PEEL.get().defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
                }
            }
        }
    }

    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);
        if (!this.level().isClientSide) {
            if (this.random.nextInt(8) == 0) {
                BlockPos pos = this.getOnPos().above();
                if(this.level().getBlockState(pos).canBeReplaced()){
                    this.level().setBlock(pos, AMBlockRegistry.BANANA_PEEL.get().defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
                }
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    protected Item getDefaultItem() {
        return AMItemRegistry.BANANA.get();
    }
}
