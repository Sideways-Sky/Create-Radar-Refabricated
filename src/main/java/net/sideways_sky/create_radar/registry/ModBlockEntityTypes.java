package net.sideways_sky.create_radar.registry;

import net.sideways_sky.create_radar.block.controller.pitch.AutoPitchControllerBlockEntity;
import net.sideways_sky.create_radar.block.controller.track.TrackControllerBlockEntity;
import net.sideways_sky.create_radar.block.controller.yaw.AutoYawControllerBlockEntity;
import net.sideways_sky.create_radar.block.datalink.DataLinkBlockEntity;
import net.sideways_sky.create_radar.block.datalink.DataLinkRenderer;
import net.sideways_sky.create_radar.block.monitor.MonitorBlockEntity;
import net.sideways_sky.create_radar.block.monitor.MonitorRenderer;
import net.sideways_sky.create_radar.block.radar.bearing.RadarBearingBlockEntity;
import net.sideways_sky.create_radar.block.radar.plane.PlaneRadarBlockEntity;
import com.simibubi.create.content.contraptions.bearing.BearingInstance;
import com.simibubi.create.content.contraptions.bearing.BearingRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import net.sideways_sky.create_radar.CreateRadar;

import static net.sideways_sky.create_radar.CreateRadar.REGISTRATE;

public class ModBlockEntityTypes {

    public static final BlockEntityEntry<MonitorBlockEntity> MONITOR = REGISTRATE
            .blockEntity("monitor", MonitorBlockEntity::new)
            .validBlocks(ModBlocks.MONITOR)
            .renderer(() -> MonitorRenderer::new)
            .register();

    public static final BlockEntityEntry<RadarBearingBlockEntity> RADAR_BEARING = REGISTRATE
            .blockEntity("radar_bearing", RadarBearingBlockEntity::new)
            .instance(() -> BearingInstance::new, true)
            .validBlocks(ModBlocks.RADAR_BEARING_BLOCK)
            .renderer(() -> BearingRenderer::new)
            .register();

    public static final BlockEntityEntry<PlaneRadarBlockEntity> PLANE_RADAR = REGISTRATE
            .blockEntity("plane_radar", PlaneRadarBlockEntity::new)
            .validBlocks(ModBlocks.PLANE_RADAR)
            .register();

    public static final BlockEntityEntry<DataLinkBlockEntity> RADAR_LINK = REGISTRATE
            .blockEntity("data_link", DataLinkBlockEntity::new)
            .renderer(() -> DataLinkRenderer::new)
            .validBlocks(ModBlocks.RADAR_LINK)
            .register();


    public static final BlockEntityEntry<AutoYawControllerBlockEntity> AUTO_YAW_CONTROLLER = REGISTRATE
            .blockEntity("auto_yaw_controller", AutoYawControllerBlockEntity::new)
            .validBlocks(ModBlocks.AUTO_YAW_CONTROLLER_BLOCK)
            .register();

    public static final BlockEntityEntry<AutoPitchControllerBlockEntity> AUTO_PITCH_CONTROLLER = REGISTRATE
            .blockEntity("auto_pitch_controller", AutoPitchControllerBlockEntity::new)
            .validBlocks(ModBlocks.AUTO_PITCH_CONTROLLER_BLOCK)
            .register();

    public static final BlockEntityEntry<TrackControllerBlockEntity> TRACK_CONTROLLER = REGISTRATE
            .blockEntity("track_controller", TrackControllerBlockEntity::new)
            .validBlocks(ModBlocks.TRACK_CONTROLLER_BLOCK)
            .register();

    public static void register() {
		CreateRadar.LOGGER.debug("Registering block entity types! {}, {}, {}, {}, {}, {}, {}", MONITOR, RADAR_BEARING, PLANE_RADAR, RADAR_LINK, AUTO_YAW_CONTROLLER, AUTO_PITCH_CONTROLLER, TRACK_CONTROLLER);
    }
}
