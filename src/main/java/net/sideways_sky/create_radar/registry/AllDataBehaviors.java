package net.sideways_sky.create_radar.registry;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.simibubi.create.foundation.utility.AttachedRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import net.sideways_sky.create_radar.CreateRadar;
import net.sideways_sky.create_radar.block.controller.pitch.PitchLinkBehavior;
import net.sideways_sky.create_radar.block.controller.track.TrackLinkBehavior;
import net.sideways_sky.create_radar.block.controller.yaw.YawLinkBehavior;
import net.sideways_sky.create_radar.block.datalink.DataController;
import net.sideways_sky.create_radar.block.datalink.DataLinkBehavior;
import net.sideways_sky.create_radar.block.datalink.DataPeripheral;
import net.sideways_sky.create_radar.block.monitor.MonitorRadarBehavior;
import net.sideways_sky.create_radar.block.radar.behavior.RadarScannerLinkBehavior;

public class AllDataBehaviors {
    public static final Map<ResourceLocation, DataLinkBehavior> GATHERER_BEHAVIOURS = new HashMap<>();

    private static final AttachedRegistry<Block, DataPeripheral> SOURCES_BY_BLOCK = new AttachedRegistry<>(BuiltInRegistries.BLOCK);
    private static final AttachedRegistry<BlockEntityType<?>, DataPeripheral> SOURCES_BY_BLOCK_ENTITY = new AttachedRegistry<>(BuiltInRegistries.BLOCK_ENTITY_TYPE);

    private static final AttachedRegistry<Block, DataController> TARGETS_BY_BLOCK = new AttachedRegistry<>(BuiltInRegistries.BLOCK);
    private static final AttachedRegistry<BlockEntityType<?>, DataController> TARGETS_BY_BLOCK_ENTITY = new AttachedRegistry<>(BuiltInRegistries.BLOCK_ENTITY_TYPE);


    public static void registerDefaults() {
        assignBlockEntity(register(CreateRadar.asResource("monitor"), new MonitorRadarBehavior()), ModBlockEntityTypes.MONITOR.get());
        assignBlockEntity(register(CreateRadar.asResource("radar"), new RadarScannerLinkBehavior()), ModBlockEntityTypes.RADAR_BEARING.get());
        assignBlockEntity(register(CreateRadar.asResource("pitch"), new PitchLinkBehavior()), ModBlockEntityTypes.AUTO_PITCH_CONTROLLER.get());
        assignBlockEntity(register(CreateRadar.asResource("yaw"), new YawLinkBehavior()), ModBlockEntityTypes.AUTO_YAW_CONTROLLER.get());
        assignBlockEntity(register(CreateRadar.asResource("track"), new TrackLinkBehavior()), ModBlockEntityTypes.TRACK_CONTROLLER.get());
        assignBlockEntity(register(CreateRadar.asResource("plane_radar"), new RadarScannerLinkBehavior()), ModBlockEntityTypes.PLANE_RADAR.get());
    }


    public static DataLinkBehavior register(ResourceLocation id, DataLinkBehavior behaviour) {
        behaviour.id = id;
        GATHERER_BEHAVIOURS.put(id, behaviour);
        return behaviour;
    }

    public static void assignBlockEntity(DataLinkBehavior behaviour, BlockEntityType<?> beType) {
        if (behaviour instanceof DataPeripheral source) {
            SOURCES_BY_BLOCK_ENTITY.register(beType, source);
        }
        if (behaviour instanceof DataController target) {
            TARGETS_BY_BLOCK_ENTITY.register(beType, target);
        }
    }

    //

    @Nullable
    public static DataPeripheral getSource(ResourceLocation resourceLocation) {
        DataLinkBehavior available = GATHERER_BEHAVIOURS.getOrDefault(resourceLocation, null);
        if (available instanceof DataPeripheral source)
            return source;
        return null;
    }

    @Nullable
    public static DataController getTarget(ResourceLocation resourceLocation) {
        DataLinkBehavior available = GATHERER_BEHAVIOURS.getOrDefault(resourceLocation, null);
        if (available instanceof DataController target)
            return target;
        return null;
    }

    //

    public static DataPeripheral sourcesOf(Block block) {
        return SOURCES_BY_BLOCK.get(block);
    }

    public static DataPeripheral sourcesOf(BlockState state) {
        return sourcesOf(state.getBlock());
    }

    public static DataPeripheral sourcesOf(BlockEntityType<?> blockEntityType) {
        return SOURCES_BY_BLOCK_ENTITY.get(blockEntityType);
    }

    public static DataPeripheral sourcesOf(BlockEntity blockEntity) {
        return sourcesOf(blockEntity.getType());
    }

    @Nullable
    public static DataController targetOf(Block block) {
        return TARGETS_BY_BLOCK.get(block);
    }

    @Nullable
    public static DataController targetOf(BlockState state) {
        return targetOf(state.getBlock());
    }

    @Nullable
    public static DataController targetOf(BlockEntityType<?> blockEntityType) {
        return TARGETS_BY_BLOCK_ENTITY.get(blockEntityType);
    }

    @Nullable
    public static DataController targetOf(BlockEntity blockEntity) {
        return targetOf(blockEntity.getType());
    }

    public static DataPeripheral sourcesOf(LevelAccessor level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        BlockEntity blockEntity = level.getBlockEntity(pos);

        DataPeripheral sourcesOfBlock = sourcesOf(blockState);
        DataPeripheral sourcesOfBlockEntity = blockEntity == null ? null : sourcesOf(blockEntity);

        if (sourcesOfBlockEntity == null)
            return sourcesOfBlock;
        return sourcesOfBlockEntity;
    }

    @Nullable
    public static DataController targetOf(LevelAccessor level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        BlockEntity blockEntity = level.getBlockEntity(pos);

        DataController targetOfBlock = targetOf(blockState);
        DataController targetOfBlockEntity = blockEntity == null ? null : targetOf(blockEntity);

        if (targetOfBlockEntity == null)
            return targetOfBlock;
        return targetOfBlockEntity;
    }


}
