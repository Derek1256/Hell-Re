package org.derek1256.hell.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortalControllerBlockEntity extends BlockEntity {
    private BlockPos targetPos;
    private String targetDimension;

    public PortalControllerBlockEntity(BlockPos pos, BlockState state) {
        super(Hell.PORTAL_CONTROLLER_BLOCK_ENTITY, pos, state);
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
    public void readComponents(ComponentMap components) {
        super.readComponents(components);
        NbtComponent nbt = components.get(DataComponentTypes.CUSTOM_DATA);
        if (nbt != null) {
            int[] pos = nbt.getIntArray("TargetPos");
            targetPos = new BlockPos(pos[0], pos[1], pos[2]);
            targetDimension = nbt.getString("TargetDimension");
        }
    }

    @Override
    public void writeComponents(ComponentMap.Builder builder) {
        super.writeComponents(builder);
        if (targetPos != null && targetDimension != null) {
            NbtComponent nbt = new NbtComponent();
            nbt.putIntArray("TargetPos", new int[]{targetPos.getX(), targetPos.getY(), targetPos.getZ()});
            nbt.putString("TargetDimension", targetDimension);
            builder.add(DataComponentTypes.CUSTOM_DATA, nbt);
        }
    }
}