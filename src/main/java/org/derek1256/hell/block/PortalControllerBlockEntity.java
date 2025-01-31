package org.derek1256.customportals.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortalControllerBlockEntity extends BlockEntity {
    private BlockPos targetPos;
    private String targetDimension;

    public PortalControllerBlockEntity(BlockPos pos, BlockState state) {
        super(CustomPortals.PORTAL_CONTROLLER_BLOCK_ENTITY, pos, state);
    }

    public void setTargetPos(BlockPos targetPos) {
        this.targetPos = targetPos;
        markDirty();
    }

    public void setTargetDimension(String targetDimension) {
        this.targetDimension = targetDimension;
        markDirty();
    }

    public BlockPos getTargetPos() {
        return targetPos;
    }

    public String getTargetDimension() {
        return targetDimension;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("TargetPos")) {
            int[] pos = nbt.getIntArray("TargetPos");
            targetPos = new BlockPos(pos[0], pos[1], pos[2]);
        }
        if (nbt.contains("TargetDimension")) {
            targetDimension = nbt.getString("TargetDimension");
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (targetPos != null) {
            nbt.putIntArray("TargetPos", new int[]{targetPos.getX(), targetPos.getY(), targetPos.getZ()});
        }
        if (targetDimension != null) {
            nbt.putString("TargetDimension", targetDimension);
        }
    }
}