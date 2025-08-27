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
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;

public class CornUpperBlock extends PlantBlock implements Fertilizable, FactoryBlock, TransparentPlant {
    public static final IntProperty CORN_AGE;
    public static final int GROWTH_CHANCE = 10;

    public CornUpperBlock(Settings settings) {
        super(settings);
    }

    public IntProperty getAgeProperty() {
        return CORN_AGE;
    }

    @Override
    protected MapCodec<? extends CornUpperBlock> getCodec() {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.INT.fieldOf("age").forGetter(block -> 0)
        ).apply(instance, ignored -> new CornUpperBlock(AbstractBlock.Settings.create().mapColor((state) -> (Integer)state.get(CornUpperBlock.CORN_AGE) >= 2 ? MapColor.YELLOW : MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY))));
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.WHEAT.getDefaultState();
    }


    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !this.isMature(state);
    }

    public final boolean isMature(BlockState state) {
        return this.getAge(state) >= this.getMaxAge();
    }

    public int getAge(BlockState state) {
        return (Integer)state.get(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 3;
    }


    protected ItemConvertible getSeedsItem() {
        return CDObjects.CORN_KERNELS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CORN_AGE);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.getBlock() == CDObjects.CORN_CROP;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }


    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.randomTick(state, worldIn, pos, rand);

        if (!worldIn.isRegionLoaded(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            return;
        }

        if (worldIn.getLightLevel(pos.up(), 0) >= 6 && this.getAge(state) <= this.getMaxAge() && rand.nextInt(3) == 0) {
            randomGrowTick(state, worldIn, pos, rand);
        }
    }

    private void randomGrowTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        int currentAge = this.getAge(state);
        if (currentAge <= this.getMaxAge() && rand.nextInt((int) (25.0F / GROWTH_CHANCE) + 1) == 0) {
            if (currentAge == this.getMaxAge()) {
                CornUpperBlock cornUpper = (CornUpperBlock) CDObjects.CORN_UPPER;
                if (cornUpper.getDefaultState().canPlaceAt(worldIn, pos.up()) && worldIn.isAir(pos.up())) {
                    worldIn.setBlockState(pos.up(), cornUpper.getDefaultState());
                }
            } else {
                worldIn.setBlockState(pos, state.with(CORN_AGE, this.getAge(state)+1));
            }
        }
    }

    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int i = Math.min(this.getMaxAge(), this.getAge(state) + this.getGrowthAmount(world));
        world.setBlockState(pos, this.withAge(i), 2);
    }

    public BlockState withAge(int age) {
        return (BlockState)this.getDefaultState().with(this.getAgeProperty(), age);
    }

    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 5);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (world.getBaseLightLevel(pos, 0) >= 8 || world.isSkyVisible(pos)) && world.getBlockState(pos.down()).getBlock() == CDObjects.CORN_CROP;
    }

    static {
        CORN_AGE = Properties.AGE_4;
    }

        @Override
        public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
            return new Model(initialBlockState);
        }

        public static class Model extends BlockModel {
            public static final ArrayList<ItemStack> MODELS = new ArrayList<>();
            static{
                for (int i = 0; i <= 3; i++){
                    MODELS.add(ItemDisplayElementUtil.getModel(Identifier.of(CulturalDelights.MOD_ID, "block/corn_upper_stage"+i)));
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
                this.main.setItem(switch (state.get(CORN_AGE)) {
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

