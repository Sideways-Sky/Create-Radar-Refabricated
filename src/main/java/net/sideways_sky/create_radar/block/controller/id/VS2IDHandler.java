package net.sideways_sky.create_radar.block.controller.id;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.loader.api.FabricLoader;

import org.jetbrains.annotations.NotNull;
import org.valkyrienskies.core.api.ships.Ship;

import net.sideways_sky.create_radar.compat.vs2.VS2Utils;
import com.simibubi.create.foundation.gui.ScreenOpener;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

//Done to avoid loading vs2 classes when the mod is not loaded
public class VS2IDHandler {

    public static @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, @NotNull Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Ship ship = VS2Utils.getShipManagingPos(pLevel, pPos);
        if (ship == null) {
            pPlayer.displayClientMessage(Component.translatable("create_radar.id_block.not_on_ship"), true);
            return InteractionResult.PASS;
        }
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			displayScreen(ship, pPlayer);
		}
        return InteractionResult.SUCCESS;
    }

	@Environment(EnvType.CLIENT)
    private static void displayScreen(Ship ship, Player player) {
        if (!(player instanceof LocalPlayer))
            return;
        ScreenOpener.open(new IDBlockScreen(ship));
    }

    public static void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        Ship ship = VS2Utils.getShipManagingPos(pLevel, pPos);
        if (ship != null) {
            IDManager.removeIDRecord(ship);
        }
    }
}
