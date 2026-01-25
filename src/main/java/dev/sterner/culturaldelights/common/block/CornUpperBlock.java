package dev.sterner.culturaldelights.common.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.sterner.culturaldelights.CulturalDelights;
import dev.sterner.culturaldelights.common.registry.CDObjects;
import dev.sterner.culturaldelights.common.utils.TransparentPlant;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.BlockBoundAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;

public class CornUpperBlock extends VegetationBlock implements BonemealableBlock, FactoryBlock, TransparentPlant {
    public static final IntegerProperty CORN_AGE;
    public static final int GROWTH_CHANCE = 10;

    public CornUpperBlock(Properties settings) {
        super(settings);
    }

    public IntegerProperty getAgeProperty() {
        return CORN_AGE;
    }

    @Override
    protected MapCodec<? extends CornUpperBlock> codec() {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.INT.fieldOf("age").forGetter(block -> 0)
        ).apply(instance, ignored -> new CornUpperBlock(BlockBehaviour.Properties.of().mapColor((state) -> (Integer)state.getValue(CornUpperBlock.CORN_AGE) >= 2 ? MapColor.COLOR_YELLOW : MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY))));
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.WHEAT.defaultBlockState();
    }


    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return !this.isMature(state);
    }

    public final boolean isMature(BlockState state) {
        return this.getAge(state) >= this.getMaxAge();
    }

    public int getAge(BlockState state) {
        return (Integer)state.getValue(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 3;
    }


    protected ItemLike getSeedsItem() {
        return CDObjects.CORN_KERNELS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CORN_AGE);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.getBlock() == CDObjects.CORN_CROP;
    }

    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }


    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        super.randomTick(state, worldIn, pos, rand);

        if (!worldIn.hasChunksAt(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            return;
        }

        if (worldIn.getMaxLocalRawBrightness(pos.above(), 0) >= 6 && this.getAge(state) <= this.getMaxAge() && rand.nextInt(3) == 0) {
            randomGrowTick(state, worldIn, pos, rand);
        }
    }

    private void randomGrowTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        int currentAge = this.getAge(state);
        if (currentAge <= this.getMaxAge() && rand.nextInt((int) (25.0F / GROWTH_CHANCE) + 1) == 0) {
            if (currentAge == this.getMaxAge()) {
                CornUpperBlock cornUpper = (CornUpperBlock) CDObjects.CORN_UPPER;
                if (cornUpper.defaultBlockState().canSurvive(worldIn, pos.above()) && worldIn.isEmptyBlock(pos.above())) {
                    worldIn.setBlockAndUpdate(pos.above(), cornUpper.defaultBlockState());
                }
            } else {
                worldIn.setBlockAndUpdate(pos, state.setValue(CORN_AGE, this.getAge(state)+1));
            }
        }
    }

    public void applyGrowth(Level world, BlockPos pos, BlockState state) {
        int i = Math.min(this.getMaxAge(), this.getAge(state) + this.getGrowthAmount(world));
        world.setBlock(pos, this.withAge(i), 2);
    }

    public BlockState withAge(int age) {
        return (BlockState)this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    protected int getGrowthAmount(Level world) {
        return Mth.nextInt(world.random, 2, 5);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return (world.getRawBrightness(pos, 0) >= 8 || world.canSeeSky(pos)) && world.getBlockState(pos.below()).getBlock() == CDObjects.CORN_CROP;
    }

    static {
        CORN_AGE = BlockStateProperties.AGE_4;
    }

        @Override
        public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
            return new Model(initialBlockState);
        }

        public static class Model extends BlockModel {
            public static final ArrayList<ItemStack> MODELS = new ArrayList<>();
            static{
                for (int i = 0; i <= 3; i++){
                    MODELS.add(ItemDisplayElementUtil.getModel(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/corn_upper_stage"+i)));
                }
            }
            public ItemDisplayElement main;
            public Model(BlockState state){
                init(state);
            }
            public void init(BlockState state){
                this.main = ItemDisplayElementUtil.createSimple();
                updateItem(state);
                this.main.setScale(new Vector3f(1));
                this.addElement(main);
            }
            protected void updateItem(BlockState state) {
                this.main.setItem(switch (state.getValue(CORN_AGE)) {
                    case 1 -> getModels().get(1);
                    case 2 -> getModels().get(2);
                    case 3 -> getModels().get(3);
                    default -> getModels().getFirst();
                });
            }
            @Override
            public void notifyUpdate(HolderAttachment.UpdateType updateType) {
                if (updateType == BlockBoundAttachment.BLOCK_STATE_UPDATE){
                    updateItem(this.blockState());
                    this.tick();
                }
                super.notifyUpdate(updateType);
            }
            public ArrayList<ItemStack> getModels (){
                return MODELS;
            }
        }
    }

