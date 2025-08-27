package dev.sterner.culturaldelights;

import com.mojang.serialization.MapCodec;
import dev.sterner.culturaldelights.common.block.*;
import dev.sterner.culturaldelights.common.registry.CDConfiguredFeatures;
import dev.sterner.culturaldelights.common.registry.CDObjects;
import dev.sterner.culturaldelights.common.registry.CDWorldGenerators;
import dev.sterner.culturaldelights.common.utils.Constants;
import dev.sterner.culturaldelights.common.world.AvocadoBundleTreeDecorator;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.extras.api.ResourcePackExtras;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CulturalDelights implements ModInitializer {
	public static final String MOD_ID = "culturaldelights";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Identifier SQUID_LOOT_TABLE_ID = EntityType.SQUID.getLootTableKey().get().getValue();
	private static final Identifier GLOW_SQUID_LOOT_TABLE_ID = EntityType.GLOW_SQUID.getLootTableKey().get().getValue();

	public static final TreeDecoratorType<AvocadoBundleTreeDecorator> AVOCADO_BUNDLE_TREE_DECORATOR_TYPE = register(Constants.id("avocado_bundle"), AvocadoBundleTreeDecorator.CODEC);

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(Identifier id, MapCodec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, id, new TreeDecoratorType<>(codec));
	}

	@Override
	public void onInitialize() {
		CDObjects.init();

		CDConfiguredFeatures.registerAll();
		CDWorldGenerators.init();

		BiomeModifications.addFeature(context -> context.getBiomeKey().equals(BiomeKeys.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION,
				CDConfiguredFeatures.PATCH_WILD_CORN.key());
		BiomeModifications.addFeature(context -> context.getBiomeKey().equals(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION,
				CDConfiguredFeatures.PATCH_WILD_EGGPLANTS.key());
		BiomeModifications.addFeature(context -> context.getBiomeKey().equals(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION,
				CDConfiguredFeatures.PATCH_WILD_CUCUMBERS.key());
		BiomeModifications.addFeature(context -> context.getBiomeKey().equals(BiomeKeys.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION,
				CDConfiguredFeatures.PATCH_WILD_EGGPLANTS.key());

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
			factories.add(new EmeraldToItemOffer(new ItemStack(CDObjects.CUCUMBER), 1, 10, 2, 0.2F));
			factories.add(new EmeraldToItemOffer(new ItemStack(CDObjects.EGGPLANT), 1, 10, 2, 0.2F));
			factories.add(new EmeraldToItemOffer(new ItemStack(CDObjects.CORN_COB), 1, 10, 2, 0.2F));
			factories.add(new EmeraldToItemOffer(new ItemStack(CDObjects.AVOCADO), 1, 10, 2, 0.2F));
		});


		LootTableEvents.MODIFY.register(((registryKey, builder, source) -> {
			if (source.isBuiltin()) {
                Identifier id = registryKey.getValue();
                if (SQUID_LOOT_TABLE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(CDObjects.SQUID));
                    builder.pool(poolBuilder);
                }
                if (GLOW_SQUID_LOOT_TABLE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(CDObjects.GLOW_SQUID));
                    builder.pool(poolBuilder);
                }
            }
		}));

        if (PolymerResourcePackUtils.addModAssets(MOD_ID)) {
            ResourcePackExtras.forDefault().addBridgedModelsFolder(Identifier.of(MOD_ID, "block"), Identifier.of(MOD_ID, "item"));
            LOGGER.info("Successfully added mod assets for " + MOD_ID);
        } else {
            LOGGER.error("Failed to add mod assets for " + MOD_ID);
        }
        initModels();
        PolymerResourcePackUtils.markAsRequired();


	}

	public static class EmeraldToItemOffer implements TradeOffers.Factory {

		private final ItemStack sell;
		private final int price;
		private final int maxUses;
		private final int experience;
		private final float multiplier;

		public EmeraldToItemOffer(ItemStack stack, int price, int maxUses, int experience, float multiplier) {
			this.sell = stack;
			this.price = price;
			this.maxUses = maxUses;
			this.experience = experience;
			this.multiplier = multiplier;
		}

		public TradeOffer create(Entity entity, Random random) {
			return new TradeOffer(new TradedItem(Items.EMERALD, this.price + random.nextInt(3)), sell, this.maxUses, this.experience, this.multiplier);
		}

	}

    public void initModels(){
        CornBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        CornUpperBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        CucumbersBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        EggplantBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        ExoticRollMedleyBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        AvocadoPitBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        PolymerSaplingBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        PolymerWildCucumberBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        PolymerWildCornBlock.Model.MODELS.forEach(ItemStack::isEmpty);
        PolymerWildEggPlantsBlock.Model.MODELS.forEach(ItemStack::isEmpty);
    }

}
