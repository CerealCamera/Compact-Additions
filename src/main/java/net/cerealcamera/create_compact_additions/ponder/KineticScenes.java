package net.cerealcamera.create_compact_additions.ponder;

import com.simibubi.create.content.redstone.analogLever.AnalogLeverBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import dev.simulated_team.simulated.ponder.SmoothMovementUtils;
import dev.simulated_team.simulated.ponder.instructions.CustomAnimateWorldSectionInstruction;
import net.cerealcamera.create_compact_additions.blocks.inverted_analog_transmission.InvertedAnalogTransmissionBlockEntity;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.phys.Vec3;

public class KineticScenes {
    public static void invertedAnalogTransmission(final SceneBuilder builder, final SceneBuildingUtil util) {
        final CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final CreateSceneBuilder.WorldInstructions world = scene.world();
        final CreateSceneBuilder.EffectInstructions effects = scene.effects();
        final OverlayInstructions overlay = scene.overlay();
        final SelectionUtil select = util.select();
        final VectorUtil vector = util.vector();

        scene.title("inverted_analog_transmission", "Controlling rotational speed using Inverted Analog Transmission");
        scene.configureBasePlate(0, 0, 5);
        //scene.setSceneOffsetY(-1);
        world.showSection(select.fromTo(0, 0, 0, 5, 0, 4), Direction.UP);

        final BlockPos analogLeverPos = new BlockPos(2, 1, 0);
        final BlockPos redstonePos = new BlockPos(2, 1, 1);
        final BlockPos transmissionPos = new BlockPos(2, 1, 2);
        final BlockPos bottomGauge = new BlockPos(0, 1, 2);
        final BlockPos topGauge = new BlockPos(0, 2, 2);

        final ElementLink<WorldSectionElement> redstoneSection = world.showIndependentSection(select.position(redstonePos), Direction.UP);
        final ElementLink<WorldSectionElement> analogLeverSection = world.showIndependentSection(select.position(analogLeverPos), Direction.UP);

        scene.idle(8);

        for (int i = 5; i >= 2; i--) {
            scene.idle(3);
            world.showSection(select.position(i, 1, 2), Direction.DOWN);
        }
        scene.idle(10);
        world.showSection(select.position(transmissionPos.above()), Direction.DOWN);
        world.modifyBlockEntityNBT(select.position(transmissionPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", 0f);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 2, 2, 2), 0f);
        scene.idle(5);
        overlay.showText(90)
                .text("Unpowered Inverted Analog Transmissions behave exactly like fully powered Analog Transmissions, so their shaft and cogwheel rotate independently")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(vector.blockSurface(transmissionPos, Direction.NORTH));
        scene.idle(100);
        world.showSection(select.fromTo(0, 1, 2, 1, 2, 3), Direction.EAST);
        scene.idle(10);
        overlay.showText(50)
                .sharedText("rpm16")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(vector.blockSurface(bottomGauge, Direction.NORTH));
        scene.idle(5);
        overlay.showText(50)
                .text("0 RPM")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(vector.blockSurface(topGauge, Direction.NORTH));

        scene.idle(60);
        effects.indicateRedstone(analogLeverPos);
        world.toggleRedstonePower(select.position(transmissionPos));
        world.modifyBlock(redstonePos, s -> s.setValue(RedStoneWireBlock.POWER, 5), false);
        world.modifyBlockEntityNBT(select.position(analogLeverPos), AnalogLeverBlockEntity.class, nbt -> {
            nbt.putInt("State", 5);
        });

        world.modifyBlockEntityNBT(select.position(transmissionPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", 10f);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 2, 2, 2), -10f);
        scene.idle(10);
        overlay.showText(90)
                .text("The cogwheel's RPM scales with redstone strength towards the shaft's RPM")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(vector.blockSurface(transmissionPos, Direction.NORTH));
        scene.idle(90);

        overlay.showText(50)
                .sharedText("rpm16")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(vector.blockSurface(bottomGauge, Direction.NORTH));
        scene.idle(5);
        overlay.showText(50)
                .text("5 RPM")
                .colored(PonderPalette.SLOW)
                .placeNearTarget()
                .pointAt(vector.blockSurface(topGauge, Direction.NORTH));

        scene.idle(100);
        effects.indicateRedstone(analogLeverPos);
        world.modifyBlock(redstonePos, s -> s.setValue(RedStoneWireBlock.POWER, 10), false);
        world.modifyBlockEntityNBT(select.position(analogLeverPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.putInt("State", 10);
        });

        world.modifyBlockEntityNBT(select.position(transmissionPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", 20f);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 2, 2, 2), -20f);
        scene.idle(10);
        overlay.showText(60)
                .text("Higher signal strengths result in smaller speed differences")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(vector.blockSurface(transmissionPos, Direction.NORTH));
        scene.idle(70);
        overlay.showText(50)
                .sharedText("rpm16")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(vector.blockSurface(bottomGauge, Direction.NORTH));
        scene.idle(5);
        overlay.showText(50)
                .text("10 RPM")
                .colored(PonderPalette.SLOW)
                .placeNearTarget()
                .pointAt(vector.blockSurface(topGauge, Direction.NORTH));
        scene.idle(60);


        world.toggleRedstonePower(select.position(transmissionPos));
        world.modifyBlock(redstonePos, s -> s.setValue(RedStoneWireBlock.POWER, 0), false);
        world.modifyBlockEntityNBT(select.position(analogLeverPos), AnalogLeverBlockEntity.class, nbt -> {
            nbt.putInt("State", 0);
        });

        world.modifyBlockEntityNBT(select.position(transmissionPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", 0f);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 2, 2, 2), 0f);

        final ElementLink<WorldSectionElement> transmissionSection = world.makeSectionIndependent(select.fromTo(transmissionPos, transmissionPos.above()));

        /*world.moveSection(transmissionSection,new Vec3(0,0.5,0),5);
        scene.idle(5);
        world.rotateSection(transmissionSection,180,0,0,10);
        scene.idle(10);
        world.moveSection(transmissionSection,new Vec3(0,-0.5,0),5);
        scene.idle(5);*/
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(transmissionSection, new Vec3(0, 0.5, 0), 20, SmoothMovementUtils.quadraticJump()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(transmissionSection, new Vec3(180, 0, 0), 20, SmoothMovementUtils.quinticSmoothing()));
        scene.idle(20);
        world.moveSection(transmissionSection, new Vec3(0, -2.5, 0), 0);

        final BlockPos newTransmissionPos = new BlockPos(0, 2, 0);

        //world.hideIndependentSection(transmissionSection,null);
        final ElementLink<WorldSectionElement> newTransmissionSection = world.showIndependentSectionImmediately(select.fromTo(newTransmissionPos, newTransmissionPos.below()));
        world.moveSection(newTransmissionSection, new Vec3(2, 0, 2), 0);

        world.setKineticSpeed(select.fromTo(0, 2, 2, 1, 2, 2), 0f);
        world.setKineticSpeed(select.position(newTransmissionPos), 0f);
        world.setKineticSpeed(select.fromTo(0, 1, 2, 1, 1, 2), 32f);
        scene.idle(10);
        world.moveSection(redstoneSection, new Vec3(0, 1, 0), 10);
        world.moveSection(analogLeverSection, new Vec3(0, 1, 0), 10);
        scene.idle(10);
        final ElementLink<WorldSectionElement> casingSection = world.showIndependentSection(select.fromTo(3, 1, 0, 3, 1, 1), Direction.WEST);
        world.moveSection(casingSection, new Vec3(-1, 0, 0), 0);

        scene.idle(20);
        effects.indicateRedstone(analogLeverPos.above());
        world.toggleRedstonePower(select.position(newTransmissionPos));
        world.modifyBlock(redstonePos, s -> s.setValue(RedStoneWireBlock.POWER, 4), false);
        world.modifyBlockEntityNBT(select.position(analogLeverPos), AnalogLeverBlockEntity.class, nbt -> {
            nbt.putInt("State", 4);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 1, 2, 2), -128f);
        world.setKineticSpeed(select.position(newTransmissionPos), -128f);

        scene.idle(10);
        overlay.showText(80)
                .text("When the cogwheel drives the transmission, the shaft begins at a higher speed and slows down as the Redstone signal increases")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(vector.blockSurface(transmissionPos.above(), Direction.NORTH));
        scene.idle(90);
        overlay.showText(50)
                .sharedText("rpm16")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(vector.blockSurface(bottomGauge, Direction.NORTH));
        scene.idle(5);
        overlay.showText(50)
                .text("64 RPM")
                .colored(PonderPalette.FAST)
                .placeNearTarget()
                .pointAt(vector.blockSurface(topGauge, Direction.NORTH));
        scene.idle(60);

        world.toggleRedstonePower(select.position(newTransmissionPos));
        world.modifyBlock(redstonePos, s -> s.setValue(RedStoneWireBlock.POWER, 0), false);
        world.modifyBlockEntityNBT(select.position(analogLeverPos), AnalogLeverBlockEntity.class, nbt -> {
            nbt.putInt("State", 0);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 1, 2, 2), 0f);
        world.setKineticSpeed(select.position(newTransmissionPos), -32f);
        scene.idle(5);
        world.hideIndependentSection(casingSection, Direction.EAST);
        scene.idle(20);
        world.moveSection(redstoneSection, new Vec3(0, -1, 0), 10);
        world.moveSection(analogLeverSection, new Vec3(0, -1, 0), 10);
        scene.idle(15);

        //world.setKineticSpeed(select.fromTo(0, 1, 2, 1, 2, 2), 0);

        world.moveSection(newTransmissionSection, new Vec3(0, -2.5, 0), 0);
        world.moveSection(transmissionSection, new Vec3(0, 2.5, 0), 0);
        /*world.moveSection(transmissionSection,new Vec3(0,0.5,0),5);
        scene.idle(5);
        world.rotateSection(transmissionSection,180,0,0,10);
        scene.idle(10);
        world.moveSection(transmissionSection,new Vec3(0,-0.5,0),5);
        scene.idle(5);*/
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(transmissionSection, new Vec3(0, 0.5, 0), 20, SmoothMovementUtils.quadraticJump()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(transmissionSection, new Vec3(180, 0, 0), 20, SmoothMovementUtils.quinticSmoothing()));
        scene.idle(20);

        world.setKineticSpeed(select.fromTo(0, 1, 2, 2, 1, 2), 32);
        world.setKineticSpeed(select.fromTo(0, 2, 2, 2, 2, 2), 0);
        world.modifyBlockEntityNBT(select.position(transmissionPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", 0f);
        });
        scene.idle(10);
        world.hideIndependentSection(analogLeverSection, Direction.UP);
        scene.idle(20);
        final ElementLink<WorldSectionElement> leverSection = world.showIndependentSection(select.position(analogLeverPos.west()), Direction.DOWN);
        world.moveSection(leverSection, new Vec3(1, 0, 0), 0);
        scene.idle(20);
        effects.indicateRedstone(analogLeverPos);
        world.toggleRedstonePower(select.fromTo(1, 1, 0, 2, 1, 2));
        world.modifyBlockEntityNBT(select.position(transmissionPos), InvertedAnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", 32f);
        });
        world.setKineticSpeed(select.fromTo(0, 2, 2, 2, 2, 2), -32);
        scene.idle(20);
        overlay.showText(60)
                .text("At full signal input, the Transmission behaves exactly like an Encased Cogwheel")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(vector.blockSurface(transmissionPos, Direction.NORTH));
        scene.idle(60);
    }
}
