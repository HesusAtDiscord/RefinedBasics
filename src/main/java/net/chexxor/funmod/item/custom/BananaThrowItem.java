package net.chexxor.funmod.item.custom;

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

public class BananaThrowItem extends Item
{
   public static final int BURST_COUNT = 3;
   public static final int EXPLSION_POWER = 1;

   public BananaThrowItem(Item.Properties properties)
   {
      super(properties);
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
   {
      ItemStack itemStack = player.getItemInHand(hand);
      int itemsInHand = itemStack.getCount();
      int itemsToThrow = Math.min(itemsInHand, BURST_COUNT);
      RandomSource randomsource = RandomSource.create();

      world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
      if (!world.isClientSide)
      {
         for(int i = 0; i < itemsToThrow; ++i)
         {
            throwItem(world, player, itemStack, randomsource);
         }
      }

      player.awardStat(Stats.ITEM_USED.get(this));
      if (!player.getAbilities().instabuild) {
         itemStack.shrink(itemsToThrow);
      }

      return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
   }

   private void throwItem(Level world, Player player, ItemStack itemStack, RandomSource random)
   {
      float xRot = getRandomizedAngle(player.getXRot(), random);
      float yRot = getRandomizedAngle(player.getYRot(), random);

      LargeFireball projectile = createFireball(world, player, 0, 0, 0, EXPLSION_POWER);
      projectile.setItem(itemStack);
      projectile.shootFromRotation(player, xRot, yRot, 0.0F, 1.5F, 1.0F);
      world.addFreshEntity(projectile);
   }

   private float getRandomizedAngle(float value, RandomSource random)
   {
      return value + random.nextFloat() * 6.0F - 3.0F;
   }

   private LargeFireball createFireball(Level world, LivingEntity shooter, double dx, double dy, double dz, int explosionPower)
   {
      return new LargeFireball(world, shooter, dx, dy, dz, explosionPower);
   }
}