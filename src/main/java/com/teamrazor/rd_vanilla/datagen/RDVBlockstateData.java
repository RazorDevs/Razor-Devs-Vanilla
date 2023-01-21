package com.teamrazor.rd_vanilla.datagen;

import com.teamrazor.rd_vanilla.RdVanilla;
import com.teamrazor.rd_vanilla.blocks.RDVBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class RDVBlockstateData extends BlockStateProvider {
    public RDVBlockstateData(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RdVanilla.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.crossBlock(RDVBlocks.REED_PLANT.get());
        this.block(RDVBlocks.REED_PLANKS.get());
        this.slab(RDVBlocks.REED_SLAB.get(), RDVBlocks.REED_PLANKS.get());
        this.stairs(RDVBlocks.REED_STAIRS.get(), RDVBlocks.REED_PLANKS.get());
        this.log(RDVBlocks.REED_BLOCK.get());
    }


    public String name(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }


    public ResourceLocation texture(String name, String suffix) {
        return this.modLoc("block/"  + name + suffix);
    }

    public void fence(FenceBlock block, Block baseBlock) {
        this.fenceBlock(block, this.texture(this.name(baseBlock)));
        this.fenceColumn(block, this.name(baseBlock));
    }
    public void fenceColumn(CrossCollisionBlock block, String side) {
        String baseName = this.name(block);
        this.fourWayBlock(block,
                this.models().fencePost(baseName + "_post", this.texture(side)),
                this.models().fenceSide(baseName + "_side", this.texture(side)));
    }

    public ModelFile cubeBottomTop(String block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return this.models().cubeBottomTop(block, side, bottom, top);
    }

    public void fenceGateBlock(FenceGateBlock block, Block baseBlock) {
        this.fenceGateBlockInternal(block, this.name(block), this.texture(this.name(baseBlock)));
    }

    public ResourceLocation extend(ResourceLocation location, String suffix) {
        return new ResourceLocation(location.getNamespace(), location.getPath() + suffix);
    }


    public void fenceGateBlockInternal(FenceGateBlock block, String baseName, ResourceLocation texture) {
        ModelFile gate = this.models().fenceGate(baseName, texture);
        ModelFile gateOpen = this.models().fenceGateOpen(baseName + "_open", texture);
        ModelFile gateWall = this.models().fenceGateWall(baseName + "_wall", texture);
        ModelFile gateWallOpen = this.models().fenceGateWallOpen(baseName + "_wall_open", texture);
        this.fenceGateBlock(block, gate, gateOpen, gateWall, gateWallOpen);
    }
    public void log(Block block) {
        if (block instanceof RotatedPillarBlock rotatedPillarBlock) {
            this.axisBlock(rotatedPillarBlock, this.texture(this.name(rotatedPillarBlock)), this.extend(this.texture(this.name(rotatedPillarBlock)), "_top"));
        }
    }


    public void wood(RotatedPillarBlock block, RotatedPillarBlock baseBlock) {
        this.axisBlock(block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }

    public void crossBlock(Block block) {
        this.crossBlock(block, models().cross(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout")));
    }
    public void crossBlock(Block block, ModelFile model) {
        this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
    }
    public void pottedPlant(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void saplingBlock(Block block) {
        ModelFile sapling = models().cross(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(sapling).build(), SaplingBlock.STAGE);
    }

    public void stairs(Block block, Block baseBlock) {
        this.stairsBlock((StairBlock) block, this.texture(this.name(baseBlock)));
    }

    public void slab(Block block, Block baseBlock) {
        this.slabBlock((SlabBlock) block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void wallBlock(WallBlock block, Block baseBlock) {
        this.wallBlockInternal(block, this.name(block), this.texture(this.name(baseBlock)));
    }
    public void wallBlockInternal(WallBlock block, String baseName, ResourceLocation texture) {
        this.wallBlock(block, this.models().wallPost(baseName + "_post", texture),
                this.models().wallSide(baseName + "_side", texture),
                this.models().wallSideTall(baseName + "_side_tall", texture));
    }
}

