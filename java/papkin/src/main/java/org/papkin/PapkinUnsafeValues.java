package org.papkin;

import com.google.common.collect.Multimap;
import com.google.gson.JsonObject;
import io.papermc.paper.entity.EntitySerializationFlag;
import io.papermc.paper.inventory.tooltip.TooltipContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.flattener.ComponentFlattener;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@SuppressWarnings("removal")
public class PapkinUnsafeValues implements UnsafeValues {

    public static final PapkinUnsafeValues INSTANCE = new PapkinUnsafeValues();

    // These are commonly used - implement first
    @Override
    public int getDataVersion() {
        return 4189; // 1.21.4 data version - match your server
    }

    @Override
    public ItemStack modifyItemStack(ItemStack stack, String arguments) {
        return null;
    }

    @Override
    public boolean isSupportedApiVersion(String apiVersion) {
        // Accept common API versions
        return (
            apiVersion != null &&
            (apiVersion.equals("1.21") || apiVersion.equals("1.20"))
        );
    }

    @Override
    public byte[] serializeItem(ItemStack item) {
        return new byte[0];
    }

    @Override
    public ItemStack deserializeItem(byte[] data) {
        return null;
    }

    @Override
    public @NotNull JsonObject serializeItemAsJson(@NotNull ItemStack itemStack) {
        return null;
    }

    @Override
    public @NotNull ItemStack deserializeItemFromJson(@NotNull JsonObject data) throws IllegalArgumentException {
        return null;
    }

    @Override
    public byte @NotNull [] serializeEntity(@NotNull Entity entity, @NonNull @NotNull EntitySerializationFlag... serializationFlags) {
        return new byte[0];
    }

    @Override
    public @NotNull Entity deserializeEntity(byte @NotNull [] data, @NotNull World world, boolean preserveUUID, boolean preservePassengers) {
        return null;
    }

    @Override
    public int nextEntityId() {
        return 0;
    }

    @Override
    public @NotNull String getMainLevelName() {
        return "";
    }

    @Override
    public int getProtocolVersion() {
        return 0;
    }

    @Override
    public boolean isValidRepairItemStack(@NotNull ItemStack itemToBeRepaired, @NotNull ItemStack repairMaterial) {
        return false;
    }

    @Override
    public boolean hasDefaultEntityAttributes(@NotNull NamespacedKey entityKey) {
        return false;
    }

    @Override
    public @NotNull Attributable getDefaultEntityAttributes(@NotNull NamespacedKey entityKey) {
        return null;
    }

    @Override
    public @NotNull NamespacedKey getBiomeKey(RegionAccessor accessor, int x, int y, int z) {
        return null;
    }

    @Override
    public void setBiomeKey(RegionAccessor accessor, int x, int y, int z, NamespacedKey biomeKey) {

    }

    @Override
    public String getStatisticCriteriaKey(@NotNull Statistic statistic) {
        return "";
    }

    @Override
    public @Nullable Color getSpawnEggLayerColor(EntityType entityType, int layer) {
        return null;
    }

    @Override
    public LifecycleEventManager<Plugin> createPluginLifecycleEventManager(JavaPlugin plugin, BooleanSupplier registrationCheck) {
        return null;
    }

    @Override
    public @NotNull List<Component> computeTooltipLines(@NotNull ItemStack itemStack, @NotNull TooltipContext tooltipContext, @Nullable Player player) {
        return List.of();
    }

    @Override
    public ItemStack createEmptyStack() {
        return null;
    }

    @Override
    public @NotNull Map<String, Object> serializeStack(ItemStack itemStack) {
        return Map.of();
    }

    @Override
    public @NotNull ItemStack deserializeStack(@NotNull Map<String, Object> args) {
        return null;
    }

    @Override
    public @NotNull ItemStack deserializeItemHover(HoverEvent.@NotNull ShowItem itemHover) {
        return null;
    }

    @Override
    public void checkSupported(PluginDescriptionFile pdf) throws InvalidPluginException {
        // Be lenient during development - accept most plugins
        String api = pdf.getAPIVersion();
        if (api != null && !isSupportedApiVersion(api)) {
            throw new InvalidPluginException("Unsupported API: " + api);
        }
    }

    @Override
    public byte[] processClass(
        PluginDescriptionFile pdf,
        String path,
        byte[] clazz
    ) {
        // Return unchanged - skip Commodore remapping for now
        return clazz;
    }

    @Override
    public Advancement loadAdvancement(NamespacedKey key, String advancement) {
        return null;
    }

    @Override
    public boolean removeAdvancement(NamespacedKey key) {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(Material material, EquipmentSlot slot) {
        return null;
    }

    @Override
    public CreativeCategory getCreativeCategory(Material material) {
        return null;
    }

    @Override
    public String getBlockTranslationKey(Material material) {
        return "";
    }

    @Override
    public String getItemTranslationKey(Material material) {
        return "";
    }

    @Override
    public String getTranslationKey(EntityType entityType) {
        return "";
    }

    @Override
    public String getTranslationKey(ItemStack itemStack) {
        return "";
    }

    @Override
    public String getTranslationKey(Attribute attribute) {
        return "";
    }

    @Override
    public PotionType.InternalPotionData getInternalPotionData(NamespacedKey key) {
        return null;
    }

    @Override
    public DamageSource.@NotNull Builder createDamageSourceBuilder(@NotNull DamageType damageType) {
        return null;
    }

    @Override
    public String get(Class<?> aClass, String value) {
        return "";
    }

    @Override
    public <B extends Keyed> B get(RegistryKey<B> registry, NamespacedKey key) {
        return null;
    }

    // Adventure components - commonly used
    @Override
    public ComponentFlattener componentFlattener() {
        return ComponentFlattener.basic();
    }

    @Override
    public PlainComponentSerializer plainComponentSerializer() {
        return null;
    }

    @Override
    public PlainTextComponentSerializer plainTextSerializer() {
        return null;
    }

    @Override
    public GsonComponentSerializer gsonComponentSerializer() {
        return null;
    }

    @Override
    public GsonComponentSerializer colorDownsamplingGsonComponentSerializer() {
        return null;
    }

    @Override
    public LegacyComponentSerializer legacyComponentSerializer() {
        return null;
    }

    @Override
    public Component resolveWithContext(Component component, CommandSender context, Entity scoreboardSubject, boolean bypassPermissions) throws IOException {
        return null;
    }

    // Stub everything else
    @Override
    public Material toLegacy(Material material) {
        throw new UnsupportedOperationException(
            "Pumpkin doesn't support legacy materials"
        );
    }

    @Override
    public Material fromLegacy(Material material) {
        return null;
    }

    @Override
    public Material fromLegacy(MaterialData material) {
        return null;
    }

    @Override
    public Material fromLegacy(MaterialData material, boolean itemPriority) {
        return null;
    }

    @Override
    public BlockData fromLegacy(Material material, byte data) {
        return null;
    }

    @Override
    public Material getMaterial(String material, int version) {
        return null;
    }

    // ... etc
}
