package net.sideways_sky.create_radar.compat.computercraft;

import net.sideways_sky.create_radar.CreateRadar;

import dan200.computercraft.api.ComputerCraftAPI;

public class CCCompatRegister {
    public static void registerPeripherals(){
        CreateRadar.LOGGER.debug("Registering Peripherals!");
        ComputerCraftAPI.registerGenericSource(new RadarBearingPeripheral());
        ComputerCraftAPI.registerGenericSource(new MonitorPeripheral());
        ComputerCraftAPI.registerGenericSource(new YawControllerPeripheral());
        ComputerCraftAPI.registerGenericSource(new PitchControllerPeripheral());
    }
}
