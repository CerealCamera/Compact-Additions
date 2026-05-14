package net.cerealcamera.inverted_analog_transmission;

import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.simulated_team.simulated.registrate.SimulatedRegistrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(InvertedAnalogTransmission.MODID)
public class InvertedAnalogTransmission {
    public static final String MODID = "inverted_analog_transmission";

    public static IEventBus modEventBus;
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public InvertedAnalogTransmission(IEventBus eventBus) {
        init(eventBus);
    }

    public static void init(IEventBus eventBus) {
        modEventBus = eventBus;
        REGISTRATE.registerEventListeners(modEventBus);

        InvertedAnalogTransmissionBlocks.register();
        InvertedAnalogTransmissionBlockEntityTypes.register();

        SimulatedRegistrate.TAB_ITEMS.add(InvertedAnalogTransmissionBlocks.INVERTED_ANALOG_TRANSMISSION::asItem);
        SimulatedRegistrate.ITEM_TO_SECTION.put(
                ResourceLocation.fromNamespaceAndPath(MODID, "inverted_analog_transmission"),
                ResourceLocation.fromNamespaceAndPath("simulated", "simulated")
        );
    }

    public static CreateRegistrate getRegistrate() {
        return REGISTRATE;
    }

    public static ResourceLocation path(final String path) {
        return ResourceLocation.tryBuild(MODID, path);
    }
}
