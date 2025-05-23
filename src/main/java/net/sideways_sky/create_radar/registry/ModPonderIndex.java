package net.sideways_sky.create_radar.registry;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;

import net.sideways_sky.create_radar.CreateRadar;
import net.sideways_sky.create_radar.ponder.PonderScenes;

public class ModPonderIndex {
    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreateRadar.MODID);

    public static void register() {
        CreateRadar.LOGGER.debug("Registering Ponder!");
        HELPER.forComponents(ModBlocks.RADAR_BEARING_BLOCK)
                .addStoryBoard("radar_contraption", PonderScenes::radarContraption, ModPonderTags.RADAR_COMPONENT)
                .addStoryBoard("radar_linking", PonderScenes::radarLinking, ModPonderTags.RADAR_COMPONENT);

        HELPER.addStoryBoard(ModBlocks.RADAR_RECEIVER_BLOCK, "radar_contraption", PonderScenes::radarContraption, ModPonderTags.RADAR_COMPONENT);
        HELPER.addStoryBoard(ModBlocks.RADAR_DISH_BLOCK, "radar_contraption", PonderScenes::radarContraption, ModPonderTags.RADAR_COMPONENT);
        HELPER.addStoryBoard(ModBlocks.RADAR_PLATE_BLOCK, "radar_contraption", PonderScenes::radarContraption, ModPonderTags.RADAR_COMPONENT);

        HELPER.addStoryBoard(ModBlocks.MONITOR, "radar_linking", PonderScenes::radarLinking, ModPonderTags.RADAR_COMPONENT);
        HELPER.addStoryBoard(ModBlocks.AUTO_YAW_CONTROLLER_BLOCK, "controller_linking", PonderScenes::controllerLinking, ModPonderTags.RADAR_COMPONENT);
        HELPER.addStoryBoard(ModBlocks.AUTO_PITCH_CONTROLLER_BLOCK, "controller_linking", PonderScenes::controllerLinking, ModPonderTags.RADAR_COMPONENT);

    }
}
