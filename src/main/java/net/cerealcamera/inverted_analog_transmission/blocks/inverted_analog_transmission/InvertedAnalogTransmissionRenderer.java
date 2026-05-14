package net.cerealcamera.inverted_analog_transmission.blocks.inverted_analog_transmission;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.cerealcamera.inverted_analog_transmission.InvertedAnalogTransmissionPartialModels;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class InvertedAnalogTransmissionRenderer extends KineticBlockEntityRenderer<InvertedAnalogTransmissionBlockEntity> {

    public InvertedAnalogTransmissionRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(final InvertedAnalogTransmissionBlockEntity be, final float partialTicks, final PoseStack ms, final MultiBufferSource buffer, final int light, final int overlay) {
        if (VisualizationManager.supportsVisualization(be.getLevel())) {
            return;
        }

        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        final BlockState state = be.getBlockState();
        final Direction.Axis axis = ((IRotate) state.getBlock()).getRotationAxis(state);

        final SuperByteBuffer cogwheel = kineticRotationTransform(
                CachedBuffers.partialFacingVertical(InvertedAnalogTransmissionPartialModels.INVERTED_ANALOG_TRANSMISSION_COG, state, Direction.fromAxisAndDirection(state.getValue(InvertedAnalogTransmissionBlock.AXIS), Direction.AxisDirection.POSITIVE)),
                be.getExtraKinetics(),
                axis,
                getAngleForBe(be.getExtraKinetics(), be.getBlockPos(), axis),
                light);

        cogwheel.renderInto(ms, buffer.getBuffer(RenderType.solid()));

        final VertexConsumer vb = buffer.getBuffer(RenderType.solid());
        KineticBlockEntityRenderer.renderRotatingKineticBlock(be, shaft(getRotationAxisOf(be)), ms, vb, light);
    }

    @Override
    protected BlockState getRenderedBlockState(final InvertedAnalogTransmissionBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}