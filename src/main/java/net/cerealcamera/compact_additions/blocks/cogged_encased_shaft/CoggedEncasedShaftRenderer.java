package net.cerealcamera.compact_additions.blocks.cogged_encased_shaft;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import static net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlockEntity.wheelPartialModel;

public class CoggedEncasedShaftRenderer extends KineticBlockEntityRenderer<CoggedEncasedShaftBlockEntity> {

    public CoggedEncasedShaftRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(final CoggedEncasedShaftBlockEntity be, final float partialTicks, final PoseStack ms, final MultiBufferSource buffer, final int light, final int overlay) {
        if (VisualizationManager.supportsVisualization(be.getLevel())) {
            return;
        }

        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        final BlockState state = be.getBlockState();
        final Direction.Axis axis = ((IRotate) state.getBlock()).getRotationAxis(state);

        final SuperByteBuffer cogwheel = kineticRotationTransform(
                CachedBuffers.partialFacingVertical(wheelPartialModel(), state, Direction.fromAxisAndDirection(state.getValue(CoggedEncasedShaftBlock.AXIS), Direction.AxisDirection.POSITIVE)),
                be.getExtraKinetics(),
                axis,
                getAngleForBe(be.getExtraKinetics(), be.getBlockPos(), axis),
                light);

        cogwheel.renderInto(ms, buffer.getBuffer(RenderType.solid()));

        final VertexConsumer vb = buffer.getBuffer(RenderType.solid());
        KineticBlockEntityRenderer.renderRotatingKineticBlock(be, shaft(getRotationAxisOf(be)), ms, vb, light);
    }

    @Override
    protected BlockState getRenderedBlockState(final CoggedEncasedShaftBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}