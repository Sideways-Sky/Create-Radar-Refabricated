package net.sideways_sky.create_radar.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.sideways_sky.create_radar.CreateRadar;

import org.apache.commons.lang3.tuple.Pair;

import net.sideways_sky.create_radar.config.client.RadarClientConfig;
import net.sideways_sky.create_radar.config.server.RadarServerConfig;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.foundation.config.ui.BaseConfigScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class RadarConfig {
    private static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);

    private static RadarClientConfig client;
    private static RadarServerConfig server;

    public static RadarClientConfig client() {
        return client;
    }


    public static RadarServerConfig server() {
        return server;
    }

    public static ConfigBase byType(ModConfig.Type type) {
        return CONFIGS.get(type);
    }

    private static <T extends ConfigBase> T register(Supplier<T> factory, ModConfig.Type side) {
        Pair<T, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(builder -> {
            T config = factory.get();
            config.registerAll(builder);
            return config;
        });

        T config = specPair.getLeft();
        config.specification = specPair.getRight();
        CONFIGS.put(side, config);
        return config;
    }

    public static void register() {
		CreateRadar.LOGGER.debug("Registering Config");
        client = register(RadarClientConfig::new, ModConfig.Type.CLIENT);
        server = register(RadarServerConfig::new, ModConfig.Type.SERVER);

        for (Map.Entry<ModConfig.Type, ConfigBase> pair : CONFIGS.entrySet())
			ForgeConfigRegistry.INSTANCE.register(CreateRadar.MODID, pair.getKey(), pair.getValue().specification);
    }

    public static void onLoad(ModConfig modConfig) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == modConfig
                    .getSpec())
                config.onLoad();
    }

    public static void onReload(ModConfig modConfig) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == modConfig
                    .getSpec())
                config.onReload();
    }

    public static BaseConfigScreen createConfigScreen(Minecraft mc, Screen parent) {
        BaseConfigScreen.setDefaultActionFor(CreateRadar.MODID, (base) -> base
                .withSpecs(RadarConfig.client().specification,
                        null,
                        RadarConfig.server().specification));

        return new BaseConfigScreen(parent, CreateRadar.MODID);
    }
}
