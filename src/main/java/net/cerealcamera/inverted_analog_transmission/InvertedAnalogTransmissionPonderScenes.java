package net.cerealcamera.inverted_analog_transmission;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.cerealcamera.inverted_analog_transmission.ponder.InvertedAnalogTransmissionScene;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

public class InvertedAnalogTransmissionPonderScenes {
    public static void register(final PonderSceneRegistrationHelper<ResourceLocation> registry) {
        final PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> helper = registry.withKeyFunction(DeferredHolder::getId);

        helper.forComponents(InvertedAnalogTransmissionBlocks.INVERTED_ANALOG_TRANSMISSION)
                .addStoryBoard("inverted_analog_transmission", InvertedAnalogTransmissionScene::invertedAnalogTransmission);
    }
}
