package org.derek1256.hell;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.derek1256.hell.block.CustomPortalBlock;
import org.derek1256.hell.block.PortalControllerBlock;
import org.derek1256.hell.block.entity.PortalControllerBlockEntity;
import org.derek1256.hell.item.TargetSetItem;

public class Hell implements ModInitializer {
    public static final Item TARGET_SET_ITEM = new TargetSetItem(new FabricItemSettings().maxCount(1));
    public static final Block PORTAL_CONTROLLER_BLOCK = new PortalControllerBlock(FabricBlockSettings.create().strength(4.0f));
    public static final Block CUSTOM_PORTAL_BLOCK = new CustomPortalBlock(FabricBlockSettings.create().noCollision().strength(-1.0f).luminance(state -> 11));
    public static final BlockEntityType<PortalControllerBlockEntity> PORTAL_CONTROLLER_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(PortalControllerBlockEntity::new, PORTAL_CONTROLLER_BLOCK).build();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("hell", "target_set"), TARGET_SET_ITEM);
        Registry.register(Registries.BLOCK, new Identifier("hell", "portal_controller"), PORTAL_CONTROLLER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("hell", "portal_controller"), new BlockItem(PORTAL_CONTROLLER_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, new Identifier("hell", "custom_portal"), CUSTOM_PORTAL_BLOCK);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("hell", "portal_controller"), PORTAL_CONTROLLER_BLOCK_ENTITY);
    }
}