package com.teamrazor.rd_vanilla.init;

import com.teamrazor.rd_vanilla.RdVanilla;
import com.teamrazor.rd_vanilla.blocks.RDVBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RdVanilla.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RDVTabs {
    public static CreativeModeTab TAB_DEEP_AETHER_BLOCKS_TAB;
    public static CreativeModeTab TAB_DEEP_AETHER_ITEMS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        TAB_DEEP_AETHER_BLOCKS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(RdVanilla.MODID, "razordevs_vanilla_blocks"),
                builder -> builder.icon(() -> new ItemStack(RDVBlocks.REED_BLOCK.get()))
                        .title(Component.translatable("itemGroup." + RdVanilla.MODID + ".razordevs_vanilla_blocks"))
                        .displayItems((features, output, hasPermissions) -> {

                            output.accept(RDVBlocks.REED_PLANT.get());
                            output.accept(RDVBlocks.REED_BLOCK.get());
                            output.accept(RDVBlocks.REED_SLAB.get());
                            output.accept(RDVBlocks.REED_STAIRS.get());
                            output.accept(RDVBlocks.REED_SLAB.get());
                        }));

    }
}