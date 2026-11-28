package net.cerealcamera.compact_additions.blocks.cogged_gearshift;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlockEntity;
import net.cerealcamera.compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CoggedGearshiftBlockEntity extends CoggedEncasedShaftBlockEntity implements ExtraKinetics {

    public boolean powered = false;

    public CoggedGearshiftBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    /**
     * Required override, as we need to send overstressed effects and update the speed if necessary
     */
    @Override
    public void tick() {
        final boolean hasNeighborSignal = this.getLevel().hasNeighborSignal(this.getBlockPos());

        if (!this.getLevel().isClientSide) {
            if (hasNeighborSignal != this.powered) {
                this.powered = hasNeighborSignal;
                reAttachKinetics(this.getBlockState().setValue(InvertedAnalogTransmissionBlock.POWERED, this.powered));
            }
        }

        super.tick();
    }

    /**
     * This propagateRotationTo handles both the CoggedGearshift's modifier towards the ExtraKinetic BlockEntity, and vise versa
     */
    @Override
    public float propagateRotationTo(final KineticBlockEntity target, final BlockState stateFrom, final BlockState stateTo, final BlockPos diff, final boolean connectedViaAxes, final boolean connectedViaCogs) {
        if (target == getExtraKinetics() || target == this) {
            if (this.powered)
                return -1;
            return 1;
        }
        return 0;
    }
}
