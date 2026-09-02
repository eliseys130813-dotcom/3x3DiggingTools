package com.example.mchwe;

import com.example.mchwe.item.items;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(threexthreemod.MODID)
public class threexthreemod {
    private boolean isPlacing3x3 = false;
    public boolean mining=true;
    public BlockPos getPlaceBlockPos(BlockEvent.EntityPlaceEvent event){
        return event.getPos();
    }

    public BlockState getPlaceBlock(BlockEvent.EntityPlaceEvent event){
        return event.getPlacedBlock();
    }
    public Direction getDir(BlockEvent.EntityPlaceEvent event){
        return getTargetedFace();
    }
    public static Direction getTargetedFace() {
        Minecraft mc = Minecraft.getInstance();


        if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) mc.hitResult;
            return blockHit.getDirection(); // Возвращает Direction
        }

        return null;
    }
    public Player getPlayer(BlockEvent.EntityPlaceEvent event){
        if (event.getEntity() instanceof Player) {
            return (Player) event.getEntity();
        } else {
            return null;
        }
    }
    public Player getBreakPlayer(BlockEvent.BreakEvent event){

            return (Player) event.getPlayer();

    }
    @SubscribeEvent
    public void blockPlacing3x3(BlockEvent.EntityPlaceEvent event) {

        if (this.isPlacing3x3) {
            return;
        }
        BlockPos pos = getPlaceBlockPos(event);
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack stack=player.getMainHandItem();
            int r = 1;
            if (player.getMainHandItem().is(items.PLACER5X5.get())) {
                r = 2;
            }
            if (player.getOffhandItem().getItem() instanceof DiggerItem){
                stack = player.getOffhandItem();
            }
            if (player.getMainHandItem().is(items.PLACER3X3.get()) && !player.getOffhandItem().isEmpty() || player.getMainHandItem().is(items.PLACER5X5.get()) && !player.getOffhandItem().isEmpty()) {
                if (player instanceof ServerPlayer) {
                    ServerPlayer splayer = (ServerPlayer) player;
                    if (!player.isCrouching()) {
                        if (getDir(event) == Direction.UP || getDir(event) == Direction.DOWN) {
                            try {
                                this.isPlacing3x3 = true;
                                for (int i = -r; i <= r; i++) {
                                    for (int j = -r; j <= r; j++) {
                                        //are calculated positions
                                        BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + j);
                                        BlockState state1 = event.getLevel().getBlockState(pos1);
                                        //PLACING BLOCK AROUND
                                        if (!state1.is(Blocks.BEDROCK) && !pos1.equals(pos)) {
                                            splayer.gameMode.useItemOn(splayer, splayer.level(), splayer.getOffhandItem(), InteractionHand.OFF_HAND, new net.minecraft.world.phys.BlockHitResult(net.minecraft.world.phys.Vec3.atCenterOf(pos1), net.minecraft.core.Direction.UP, pos1, false));
                                            player.getMainHandItem().hurtAndBreak(1, splayer, EquipmentSlot.MAINHAND);
                                        }
                                    }
                                }
                                player.getMainHandItem().hurtAndBreak(1, splayer, EquipmentSlot.MAINHAND);
                            } finally {
                                this.isPlacing3x3 = false;
                            }
                        } else if (getDir(event) == Direction.NORTH || getDir(event) == Direction.SOUTH) {
                            try {
                                this.isPlacing3x3 = true;
                                for (int i = -r; i <= r; i++) {
                                    for (int j = -r; j <= r; j++) {
                                        //are calculated positions
                                        BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ());
                                        BlockState state1 = event.getLevel().getBlockState(pos1);
                                        //PLACING BLOCK AROUND
                                        if (!state1.is(Blocks.BEDROCK) && !pos1.equals(pos)) {
                                            splayer.gameMode.useItemOn(splayer, splayer.level(), splayer.getOffhandItem(), InteractionHand.OFF_HAND, new net.minecraft.world.phys.BlockHitResult(net.minecraft.world.phys.Vec3.atCenterOf(pos1), net.minecraft.core.Direction.UP, pos1, false));
                                            player.getMainHandItem().hurtAndBreak(1, splayer, EquipmentSlot.MAINHAND);
                                        }
                                    }
                                }
                                player.getMainHandItem().hurtAndBreak(1, splayer, EquipmentSlot.MAINHAND);
                            } finally {
                                this.isPlacing3x3 = false;
                            }
                        } else if (getDir(event) == Direction.EAST || getDir(event) == Direction.WEST) {
                            try {
                                this.isPlacing3x3 = true;
                                for (int i = -r; i <= r; i++) {
                                    for (int j = -r; j <= r; j++) {
                                        //are calculated positions
                                        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i);
                                        BlockState state1 = event.getLevel().getBlockState(pos1);
                                        //PLACING BLOCK AROUND
                                        if (!state1.is(Blocks.BEDROCK) && !pos1.equals(pos)) {
                                            splayer.gameMode.useItemOn(splayer, splayer.level(), splayer.getOffhandItem(), InteractionHand.OFF_HAND, new net.minecraft.world.phys.BlockHitResult(net.minecraft.world.phys.Vec3.atCenterOf(pos1), net.minecraft.core.Direction.UP, pos1, false));
                                            player.getMainHandItem().hurtAndBreak(1, splayer, EquipmentSlot.MAINHAND);
                                        }
                                    }
                                }
                                player.getMainHandItem().hurtAndBreak(1, splayer, EquipmentSlot.MAINHAND);
                            } finally {
                                this.isPlacing3x3 = false;
                            }
                        }
                    }
                }
            }
        }
    }
