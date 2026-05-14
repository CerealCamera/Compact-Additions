package net.cerealcamera.inverted_analog_transmission;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.cerealcamera.inverted_analog_transmission.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

@SuppressWarnings("removal")
public class InvertedAnalogTransmissionBlocks {
    private static final CreateRegistrate REGISTRATE = InvertedAnalogTransmission.getRegistrate();

    public static final BlockEntry<InvertedAnalogTransmissionBlock> INVERTED_ANALOG_TRANSMISSION =
            REGISTRATE.block("inverted_analog_transmission", InvertedAnalogTransmissionBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties((p) -> p.noOcclusion()
                            .isRedstoneConductor(InvertedAnalogTransmissionBlocks::never))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, s -> {
                        String suffix = s.getValue(InvertedAnalogTransmissionBlock.POWERED) ? "_on" : "";
                        ResourceLocation path = InvertedAnalogTransmission.path("block/" + c.getName() + "/block" + suffix);
                        return p.models().getExistingFile(path);
                    }))
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .tag(BlockTags.MINEABLE_WITH_AXE)
                    .item().transform(customItemModel())
                    .register();

    private static Boolean never(final BlockState state, final BlockGetter blockGetter, final BlockPos pos) {
        return false;
    }

    public static void register() {
    }
}
