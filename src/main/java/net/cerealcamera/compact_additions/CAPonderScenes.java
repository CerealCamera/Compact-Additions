package net.cerealcamera.compact_additions;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.cerealcamera.compact_additions.ponder.KineticScenes;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CAPonderScenes {
    public static void register(final PonderSceneRegistrationHelper<ResourceLocation> registry) {
        final PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> helper = registry.withKeyFunction(DeferredHolder::getId);

        helper.forComponents(CABlocks.INVERTED_ANALOG_TRANSMISSION)
                .addStoryBoard("inverted_analog_transmission", KineticScenes::invertedAnalogTransmission);
    }
}
