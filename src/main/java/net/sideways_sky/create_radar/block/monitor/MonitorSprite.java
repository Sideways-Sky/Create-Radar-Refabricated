package net.sideways_sky.create_radar.block.monitor;

import java.util.Locale;

import net.sideways_sky.create_radar.CreateRadar;

import net.minecraft.resources.ResourceLocation;

public enum MonitorSprite {
    CONTRAPTION_HITBOX,
    ENTITY_HITBOX,
    PROJECTILE,
    PLAYER,
    GRID_SQUARE,
    RADAR_BG_CIRCLE,
    RADAR_BG_FILLER,
    RADAR_SWEEP,
    TARGET_SELECTED,
    TARGET_HOVERED;


    public ResourceLocation getTexture() {
        return CreateRadar.asResource("textures/monitor_sprite/" + name().toLowerCase(Locale.ROOT) + ".png");
    }
}
