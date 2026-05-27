package net.cerealcamera.compact_additions.blocks.cogged_encased_shaft;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;

import java.util.function.Consumer;

import static net.cerealcamera.compact_additions.blocks.cogged_encased_shaft.CoggedEncasedShaftBlockEntity.wheelPartialModel;

public class CoggedEncasedShaftVisual extends SingleAxisRotatingVisual<CoggedEncasedShaftBlockEntity> {

    private final RotatingInstance cogInstance;

    public CoggedEncasedShaftVisual(final VisualizationContext context, final CoggedEncasedShaftBlockEntity blockEntity, final float partialTick) {
        super(context, blockEntity, partialTick, Direction.UP, Models.partial(AllPartialModels.SHAFT));
        this.cogInstance = this.instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(wheelPartialModel())).createInstance()
                .rotateToFace(Direction.UP, this.rotationAxis())
                .setup(blockEntity.getExtraKinetics())
                .setPosition(this.getVisualPosition());
        this.cogInstance.setChanged();
    }

    @Override
    public void update(final float pt) {
        super.update(pt);
        this.cogInstance.setup(this.blockEntity.getExtraKinetics()).setChanged();
    }

    @Override
    public void updateLight(final float partialTick) {
        super.updateLight(partialTick);
        this.relight(this.cogInstance);
    }

    @Override
    protected void _delete() {
        super._delete();
        this.cogInstance.delete();
    }

    @Override
    public void collectCrumblingInstances(final Consumer<Instance> consumer) {
        super.collectCrumblingInstances(consumer);
        consumer.accept(this.cogInstance);
    }
}