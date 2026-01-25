package dev.sterner.culturaldelights.common.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import xyz.nucleoid.packettweaker.PacketContext;

import static dev.sterner.culturaldelights.CulturalDelights.MOD_ID;


public class PolymerPillarBlock  extends RotatedPillarBlock implements PolymerTexturedBlock {
    private final BlockState[] model = new BlockState[3];

    public PolymerPillarBlock(Properties settings, String path) {
        super(settings.noOcclusion());
        model[0] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Identifier.fromNamespaceAndPath(MOD_ID, "block/"+path), 90, 90));
        model[1] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Identifier.fromNamespaceAndPath(MOD_ID, "block/"+path), 0, 0));
        model[2] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Identifier.fromNamespaceAndPath(MOD_ID, "block/"+path), 90, 0));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return switch (state.getValue(RotatedPillarBlock.AXIS)) {
            case X -> model[0];
            case Y -> model[1];
            case Z -> model[2];
        };
    }
}
