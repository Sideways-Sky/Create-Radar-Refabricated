package net.sideways_sky.create_radar.networking;

import com.simibubi.create.foundation.networking.SimplePacketBase;

import me.pepperbell.simplenetworking.SimpleChannel;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.sideways_sky.create_radar.CreateRadar;
import net.sideways_sky.create_radar.networking.packets.IDRecordPacket;
import net.sideways_sky.create_radar.networking.packets.RadarLinkConfigurationPacket;

import java.util.function.Function;

import static com.simibubi.create.foundation.networking.SimplePacketBase.NetworkDirection.PLAY_TO_SERVER;


public enum AllPackets {

	CONFIGURE_RADAR_LINK(RadarLinkConfigurationPacket.class, RadarLinkConfigurationPacket::new, PLAY_TO_SERVER),
	ID_RECORD(IDRecordPacket.class, IDRecordPacket::new, PLAY_TO_SERVER);

	public static final ResourceLocation CHANNEL_NAME = CreateRadar.asResource("main");
	public static final int NETWORK_VERSION = 3;
	public static final String NETWORK_VERSION_STR = String.valueOf(NETWORK_VERSION);
	private static SimpleChannel channel;

	private PacketType<?> packetType;

	<T extends SimplePacketBase> AllPackets(Class<T> type, Function<FriendlyByteBuf, T> factory, SimplePacketBase.NetworkDirection direction) {
		packetType = new PacketType<>(type, factory, direction);
	}

	public static void registerPackets() {
		channel = new SimpleChannel(CHANNEL_NAME);
		for (AllPackets packet : values())
			packet.packetType.register();
	}

	public static SimpleChannel getChannel() {
		return channel;
	}

	private static class PacketType<T extends SimplePacketBase> {
		private static int index = 0;

		private final Function<FriendlyByteBuf, T> decoder;
		private final Class<T> type;
		private final SimplePacketBase.NetworkDirection direction;

		private PacketType(Class<T> type, Function<FriendlyByteBuf, T> factory, SimplePacketBase.NetworkDirection direction) {
			decoder = factory;
			this.type = type;
			this.direction = direction;
		}

		private void register() {
			switch (direction) {
				case PLAY_TO_CLIENT -> getChannel().registerS2CPacket(type, index++, decoder);
				case PLAY_TO_SERVER -> getChannel().registerC2SPacket(type, index++, decoder);
			}
		}
	}
}
