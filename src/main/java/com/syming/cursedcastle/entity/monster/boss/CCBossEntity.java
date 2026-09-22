package com.syming.cursedcastle.entity.monster.boss;

import com.syming.cursedcastle.api.ICustomBossBar;
import com.syming.cursedcastle.entity.monster.CCMonster;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class CCBossEntity extends CCMonster implements ICustomBossBar {
    public CCBossEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
}
