package net.sideways_sky.create_radar.block.datalink;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.sideways_sky.create_radar.CreateRadar;
import net.sideways_sky.create_radar.registry.ModBlockEntityTypes;
import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import com.simibubi.create.foundation.gui.ScreenOpener;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.NotNull;

public class DataLinkBlock extends WrenchableDirectionalBlock implements IBE<DataLinkBlockEntity> {

    public DataLinkBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (pPlayer == null)
            return InteractionResult.PASS;
        if (pPlayer.isShiftKeyDown())
            return InteractionResult.PASS;
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			withBlockEntityDo(pLevel, pPos, be -> this.displayScreen(be, pPlayer));
		}
        return InteractionResult.SUCCESS;
    }

	@Environment(EnvType.CLIENT)
    protected void displayScreen(DataLinkBlockEntity be, Player player) {
        if (!(player instanceof LocalPlayer))
            return;
        if (be.targetOffset.equals(BlockPos.ZERO)) {
            player.displayClientMessage(Component.translatable(CreateRadar.MODID + "data_link.fail"), true);
            return;
        }
        be.getScreen().ifPresent(ScreenOpener::open);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AllShapes.DATA_GATHERER.get(pState.getValue(FACING));
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placed = super.getStateForPlacement(context);
        return placed.setValue(FACING, context.getClickedFace());
    }

    @Override
    public Class<DataLinkBlockEntity> getBlockEntityClass() {
        return DataLinkBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends DataLinkBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.RADAR_LINK.get();
    }

}
