package dev.sterner.culturaldelights.common.block;

import dev.sterner.culturaldelights.CulturalDelights;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerLeavesBlock extends TintedParticleLeavesBlock implements PolymerTexturedBlock {
    public final BlockState model;
    public final BlockState modelWaterlogged;
    public PolymerLeavesBlock(Properties settings, String modelId) {
        super(1.f, settings);
        model = PolymerBlockResourceUtils.requestBlock(BlockModelType.TRANSPARENT_BLOCK, PolymerBlockModel.of(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/"+ modelId)));
        modelWaterlogged = PolymerBlockResourceUtils.requestBlock(BlockModelType.TRANSPARENT_BLOCK_WATERLOGGED, PolymerBlockModel.of(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/"+modelId)));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) return modelWaterlogged;
        else return model;
    }
}