package dev.sterner.culturaldelights.mixin;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SimpleStateProvider.class)
public interface SimpleBlockStateProviderAccessor {
    @Invoker(value = "<init>")
    static SimpleStateProvider callInit(BlockState state) {
        throw new UnsupportedOperationException();
    }
}
