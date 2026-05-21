package net.cerealcamera.create_compact_additions.blocks.cogged_gearshift;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.AbstractEncasedShaftBlock;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import dev.simulated_team.simulated.util.extra_kinetics.ExtraKinetics;
import dev.simulated_team.simulated.util.placement_helpers.CogwheelPlacementExtension;
import net.cerealcamera.create_compact_additions.CABlockEntityTypes;
import net.cerealcamera.create_compact_additions.CABlocks;
import net.createmod.catnip.placement.IPlacementHelper;
import net.createmod.catnip.placement.PlacementHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

/**
 * The parent Block. Implements {@link ExtraKinetics.ExtraKineticsBlock ExtraKienticsBlock} to ensure {@link com.simibubi.create.content.kinetics.RotationPropagator} sees the ExtraKinetic BlockEntity
 */
public class CoggedGearshiftBlock extends AbstractEncasedShaftBlock implements IBE<SplitShaftBlockEntity>, ExtraKinetics.ExtraKineticsBlock {

    public static final int placementHelperId = PlacementHelpers.register(new CogwheelPlacementExtension((i) -> i.getItem() instanceof CogwheelBlockItem, CABlocks.COGGED_GEARSHIFT::has));

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public CoggedGearshiftBlock(final Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(POWERED, false));
    }

    @Override
    protected ItemInteractionResult useItemOn(final ItemStack itemStack, final BlockState blockState, final Level level, final BlockPos blockPos, final Player player, final InteractionHand interactionHand, final BlockHitResult blockHitResult) {
        final ItemStack heldItem = player.getItemInHand(interactionHand);

        final IPlacementHelper helper = PlacementHelpers.get(placementHelperId);
        if (helper.matchesItem(heldItem)) {
            return helper
                    .getOffset(player, level, blockState, blockPos, blockHitResult)
                    .placeInWorld(level, (BlockItem) heldItem.getItem(), player, interactionHand, blockHitResult);
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(POWERED,
                context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    public IRotate getExtraKineticsRotationConfiguration() {
        return CoggedGearshiftBlockEntity.CoggedGearshiftCogwheel.EXTRA_COGWHEEL_CONFIG;
    }

    @Override
    public Class<SplitShaftBlockEntity> getBlockEntityClass() {
        return SplitShaftBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SplitShaftBlockEntity> getBlockEntityType() {
        return CABlockEntityTypes.COGGED_GEARSHIFT.get();
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        BlockEntity be = worldIn.getBlockEntity(pos);
        if (!(be instanceof KineticBlockEntity kte))
            return;
        RotationPropagator.handleAdded(worldIn, pos, kte);
    }
}