package net.cerealcamera.compact_additions.blocks.inverted_analog_transmission;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import dev.simulated_team.simulated.mixin_interface.extra_kinetics.KineticBlockEntityExtension;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlockEntity;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.List;

import static net.cerealcamera.compact_additions.CALang.translate;
import static net.minecraft.ChatFormatting.GOLD;

/**
 * The parent BlockEntity class. Implements {@link ExtraKinetics ExtraKinetics} to allow multi-kinetic functionality
 */
public class InvertedAnalogTransmissionBlockEntity extends CoggedEncasedShaftBlockEntity implements ExtraKinetics {

    private int signal = 0;

    /**
     * Set whenever the InvertedAnalogTransmission disconnects due to overspeeding
     */
    private boolean oversaturated = false;
    boolean alreadySentEffects = false;

    public InvertedAnalogTransmissionBlockEntity(final BlockEntityType<?> typeIn, final BlockPos pos, final BlockState state) {
        super(typeIn, pos, state);
    }

    /**
     * Required override, as we need our ExtraKinetic BlockEntity to tick
     */
    @Override
    public void tick() {
        final int bestNeighborSignal = this.getLevel().getBestNeighborSignal(this.getBlockPos());

        if (!this.getLevel().isClientSide) {
            if (bestNeighborSignal != this.signal) {
                //detach our own network, and our ExtraKinetic's
                this.detachKinetics();
                this.extraWheel.detachKinetics();

                //Remove the sources
                this.removeSource();
                this.extraWheel.removeSource();

                this.signal = bestNeighborSignal;
                this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(InvertedAnalogTransmissionBlock.POWERED, this.signal > 0));

                //Depending on if we are connected to the ExtraKinetic BlockEntity, or vice versa, we need to attach kinetics accordingly
                if (((KineticBlockEntityExtension) this).simulated$getConnectedToExtraKinetics()) {//Attach ours, then ExtraKientic's
                    this.attachKinetics();
                    this.extraWheel.attachKinetics();
                } else { //Attach ExtraKinetic's, then ours
                    this.extraWheel.attachKinetics();
                    this.attachKinetics();
                }
            }
        } else if (this.oversaturated) {
            if (!this.alreadySentEffects) {
                this.alreadySentEffects = true;
                this.effects.triggerOverStressedEffect();
            }
        } else {
            this.alreadySentEffects = false;
        }

        this.extraWheel.tick();
        super.tick();
    }

    @VisibleForTesting
    public float getRotationModifier() {
        return 1 - ((15 - this.signal) + 1) / 16f;
    }

    /**
     * This propagateRotationTo handles both the InvertedAnalogTransmission's modifier towards the ExtraKinetic BlockEntity, and vise versa
     */
    @Override
    public float propagateRotationTo(final KineticBlockEntity target, final BlockState stateFrom, final BlockState stateTo, final BlockPos diff, final boolean connectedViaAxes, final boolean connectedViaCogs) {
        float gatheredRotationModifier = 0;
        if (this.signal != 0) {
            if (target == this.extraWheel) { //reduce speed
                gatheredRotationModifier = this.signal == 15 ? 1 : this.getRotationModifier();
                if (this.oversaturated) {
                    return 0;
                }
            } else if (target == this) { //increase speed
                gatheredRotationModifier = this.signal == 15 ? 1 : (1 / this.getRotationModifier());

                if (Math.abs(this.extraWheel.getTheoreticalSpeed() * gatheredRotationModifier) > AllConfigs.server().kinetics.maxRotationSpeed.get()) {
                    this.oversaturated = true;
                    return 0;
                } else {
                    this.oversaturated = false;
                }
            }
        } else {
            this.oversaturated = false;
        }

        return gatheredRotationModifier;
    }

    @Override
    protected void write(final CompoundTag compound, final HolderLookup.Provider registries, final boolean clientPacket) {
        super.write(compound, registries, clientPacket);

        compound.putBoolean("Oversaturated", this.oversaturated);
    }

    @Override
    protected void read(final CompoundTag compound, final HolderLookup.Provider registries, final boolean clientPacket) {
        super.read(compound, registries, clientPacket);

        this.oversaturated = compound.getBoolean("Oversaturated");
    }

    @Override
    public boolean isOverStressed() {
        if (this.level.isClientSide) {
            return this.oversaturated || this.overStressed;
        }

        return super.isOverStressed();
    }

    @Override
    public boolean addToTooltip(final List<Component> tooltip, final boolean isPlayerSneaking) {
        if (this.oversaturated) {
            translate("inverted_analog_transmission.too_fast")
                    .style(GOLD)
                    .forGoggles(tooltip);

            final MutableComponent component = translate("inverted_analog_transmission.too_fast_error")
                    .component();

            final List<Component> cutString = TooltipHelper.cutTextComponent(component, FontHelper.Palette.GRAY_AND_WHITE);
            tooltip.addAll(cutString);

            return true;
        }

        return super.addToTooltip(tooltip, isPlayerSneaking);
    }
}