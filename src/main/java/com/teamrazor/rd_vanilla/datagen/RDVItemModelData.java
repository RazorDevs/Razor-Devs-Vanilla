package com.teamrazor.rd_vanilla.datagen;

import com.teamrazor.rd_vanilla.blocks.RDVBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class RDVItemModelData extends ItemModelProvider {
    public RDVItemModelData(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.itemBlock(RDVBlocks.REED_PLANKS.get());
        this.itemBlockFlat(RDVBlocks.REED_PLANT.get());
        this.itemBlock(RDVBlocks.REED_BLOCK.get());
        this.itemBlock(RDVBlocks.REED_STAIRS.get());
        this.itemBlock(RDVBlocks.REED_SLAB.get());
    }

    public String blockName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }

    public void itemBlock(Block block) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
    }
    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    public void handheldItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld"))
                .texture("layer0", this.modLoc("item/"  + this.itemName(item)));
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }
    public void itemFence(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }

    public void itemButton(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }
    public void eggItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
    }
    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }


    public void itemBlockFlat(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated"))
                .texture("layer0", this.texture(this.blockName(block)));
    }
}
