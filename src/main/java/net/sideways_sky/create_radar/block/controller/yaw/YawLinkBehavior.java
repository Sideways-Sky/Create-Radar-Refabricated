package net.sideways_sky.create_radar.block.controller.yaw;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import net.sideways_sky.create_radar.block.datalink.DataController;
import net.sideways_sky.create_radar.block.datalink.DataLinkBlockEntity;
import net.sideways_sky.create_radar.block.datalink.DataLinkContext;
import net.sideways_sky.create_radar.block.datalink.DataPeripheral;
import net.sideways_sky.create_radar.block.datalink.screens.AbstractDataLinkScreen;
import net.sideways_sky.create_radar.block.datalink.screens.TargetingConfig;
import net.sideways_sky.create_radar.block.monitor.MonitorBlockEntity;

import net.minecraft.world.phys.Vec3;

public class YawLinkBehavior extends DataPeripheral {

    public void transferData(DataLinkContext context, @NotNull DataController activeTarget) {
        if (!(context.getSourceBlockEntity() instanceof AutoYawControllerBlockEntity controller))
            return;

        MonitorBlockEntity monitor = context.getMonitorBlockEntity();
        if (monitor == null)
            return;
        TargetingConfig targetingConfig = TargetingConfig.fromTag(context.sourceConfig());
        Vec3 targetPos = monitor.getTargetPos(targetingConfig);
        if (targetPos == null)
            return;
        controller.setTarget(targetPos);
    }

    @Override
	@Environment(EnvType.CLIENT)
    protected AbstractDataLinkScreen getScreen(DataLinkBlockEntity be) {
        return null;
    }
}
