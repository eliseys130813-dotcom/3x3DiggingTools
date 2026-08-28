package com.example.mchwe.Tires;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.SimpleTier;

public class myTiers {

    public static final Tier HAMMER_IRON = new SimpleTier(
            Tiers.IRON.getIncorrectBlocksForDrops(),
            Tiers.IRON.getUses()*2,
            6.0F,
            10F,
            14,
            Tiers.IRON::getRepairIngredient
    );
    public static final Tier HAMMER_DIAMOND = new SimpleTier(
            Tiers.DIAMOND.getIncorrectBlocksForDrops(),
            Tiers.DIAMOND.getUses()*2,
            6.0F,
            10F,
            14,
            Tiers.DIAMOND::getRepairIngredient // Чем чинится
    );
    public static final Tier HAMMER_NETHERITE = new SimpleTier(
            Tiers.NETHERITE.getIncorrectBlocksForDrops(),
            Tiers.NETHERITE.getUses()*2,
            6.0F,
            11F,
            14,
            Tiers.NETHERITE::getRepairIngredient
    );
    public static final Tier SHOVEL_IRON = new SimpleTier(
            Tiers.IRON.getIncorrectBlocksForDrops(),
            Tiers.IRON.getUses()*3,
            Tiers.IRON.getSpeed(),
            10F,
            14,
            Tiers.IRON::getRepairIngredient
    );
    public static final Tier SLEDGEHAMMER_NETHERITE = new SimpleTier(
            Tiers.NETHERITE.getIncorrectBlocksForDrops(),
            Tiers.NETHERITE.getUses()*4,
            7.0F,
            13F,
            14,
            Tiers.NETHERITE::getRepairIngredient
    );


}
