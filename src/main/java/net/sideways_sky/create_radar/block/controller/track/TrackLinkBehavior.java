package net.sideways_sky.create_radar.block.controller.track;

import org.jetbrains.annotations.NotNull;

import net.sideways_sky.create_radar.block.datalink.DataController;
import net.sideways_sky.create_radar.block.datalink.DataLinkBlockEntity;
import net.sideways_sky.create_radar.block.datalink.DataLinkContext;
import net.sideways_sky.create_radar.block.datalink.DataPeripheral;
import net.sideways_sky.create_radar.block.datalink.screens.AbstractDataLinkScreen;
import net.sideways_sky.create_radar.block.datalink.screens.TargetingConfig;
import net.sideways_sky.create_radar.block.monitor.MonitorBlockEntity;

import net.minecraft.world.phys.Vec3;

public class TrackLinkBehavior extends DataPeripheral {
    @Override
    protected AbstractDataLinkScreen getScreen(DataLinkBlockEntity be) {
        return null;
    }

    public void transferData(DataLinkContext context, @NotNull DataController activeTarget) {

        if (!(context.getSourceBlockEntity() instanceof TrackControllerBlockEntity controller))
            return;

        MonitorBlockEntity monitor = context.getMonitorBlockEntity();
        if (monitor == null)
            return;

        Vec3 targetPos = monitor.getTargetPos(TargetingConfig.DEFAULT);
        controller.setTarget(targetPos);
    }
}
