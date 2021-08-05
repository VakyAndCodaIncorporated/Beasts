package coda.beasts.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class BeastsSpawnEggItem<T extends Entity> extends SpawnEggItem {
    public static final Set<BeastsSpawnEggItem<?>> SPAWN_EGGS = new HashSet<>();
    public final Lazy<EntityType<T>> type;

    @SuppressWarnings("ConstantConditions")
    public BeastsSpawnEggItem(Supplier<EntityType<T>> type, int primaryColor, int secondaryColor, Properties properties) {
        super(null, primaryColor, secondaryColor, properties);

        this.type = Lazy.of(type);
        SPAWN_EGGS.add(this);
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT tag) {
        if (tag != null && tag.contains("EntityTag", 10)) {
            CompoundNBT childTag = tag.getCompound("EntityTag");
            if (childTag.contains("id", 8))
                return EntityType.byString(childTag.getString("id")).orElse(type.get());
        }

        return type.get();
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        World world = p_195939_1_.getLevel();
        if (!(world instanceof ServerWorld)) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = p_195939_1_.getItemInHand();
            BlockPos blockpos = p_195939_1_.getClickedPos();
            Direction direction = p_195939_1_.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);
            TileEntity tileentity = world.getBlockEntity(blockpos);
            EntityType<?> entitytype1 = this.getType(itemstack.getTag());
            if (blockstate.is(Blocks.SPAWNER)) {
                if (tileentity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner abstractspawner = ((MobSpawnerTileEntity)tileentity).getSpawner();
                    abstractspawner.setEntityId(entitytype1);
                    tileentity.setChanged();
                    world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return ActionResultType.CONSUME;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entitytype = this.getType(itemstack.getTag());
            if (entitytype.spawn((ServerWorld)world, itemstack, p_195939_1_.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
                itemstack.shrink(1);
            }

            return ActionResultType.CONSUME;
        }
    }

}