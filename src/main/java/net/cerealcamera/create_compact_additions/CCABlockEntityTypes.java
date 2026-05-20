package net.cerealcamera.create_compact_additions;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.cerealcamera.create_compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlockEntity;
import net.cerealcamera.create_compact_additions.blocks.cogged_gearshift.CoggedGearshiftRenderer;
import net.cerealcamera.create_compact_additions.blocks.cogged_gearshift.CoggedGearshiftVisual;
import net.cerealcamera.create_compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlockEntity;
import net.cerealcamera.create_compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionRenderer;
import net.cerealcamera.create_compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionVisual;

public class CCABlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = CreateCompactAdditions.getRegistrate();

    public static final BlockEntityEntry<InvertedAnalogTransmissionBlockEntity> INVERTED_ANALOG_TRANSMISSION = REGISTRATE
            .blockEntity("inverted_analog_transmission", InvertedAnalogTransmissionBlockEntity::new)
            .visual(() -> InvertedAnalogTransmissionVisual::new)
            .validBlocks(CCABlocks.INVERTED_ANALOG_TRANSMISSION)
            .renderer(() -> InvertedAnalogTransmissionRenderer::new)
            .register();

    public static final BlockEntityEntry<CoggedGearshiftBlockEntity> COGGED_GEARSHIFT = REGISTRATE
            .blockEntity("cogged_gearshift", CoggedGearshiftBlockEntity::new)
            .visual(() -> CoggedGearshiftVisual::new)
            .validBlocks(CCABlocks.COGGED_GEARSHIFT)
            .renderer(() -> CoggedGearshiftRenderer::new)
            .register();

    public static void register() {
    }
}
