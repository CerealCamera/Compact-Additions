package net.cerealcamera.compact_additions.blocks.cogged_gearshift;

import com.simibubi.create.foundation.block.IBE;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import net.cerealcamera.compact_additions.CABlockEntityTypes;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlock;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CoggedGearshiftBlock extends CoggedEncasedShaftBlock implements IBE<CoggedEncasedShaftBlockEntity>, ExtraKinetics.ExtraKineticsBlock {

    public CoggedGearshiftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<CoggedEncasedShaftBlockEntity> getBlockEntityClass() {
        return CoggedEncasedShaftBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CoggedEncasedShaftBlockEntity> getBlockEntityType() {
        return CABlockEntityTypes.COGGED_GEARSHIFT.get();
    }
}
