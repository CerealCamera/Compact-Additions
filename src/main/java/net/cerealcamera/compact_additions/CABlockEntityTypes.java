package net.cerealcamera.compact_additions;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlockEntity;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftRenderer;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftVisual;
import net.cerealcamera.compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlockEntity;
import net.cerealcamera.compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionRenderer;
import net.cerealcamera.compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionVisual;
import net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift.InvertedCoggedGearshiftBlockEntity;
import net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift.InvertedCoggedGearshiftRenderer;
import net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift.InvertedCoggedGearshiftVisual;

public class CABlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = CompactAdditions.getRegistrate();

    public static final BlockEntityEntry<InvertedAnalogTransmissionBlockEntity> INVERTED_ANALOG_TRANSMISSION = REGISTRATE
            .blockEntity("inverted_analog_transmission", InvertedAnalogTransmissionBlockEntity::new)
            .visual(() -> InvertedAnalogTransmissionVisual::new)
            .validBlocks(CABlocks.INVERTED_ANALOG_TRANSMISSION)
            .renderer(() -> InvertedAnalogTransmissionRenderer::new)
            .register();

    public static final BlockEntityEntry<CoggedGearshiftBlockEntity> COGGED_GEARSHIFT = REGISTRATE
            .blockEntity("cogged_gearshift", CoggedGearshiftBlockEntity::new)
            .visual(() -> CoggedGearshiftVisual::new)
            .validBlocks(CABlocks.COGGED_GEARSHIFT)
            .renderer(() -> CoggedGearshiftRenderer::new)
            .register();

    public static final BlockEntityEntry<InvertedCoggedGearshiftBlockEntity> INVERTED_COGGED_GEARSHIFT = REGISTRATE
            .blockEntity("inverted_cogged_gearshift", InvertedCoggedGearshiftBlockEntity::new)
            .visual(() -> InvertedCoggedGearshiftVisual::new)
            .validBlocks(CABlocks.INVERTED_COGGED_GEARSHIFT)
            .renderer(() -> InvertedCoggedGearshiftRenderer::new)
            .register();

    public static void register() {
    }
}
