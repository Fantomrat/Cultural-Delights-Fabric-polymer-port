package dev.sterner.culturaldelights.common.registry;

import dev.sterner.culturaldelights.CulturalDelights;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public enum CDConfiguredFeatures {
    PATCH_WILD_CORN("patch_wild_corn"),
    PATCH_WILD_CUCUMBERS("patch_wild_cucumbers"),
    PATCH_WILD_EGGPLANTS("patch_wild_eggplants");

    private final Identifier featureIdentifier;
    private ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey;
    private ResourceKey<PlacedFeature> featureRegistryKey;

    CDConfiguredFeatures(String featurePathName) {
        this.featureIdentifier = Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, featurePathName);
    }

    public static void registerAll() {
        for (CDConfiguredFeatures value : values()) {
            value.configuredFeatureRegistryKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, value.featureIdentifier);
            value.featureRegistryKey = ResourceKey.create(Registries.PLACED_FEATURE, value.featureIdentifier);
        }
    }

    public ResourceKey<ConfiguredFeature<? extends FeatureConfiguration, ?>> configKey() {
        return configuredFeatureRegistryKey;
    }

    public ResourceKey<PlacedFeature> key() {
        return featureRegistryKey;
    }

    public Identifier identifier() {
        return featureIdentifier;
    }
}
