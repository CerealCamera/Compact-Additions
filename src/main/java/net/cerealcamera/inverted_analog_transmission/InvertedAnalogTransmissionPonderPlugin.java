package net.cerealcamera.inverted_analog_transmission;

import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.registration.IndexExclusionHelper;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class InvertedAnalogTransmissionPonderPlugin extends CreatePonderPlugin {
    public InvertedAnalogTransmissionPonderPlugin() {
    }

    public String getModId() {
        return InvertedAnalogTransmission.MODID;
    }

    @Override
    public void registerScenes(final PonderSceneRegistrationHelper<ResourceLocation> helper) {
        InvertedAnalogTransmissionPonderScenes.register(helper);
    }

    @Override
    public void registerTags(final PonderTagRegistrationHelper<ResourceLocation> helper) {
        InvertedAnalogTransmissionPonderTags.register(helper);
    }

    @Override
    public void registerSharedText(@NotNull final SharedTextRegistrationHelper helper) {
        helper.registerSharedText("rpm16", "16 RPM");
    }

    @Override
    public void onPonderLevelRestore(final PonderLevel ponderLevel) {

    }

    @Override
    public void indexExclusions(final IndexExclusionHelper helper) {

    }
}