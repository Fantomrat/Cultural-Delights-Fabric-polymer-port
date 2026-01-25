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
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;

import static vectorwing.farmersdelight.common.registry.ModBlocks.RICH_SOIL_FARMLAND;

public class CornBlock extends VegetationBlock implements BonemealableBlock, FactoryBlock, TransparentPlant {
    public static final IntegerProperty AGE;
    public static final BooleanProperty SUPPORTING;
    public static final int GROWTH_CHANCE = 10;

    public CornBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(SUPPORTING, false));
    }

    @Override
    protected MapCodec<? extends CornBlock> codec() {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.INT.fieldOf("age").forGetter(block -> 0)
        ).apply(instance, ignored -> new CornBlock(BlockBehaviour.Properties.of().mapColor((state) -> (Integer)state.getValue(CornBlock.AGE) >= 2 ? MapColor.COLOR_YELLOW : MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY))));
    }


    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.WHEAT.defaultBlockState();
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        BlockState state1 = super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        if (!state.isAir()) {
            if (direction == Direction.UP) {
                return state.setValue(SUPPORTING, this.isSupportingCornUpper(neighborState));
            }
        }
        return state1;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(Blocks.FARMLAND) || floor.is(RICH_SOIL_FARMLAND.get());
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 4;
    }


    public BlockState withAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    public boolean isMaxAge(BlockState state) {
        return (Integer)state.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, SUPPORTING);
    }

    public boolean isSupportingCornUpper(BlockState topState) {
        return topState.getBlock() == CDObjects.CORN_UPPER;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        BlockState upperState = world.getBlockState(pos.above());
        if (upperState.getBlock() instanceof CornUpperBlock) {
            return !((CornUpperBlock)upperState.getBlock()).isMature(upperState);
        } else {
            return true;
        }
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        BlockState upperState = world.getBlockState(pos.above());
        if (upperState.getBlock() instanceof CornUpperBlock) {
            return !((CornUpperBlock)upperState.getBlock()).isMature(upperState);
        } else {
            return true;
        }
    }

    protected int getBonemealAgeIncrease(Level worldIn) {
        return Mth.nextInt(worldIn.random, 1, 4);
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        int ageGrowth = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(worldIn), 3);
        if (ageGrowth <= this.getMaxAge()) {
            worldIn.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth));
        } else {
            BlockState top = worldIn.getBlockState(pos.above());
            if (top.getBlock() == CDObjects.CORN_UPPER) {
                BonemealableBlock growable = (BonemealableBlock)worldIn.getBlockState(pos.above()).getBlock();
                if (growable.isValidBonemealTarget(worldIn, pos.above(), top)) {
                    growable.performBonemeal(worldIn, worldIn.random, pos.above(), top);
                }
            } else {
                CornUpperBlock cornUpper = (CornUpperBlock) CDObjects.CORN_UPPER;
                int remainingGrowth = ageGrowth - this.getMaxAge() - 1;
                if (cornUpper.defaultBlockState().canSurvive(worldIn, pos.above()) && worldIn.isEmptyBlock(pos.above())) {
                    worldIn.setBlockAndUpdate(pos, state.setValue(AGE, this.getMaxAge()));
                    worldIn.setBlock(pos.above(), cornUpper.defaultBlockState().setValue(CornUpperBlock.CORN_AGE, remainingGrowth), 2);
                }
            }
        }

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
                worldIn.setBlockAndUpdate(pos, state.setValue(AGE, this.getAge(state)+1));
            }
        }
    }

    static {
        AGE = BlockStateProperties.AGE_4;
        SUPPORTING = BooleanProperty.create("supporting");

    }


        @Override
        public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
            return new Model(initialBlockState);
        }

        public static class Model extends BlockModel {
            public static final ArrayList<ItemStack> MODELS = new ArrayList<>();
            static{
                for (int i = 0; i <= 3; i++){
                    MODELS.add(ItemDisplayElementUtil.getModel(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/corn_stage"+i)));
                }
                MODELS.add(ItemDisplayElementUtil.getModel(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/corn_supporting")));
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

                ItemStack model;

                if (!state.getValue(SUPPORTING)) {
                    switch (state.getValue(AGE)) {
                        case 1 -> model = getModels().get(1);
                        case 2, 3 -> model = getModels().get(2);
                        case 4 -> model = getModels().get(3);
                        default -> model = getModels().getFirst();
                    }
                } else model = getModels().getLast();


                this.main.setItem(model);
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
