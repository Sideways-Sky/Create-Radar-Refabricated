package net.sideways_sky.create_radar.compat.cbc;

import net.sideways_sky.create_radar.CreateRadar;
import net.sideways_sky.create_radar.item.GuidedFuzeItem;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CBCCompatRegister {
    public static ItemEntry<? extends Item> GUIDED_FUZE;

    public static void registerCBC() {
        CreateRadar.LOGGER.info("Registering CBC Compat Items!");
        //conditionally register items, probably a bad idea
        GUIDED_FUZE = CreateRadar.REGISTRATE
                .item("guided_fuze", GuidedFuzeItem::new)
                .properties(properties -> properties.rarity(Rarity.EPIC))
                .register();
    }
}
