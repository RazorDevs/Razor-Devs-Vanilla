package com.teamrazor.rd_vanilla.blocks;

import com.teamrazor.rd_vanilla.RdVanilla;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = RdVanilla.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RDVBlockstateProperties {
    public static final BooleanProperty TOP = BooleanProperty.create("top");

}
