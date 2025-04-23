package net.sideways_sky.create_radar.block.radar.behavior;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import net.sideways_sky.create_radar.block.datalink.DataController;
import net.sideways_sky.create_radar.block.datalink.DataLinkBlockEntity;
import net.sideways_sky.create_radar.block.datalink.DataLinkContext;
import net.sideways_sky.create_radar.block.datalink.DataPeripheral;
import net.sideways_sky.create_radar.block.datalink.screens.AbstractDataLinkScreen;
import net.sideways_sky.create_radar.block.datalink.screens.RadarFilterScreen;
import net.sideways_sky.create_radar.block.monitor.MonitorBlockEntity;
import net.sideways_sky.create_radar.block.monitor.MonitorFilter;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;

public class RadarScannerLinkBehavior extends DataPeripheral {

    public void transferData(@NotNull DataLinkContext context, @NotNull DataController activeTarget) {
        if (context.level().isClientSide())
            return;
        if (context.getSourceBlockEntity() instanceof SmartBlockEntity smartBlockEntity) {
            RadarScanningBlockBehavior behavior = smartBlockEntity.getBehaviour(RadarScanningBlockBehavior.TYPE);
            if (behavior != null && context.getMonitorBlockEntity() != null) {
                MonitorBlockEntity monitorBlockEntity = context.getMonitorBlockEntity();
                    monitorBlockEntity.getController().setRadarPos(context.getSourcePos());
                    if (context.sourceConfig().contains("filter")) {
                        monitorBlockEntity.setFilter(MonitorFilter.fromTag(context.sourceConfig().getCompound("filter")));
                    }
                    monitorBlockEntity.getController().updateCache();
            }
        }
    }


	@Environment(EnvType.CLIENT)
    @Override
    protected AbstractDataLinkScreen getScreen(DataLinkBlockEntity be) {
        return new RadarFilterScreen(be);
    }
}
