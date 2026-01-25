package dev.sterner.culturaldelights.common.block;

import dev.sterner.culturaldelights.CulturalDelights;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.BlockBoundAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class PolymerWildCornBlock extends PolymerWildCucumberBlock {

    public PolymerWildCornBlock(Holder<MobEffect> suspiciousStewEffect, int effectDuration, Properties properties) {
        super(suspiciousStewEffect, effectDuration, properties);
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        return new dev.sterner.culturaldelights.common.block.PolymerWildCornBlock.Model(initialBlockState);
    }

    public static class Model extends BlockModel {
        public static final ArrayList<ItemStack> MODELS = new ArrayList<>();

        static{
            MODELS.add(ItemDisplayElementUtil.getModel(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "block/wild_corn")));
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
            this.main.setItem(getModels().getFirst());

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
