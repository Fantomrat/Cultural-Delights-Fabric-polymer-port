package dev.sterner.culturaldelights.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.sterner.culturaldelights.CulturalDelights;
import dev.sterner.culturaldelights.common.registry.CDObjects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AvocadoBundleTreeDecorator extends TreeDecorator {

    public static final MapCodec<AvocadoBundleTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(AvocadoBundleTreeDecorator::new, (thing) -> thing.probability);


    private final float probability;

    public AvocadoBundleTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return CulturalDelights.AVOCADO_BUNDLE_TREE_DECORATOR_TYPE;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if ((random.nextFloat() < this.probability)) {
            List<BlockPos> list = generator.getLeavesPositions();
            if (!list.isEmpty()) {
                List<BlockPos> list3 = list.stream().filter((pos) -> generator.isAir(pos.down()) && generator.isAir(pos.down(2)) && generator.isAir(pos.down(3))).collect(Collectors.toList());
                if (!list3.isEmpty()) {
                    for(Direction direction : Direction.Type.HORIZONTAL) {
                        if (random.nextFloat() <= 0.25F) {
                            Collections.shuffle(list3);
                            Optional<BlockPos> optional = list3.stream().findFirst();
                            if (optional.isPresent()) {
                                generator.replace(optional.get().down(), CDObjects.AVOCADO_BUNDLE.getDefaultState());
                            }
                        }
                    }

                }
            }
        }

    }
}
