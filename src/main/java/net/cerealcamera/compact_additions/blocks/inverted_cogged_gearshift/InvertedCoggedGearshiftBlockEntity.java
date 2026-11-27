package net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift;

import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

/**
 * The parent BlockEntity class. implements {@link ExtraKinetics ExtraKinetics} to allow multi-kinetic functionality
 */
public class InvertedCoggedGearshiftBlockEntity extends CoggedGearshiftBlockEntity {

    public InvertedCoggedGearshiftBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        if (hasSource() && face != getSourceFacing()) {
            if (getBlockState().getValue(BlockStateProperties.POWERED))
                return 1;
            else
                return -1;
        }
        return 1;
    }
}
