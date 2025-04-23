package net.sideways_sky.create_radar.registry;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.sideways_sky.create_radar.compat.Mods;
import net.sideways_sky.create_radar.compat.cbc.CBCCompatRegister;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.sideways_sky.create_radar.CreateRadar;

import static net.sideways_sky.create_radar.CreateRadar.REGISTRATE;

public class ModCreativeTabs {

	private static AllCreativeModeTabs.TabInfo register(String id, String name, Function<CreativeModeTab.Builder,  CreativeModeTab.Builder> supplier) {
		String itemGroupName = "itemGroup." + CreateRadar.MODID + "." + id;
		REGISTRATE.addRawLang(itemGroupName, name);
		ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, CreateRadar.asResource(id));
		CreativeModeTab tab = supplier.apply(FabricItemGroup.builder().title(Components.translatable(itemGroupName))).build();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
		return new AllCreativeModeTabs.TabInfo(key, tab);
	}

	public static AllCreativeModeTabs.TabInfo RADAR_CREATIVE_TAB = register("main_tab", "Create: Radars", (builder) -> builder.icon(ModBlocks.MONITOR::asStack).displayItems((params, output) -> {
		output.accept(ModBlocks.MONITOR);
		output.accept(ModItems.SAFE_ZONE_DESIGNATOR);
		output.accept(ModBlocks.RADAR_LINK);
		output.accept(ModBlocks.RADAR_BEARING_BLOCK);
		output.accept(ModBlocks.RADAR_RECEIVER_BLOCK);
		output.accept(ModBlocks.RADAR_PLATE_BLOCK);
		output.accept(ModBlocks.RADAR_DISH_BLOCK);
		output.accept(ModBlocks.CREATIVE_RADAR_PLATE_BLOCK);
		output.accept(ModBlocks.AUTO_YAW_CONTROLLER_BLOCK);
		output.accept(ModBlocks.AUTO_PITCH_CONTROLLER_BLOCK);
		if (Mods.CREATEBIGCANNONS.isLoaded()) {
			output.accept(CBCCompatRegister.GUIDED_FUZE);
		}
		if (Mods.VALKYRIENSKIES.isLoaded()) {
			output.accept(ModBlocks.ID_BLOCK);
		}
	}));

	public static void register() {
		CreateRadar.LOGGER.debug("Registering Creative Tabs! {}", RADAR_CREATIVE_TAB);
	}
}
