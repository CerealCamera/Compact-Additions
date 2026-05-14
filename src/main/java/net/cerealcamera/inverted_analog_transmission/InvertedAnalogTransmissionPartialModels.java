package net.cerealcamera.inverted_analog_transmission;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class InvertedAnalogTransmissionPartialModels {
    public static final PartialModel INVERTED_ANALOG_TRANSMISSION_COG = block("inverted_analog_transmission/gear");

    private static PartialModel block(final String path) {
        return PartialModel.of(InvertedAnalogTransmission.path("block/" + path));
    }

    public static void init() {
    }
}
