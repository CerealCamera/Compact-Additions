package net.cerealcamera.inverted_analog_transmission;

import net.createmod.catnip.lang.Lang;
import net.createmod.catnip.lang.LangBuilder;

public class InvertedAnalogTransmissionLang {
    public static LangBuilder builder() {
        return Lang.builder(InvertedAnalogTransmission.MODID);
    }

    public static LangBuilder translate(final String key, final Object... args) {
        return builder().translate(key, args);
    }
}
