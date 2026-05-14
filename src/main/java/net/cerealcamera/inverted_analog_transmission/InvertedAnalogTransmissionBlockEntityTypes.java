package net.cerealcamera.inverted_analog_transmission;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.cerealcamera.inverted_analog_transmission.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlockEntity;
import net.cerealcamera.inverted_analog_transmission.blocks.inverted_analog_transmission.InvertedAnalogTransmissionRenderer;
import net.cerealcamera.inverted_analog_transmission.blocks.inverted_analog_transmission.InvertedAnalogTransmissionVisual;

public class InvertedAnalogTransmissionBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = InvertedAnalogTransmission.getRegistrate();

    public static final BlockEntityEntry<InvertedAnalogTransmissionBlockEntity> SIMPLE_BE = REGISTRATE
            .blockEntity("simple", InvertedAnalogTransmissionBlockEntity::new)
            .visual(() -> InvertedAnalogTransmissionVisual::new)
            .validBlocks(InvertedAnalogTransmissionBlocks.INVERTED_ANALOG_TRANSMISSION)
            .renderer(() -> InvertedAnalogTransmissionRenderer::new)
            .register();

    public static void register() {
    }
}
