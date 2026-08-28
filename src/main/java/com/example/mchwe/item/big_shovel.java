
package com.example.mchwe.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
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
            Direction direction = getTargetedFace();
            if (!miningEntity.isCrouching()) {
                if (miningEntity instanceof ServerPlayer && mining) {
                    ServerPlayer player = (ServerPlayer) miningEntity;
                    if (direction == Direction.DOWN || direction == Direction.UP) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                        BlockPos pos2 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                        BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                        BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                        BlockPos pos5 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
                        BlockPos pos6 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1);
                        BlockPos pos7 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
                        BlockPos pos8 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1);
                        BlockState state1 = level.getBlockState(pos1);
                        BlockState state2 = level.getBlockState(pos2);
                        BlockState state3 = level.getBlockState(pos3);
                        BlockState state4 = level.getBlockState(pos4);
                        BlockState state5 = level.getBlockState(pos5);
                        BlockState state6 = level.getBlockState(pos6);
                        BlockState state7 = level.getBlockState(pos7);
                        BlockState state8 = level.getBlockState(pos8);
                        //DESTRUCTING BLOCK AROUND
                        mining = false;


                        if (!state1.is(Blocks.BEDROCK) || state1.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos1);
                        }
                        if (!state2.is(Blocks.BEDROCK) || state2.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos2);
                        }
                        if (!state3.is(Blocks.BEDROCK) || state3.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos3);
                        }
                        if (!state4.is(Blocks.BEDROCK) || state4.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos4);
                        }
                        if (!state5.is(Blocks.BEDROCK) || state5.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos5);
                        }
                        if (!state6.is(Blocks.BEDROCK) || state6.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos6);
                        }
                        if (!state7.is(Blocks.BEDROCK) || state7.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos7);
                        }
                        if (!state8.is(Blocks.BEDROCK) || state8.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos8);
                        }

                        mining = true;
                    } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                        BlockPos pos3 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                        BlockPos pos4 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                        BlockPos pos5 = new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ());
                        BlockPos pos6 = new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ());
                        BlockPos pos7 = new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ());
                        BlockPos pos8 = new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ());
                        BlockState state1 = level.getBlockState(pos1);
                        BlockState state2 = level.getBlockState(pos2);
                        BlockState state3 = level.getBlockState(pos3);
                        BlockState state4 = level.getBlockState(pos4);
                        BlockState state5 = level.getBlockState(pos5);
                        BlockState state6 = level.getBlockState(pos6);
                        BlockState state7 = level.getBlockState(pos7);
                        BlockState state8 = level.getBlockState(pos8);
                        //DESTRUCTING BLOCK AROUND
                        mining = false;


                        if (!state1.is(Blocks.BEDROCK) || state1.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos1);
                        }
                        if (!state2.is(Blocks.BEDROCK) || state2.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos2);
                        }
                        if (!state3.is(Blocks.BEDROCK) || state3.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos3);
                        }
                        if (!state4.is(Blocks.BEDROCK) || state4.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos4);
                        }
                        if (!state5.is(Blocks.BEDROCK) || state5.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos5);
                        }
                        if (!state6.is(Blocks.BEDROCK) || state6.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos6);
                        }
                        if (!state7.is(Blocks.BEDROCK) || state7.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos7);
                        }
                        if (!state8.is(Blocks.BEDROCK) || state8.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos8);
                        }

                        mining = true;
                    } else if (direction == Direction.EAST || direction == Direction.WEST) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                        BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                        BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                        BlockPos pos5 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1);
                        BlockPos pos6 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1);
                        BlockPos pos7 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1);
                        BlockPos pos8 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1);
                        BlockState state1 = level.getBlockState(pos1);
                        BlockState state2 = level.getBlockState(pos2);
                        BlockState state3 = level.getBlockState(pos3);
                        BlockState state4 = level.getBlockState(pos4);
                        BlockState state5 = level.getBlockState(pos5);
                        BlockState state6 = level.getBlockState(pos6);
                        BlockState state7 = level.getBlockState(pos7);
                        BlockState state8 = level.getBlockState(pos8);
                        //DESTRUCTING BLOCK AROUND
                        mining = false;


                        if (!state1.is(Blocks.BEDROCK) || state1.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos1);
                        }
                        if (!state2.is(Blocks.BEDROCK) || state2.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos2);
                        }
                        if (!state3.is(Blocks.BEDROCK) || state3.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos3);
                        }
                        if (!state4.is(Blocks.BEDROCK) || state4.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos4);
                        }
                        if (!state5.is(Blocks.BEDROCK) || state5.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos5);
                        }
                        if (!state6.is(Blocks.BEDROCK) || state6.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos6);
                        }
                        if (!state7.is(Blocks.BEDROCK) || state7.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos7);
                        }
                        if (!state8.is(Blocks.BEDROCK) || state8.is(Blocks.AIR)) {
                            player.gameMode.destroyBlock(pos8);
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


        Tool tool = (Tool) stack.get(DataComponents.TOOL);
        if (tool == null) {
            return InteractionResult.FAIL;
        } else {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
            }
            Direction direction1 = getTargetedFace();
            if (!miningEntity.isCrouching()) {
                if (miningEntity instanceof ServerPlayer && mining) {
                    ServerPlayer player = (ServerPlayer) miningEntity;
                    ServerLevel Level = player.serverLevel();
                    if (direction == Direction.DOWN || direction == Direction.UP) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                        BlockPos pos2 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                        BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                        BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                        BlockPos pos5 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
                        BlockPos pos6 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1);
                        BlockPos pos7 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
                        BlockPos pos8 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1);
                        BlockState state0 = level.getBlockState(pos);
                        BlockState state1 = level.getBlockState(pos1);
                        BlockState state2 = level.getBlockState(pos2);
                        BlockState state3 = level.getBlockState(pos3);
                        BlockState state4 = level.getBlockState(pos4);
                        BlockState state5 = level.getBlockState(pos5);
                        BlockState state6 = level.getBlockState(pos6);
                        BlockState state7 = level.getBlockState(pos7);
                        BlockState state8 = level.getBlockState(pos8);
                        //DESTRUCTING BLOCK AROUND
                        mining = false;
                        int i = 0;
                        if (state0.is(Blocks.GRASS_BLOCK) && !state0.is(Blocks.AIR) || state0.is(Blocks.DIRT) && !state0.is(Blocks.AIR)) {
                            player.level().setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state1.is(Blocks.GRASS_BLOCK) && !state1.is(Blocks.AIR)|| state1.is(Blocks.DIRT) && !state1.is(Blocks.AIR)) {
                            player.level().setBlock(pos1, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state2.is(Blocks.GRASS_BLOCK) && !state2.is(Blocks.AIR) || state2.is(Blocks.DIRT) && !state2.is(Blocks.AIR)) {
                            player.level().setBlock(pos2, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state3.is(Blocks.GRASS_BLOCK) && !state3.is(Blocks.AIR)  || state3.is(Blocks.DIRT) && !state3.is(Blocks.AIR)) {
                            player.level().setBlock(pos3, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state4.is(Blocks.GRASS_BLOCK) && !state4.is(Blocks.AIR)  || state4.is(Blocks.DIRT) && !state4.is(Blocks.AIR)) {
                            player.level().setBlock(pos4, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state5.is(Blocks.GRASS_BLOCK) && !state5.is(Blocks.AIR)  || state5.is(Blocks.DIRT) && !state5.is(Blocks.AIR)) {
                            player.level().setBlock(pos5, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state6.is(Blocks.GRASS_BLOCK) && !state6.is(Blocks.AIR)  || state6.is(Blocks.DIRT) && !state6.is(Blocks.AIR)) {
                            player.level().setBlock(pos6, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state7.is(Blocks.GRASS_BLOCK) && !state7.is(Blocks.AIR)  || state7.is(Blocks.DIRT) && !state7.is(Blocks.AIR)) {
                            player.level().setBlock(pos7, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state8.is(Blocks.GRASS_BLOCK) && !state8.is(Blocks.AIR)  || state8.is(Blocks.DIRT) && !state6.is(Blocks.AIR)) {
                            player.level().setBlock(pos8, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        stack.hurtAndBreak(i-1, player, EquipmentSlot.MAINHAND);
                        mining = true;
                    } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                        BlockPos pos3 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                        BlockPos pos4 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                        BlockPos pos5 = new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ());
                        BlockPos pos6 = new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ());
                        BlockPos pos7 = new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ());
                        BlockPos pos8 = new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ());
                        BlockState state0 = level.getBlockState(pos);
                        BlockState state1 = level.getBlockState(pos1);
                        BlockState state2 = level.getBlockState(pos2);
                        BlockState state3 = level.getBlockState(pos3);
                        BlockState state4 = level.getBlockState(pos4);
                        BlockState state5 = level.getBlockState(pos5);
                        BlockState state6 = level.getBlockState(pos6);
                        BlockState state7 = level.getBlockState(pos7);
                        BlockState state8 = level.getBlockState(pos8);
                        //DESTRUCTING BLOCK AROUND
                        mining = false;
                        int i = 0;
                        if (state0.is(Blocks.GRASS_BLOCK) && !state0.is(Blocks.AIR) || state0.is(Blocks.DIRT) && !state0.is(Blocks.AIR)) {
                            player.level().setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state1.is(Blocks.GRASS_BLOCK) && !state1.is(Blocks.AIR)|| state1.is(Blocks.DIRT) && !state1.is(Blocks.AIR)) {
                            player.level().setBlock(pos1, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state2.is(Blocks.GRASS_BLOCK) && !state2.is(Blocks.AIR) || state2.is(Blocks.DIRT) && !state2.is(Blocks.AIR)) {
                            player.level().setBlock(pos2, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state3.is(Blocks.GRASS_BLOCK) && !state3.is(Blocks.AIR)  || state3.is(Blocks.DIRT) && !state3.is(Blocks.AIR)) {
                            player.level().setBlock(pos3, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state4.is(Blocks.GRASS_BLOCK) && !state4.is(Blocks.AIR)  || state4.is(Blocks.DIRT) && !state4.is(Blocks.AIR)) {
                            player.level().setBlock(pos4, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state5.is(Blocks.GRASS_BLOCK) && !state5.is(Blocks.AIR)  || state5.is(Blocks.DIRT) && !state5.is(Blocks.AIR)) {
                            player.level().setBlock(pos5, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state6.is(Blocks.GRASS_BLOCK) && !state6.is(Blocks.AIR)  || state6.is(Blocks.DIRT) && !state6.is(Blocks.AIR)) {
                            player.level().setBlock(pos6, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state7.is(Blocks.GRASS_BLOCK) && !state7.is(Blocks.AIR)  || state7.is(Blocks.DIRT) && !state7.is(Blocks.AIR)) {
                            player.level().setBlock(pos7, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state8.is(Blocks.GRASS_BLOCK) && !state8.is(Blocks.AIR)  || state8.is(Blocks.DIRT) && !state6.is(Blocks.AIR)) {
                            player.level().setBlock(pos8, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        stack.hurtAndBreak(i-1, player, EquipmentSlot.MAINHAND);
                        mining = true;
                    } else if (direction == Direction.EAST || direction == Direction.WEST) {
                        //are calculated positions
                        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                        BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                        BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                        BlockPos pos5 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1);
                        BlockPos pos6 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1);
                        BlockPos pos7 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1);
                        BlockPos pos8 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1);
                        BlockState state0 = level.getBlockState(pos);
                        BlockState state1 = level.getBlockState(pos1);
                        BlockState state2 = level.getBlockState(pos2);
                        BlockState state3 = level.getBlockState(pos3);
                        BlockState state4 = level.getBlockState(pos4);
                        BlockState state5 = level.getBlockState(pos5);
                        BlockState state6 = level.getBlockState(pos6);
                        BlockState state7 = level.getBlockState(pos7);
                        BlockState state8 = level.getBlockState(pos8);

                        //DESTRUCTING BLOCK AROUND
                        mining = false;
                        int i = 0;
                        if (state0.is(Blocks.GRASS_BLOCK) && !state0.is(Blocks.AIR) || state0.is(Blocks.DIRT) && !state0.is(Blocks.AIR)) {
                            player.level().setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state1.is(Blocks.GRASS_BLOCK) && !state1.is(Blocks.AIR)|| state1.is(Blocks.DIRT) && !state1.is(Blocks.AIR)) {
                            player.level().setBlock(pos1, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state2.is(Blocks.GRASS_BLOCK) && !state2.is(Blocks.AIR) || state2.is(Blocks.DIRT) && !state2.is(Blocks.AIR)) {
                            player.level().setBlock(pos2, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state3.is(Blocks.GRASS_BLOCK) && !state3.is(Blocks.AIR)  || state3.is(Blocks.DIRT) && !state3.is(Blocks.AIR)) {
                            player.level().setBlock(pos3, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state4.is(Blocks.GRASS_BLOCK) && !state4.is(Blocks.AIR)  || state4.is(Blocks.DIRT) && !state4.is(Blocks.AIR)) {
                            player.level().setBlock(pos4, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state5.is(Blocks.GRASS_BLOCK) && !state5.is(Blocks.AIR)  || state5.is(Blocks.DIRT) && !state5.is(Blocks.AIR)) {
                            player.level().setBlock(pos5, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state6.is(Blocks.GRASS_BLOCK) && !state6.is(Blocks.AIR)   && !state6.is(Blocks.DIRT_PATH)  || state6.is(Blocks.DIRT) && !state6.is(Blocks.AIR)  && !state6.is(Blocks.DIRT_PATH) ) {
                            player.level().setBlock(pos6, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state7.is(Blocks.GRASS_BLOCK) && !state7.is(Blocks.AIR)  && !state7.is(Blocks.DIRT_PATH) || state7.is(Blocks.DIRT) && !state7.is(Blocks.AIR)  && !state7.is(Blocks.DIRT_PATH)) {
                            player.level().setBlock(pos7, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        if (state8.is(Blocks.GRASS_BLOCK) && !state8.is(Blocks.AIR) && !state8.is(Blocks.DIRT_PATH) || state8.is(Blocks.DIRT) && !state6.is(Blocks.AIR) && !state8.is(Blocks.DIRT_PATH)) {
                            player.level().setBlock(pos8, Blocks.DIRT_PATH.defaultBlockState(), 3);
                            i++;
                        }
                        stack.hurtAndBreak(i-1, player, EquipmentSlot.MAINHAND);
                        mining = true;
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
    }
}
