package net.cerealcamera.compact_additions.blocks.cogged_gearshift;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.AbstractInstance;
import dev.engine_room.flywheel.lib.instance.FlatLit;
import dev.engine_room.flywheel.lib.model.Models;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CoggedGearshiftVisual extends KineticBlockEntityVisual<CoggedGearshiftBlockEntity> {

    private final RotatingInstance cogInstance;

    protected final ArrayList<RotatingInstance> keys;

    public CoggedGearshiftVisual(VisualizationContext context, CoggedGearshiftBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);
        this.cogInstance = this.instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL)).createInstance()
                .rotateToFace(Direction.UP, this.rotationAxis())
                .setup(blockEntity.getExtraKinetics())
                .setPosition(this.getVisualPosition());
        this.cogInstance.setChanged();

        keys = new ArrayList<>(2);

        float speed = blockEntity.getSpeed();

        for (Direction dir : Iterate.directionsInAxis(rotationAxis())) {

            float splitSpeed = speed * blockEntity.getRotationSpeedModifier(dir);

            var instance = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFT_HALF))
                    .createInstance();

            instance.setup(blockEntity, splitSpeed)
                    .setPosition(getVisualPosition())
                    .rotateToFace(Direction.SOUTH, dir)
                    .setChanged();

            keys.add(instance);
        }
    }

    @Override
    public void update(final float pt) {
        Block block = blockState.getBlock();
        final Direction.Axis boxAxis = ((IRotate) block).getRotationAxis(blockState);

        Direction[] directions = Iterate.directionsInAxis(boxAxis);

        for (int i : Iterate.zeroAndOne) {
            keys.get(i)
                    .setup(blockEntity, blockEntity.getSpeed() * blockEntity.getRotationSpeedModifier(directions[i]))
                    .setChanged();
        }
        this.cogInstance.setup(this.blockEntity.getExtraKinetics()).setChanged();
    }

    @Override
    public void updateLight(final float partialTick) {
        relight(keys.toArray(FlatLit[]::new));
        this.relight(this.cogInstance);
    }

    @Override
    protected void _delete() {
        keys.forEach(AbstractInstance::delete);
        keys.clear();
        this.cogInstance.delete();
    }

    @Override
    public void collectCrumblingInstances(final Consumer<Instance> consumer) {
        keys.forEach(consumer);
        consumer.accept(this.cogInstance);
    }
}