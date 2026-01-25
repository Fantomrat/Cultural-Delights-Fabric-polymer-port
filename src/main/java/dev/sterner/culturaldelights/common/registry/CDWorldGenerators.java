package dev.sterner.culturaldelights.common.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.Optional;

import static dev.sterner.culturaldelights.CulturalDelights.MOD_ID;

public class CDWorldGenerators {

    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MOD_ID, "avocado"));
    public static final ResourceKey<PlacedFeature> TREE_AVOCADO = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(MOD_ID, "tree_avocado"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_PIT = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MOD_ID, "avocado_pit"));
    public static final ResourceKey<PlacedFeature> TREE_AVOCADO_PIT = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(MOD_ID, "tree_avocado_pit"));

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_JUNGLE), GenerationStep.Decoration.VEGETAL_DECORATION, TREE_AVOCADO);
    }

    public static final TreeGrower AVOCADO_SAPLING_GENERATOR = new TreeGrower(
            "avocado",
            Optional.empty(),
            Optional.of(AVOCADO),
            Optional.empty()
    );

    public static final TreeGrower AVOCADO_PIT_GENERATOR = new TreeGrower(
            "avocado_pit",
            Optional.empty(),
            Optional.of(AVOCADO_PIT),
            Optional.empty()
    );




}
