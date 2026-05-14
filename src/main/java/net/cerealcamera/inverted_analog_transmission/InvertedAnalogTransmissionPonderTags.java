package net.cerealcamera.inverted_analog_transmission;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class InvertedAnalogTransmissionPonderTags {
    public static void register(final PonderTagRegistrationHelper<ResourceLocation> helper) {
        final PonderTagRegistrationHelper<ItemLike> itemHelper = helper.withKeyFunction(
                RegisteredObjectsHelper::getKeyOrThrow);

        itemHelper.addToTag(AllCreatePonderTags.KINETIC_RELAYS).add(InvertedAnalogTransmissionBlocks.INVERTED_ANALOG_TRANSMISSION.asItem());
    }
}
