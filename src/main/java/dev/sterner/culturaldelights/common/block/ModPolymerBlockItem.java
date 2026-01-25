package dev.sterner.culturaldelights.common.block;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.Vec3;

public class ModPolymerBlockItem extends PolymerBlockItem {

    public ModPolymerBlockItem(Block block, Properties settings) {
        super(block, settings);
    }

    public InteractionResult useOn(UseOnContext context) {
        InteractionResult x = super.useOn(context);
        if (x == InteractionResult.SUCCESS) {
            Player player = context.getPlayer();
            if (player instanceof ServerPlayer) {
                ServerPlayer serverPlayer = (ServerPlayer)player;
                Vec3 soundPos = Vec3.atCenterOf(context.getClickedPos().relative(context.getClickedFace()));
                SoundType blockSoundGroup = this.getBlock().defaultBlockState().getSoundType();
                serverPlayer.connection.send(new ClientboundSoundPacket(
                        BuiltInRegistries.SOUND_EVENT.wrapAsHolder(this.getPlaceSound(this.getBlock().defaultBlockState())),
                        SoundSource.BLOCKS,
                        soundPos.x,
                        soundPos.y,
                        soundPos.z,
                        (blockSoundGroup.getVolume() + 1.0F) / 2.0F,
                        blockSoundGroup.getPitch() * 0.8F,
                        player.getRandom().nextLong()
                ));
            }
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return x;
        }
    }
}
