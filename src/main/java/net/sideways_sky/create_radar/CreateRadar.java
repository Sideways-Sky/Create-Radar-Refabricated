package net.sideways_sky.create_radar;

import com.simibubi.create.Create;

import com.simibubi.create.foundation.data.CreateRegistrate;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.resources.ResourceLocation;

import net.sideways_sky.create_radar.block.controller.id.IDManager;
import net.sideways_sky.create_radar.block.monitor.MonitorInputHandler;
import net.sideways_sky.create_radar.compat.Mods;
import net.sideways_sky.create_radar.compat.cbc.CBCCompatRegister;
import net.sideways_sky.create_radar.compat.computercraft.CCCompatRegister;
import net.sideways_sky.create_radar.config.RadarConfig;
import net.sideways_sky.create_radar.networking.AllPackets;
import net.sideways_sky.create_radar.registry.AllDataBehaviors;
import net.sideways_sky.create_radar.registry.ModBlockEntityTypes;
import net.sideways_sky.create_radar.registry.ModBlocks;
import net.sideways_sky.create_radar.registry.ModContraptionTypes;
import net.sideways_sky.create_radar.registry.ModCreativeTabs;
import net.sideways_sky.create_radar.registry.ModDisplayBehaviors;
import net.sideways_sky.create_radar.registry.ModItems;

import net.sideways_sky.create_radar.registry.ModLang;

import net.sideways_sky.create_radar.registry.ModPartials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateRadar implements ModInitializer {
	public static final String MODID = "create_radar";
	public static final String NAME = "Create: Radars";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

	@Override
	public void onInitialize() {
		LOGGER.debug("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);
		LOGGER.debug(EnvExecutor.unsafeRunForDist(
				() -> () -> "{} is accessing Porting Lib from the client!",
				() -> () -> "{} is accessing Porting Lib from the server!"
		), NAME);

		ModItems.register();
		ModBlocks.register();
		ModBlockEntityTypes.register();
		ModCreativeTabs.register();
		ModLang.register();
		ModPartials.init();
		RadarConfig.register();
		ModContraptionTypes.register();

		if (Mods.CREATEBIGCANNONS.isLoaded())
			CBCCompatRegister.registerCBC();
		if (Mods.COMPUTERCRAFT.isLoaded())
			CCCompatRegister.registerPeripherals();

		REGISTRATE.register();

		ModDisplayBehaviors.register();
		AllDataBehaviors.registerDefaults();
		AllPackets.registerPackets();
		AllPackets.getChannel().initServerListener();

		ServerWorldEvents.LOAD.register((server, world) -> {
			if (server != null){
				IDManager.load(server);
			}
		});

		MonitorInputHandler.registerEvents();
	}

	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(MODID, path);
	}
}
