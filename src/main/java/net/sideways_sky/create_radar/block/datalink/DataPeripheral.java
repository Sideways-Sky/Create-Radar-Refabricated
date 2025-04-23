package net.sideways_sky.create_radar.block.datalink;

import javax.annotation.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import net.sideways_sky.create_radar.block.datalink.screens.AbstractDataLinkScreen;

public abstract class DataPeripheral extends DataLinkBehavior {

    @Nullable
	@Environment(EnvType.CLIENT)
    protected abstract AbstractDataLinkScreen getScreen(DataLinkBlockEntity be);

    protected abstract void transferData(@NotNull DataLinkContext context, @NotNull DataController activeTarget);
}
