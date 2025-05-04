package net.sideways_sky.create_radar.registry;

import com.jozufozu.flywheel.core.PartialModel;

import net.sideways_sky.create_radar.CreateRadar;

public class ModPartials {

    public static final PartialModel RADAR_GLOW = block("data_link/glow");
    public static final PartialModel RADAR_LINK_TUBE = block("data_link/tube");

    private static PartialModel block(String path) {
        return new PartialModel(CreateRadar.asResource("block/" + path));
    }

    private static PartialModel entity(String path) {
        return new PartialModel(CreateRadar.asResource("entity/" + path));
    }

    public static void init() {
		CreateRadar.LOGGER.debug("Registering Partials! {}, {}", RADAR_GLOW, RADAR_LINK_TUBE);
    }
}
