package org.patchbukkit.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Color;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.patchbukkit.PatchBukkitServer;

import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.block.TargetBlockInfo.FluidMode;
import com.destroystokyo.paper.entity.TargetEntityInfo;

import io.papermc.paper.world.damagesource.CombatTracker;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.util.TriState;

@SuppressWarnings({ "deprecation", "removal" })
public class PatchBukkitLivingEntity
    extends PatchBukkitEntity
    implements LivingEntity {

    public PatchBukkitLivingEntity(UUID uuid,
        String name) {
        super(uuid, name);
    }
    @Override
    public @Nullable AttributeInstance getAttribute(@NotNull Attribute attribute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttribute'");
    }

    @Override
    public void registerAttribute(@NotNull Attribute attribute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerAttribute'");
    }

    @Override
    public void damage(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'damage'");
    }

    @Override
    public void damage(double amount, @Nullable Entity source) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'damage'");
    }

    @Override
    public void damage(double amount, @NotNull DamageSource damageSource) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'damage'");
    }

    @Override
    public double getHealth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHealth'");
    }

    @Override
    public void setHealth(double health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHealth'");
    }

    @Override
    public void heal(double amount, @NotNull RegainReason reason) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'heal'");
    }

    @Override
    public double getAbsorptionAmount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAbsorptionAmount'");
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAbsorptionAmount'");
    }

    @Override
    public double getMaxHealth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxHealth'");
    }

    @Override
    public void setMaxHealth(double health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaxHealth'");
    }

    @Override
    public void resetMaxHealth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetMaxHealth'");
    }

    @Override
    public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile,
            @Nullable Vector velocity, @Nullable Consumer<? super T> function) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'launchProjectile'");
    }

    @Override
    public TriState getFrictionState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrictionState'");
    }

    @Override
    public void setFrictionState(TriState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFrictionState'");
    }

    @Override
    public double getEyeHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEyeHeight'");
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEyeHeight'");
    }

    @Override
    public @NotNull Location getEyeLocation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEyeLocation'");
    }

    @Override
    public @NotNull List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLineOfSight'");
    }

    @Override
    public @NotNull Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlock'");
    }

    @Override
    public @Nullable Block getTargetBlock(int maxDistance, @NotNull FluidMode fluidMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlock'");
    }

    @Override
    public @Nullable BlockFace getTargetBlockFace(int maxDistance, @NotNull FluidMode fluidMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlockFace'");
    }

    @Override
    public @Nullable BlockFace getTargetBlockFace(int maxDistance, @NotNull FluidCollisionMode fluidMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlockFace'");
    }

    @Override
    public @Nullable TargetBlockInfo getTargetBlockInfo(int maxDistance, @NotNull FluidMode fluidMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlockInfo'");
    }

    @Override
    public @Nullable Entity getTargetEntity(int maxDistance, boolean ignoreBlocks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetEntity'");
    }

    @Override
    public @Nullable TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetEntityInfo'");
    }

    @Override
    public @Nullable RayTraceResult rayTraceEntities(int maxDistance, boolean ignoreBlocks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rayTraceEntities'");
    }

    @Override
    public @NotNull List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastTwoTargetBlocks'");
    }

    @Override
    public @Nullable Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlockExact'");
    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rayTraceBlocks'");
    }

    @Override
    public int getRemainingAir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRemainingAir'");
    }

    @Override
    public void setRemainingAir(int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRemainingAir'");
    }

    @Override
    public int getMaximumAir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaximumAir'");
    }

    @Override
    public void setMaximumAir(int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaximumAir'");
    }

    @Override
    public @Nullable ItemStack getItemInUse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemInUse'");
    }

    @Override
    public int getItemInUseTicks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemInUseTicks'");
    }

    @Override
    public void setItemInUseTicks(int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setItemInUseTicks'");
    }

    @Override
    public @NonNegative int getArrowCooldown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrowCooldown'");
    }

    @Override
    public void setArrowCooldown(@NonNegative int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setArrowCooldown'");
    }

    @Override
    public @NonNegative int getArrowsInBody() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrowsInBody'");
    }

    @Override
    public void setArrowsInBody(@NonNegative int count, boolean fireEvent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setArrowsInBody'");
    }

    @Override
    public @NonNegative int getBeeStingerCooldown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBeeStingerCooldown'");
    }

    @Override
    public void setBeeStingerCooldown(@NonNegative int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBeeStingerCooldown'");
    }

    @Override
    public @NonNegative int getBeeStingersInBody() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBeeStingersInBody'");
    }

    @Override
    public void setBeeStingersInBody(@NonNegative int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBeeStingersInBody'");
    }

    @Override
    public int getMaximumNoDamageTicks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaximumNoDamageTicks'");
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaximumNoDamageTicks'");
    }

    @Override
    public double getLastDamage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastDamage'");
    }

    @Override
    public void setLastDamage(double damage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLastDamage'");
    }

    @Override
    public int getNoDamageTicks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNoDamageTicks'");
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNoDamageTicks'");
    }

    @Override
    public int getNoActionTicks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNoActionTicks'");
    }

    @Override
    public void setNoActionTicks(int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNoActionTicks'");
    }

    @Override
    public @Nullable Player getKiller() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKiller'");
    }

    @Override
    public void setKiller(@Nullable Player killer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setKiller'");
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPotionEffect'");
    }

    @Override
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPotionEffects'");
    }

    @Override
    public boolean hasPotionEffect(@NotNull PotionEffectType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPotionEffect'");
    }

    @Override
    public @Nullable PotionEffect getPotionEffect(@NotNull PotionEffectType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPotionEffect'");
    }

    @Override
    public void removePotionEffect(@NotNull PotionEffectType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePotionEffect'");
    }

    @Override
    public @NotNull Collection<PotionEffect> getActivePotionEffects() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActivePotionEffects'");
    }

    @Override
    public boolean clearActivePotionEffects() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearActivePotionEffects'");
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasLineOfSight'");
    }

    @Override
    public boolean hasLineOfSight(@NotNull Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasLineOfSight'");
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRemoveWhenFarAway'");
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRemoveWhenFarAway'");
    }

    @Override
    public @Nullable EntityEquipment getEquipment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEquipment'");
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCanPickupItems'");
    }

    @Override
    public boolean getCanPickupItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCanPickupItems'");
    }

    @Override
    public boolean isLeashed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isLeashed'");
    }

    @Override
    public @NotNull Entity getLeashHolder() throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeashHolder'");
    }

    @Override
    public boolean setLeashHolder(@Nullable Entity holder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLeashHolder'");
    }

    @Override
    public boolean isGliding() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGliding'");
    }

    @Override
    public void setGliding(boolean gliding) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGliding'");
    }

    @Override
    public boolean isSwimming() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSwimming'");
    }

    @Override
    public void setSwimming(boolean swimming) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSwimming'");
    }

    @Override
    public boolean isRiptiding() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRiptiding'");
    }

    @Override
    public void setRiptiding(boolean riptiding) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRiptiding'");
    }

    @Override
    public boolean isSleeping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSleeping'");
    }

    @Override
    public boolean isClimbing() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isClimbing'");
    }

    @Override
    public void setAI(boolean ai) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAI'");
    }

    @Override
    public boolean hasAI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasAI'");
    }

    @Override
    public void attack(@NotNull Entity target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void swingMainHand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swingMainHand'");
    }

    @Override
    public void swingOffHand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swingOffHand'");
    }

    @Override
    public void playHurtAnimation(float yaw) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playHurtAnimation'");
    }

    @Override
    public void setCollidable(boolean collidable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCollidable'");
    }

    @Override
    public boolean isCollidable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCollidable'");
    }

    @Override
    public @NotNull Set<UUID> getCollidableExemptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollidableExemptions'");
    }

    @Override
    public <T> @Nullable T getMemory(@NotNull MemoryKey<T> memoryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMemory'");
    }

    @Override
    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMemory'");
    }

    @Override
    public @Nullable Sound getHurtSound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHurtSound'");
    }

    @Override
    public @Nullable Sound getDeathSound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathSound'");
    }

    @Override
    public @NotNull Sound getFallDamageSound(int fallHeight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFallDamageSound'");
    }

    @Override
    public @NotNull Sound getFallDamageSoundSmall() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFallDamageSoundSmall'");
    }

    @Override
    public @NotNull Sound getFallDamageSoundBig() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFallDamageSoundBig'");
    }

    @Override
    public @NotNull Sound getDrinkingSound(@NotNull ItemStack itemStack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDrinkingSound'");
    }

    @Override
    public @NotNull Sound getEatingSound(@NotNull ItemStack itemStack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEatingSound'");
    }

    @Override
    public boolean canBreatheUnderwater() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBreatheUnderwater'");
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
    }

    @Override
    public float getSidewaysMovement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSidewaysMovement'");
    }

    @Override
    public float getUpwardsMovement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUpwardsMovement'");
    }

    @Override
    public float getForwardsMovement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getForwardsMovement'");
    }

    @Override
    public void startUsingItem(@NotNull EquipmentSlot hand) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startUsingItem'");
    }

    @Override
    public void completeUsingActiveItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'completeUsingActiveItem'");
    }

    @Override
    public @NotNull ItemStack getActiveItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveItem'");
    }

    @Override
    public void clearActiveItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearActiveItem'");
    }

    @Override
    public int getActiveItemRemainingTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveItemRemainingTime'");
    }

    @Override
    public void setActiveItemRemainingTime(@Range(from = 0, to = 2147483647) int ticks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setActiveItemRemainingTime'");
    }

    @Override
    public boolean hasActiveItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasActiveItem'");
    }

    @Override
    public int getActiveItemUsedTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveItemUsedTime'");
    }

    @Override
    public @NotNull EquipmentSlot getActiveItemHand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveItemHand'");
    }

    @Override
    public boolean isJumping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isJumping'");
    }

    @Override
    public void setJumping(boolean jumping) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setJumping'");
    }

    @Override
    public void playPickupItemAnimation(@NotNull Item item, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playPickupItemAnimation'");
    }

    @Override
    public float getHurtDirection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHurtDirection'");
    }

    @Override
    public void setHurtDirection(float hurtDirection) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHurtDirection'");
    }

    @Override
    public void knockback(double strength, double directionX, double directionZ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'knockback'");
    }

    @Override
    public void broadcastSlotBreak(@NotNull EquipmentSlot slot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'broadcastSlotBreak'");
    }

    @Override
    public void broadcastSlotBreak(@NotNull EquipmentSlot slot, @NotNull Collection<Player> players) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'broadcastSlotBreak'");
    }

    @Override
    public @NotNull ItemStack damageItemStack(@NotNull ItemStack stack, int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'damageItemStack'");
    }

    @Override
    public void damageItemStack(@NotNull EquipmentSlot slot, int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'damageItemStack'");
    }

    @Override
    public float getBodyYaw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBodyYaw'");
    }

    @Override
    public void setBodyYaw(float bodyYaw) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBodyYaw'");
    }

    @Override
    public boolean canUseEquipmentSlot(@NotNull EquipmentSlot slot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canUseEquipmentSlot'");
    }

    @Override
    public @NotNull CombatTracker getCombatTracker() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCombatTracker'");
    }

    @Override
    public void setWaypointStyle(@Nullable Key key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWaypointStyle'");
    }

    @Override
    public void setWaypointColor(@Nullable Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWaypointColor'");
    }

    @Override
    public @NotNull Key getWaypointStyle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWaypointStyle'");
    }

    @Override
    public @Nullable Color getWaypointColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWaypointColor'");
    }}
