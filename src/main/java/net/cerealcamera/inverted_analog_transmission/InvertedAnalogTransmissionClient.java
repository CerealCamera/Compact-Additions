package net.cerealcamera.inverted_analog_transmission;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = InvertedAnalogTransmission.MODID, dist = Dist.CLIENT)
public class InvertedAnalogTransmissionClient {
    public InvertedAnalogTransmissionClient() {
        InvertedAnalogTransmissionPartialModels.init();
        PonderIndex.addPlugin(new InvertedAnalogTransmissionPonderPlugin());
    }
}
