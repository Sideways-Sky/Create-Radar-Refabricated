package net.sideways_sky.create_radar.block.radar.behavior;

import java.util.Collection;

import net.sideways_sky.create_radar.block.radar.track.RadarTrack;

import net.minecraft.core.BlockPos;

public interface IRadar {
    Collection<RadarTrack> getTracks();

    float getRange();

    boolean isRunning();

    BlockPos getWorldPos();

    float getGlobalAngle();

    //todo better name and/or plan to handle different types of radars
    default boolean renderRelativeToMonitor() {
        return true;
    }

}
