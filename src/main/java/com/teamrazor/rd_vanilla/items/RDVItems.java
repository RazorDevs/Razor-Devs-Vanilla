package com.teamrazor.rd_vanilla.items;

import com.teamrazor.rd_vanilla.RdVanilla;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RDVItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RdVanilla.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
