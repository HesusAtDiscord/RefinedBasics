package net.chexxor.funmod.item.custom;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.Util;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BananaThrowItem extends Item
{
    public static final int BURST_COUNT = 3;
    public static final int EXPLSION_POWER = 1;
    public static final float PROJECTILE_FORCE = 1f;

    public BananaThrowItem(Item.Properties properties)
    {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        int itemsToThrow = Math.min(itemStack.getCount(), BURST_COUNT);
        // RandomSource randomsource = RandomSource.create();

        world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide)
        {
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            for(int i = 0; i < itemsToThrow; ++i)
            {
                executor.schedule(() -> throwItem(world, player, itemStack), 200 * i, TimeUnit.MILLISECONDS);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this), itemsToThrow);
        if (!player.getAbilities().instabuild)
        {
            itemStack.shrink(itemsToThrow);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    // private void throwItem(Level world, Player player, ItemStack itemStack, RandomSource random)
    private void throwItem(Level world, Player player, ItemStack itemStack)
    {
        // float xRot = getRandomizedAngle(player.getXRot(), random);
        // float yRot = getRandomizedAngle(player.getYRot(), random);
        Vec3 lookVector = player.getLookAngle();

        LargeFireball projectile = createFireball(world, player, lookVector, EXPLSION_POWER);
        projectile.setItem(itemStack);
        // projectile.shootFromRotation(player, xRot, yRot, 0.0F, 1.5F, 1.0F);
        world.addFreshEntity(projectile);
    }

    // private float getRandomizedAngle(float value, RandomSource random)
    // {
    //     return value + random.nextFloat() * 6.0F - 3.0F;
    // }

    private LargeFireball createFireball(Level world, LivingEntity shooter, Vec3 dir, int explosionPower)
    {
        return createFireball(world, shooter, dir.x, dir.y, dir.z, explosionPower);
    }

    private LargeFireball createFireball(Level world, LivingEntity shooter, double dx, double dy, double dz, int explosionPower)
    {
        return new LargeFireball(world, shooter, dx, dy, dz, explosionPower);
    }
}