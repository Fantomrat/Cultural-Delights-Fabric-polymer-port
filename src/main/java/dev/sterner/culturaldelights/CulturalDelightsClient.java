package dev.sterner.culturaldelights;

import dev.sterner.culturaldelights.common.registry.CDObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;

public class CulturalDelightsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
                CDObjects.WILD_CUCUMBERS,
                CDObjects.WILD_CORN,
                CDObjects.WILD_EGGPLANTS,
                CDObjects.AVOCADO_LEAVES,
                CDObjects.AVOCADO_SAPLING,
                CDObjects.AVOCADO_PIT,
                CDObjects.CUCUMBER_CROP,
                CDObjects.EGGPLANT_CROP,
                CDObjects.CORN_CROP,
                CDObjects.CORN_UPPER
        );
    }
}
