package net.cerealcamera.compact_additions.blocks.cogged_encased_shaft;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.simulated_team.simulated.mixin_interface.extra_kinetics.KineticBlockEntityExtension;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraBlockPos;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.blocks.cogged_split_shaft.CoggedSplitShaftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * The parent BlockEntity class. Implements {@link ExtraKinetics ExtraKinetics} to allow multi-kinetic functionality
 */
public abstract class CoggedEncasedShaftBlockEntity extends KineticBlockEntity implements ExtraKinetics {

    /**
     * The ExtraKinetic BlockEntity associated with the CoggedEncasedShaft
     */
    protected final CoggedEncasedShaftCogwheel extraWheel;

    private int signal = 0;

    public static PartialModel wheelPartialModel() {
        return AllPartialModels.SHAFTLESS_COGWHEEL;
    }

    public CoggedEncasedShaftBlockEntity(final BlockEntityType<?> typeIn, final BlockPos pos, final BlockState state) {
        super(typeIn, pos, state);

        //set our ExtraKinetic BlockEntity and set the proper BlockState
        this.extraWheel = new CoggedEncasedShaftCogwheel(typeIn, new ExtraBlockPos(pos), state, this);
    }

    /**
     * Required override, as we need our ExtraKinetic BlockEntity to tick
     */
    @Override
    public void tick() {
        final int bestNeighborSignal = this.getLevel().getBestNeighborSignal(this.getBlockPos());

        if (!this.getLevel().isClientSide) {
            if (bestNeighborSignal != this.signal) {
                // Detach our own network, and our ExtraKinetic's
                this.detachKinetics();
                this.extraWheel.detachKinetics();

                // Remove the sources
                this.removeSource();
                this.extraWheel.removeSource();

                this.signal = bestNeighborSignal;
                this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(CoggedEncasedShaftBlock.POWERED, this.signal > 0));

                // Depending on if we are connected to the ExtraKinetic BlockEntity, or vice versa, we need to attach kinetics accordingly
                if (((KineticBlockEntityExtension) this).simulated$getConnectedToExtraKinetics()) {//Attach ours, then ExtraKientic's
                    this.attachKinetics();
                    this.extraWheel.attachKinetics();
                } else { // Attach ExtraKinetic's, then ours
                    this.extraWheel.attachKinetics();
                    this.attachKinetics();
                }
            }
        }

        this.extraWheel.tick();
        super.tick();
    }

    @Override
    protected void write(final CompoundTag compound, final HolderLookup.Provider registries, final boolean clientPacket) {
        super.write(compound, registries, clientPacket);

        compound.putInt("Signal", this.signal);
    }

    @Override
    protected void read(final CompoundTag compound, final HolderLookup.Provider registries, final boolean clientPacket) {
        super.read(compound, registries, clientPacket);

        this.signal = compound.getInt("Signal");
    }

    /**
     * Accesses the ExtraKinetic BlockEntity associated with the CoggedEncasedShaft
     */
    @Override
    public @NotNull KineticBlockEntity getExtraKinetics() {
        return this.extraWheel;
    }

    @Override
    //See Javadoc
    public boolean shouldConnectExtraKinetics() {
        return true;
    }

    @Override
    public String getExtraKineticsSaveName() {
        return "ExtraCogwheel";
    }

    /**
     * The ExtraKinetic BlockEntity for the CoggedEncasedShaft. Extends KineticBlockEntity (Can be any other KBE), and implements ExtraKinetics
     */
    public static class CoggedEncasedShaftCogwheel extends KineticBlockEntity implements ExtraKineticsBlockEntity {

        public static final ICogWheel EXTRA_COGWHEEL_CONFIG = new ICogWheel() {
            @Override
            public boolean hasShaftTowards(final LevelReader world, final BlockPos pos, final BlockState state, final Direction face) {
                return false;
            }

            @Override
            public Direction.Axis getRotationAxis(final BlockState state) {
                return state.getValue(CoggedSplitShaftBlock.AXIS);
            }
        };

        /**
         * Access to the parent BlockEntity to avoid called {@link Level#getBlockEntity(BlockPos) getBlockEntity} unnecessarily
         */
        private final KineticBlockEntity parentBlockEntity;

        /**
         * @param pos An ExtraBlockPos associated with this ExtraKinetic BlockEntity. This is needed to inform the {@link com.simibubi.create.content.kinetics.RotationPropagator} that this BlockEntity is an ExtraKinetic one.
         */
        public CoggedEncasedShaftCogwheel(final BlockEntityType<?> typeIn, final ExtraBlockPos pos, final BlockState state, final KineticBlockEntity parentBlockEntity) {
            super(typeIn, pos, state);
            this.parentBlockEntity = parentBlockEntity;
        }

        /**
         * We call the parent's {@link KineticBlockEntity#propagateRotationTo(KineticBlockEntity, BlockState, BlockState, BlockPos, boolean, boolean) propagateRotationTo} here for easier rotation modifier Handling.
         */
        @Override
        public float propagateRotationTo(final KineticBlockEntity target, final BlockState stateFrom, final BlockState stateTo, final BlockPos diff, final boolean connectedViaAxes, final boolean connectedViaCogs) {
            return this.parentBlockEntity.propagateRotationTo(target, stateFrom, stateTo, diff, connectedViaAxes, connectedViaCogs);
        }

        @Override
        protected boolean canPropagateDiagonally(final IRotate block, final BlockState state) {
            return true;
        }

        @Override
        public KineticBlockEntity getParentBlockEntity() {
            return this.parentBlockEntity;
        }
    }
}