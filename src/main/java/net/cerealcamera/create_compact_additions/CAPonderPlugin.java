package net.cerealcamera.create_compact_additions;

import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.registration.IndexExclusionHelper;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CAPonderPlugin extends CreatePonderPlugin {
    public CAPonderPlugin() {
    }

    public String getModId() {
        return CompactAdditions.MODID;
    }

    @Override
    public void registerScenes(final PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CAPonderScenes.register(helper);
    }

    @Override
    public void registerTags(final PonderTagRegistrationHelper<ResourceLocation> helper) {
        CAPonderTags.register(helper);
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