package org.derek1256.hell.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.derek1256.hell.block.entity.PortalControllerBlockEntity;

public class CustomPortalBlock extends NetherPortalBlock {
    public CustomPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && !entity.hasVehicle() && !entity.hasPassengers() && entity.canUsePortals()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PortalControllerBlockEntity portalController) {
                BlockPos targetPos = portalController.getTargetPos();
                String targetDimension = portalController.getTargetDimension();

                if (targetPos != null && targetDimension != null) {
                    if (entity instanceof PlayerEntity) {
                        // Players must wait before teleporting
                        if (entity.getPortalCooldown() > 0) {
                            entity.resetPortalCooldown();
                        } else {
                            entity.setPortalCooldown();
                            teleportEntity(entity, targetPos, targetDimension);
                        }
                    } else {
                        // Other entities teleport instantly
                        teleportEntity(entity, targetPos, targetDimension);
                    }
                }
            }
        }
    }

    private void teleportEntity(Entity entity, BlockPos targetPos, String targetDimension) {
        if (entity.world instanceof ServerWorld) {
            ServerWorld targetWorld = ((ServerWorld) entity.world).getServer().getWorld(World.OVERWORLD); // Replace with target dimension
            if (targetWorld != null) {
                entity.teleport(targetWorld, targetPos.getX(), targetPos.getY(), targetPos.getZ(), entity.getYaw(), entity.getPitch());
            }
        }
    }
}