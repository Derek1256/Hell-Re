package org.derek1256.hell.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.derek1256.hell.block.entity.PortalControllerBlockEntity;

public class PortalControllerBlock extends Block {
    public PortalControllerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof TargetSetItem && stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtComponent nbt = stack.get(DataComponentTypes.CUSTOM_DATA);
            int[] targetPos = nbt.getIntArray("TargetPos");
            String targetDimension = nbt.getString("TargetDimension");

            world.getBlockEntity(pos, Hell.PORTAL_CONTROLLER_BLOCK_ENTITY).ifPresent(blockEntity -> {
                blockEntity.setTargetPos(new BlockPos(targetPos[0], targetPos[1], targetPos[2]));
                blockEntity.setTargetDimension(targetDimension);
            });

            player.sendMessage(net.minecraft.text.Text.of("Portal linked to saved location!"), true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}