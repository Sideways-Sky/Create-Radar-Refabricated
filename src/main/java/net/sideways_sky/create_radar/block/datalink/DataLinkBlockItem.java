package net.sideways_sky.create_radar.block.datalink;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.sideways_sky.create_radar.config.RadarConfig;
import net.sideways_sky.create_radar.registry.AllDataBehaviors;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

public class DataLinkBlockItem extends ClickToLinkBlockItem {

    public DataLinkBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

	@Override
	public int getMaxDistanceFromSelection() {
		return RadarConfig.server().radarLinkRange.get();
	}

	@Override
	public String getMessageTranslationKey() {
		return "display_link";
	}

	@Environment(EnvType.CLIENT)
    public AABB getSelectionBounds(BlockPos pos) {
        Level world = Minecraft.getInstance().level;
        DataController target = AllDataBehaviors.targetOf(world, pos);
        if (target != null)
            return target.getMultiblockBounds(world, pos);
        return super.getSelectionBounds(pos);
    }

}

