
package com.example.mchwe.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;



public class sledgehammer extends DiggerItem {
    public sledgehammer(Tier tier, Item.Properties p_42964_) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, p_42964_);
    }
    boolean mining = true;
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility);
    }
    public static Direction getTargetedFace() {
        Minecraft mc = Minecraft.getInstance();


        if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) mc.hitResult;
            return blockHit.getDirection(); // Возвращает Direction
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
            int range = (5-1)/2;
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
                            }
                        }
                        mining = true;
                    }
                }
            }
            return true;
        }
    }
}
