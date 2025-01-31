package org.derek1256.hell.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TargetSetItem extends Item {
    public TargetSetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            BlockPos pos = user.getBlockPos();
            ServerWorld serverWorld = (ServerWorld) world;

            ItemStack stack = user.getStackInHand(hand);
            CompoundTag tag = stack.getOrCreateTag();
            tag.putIntArray("TargetPos", new int[]{pos.getX(), pos.getY(), pos.getZ()});
            tag.putString("TargetDimension", serverWorld.getRegistryKey().getValue().toString());

            user.sendMessage(net.minecraft.text.Text.of("Location saved!"), true);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
