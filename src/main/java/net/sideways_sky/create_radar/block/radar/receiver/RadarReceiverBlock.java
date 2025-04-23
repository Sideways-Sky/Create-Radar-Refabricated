package net.sideways_sky.create_radar.block.radar.receiver;

import org.jetbrains.annotations.NotNull;

import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RadarReceiverBlock extends WrenchableDirectionalBlock {
    public RadarReceiverBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter p_220071_2_, @NotNull BlockPos p_220071_3_,
                                        @NotNull CollisionContext p_220071_4_) {
        return AllShapes.CASING_13PX.get(state.getValue(FACING));
    }

//    @Override
//    public boolean isStickyBlock(BlockState state) {
//        return true;
//    }
}

