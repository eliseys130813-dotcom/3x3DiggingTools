
package com.example.mchwe.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;


public class big_shovel extends DiggerItem {
    public big_shovel(Tier tier, Properties p_42964_) {
        super(tier, BlockTags.MINEABLE_WITH_SHOVEL, p_42964_);
    }

    boolean mining = true;

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
    }

    public static Direction getTargetedFace() {
        Minecraft mc = Minecraft.getInstance();


        if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) mc.hitResult;
            return blockHit.getDirection();
        }

        return null;
    }


    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        Tool tool = (Tool) stack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
            }
            int range = 3;
            range=(range-1)/2;
            Direction direction = getTargetedFace();
            if (!miningEntity.isCrouching()) {
                if (miningEntity instanceof ServerPlayer && mining) {
                    ServerPlayer player = (ServerPlayer) miningEntity;
                    if (direction == Direction.DOWN || direction == Direction.UP) {
                        mining = false;
                        for (int i = -range; i <= range; i++){
                            for (int j = -range; j <= range; j++) {
                                //are calculated positions
                                BlockPos pos1 = new BlockPos(pos.getX()+i, pos.getY(), pos.getZ()+j);
                                BlockState state1 = level.getBlockState(pos1);
                                //DESTRUCTING BLOCK AROUND
                                if (!state1.is(Blocks.BEDROCK) && pos1 != pos || state1.is(Blocks.AIR) && pos1 != pos) {
                                    player.gameMode.destroyBlock(pos1);
                                }
                                stack.setDamageValue(Math.max(0, stack.getDamageValue() - 1));

                            }
                        }
                        mining = true;
                    } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                        mining = false;
                        for (int i = -range; i <= range; i++){
                            for (int j = -range; j <= range; j++) {
                                //are calculated positions
                                BlockPos pos1 = new BlockPos(pos.getX()+i, pos.getY()+j, pos.getZ());
                                BlockState state1 = level.getBlockState(pos1);
                                //DESTRUCTING BLOCK AROUND
                                if (!state1.is(Blocks.BEDROCK) && pos1 != pos || state1.is(Blocks.AIR) && pos1 != pos) {
                                    player.gameMode.destroyBlock(pos1);
                                }
                                stack.setDamageValue(Math.max(0, stack.getDamageValue() - 1));


                            }
                        }
                        mining = true;
                    } else if (direction == Direction.EAST || direction == Direction.WEST) {
                        mining = false;
                        for (int i = -range; i <= range; i++){
                            for (int j = -range; j <= range; j++) {
                                //are calculated positions
                                BlockPos pos1 = new BlockPos(pos.getX(), pos.getY()+j, pos.getZ()+i);
                                BlockState state1 = level.getBlockState(pos1);
                                //DESTRUCTING BLOCK AROUND
                                if (!state1.is(Blocks.BEDROCK) && pos1 != pos || state1.is(Blocks.AIR) && pos1 != pos) {
                                    player.gameMode.destroyBlock(pos1);
                                }
                                stack.setDamageValue(Math.max(0, stack.getDamageValue() - 1));

                            }
                        }
                        mining = true;
                    }
                }
            }

            return true;
        }


    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Direction direction = context.getClickedFace();
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        LivingEntity miningEntity = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        InteractionHand slot = context.getHand();

        Tool tool = (Tool) stack.get(DataComponents.TOOL);
        if (tool == null) {
            return InteractionResult.FAIL;
        } else {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
            }
            int range = 3;
            range=(range-1)/2;
            if (!miningEntity.isCrouching()) {
                if (miningEntity instanceof ServerPlayer && mining) {
                    ServerPlayer player = (ServerPlayer) miningEntity;
                    if (direction == Direction.DOWN || direction == Direction.UP) {
                        mining = false;
                        int damage=0;
                        for (int i = -range; i <= range; i++){
                            for (int j = -range; j <= range; j++) {
                                //are calculated positions
                                BlockPos pos1 = new BlockPos(pos.getX()+i, pos.getY(), pos.getZ()+j);
                                BlockState state1 = level.getBlockState(pos1);
                                //DESTRUCTING BLOCK AROUND
                                if (state1.is(Blocks.GRASS_BLOCK) && !state1.is(Blocks.AIR) || state1.is(Blocks.DIRT) && !state1.is(Blocks.AIR)) {
                                    player.level().setBlock(pos1, Blocks.DIRT_PATH.defaultBlockState(), 3);
                                    damage+=1;
                                }


                            }
                        }
                        stack.hurtAndBreak(damage, player, EquipmentSlot.MAINHAND);
                        mining = true;
                    } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                        mining = false;
                        int damage=0;
                        for (int i = -range; i <= range; i++){
                            for (int j = -range; j <= range; j++) {
                                //are calculated positions
                                BlockPos pos1 = new BlockPos(pos.getX()+i, pos.getY()+j, pos.getZ());
                                BlockState state1 = level.getBlockState(pos1);
                                //DESTRUCTING BLOCK AROUND
                                if (state1.is(Blocks.GRASS_BLOCK) && !state1.is(Blocks.AIR) || state1.is(Blocks.DIRT) && !state1.is(Blocks.AIR)) {
                                    player.level().setBlock(pos1, Blocks.DIRT_PATH.defaultBlockState(), 3);
                                    damage+=1;
                                }



                            }
                        }
                        stack.hurtAndBreak(damage, player, EquipmentSlot.MAINHAND);
                        mining = true;
                    } else if (direction == Direction.EAST || direction == Direction.WEST) {
                        mining = false;
                        int damage =0;
                        for (int i = -range; i <= range; i++){
                            for (int j = -range; j <= range; j++) {
                                //are calculated positions
                                BlockPos pos1 = new BlockPos(pos.getX(), pos.getY()+j, pos.getZ()+i);
                                BlockState state1 = level.getBlockState(pos1);
                                //DESTRUCTING BLOCK AROUND
                                if (state1.is(Blocks.GRASS_BLOCK) && !state1.is(Blocks.AIR) || state1.is(Blocks.DIRT) && !state1.is(Blocks.AIR)) {
                                    player.level().setBlock(pos1, Blocks.DIRT_PATH.defaultBlockState(), 3);
                                    damage+=1;
                                }


                            }
                        }
                        stack.hurtAndBreak(damage, player, EquipmentSlot.MAINHAND);
                        mining = true;
                    }
                }
            } else if (miningEntity.isCrouching()) {
                if (miningEntity instanceof ServerPlayer){
                    ServerPlayer player = (ServerPlayer) miningEntity;
                    player.level().setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), 3);
                }
            }
            return InteractionResult.SUCCESS;
        }


    }
}

    }
}
