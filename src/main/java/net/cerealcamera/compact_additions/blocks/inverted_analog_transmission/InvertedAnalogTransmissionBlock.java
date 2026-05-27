package net.cerealcamera.compact_additions.blocks.inverted_analog_transmission;

import com.simibubi.create.foundation.block.IBE;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.CABlockEntityTypes;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlock;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * The parent Block. Implements {@link ExtraKinetics.ExtraKineticsBlock ExtraKienticsBlock} to ensure {@link com.simibubi.create.content.kinetics.RotationPropagator} sees the ExtraKinetic BlockEntity
 */
public class InvertedAnalogTransmissionBlock extends CoggedEncasedShaftBlock implements IBE<CoggedEncasedShaftBlockEntity>, ExtraKinetics.ExtraKineticsBlock {

    public InvertedAnalogTransmissionBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<CoggedEncasedShaftBlockEntity> getBlockEntityClass() {
        return CoggedEncasedShaftBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CoggedEncasedShaftBlockEntity> getBlockEntityType() {
        return CABlockEntityTypes.INVERTED_ANALOG_TRANSMISSION.get();
    }
}