package com.teamrazor.rd_vanilla.blocks;

import com.teamrazor.rd_vanilla.RdVanilla;
import com.teamrazor.rd_vanilla.items.RDVItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RDVBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RdVanilla.MODID);
    public static final RegistryObject<Block> REED_PLANT = registerBlock("reed_plant", () -> new ReedPlant(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<Block> REED_BLOCK = registerBlock("reed_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE)));
    public static final RegistryObject<Block> REED_PLANKS = registerBlock("reed_planks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> REED_SLAB = registerBlock("reed_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<Block> REED_STAIRS = registerBlock("reed_stairs", () -> new StairBlock(RDVBlocks.REED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return RDVItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
