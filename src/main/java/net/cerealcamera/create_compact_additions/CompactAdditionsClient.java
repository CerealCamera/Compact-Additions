package net.cerealcamera.create_compact_additions;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = CompactAdditions.MODID, dist = Dist.CLIENT)
public class CompactAdditionsClient {
    public CompactAdditionsClient() {
        PonderIndex.addPlugin(new CAPonderPlugin());
    }
}
