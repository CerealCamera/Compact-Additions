package net.cerealcamera.compact_additions.blocks.cogged_gearshift;

import com.simibubi.create.foundation.block.IBE;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.CABlockEntityTypes;
import net.cerealcamera.compact_additions.blocks.cogged_split_shaft.CoggedSplitShaftBlock;
import net.cerealcamera.compact_additions.blocks.cogged_split_shaft.CoggedSplitShaftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * The parent Block. Implements {@link ExtraKinetics.ExtraKineticsBlock ExtraKienticsBlock} to ensure {@link com.simibubi.create.content.kinetics.RotationPropagator} sees the ExtraKinetic BlockEntity
 */
public class CoggedGearshiftBlock extends CoggedSplitShaftBlock implements IBE<CoggedSplitShaftBlockEntity>, ExtraKinetics.ExtraKineticsBlock {

    public CoggedGearshiftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<CoggedSplitShaftBlockEntity> getBlockEntityClass() {
        return CoggedSplitShaftBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CoggedSplitShaftBlockEntity> getBlockEntityType() {
        return CABlockEntityTypes.COGGED_GEARSHIFT.get();
    }
}