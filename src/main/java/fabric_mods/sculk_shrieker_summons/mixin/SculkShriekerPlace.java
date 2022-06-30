package fabric_mods.sculk_shrieker_summons.mixin;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockItem.class)
public class SculkShriekerPlace {
	@Inject(at = @At("HEAD"), method = "Lnet/minecraft/item/BlockItem;postPlacement(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/block/BlockState;)Z	")
	private void postPlacement(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack,
			BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (!world.isClient) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity.getType() == BlockEntityType.SCULK_SHRIEKER) {
				world.setBlockState(pos,
					state.with(BooleanProperty.of("can_summon"), true));
			}
		}
	}
}