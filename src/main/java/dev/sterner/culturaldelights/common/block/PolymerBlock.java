package dev.sterner.culturaldelights.common.block;

import dev.sterner.culturaldelights.CulturalDelights;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerBlock extends Block implements PolymerTexturedBlock {
        private final BlockState model;
        public PolymerBlock(Properties settings, String path) {
            super(settings);
            model = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/"+path)));
        }

        @Override
        public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
            return model;
        }
    }
