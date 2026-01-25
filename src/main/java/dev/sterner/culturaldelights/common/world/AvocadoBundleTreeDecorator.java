package dev.sterner.culturaldelights.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.sterner.culturaldelights.CulturalDelights;
import dev.sterner.culturaldelights.common.registry.CDObjects;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class AvocadoBundleTreeDecorator extends TreeDecorator {

    public static final MapCodec<AvocadoBundleTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(AvocadoBundleTreeDecorator::new, (thing) -> thing.probability);


    private final float probability;

    public AvocadoBundleTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return CulturalDelights.AVOCADO_BUNDLE_TREE_DECORATOR_TYPE;
    }

    @Override
    public void place(Context generator) {
        RandomSource random = generator.random();
        if ((random.nextFloat() < this.probability)) {
            List<BlockPos> list = generator.leaves();
            if (!list.isEmpty()) {
                List<BlockPos> list3 = list.stream().filter((pos) -> generator.isAir(pos.below()) && generator.isAir(pos.below(2)) && generator.isAir(pos.below(3))).collect(Collectors.toList());
                if (!list3.isEmpty()) {
                    for(Direction direction : Direction.Plane.HORIZONTAL) {
                        if (random.nextFloat() <= 0.25F) {
                            Collections.shuffle(list3);
                            Optional<BlockPos> optional = list3.stream().findFirst();
                            if (optional.isPresent()) {
                                generator.setBlock(optional.get().below(), CDObjects.AVOCADO_BUNDLE.defaultBlockState());
                            }
                        }
                    }

                }
            }
        }

    }
}
