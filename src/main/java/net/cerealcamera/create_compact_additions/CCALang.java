package net.cerealcamera.create_compact_additions;

import net.createmod.catnip.lang.Lang;
import net.createmod.catnip.lang.LangBuilder;

public class CCALang {
    public static LangBuilder builder() {
        return Lang.builder(CreateCompactAdditions.MODID);
    }

    public static LangBuilder translate(final String key, final Object... args) {
        return builder().translate(key, args);
    }
}
