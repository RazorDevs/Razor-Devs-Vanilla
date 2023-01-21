package com.teamrazor.rd_vanilla.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;


public class ReedPlant extends Block implements IPlantable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    public ReedPlant(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(WATERLOGGED, Boolean.valueOf(false)));

    }

    public VoxelShape getShape(BlockState p_57193_, BlockGetter p_57194_, BlockPos p_57195_, CollisionContext p_57196_) {
        return SHAPE;
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.isEmptyBlock(blockPos.above())) {
            int i;
            for(i = 1; serverLevel.getBlockState(blockPos.below(i)).is(this); ++i) {
            }

            if (i < 3) {
                int j = blockState.getValue(AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, true)) {
                    if (j == 15) {
                        serverLevel.setBlockAndUpdate(blockPos.above(), this.defaultBlockState());
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverLevel, blockPos.above(), this.defaultBlockState());
                        serverLevel.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(0)), 4);
                    } else {
                        serverLevel.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(j + 1)), 4);
                    }
                }
            }
        }
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(AGE).add(WATERLOGGED);

    }
    public boolean canSurvive(BlockState pBlockState, LevelReader levelReader, BlockPos blockpos) {
        BlockState soil = levelReader.getBlockState(blockpos.below());
        if (levelReader.getBlockState(blockpos) == Blocks.WATER.defaultBlockState()) return true;
        BlockState blockstate = levelReader.getBlockState(blockpos.below());
        if (blockstate.is(this) && (levelReader.getBlockState(blockpos) == Blocks.WATER.defaultBlockState()
                || levelReader.getBlockState(blockpos.below()) == Blocks.WATER.defaultBlockState())
                || levelReader.getBlockState(blockpos.below(2)) == Blocks.WATER.defaultBlockState()) {
            return true;
        }
        else return false;
    }
    public BlockState updateShape(BlockState blockstate, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos pos1) {
        if (blockstate.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (!blockState.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }
        return super.updateShape(blockstate, direction, blockState, level, pos, pos1);
    }
    @Override
    public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return net.minecraftforge.common.PlantType.BEACH;
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidstate = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return super.getStateForPlacement(blockPlaceContext).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return defaultBlockState();
    }
}
