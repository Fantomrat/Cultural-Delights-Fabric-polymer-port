package dev.sterner.culturaldelights.common.block;

import dev.sterner.culturaldelights.CulturalDelights;
import dev.sterner.culturaldelights.common.registry.CDObjects;
import dev.sterner.culturaldelights.common.utils.TransparentFlatTripWire;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.BlockBoundAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import vectorwing.farmersdelight.common.block.FeastBlock;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExoticRollMedleyBlock extends FeastBlock implements TransparentFlatTripWire, FactoryBlock {
    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, 8);
    public final List<Item> riceRollServings;

    protected static final VoxelShape PLATE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
    protected static final VoxelShape FOOD_SHAPE =
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(2.0, 2.0, 2.0, 14.0, 4.0, 14.0),
                    net.minecraft.util.function.BooleanBiFunction.OR);

    public ExoticRollMedleyBlock(Item item, boolean hasLeftovers, Settings settings) {
        super(settings, () -> item, hasLeftovers);
        this.riceRollServings = Arrays.asList(
                CDObjects.PUFFERFISH_ROLL,
                CDObjects.PUFFERFISH_ROLL,
                CDObjects.TROPICAL_ROLL,
                CDObjects.TROPICAL_ROLL,
                CDObjects.TROPICAL_ROLL,
                CDObjects.CHICKEN_ROLL_SLICE,
                CDObjects.CHICKEN_ROLL_SLICE,
                CDObjects.CHICKEN_ROLL_SLICE);
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.TERRACOTTA.getDefaultState();
    }


    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new Model(initialBlockState);
    }

    @Override
    public IntProperty getServingsProperty() {
        return SERVINGS;
    }

    @Override
    public int getMaxServings() {
        return 8;
    }

    @Override
    public ItemStack getServingItem(BlockState state) {
        return new ItemStack(riceRollServings.get(state.get(getServingsProperty()) - 1));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    public static class Model extends BlockModel {
        public static final ArrayList<ItemStack> MODELS = new ArrayList<>();
        public static final ItemStack MODELS_LEFTOVER = ItemDisplayElementUtil.getModel(Identifier.of(CulturalDelights.MOD_ID, "block/exotic_roll_medley_block_leftover"));

        static {
            for (int i = 0; i <= 7; i++) {
                MODELS.add(ItemDisplayElementUtil.getModel(Identifier.of(CulturalDelights.MOD_ID, "block/exotic_roll_medley_block")
                        .withSuffixedPath("_stage" + i)));
            }
        }

        protected Model(BlockState state) {
            init(state);
        }

        public ItemDisplayElement main;

        public void init(BlockState state){
            this.main = ItemDisplayElementUtil.createSimple();
            updateItem(state);
            this.main.setScale(new Vector3f(1));
            this.addElement(main);
        }
        protected void updateItem(BlockState state) {
            this.main.setItem(switch (state.get(SERVINGS)) {
                case 1 -> getModels().get(7);
                case 2 -> getModels().get(6);
                case 3 -> getModels().get(5);
                case 4 -> getModels().get(4);
                case 5 -> getModels().get(3);
                case 6 -> getModels().get(2);
                case 7 -> getModels().get(1);
                case 8 -> getModels().get(0);
                default -> MODELS_LEFTOVER;
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
