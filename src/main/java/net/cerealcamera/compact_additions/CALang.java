package net.cerealcamera.compact_additions;

import net.createmod.catnip.lang.Lang;
import net.createmod.catnip.lang.LangBuilder;

public class CALang {
    public static LangBuilder builder() {
        return Lang.builder(CompactAdditions.MODID);
    }

    public static LangBuilder translate(final String key, final Object... args) {
        return builder().translate(key, args);
    }
}
