package com.example.mchwe.item;

import com.example.mchwe.Tires.myTiers;
import com.example.mchwe.threexthreemod;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(threexthreemod.MODID);
    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer",
            () -> new hammer(myTiers.HAMMER_IRON, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.DIAMOND, 9.0F, -3.2F))
                    .durability(500)));
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new hammer(myTiers.HAMMER_DIAMOND, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.DIAMOND, 9.0F, -3.2F))
                    .durability(3122)));
    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new hammer(myTiers.HAMMER_NETHERITE, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.NETHERITE, 9.0F, -3.2F))
                    .durability(4061)));
    public static final DeferredItem<Item> NETHERITE_SLEDGEHAMMER = ITEMS.register("netherite_sledgehammer",
            () -> new sledgehammer(myTiers.SLEDGEHAMMER_NETHERITE, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.NETHERITE, 9.0F, -3.2F))
                    .durability(4061)));
    public static final DeferredItem<Item> BIG_SHOVEL = ITEMS.register("big_shovel",
            () -> new big_shovel(myTiers.SHOVEL_IRON, new Item.Properties()));

    public static final DeferredItem<Item> BIG_DIAMOND_SHOVEL = ITEMS.register("big_diamond_shovel",
            () -> new big_shovel(myTiers.SHOVEL_DIAMOND, new Item.Properties()));

    public static final DeferredItem<Item> BIG_NETHERITE_SHOVEL = ITEMS.register("big_netherite_shovel",
            () -> new big_shovel(myTiers.SHOVEL_NETHERITE, new Item.Properties()));
    public static final DeferredItem<Item> LARGE_NETHERITE_SHOVEL = ITEMS.register("large_netherite_shovel",
            () -> new large_shovel(myTiers.LARGE_SHOVEL_NETHERITE, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
