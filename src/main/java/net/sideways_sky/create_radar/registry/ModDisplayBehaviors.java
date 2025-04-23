package net.sideways_sky.create_radar.registry;

import static com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours.assignBlockEntity;

import net.sideways_sky.create_radar.block.monitor.MonitorTargetDisplayBehavior;
import com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours;
import com.simibubi.create.content.redstone.displayLink.DisplayBehaviour;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.sideways_sky.create_radar.CreateRadar;

public class ModDisplayBehaviors {

    public static void register(String id, DisplayBehaviour behaviour, BlockEntityType<?> be) {
        assignBlockEntity(AllDisplayBehaviours.register(CreateRadar.asResource(id), behaviour), be);
    }

    public static void register() {
        CreateRadar.LOGGER.debug("Registering Display Behaviors!");
        register("monitor", new MonitorTargetDisplayBehavior(), ModBlockEntityTypes.MONITOR.get());
    }
}
