package net.sideways_sky.create_radar.block.controller.pitch;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import net.sideways_sky.create_radar.block.datalink.DataController;
import net.sideways_sky.create_radar.block.datalink.DataLinkBlockEntity;
import net.sideways_sky.create_radar.block.datalink.DataLinkContext;
import net.sideways_sky.create_radar.block.datalink.DataPeripheral;
import net.sideways_sky.create_radar.block.datalink.screens.AbstractDataLinkScreen;
import net.sideways_sky.create_radar.block.datalink.screens.AutoTargetScreen;
import net.sideways_sky.create_radar.block.datalink.screens.TargetingConfig;
import net.sideways_sky.create_radar.block.monitor.MonitorBlockEntity;

import net.minecraft.world.phys.Vec3;

public class PitchLinkBehavior extends DataPeripheral {

	@Environment(EnvType.CLIENT)
	@Override
    protected AbstractDataLinkScreen getScreen(DataLinkBlockEntity be) {
        return new AutoTargetScreen(be);
    }

    @Override
    protected void transferData(@NotNull DataLinkContext context, @NotNull DataController activeTarget) {
        if (!(context.getSourceBlockEntity() instanceof AutoPitchControllerBlockEntity controller))
            return;

        if (context.getMonitorBlockEntity() == null || context.level().isClientSide())
            return;

        MonitorBlockEntity monitor = context.getMonitorBlockEntity();
        TargetingConfig targetingConfig = TargetingConfig.fromTag(context.sourceConfig());

        Vec3 targetPos = monitor.getTargetPos(targetingConfig);
        //todo better way to handle instead of passing null to stop firing
        controller.setSafeZones(monitor.safeZones);
        controller.setTarget(targetPos);
        controller.setFiringTarget(targetPos, targetingConfig);
    }
}
