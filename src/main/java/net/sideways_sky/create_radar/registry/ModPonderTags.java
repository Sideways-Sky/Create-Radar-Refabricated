package net.sideways_sky.create_radar.registry;

import static com.simibubi.create.infrastructure.ponder.AllPonderTags.DISPLAY_SOURCES;
import static com.simibubi.create.infrastructure.ponder.AllPonderTags.DISPLAY_TARGETS;
import static com.simibubi.create.infrastructure.ponder.AllPonderTags.MOVEMENT_ANCHOR;


import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;

import net.sideways_sky.create_radar.CreateRadar;

public class ModPonderTags {

    public static final PonderTag

            RADAR_COMPONENT = create("radar_components").item(ModBlocks.RADAR_PLATE_BLOCK)
            .defaultLang("Radar Components", "Components which allow the creation of Radar Contraptions")
            .addToIndex();

    private static PonderTag create(String id) {
        return new PonderTag(CreateRadar.asResource(id));
    }

    public static void register() {
        // Add items to tags here
        PonderRegistry.TAGS.forTag(RADAR_COMPONENT)
                .add(ModBlocks.RADAR_BEARING_BLOCK)
                .add(ModBlocks.RADAR_DISH_BLOCK)
                .add(ModBlocks.RADAR_PLATE_BLOCK)
                .add(ModBlocks.RADAR_RECEIVER_BLOCK)
                .add(ModBlocks.MONITOR);

        PonderRegistry.TAGS.forTag(MOVEMENT_ANCHOR)
                .add(ModBlocks.RADAR_BEARING_BLOCK);

        PonderRegistry.TAGS.forTag(DISPLAY_SOURCES)
                .add(ModBlocks.RADAR_BEARING_BLOCK);

        PonderRegistry.TAGS.forTag(DISPLAY_TARGETS)
                .add(ModBlocks.MONITOR);
    }

}
