package net.cerealcamera.compact_additions.blocks.analog_speed_controller;

import com.simibubi.create.content.kinetics.transmission.ClutchBlock;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.cerealcamera.compact_additions.CABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class AnalogSpeedControllerBlock extends ClutchBlock {

    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public AnalogSpeedControllerBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(POWERED, false).setValue(POWER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos blockPos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide) return;

        int signal = level.getBestNeighborSignal(blockPos);
        int prevSignal = state.getValue(POWER);
        if (signal != prevSignal) {
            level.setBlock(blockPos, state.setValue(POWERED, signal > 0).setValue(POWER, signal), 2 | 16);
            detachKinetics(level, blockPos, true);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(POWER, context.getLevel().getBestNeighborSignal(context.getClickedPos()));
    }

    @Override
    public BlockEntityType<? extends SplitShaftBlockEntity> getBlockEntityType() {
        return CABlockEntityTypes.ANALOG_SPEED_CONTROLLER.get();
    }
}
