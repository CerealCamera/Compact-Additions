package net.cerealcamera.compact_additions;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CompactAdditions.MODID)
public class CompactAdditions {
    public static final String MODID = "compact_additions";

    public static IEventBus modEventBus;
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public CompactAdditions(IEventBus eventBus) {
        init(eventBus);
    }

    public static void init(IEventBus eventBus) {
        modEventBus = eventBus;
        REGISTRATE.registerEventListeners(modEventBus);

        CABlocks.register();
        CABlockEntityTypes.register();
        CACreativeTabs.register(eventBus);
    }

    public static CreateRegistrate getRegistrate() {
        return REGISTRATE;
    }
}