@SubscribeEvent
public void blockBreaking(BlockEvent.BreakEvent event){

        int range=1;
        if (getBreakPlayer(event).getMainHandItem().is(items.PLACER5X5)){
            range = 2;
        }
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        ItemStack stack = event.getPlayer().getMainHandItem() ;
        ServerPlayer player = (ServerPlayer) getBreakPlayer(event);
        Direction Face = getTargetedFace();
        if (player.getOffhandItem().getItem() instanceof DiggerItem){
          stack = player.getOffhandItem();
        }
    if (player.getMainHandItem().is(items.PLACER3X3)||player.getMainHandItem().is(items.PLACER5X5)) {
        if (!player.isCrouching() && this.mining) {
            if (Face == Direction.UP || Face == Direction.DOWN) {
                this.mining = false;
                for (int i = -range; i <= range; i++) {
                    for (int j = -range; j <= range; j++) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX() + j, pos.getY(), pos.getZ() + i);
                        BlockState state1 = level.getBlockState(pos1);
                        //DESTRUCTING BLOCK AROUND
                        if (!state1.is(Blocks.BEDROCK) && pos1 != pos || state1.is(Blocks.AIR) && pos1 != pos) {
                            player.gameMode.destroyBlock(pos1);
                            stack.hurtAndBreak(1, (LivingEntity) player, EquipmentSlot.MAINHAND);
                        }


                    }
                }
                this.mining = true;
            } else if (Face == Direction.NORTH || Face == Direction.SOUTH) {
                this.mining = false;
                for (int i = -range; i <= range; i++) {
                    for (int j = -range; j <= range; j++) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ());
                        BlockState state1 = level.getBlockState(pos1);
                        //DESTRUCTING BLOCK AROUND
                        if (!state1.is(Blocks.BEDROCK) && pos1 != pos || state1.is(Blocks.AIR) && pos1 != pos) {
                            player.gameMode.destroyBlock(pos1);
                            stack.hurtAndBreak(1, (LivingEntity) player, EquipmentSlot.MAINHAND);
                        }


                    }
                }
                this.mining = true;
            } else if (Face == Direction.EAST || Face == Direction.WEST) {
                this.mining = false;
                for (int i = -range; i <= range; i++) {
                    for (int j = -range; j <= range; j++) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i);
                        BlockState state1 = level.getBlockState(pos1);
                        //DESTRUCTING BLOCK AROUND
                        if (!state1.is(Blocks.BEDROCK) && pos1 != pos || state1.is(Blocks.AIR) && pos1 != pos) {
                            player.gameMode.destroyBlock(pos1);
                            stack.hurtAndBreak(1, (LivingEntity) player, EquipmentSlot.MAINHAND);
                        }


                    }
                }
                this.mining = true;
            }
        }
    }
}












    // Define mod id in a common place for everything to reference
    public static final String MODID = "threexthreemod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "macecanhitwithelytra" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "macecanhitwithelytra" namespace
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "macecanhitwithelytra" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);



    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public threexthreemod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        items.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (macecanhitwithelytra) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(items.HAMMER.get());
            event.accept(items.DIAMOND_HAMMER.get());
            event.accept(items.NETHERITE_HAMMER.get());
            event.accept(items.NETHERITE_SLEDGEHAMMER.get());


        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
