package dev.sterner.culturaldelights.common.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

import java.util.Optional;

import static dev.sterner.culturaldelights.CulturalDelights.MOD_ID;

public class CDWorldGenerators {

    public static final RegistryKey<ConfiguredFeature<?, ?>> AVOCADO = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MOD_ID, "avocado"));
    public static final RegistryKey<PlacedFeature> TREE_AVOCADO = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "tree_avocado"));

    public static final RegistryKey<ConfiguredFeature<?, ?>> AVOCADO_PIT = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MOD_ID, "avocado_pit"));
    public static final RegistryKey<PlacedFeature> TREE_AVOCADO_PIT = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "tree_avocado_pit"));

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, TREE_AVOCADO);
    }

    public static final SaplingGenerator AVOCADO_SAPLING_GENERATOR = new SaplingGenerator(
            "avocado",
            Optional.empty(),
            Optional.of(AVOCADO),
            Optional.empty()
    );

    public static final SaplingGenerator AVOCADO_PIT_GENERATOR = new SaplingGenerator(
            "avocado_pit",
            Optional.empty(),
            Optional.of(AVOCADO_PIT),
            Optional.empty()
    );




}
