package net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift;

import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.CABlockEntityTypes;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlock;
import net.cerealcamera.compact_additions.blocks.cogged_split_shaft.CoggedSplitShaftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * The parent Block. Implements {@link ExtraKinetics.ExtraKineticsBlock ExtraKienticsBlock} to ensure {@link com.simibubi.create.content.kinetics.RotationPropagator} sees the ExtraKinetic BlockEntity
 */
public class InvertedCoggedGearshiftBlock extends CoggedGearshiftBlock {
    public InvertedCoggedGearshiftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends CoggedSplitShaftBlockEntity> getBlockEntityType() {
        return CABlockEntityTypes.INVERTED_COGGED_GEARSHIFT.get();
    }
}
