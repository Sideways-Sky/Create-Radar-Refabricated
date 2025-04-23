package net.sideways_sky.create_radar;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.sideways_sky.create_radar.block.datalink.DataLinkBlockItem;
import net.sideways_sky.create_radar.networking.AllPackets;
import net.sideways_sky.create_radar.registry.ModPonderIndex;
import net.sideways_sky.create_radar.registry.ModPonderTags;

public class CreateRadarClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModPonderIndex.register();
		ModPonderTags.register();

		ClientTickEvents.END_CLIENT_TICK.register((mcClient) -> {
			DataLinkBlockItem.clientTick();
		});

		AllPackets.getChannel().initClientListener();
	}
}
