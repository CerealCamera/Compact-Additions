package net.cerealcamera.create_compact_additions;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = CreateCompactAdditions.MODID, dist = Dist.CLIENT)
public class CCAClient {
    public CCAClient() {
        PonderIndex.addPlugin(new CCAPonderPlugin());
    }
}
