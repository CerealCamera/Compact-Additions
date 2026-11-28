package net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * The parent BlockEntity class. implements {@link ExtraKinetics ExtraKinetics} to allow multi-kinetic functionality
 */
public class InvertedCoggedGearshiftBlockEntity extends CoggedGearshiftBlockEntity {

    public InvertedCoggedGearshiftBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    /**
     * This propagateRotationTo handles both the CoggedGearshift's modifier towards the ExtraKinetic BlockEntity, and vise versa
     */
    @Override
    public float propagateRotationTo(final KineticBlockEntity target, final BlockState stateFrom, final BlockState stateTo, final BlockPos diff, final boolean connectedViaAxes, final boolean connectedViaCogs) {
        if (target == getExtraKinetics() || target == this) {
            if (this.powered)
                return 1;
            return -1;
        }
        return 0;
    }
}
