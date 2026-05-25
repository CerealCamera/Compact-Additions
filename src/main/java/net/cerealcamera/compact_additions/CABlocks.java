package net.cerealcamera.compact_additions;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.cerealcamera.compact_additions.blocks.cogged_gearshift.CoggedGearshiftBlock;
import net.cerealcamera.compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlock;
import net.cerealcamera.compact_additions.blocks.inverted_cogged_gearshift.InvertedCoggedGearshiftBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

@SuppressWarnings("removal")
public class CABlocks {
    private static final CreateRegistrate REGISTRATE = CompactAdditions.getRegistrate();

    public static final BlockEntry<InvertedAnalogTransmissionBlock> INVERTED_ANALOG_TRANSMISSION =
            REGISTRATE.block("inverted_analog_transmission", InvertedAnalogTransmissionBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties((p) -> p.noOcclusion()
                            .isRedstoneConductor(CABlocks::never))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .item().transform(customItemModel())
                    .register();

    public static final BlockEntry<CoggedGearshiftBlock> COGGED_GEARSHIFT =
            REGISTRATE.block("cogged_gearshift", CoggedGearshiftBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties((p) -> p.noOcclusion()
                            .isRedstoneConductor(CABlocks::never))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .item().transform(customItemModel())
                    .register();

    public static final BlockEntry<InvertedCoggedGearshiftBlock> INVERTED_COGGED_GEARSHIFT =
            REGISTRATE.block("inverted_cogged_gearshift", InvertedCoggedGearshiftBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties((p) -> p.noOcclusion()
                            .isRedstoneConductor(CABlocks::never))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .item().transform(customItemModel())
                    .register();

    private static Boolean never(final BlockState state, final BlockGetter blockGetter, final BlockPos pos) {
        return false;
    }

    public static void register() {
    }
}
