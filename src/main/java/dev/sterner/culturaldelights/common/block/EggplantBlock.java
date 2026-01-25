package dev.sterner.culturaldelights.common.block;

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
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;

public class EggplantBlock extends CropBlock implements FactoryBlock, TransparentPlant {
    public EggplantBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return CDObjects.EGGPLANT_SEEDS;
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.WHEAT.defaultBlockState();
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        return new EggplantBlock.Model(initialBlockState);
    }

    public static class Model extends BlockModel {
        public static final ArrayList<ItemStack> MODELS = new ArrayList<>();
        static{
            for (int i = 0; i <= 5; i++){
                MODELS.add(ItemDisplayElementUtil.getModel(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/eggplants_stage"+i)));
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
            this.main.setItem(switch (state.getValue(AGE)) {
                case 1 -> getModels().get(1);
                case 2, 3 -> getModels().get(2);
                case 4, 5 -> getModels().get(3);
                case 6 -> getModels().get(4);
                case 7 -> getModels().get(5);
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
