package net.sideways_sky.create_radar.registry;

import net.sideways_sky.create_radar.item.SafeZoneDesignatorItem;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.sideways_sky.create_radar.CreateRadar;

import static net.sideways_sky.create_radar.CreateRadar.REGISTRATE;

public class ModItems {

    public static final ItemEntry<SafeZoneDesignatorItem> SAFE_ZONE_DESIGNATOR = REGISTRATE.item("radar_safe_zone_designator", SafeZoneDesignatorItem::new)
            .register();

    public static void register() {
		CreateRadar.LOGGER.debug("Registering Items! {}", SAFE_ZONE_DESIGNATOR);
    }
}
