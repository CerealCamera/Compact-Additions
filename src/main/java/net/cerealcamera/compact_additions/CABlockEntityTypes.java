package net.cerealcamera.compact_additions;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftRenderer;
import net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftVisual;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlockEntity;
import net.cerealcamera.compact_additions.blocks.cogged_split_shaft.CoggedSplitShaftRenderer;
import net.cerealcamera.compact_additions.blocks.cogged_split_shaft.CoggedSplitShaftVisual;
import net.cerealcamera.compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlockEntity;
import net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift.InvertedCoggedGearshiftBlockEntity;

public class CABlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = CompactAdditions.getRegistrate();

    public static final BlockEntityEntry<InvertedAnalogTransmissionBlockEntity> INVERTED_ANALOG_TRANSMISSION = REGISTRATE
            .blockEntity("inverted_analog_transmission", InvertedAnalogTransmissionBlockEntity::new)
            .visual(() -> CoggedEncasedShaftVisual::new)
            .validBlocks(CABlocks.INVERTED_ANALOG_TRANSMISSION)
            .renderer(() -> CoggedEncasedShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<CoggedGearshiftBlockEntity> COGGED_GEARSHIFT = REGISTRATE
            .blockEntity("cogged_gearshift", CoggedGearshiftBlockEntity::new)
            .visual(() -> CoggedSplitShaftVisual::new)
            .validBlocks(CABlocks.COGGED_GEARSHIFT)
            .renderer(() -> CoggedSplitShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<InvertedCoggedGearshiftBlockEntity> INVERTED_COGGED_GEARSHIFT = REGISTRATE
            .blockEntity("inverted_cogged_gearshift", InvertedCoggedGearshiftBlockEntity::new)
            .visual(() -> CoggedSplitShaftVisual::new)
            .validBlocks(CABlocks.INVERTED_COGGED_GEARSHIFT)
            .renderer(() -> CoggedSplitShaftRenderer::new)
            .register();

    public static void register() {
    }
}
