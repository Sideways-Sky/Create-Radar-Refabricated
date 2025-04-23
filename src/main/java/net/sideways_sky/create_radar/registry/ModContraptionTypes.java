package net.sideways_sky.create_radar.registry;


import net.sideways_sky.create_radar.block.radar.bearing.RadarContraption;
import com.simibubi.create.content.contraptions.ContraptionType;

import net.sideways_sky.create_radar.CreateRadar;

public class ModContraptionTypes {
    public static final ContraptionType RADAR_BEARING =
            ContraptionType.register("radar_bearing", RadarContraption::new);

    public static void register() {
		CreateRadar.LOGGER.debug("Registering ContraptionType! {}", RADAR_BEARING);
    }
}
