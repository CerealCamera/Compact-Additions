package net.cerealcamera.inverted_analog_transmission;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InvertedAnalogTransmissionCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InvertedAnalogTransmission.MODID);

    public static final List<ItemProviderEntry<?, ?>> ITEMS = List.of(InvertedAnalogTransmissionBlocks.INVERTED_ANALOG_TRANSMISSION);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = REGISTER.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.inverted_analog_transmission.main"))
                    .withTabsBefore(ResourceLocation.parse("simulated:main_tab"))
                    .icon(InvertedAnalogTransmissionBlocks.INVERTED_ANALOG_TRANSMISSION::asStack)
                    .displayItems(new DisplayItemsGenerator(ITEMS))
                    .build());

    @ApiStatus.Internal
    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

    private record DisplayItemsGenerator(List<ItemProviderEntry<?, ?>> items) implements CreativeModeTab.DisplayItemsGenerator {
        @Override
        public void accept(@NotNull CreativeModeTab.ItemDisplayParameters params, @NotNull CreativeModeTab.Output output) {
            for (ItemProviderEntry<?, ?> item : items) {
                output.accept(item);
            }
        }
    }
}
