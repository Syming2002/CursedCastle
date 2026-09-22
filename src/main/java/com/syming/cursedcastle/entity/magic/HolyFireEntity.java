package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;

public class HolyFireEntity extends AreaEffectCloud implements IElementalDamage {
    public HolyFireEntity(EntityType<? extends AreaEffectCloud> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public HolyFireEntity(Level pLevel) {
        super(CCEntity.HOLY_FIRE.get(), pLevel);
    }

    public HolyFireEntity(Level pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);

        setRadius(2.25F);
        setRadiusOnUse(-0.5F);
        setRadiusPerTick(-0.03F);
        addEffect(new MobEffectInstance(CCEffect.HOLY));
        setParticle(ParticleTypes.EFFECT);
    }

    @Override
    public void tick() {
        super.tick();

//        List<Entity> livingEntities = this.level().getEntities(this, getBoundingBox());
//        for (Entity livingEntity : livingEntities) {
//            if (livingEntity instanceof Mob mob && livingEntity != getOwner()) {
//                if (!mob.fireImmune()) {
//                    mob.setRemainingFireTicks(5);
//                }
//                mob.addEffect(new MobEffectInstance(CCEffect.HOLY, 200, 10));
//            }
//        }
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return CCDamageType.causeHolyDamage(target);
    }
}
