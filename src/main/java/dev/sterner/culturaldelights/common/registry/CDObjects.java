package dev.sterner.culturaldelights.common.registry;

import dev.sterner.culturaldelights.CulturalDelights;
import dev.sterner.culturaldelights.common.block.*;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class CDObjects {
    public static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();



    public static final Item AVOCADO = register("avocado", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.AVOCADO));
    public static final Item CUT_AVOCADO = register("cut_avocado", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CUT_AVOCADO, CDFoodComponents.CUT_AVOCADO_CONSUMABLE));
    public static final Item CUCUMBER = register("cucumber", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CUCUMBER));
    public static final Item PICKLE = register("pickle", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.PICKLE));
    public static final Item CUT_CUCUMBER = register("cut_cucumber", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CUT_CUCUMBER, CDFoodComponents.CUT_CUCUMBER_CONSUMABLE));
    public static final Item CUT_PICKLE = register("cut_pickle", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CUT_PICKLE, CDFoodComponents.CUT_PICKLE_CONSUMABLE));
    public static final Item EGGPLANT = register("eggplant", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.EGGPLANT));
    public static final Item CUT_EGGPLANT = register("cut_eggplant", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CUT_EGGPLANT, CDFoodComponents.CUT_EGGPLANT_CONSUMABLE));
    public static final Item SMOKED_EGGPLANT = register("smoked_eggplant",SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SMOKED_EGGPLANT));
    public static final Item SMOKED_TOMATO = register("smoked_tomato", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SMOKED_TOMATO));
    public static final Item SMOKED_CORN = register("smoked_corn", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SMOKED_CORN));
    public static final Item SMOKED_CUT_EGGPLANT = register("smoked_cut_eggplant", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SMOKED_CUT_EGGPLANT, CDFoodComponents.SMOKED_CUT_EGGPLANT_CONSUMABLE));
    public static final Item SMOKED_WHITE_EGGPLANT = register("smoked_white_eggplant", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SMOKED_WHITE_EGGPLANT));
    public static final Item WHITE_EGGPLANT = register("white_eggplant", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.WHITE_EGGPLANT));
    public static final Item CORN_COB = register("corn_cob", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CORN_COB));
    public static final Item SQUID = register("squid", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SQUID));
    public static final Item COOKED_SQUID = register("cooked_squid", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.COOKED_SQUID));
    public static final Item GLOW_SQUID = register("glow_squid", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.GLOW_SQUID, CDFoodComponents.GLOW_SQUID_CONSUMABLE));
    public static final Item RAW_CALAMARI = register("raw_calamari", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.RAW_CALAMARI));
    public static final Item COOKED_CALAMARI = register("cooked_calamari", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.COOKED_CALAMARI));

    public static final Item POPCORN = register("popcorn", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.POPCORN, CDFoodComponents.POPCORN_CONSUMABLE));
    public static final Item CORN_DOUGH = register("corn_dough", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CORN_DOUGH));
    public static final Item TORTILLA = register("tortilla", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.TORTILLA));
    public static final Item TORTILLA_CHIPS = register("tortilla_chips", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.TORTILLA_CHIPS, CDFoodComponents.TORTILLA_CHIPS_CONSUMABLE));
    public static final Item ELOTE = register("elote", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.ELOTE));
    public static final Item HEARTY_SALAD = register("hearty_salad", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.HEARTY_SALAD, CDFoodComponents.HEARTY_SALAD_CONSUMABLE).useRemainder(Items.BOWL));
    public static final Item BEEF_BURRITO = register("beef_burrito", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.BEEF_BURRITO));
    public static final Item MUTTON_SANDWICH = register("mutton_sandwich", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.MUTTON_SANDWICH));
    public static final Item FRIED_EGGPLANT_PASTA = register("fried_eggplant_pasta", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.FRIED_EGGPLANT_PASTA, CDFoodComponents.FRIED_EGGPLANT_PASTA_CONSUMABLE).useRemainder(Items.BOWL));
    public static final Item EGGPLANT_BURGER = register("eggplant_burger", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.EGGPLANT_BURGER));
    public static final Item AVOCADO_TOAST = register("avocado_toast", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.AVOCADO_TOAST));
    public static final Item CREAMED_CORN = register("creamed_corn", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CREAMED_CORN).recipeRemainder(Items.BOWL));
    public static final Item CHICKEN_TACO = register("chicken_taco", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CHICKEN_TACO));
    public static final Item SPICY_CURRY = register("spicy_curry", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.SPICY_CURRY,CDFoodComponents.SPICY_CURRY_CONSUMABLE).recipeRemainder(Items.BOWL));
    public static final Item PORK_WRAP = register("pork_wrap", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.PORK_WRAP));
    public static final Item FISH_TACO = register("fish_taco", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.FISH_TACO));
    public static final Item MIDORI_ROLL = register("midori_roll", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.MIDORI_ROLL));
    public static final Item MIDORI_ROLL_SLICE = register("midori_roll_slice", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.MIDORI_ROLL_SLICE, CDFoodComponents.MIDORI_ROLL_SLICE_CONSUMABLE));
    public static final Item EGG_ROLL = register("egg_roll", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.EGG_ROLL, CDFoodComponents.EGG_ROLL_CONSUMABLE));
    public static final Item CHICKEN_ROLL = register("chicken_roll", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CHICKEN_ROLL));
    public static final Item CHICKEN_ROLL_SLICE = register("chicken_roll_slice", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CHICKEN_ROLL_SLICE, CDFoodComponents.CHICKEN_ROLL_SLICE_CONSUMABLE));
    public static final Item PUFFERFISH_ROLL = register("pufferfish_roll", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.PUFFERFISH_ROLL, CDFoodComponents.PUFFERFISH_ROLL_CONSUMABLE));
    public static final Item TROPICAL_ROLL = register("tropical_roll", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.TROPICAL_ROLL, CDFoodComponents.TROPICAL_ROLL_CONSUMABLE));
    public static final Item RICE_BALL = register("rice_ball", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.RICE_BALL, CDFoodComponents.RICE_BALL_CONSUMABLE));
    public static final Item CALAMARI_ROLL = register("calamari_roll", SimplePolymerItem::new, new Item.Settings().food(CDFoodComponents.CALAMARI_ROLL, CDFoodComponents.CALAMARI_ROLL_CONSUMABLE));

    public static final Block AVOCADO_BUNDLE = register("avocado_bundle", settings -> new PolymerBlock(settings, "avocado_bundle"), AbstractBlock.Settings.copy(Blocks.PUMPKIN), true);
    public static final Block WILD_CUCUMBERS = register("wild_cucumbers", settings -> new PolymerWildCucumberBlock(StatusEffects.GLOWING, 100, settings), AbstractBlock.Settings.copy(Blocks.TALL_GRASS), true);
    public static final Block WILD_CORN = register("wild_corn", settings -> new PolymerWildCornBlock(StatusEffects.GLOWING, 100, settings), AbstractBlock.Settings.copy(Blocks.TALL_GRASS), true);
    public static final Block WILD_EGGPLANTS = register("wild_eggplants", settings -> new PolymerWildEggPlantsBlock(StatusEffects.GLOWING, 100, settings), AbstractBlock.Settings.copy(Blocks.TALL_GRASS), true);

    public static final Block AVOCADO_LOG = register("avocado_log", settings -> new PolymerPillarBlock(settings, "avocado_log"), AbstractBlock.Settings.copy(Blocks.JUNGLE_LOG), true);
    public static final Block AVOCADO_WOOD = register("avocado_wood", settings -> new PolymerPillarBlock(settings, "avocado_wood"), AbstractBlock.Settings.copy(Blocks.JUNGLE_WOOD), true);
    public static final Block AVOCADO_LEAVES = register("avocado_leaves", settings -> new PolymerLeavesBlock(settings, "avocado_leaves"), AbstractBlock.Settings.copy(Blocks.JUNGLE_LEAVES), true);

     public static final Block AVOCADO_SAPLING = register("avocado_sapling", settings -> new PolymerSaplingBlock(CDWorldGenerators.AVOCADO_SAPLING_GENERATOR, settings), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), true);


    public static final Block CUCUMBER_CROP = register("cucumbers", CucumbersBlock::new, AbstractBlock.Settings.copy(Blocks.WHEAT), false);
    public static final Block EGGPLANT_CROP = register("eggplants", EggplantBlock::new, AbstractBlock.Settings.copy(Blocks.WHEAT), false);

    public static final Block CORN_CROP = register("corn", CornBlock::new, AbstractBlock.Settings.create().mapColor((state) -> (Integer)state.get(CornBlock.AGE) >= 2 ? MapColor.YELLOW : MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY), false);
    public static final Block CORN_UPPER = register("corn_upper", CornUpperBlock::new, AbstractBlock.Settings.create().mapColor((state) -> (Integer)state.get(CornUpperBlock.CORN_AGE) >= 2 ? MapColor.YELLOW : MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY), false);

    public static final Block AVOCADO_PIT = register("avocado_pit", settings -> new AvocadoPitBlock(CDWorldGenerators.AVOCADO_PIT_GENERATOR, settings), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), true);

    public static final Block AVOCADO_CRATE = register("avocado_crate", settings -> new PolymerBlock(settings, "avocado_crate"), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);
    public static final Block CUCUMBER_CRATE = register("cucumber_crate", settings -> new PolymerBlock(settings, "cucumber_crate"), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);
    public static final Block PICKLE_CRATE = register("pickle_crate", settings -> new PolymerBlock(settings, "pickle_crate"), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);
    public static final Block CORN_COB_CRATE = register("corn_cob_crate", settings -> new PolymerBlock(settings, "corn_cob_crate"), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);
    public static final Block EGGPLANT_CRATE = register("eggplant_crate", settings -> new PolymerBlock(settings, "eggplant_crate"), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);


    public static final Block EXOTIC_ROLL_MEDLEY = register("exotic_roll_medley", settings -> new ExoticRollMedleyBlock(CDObjects.TROPICAL_ROLL, true, settings), AbstractBlock.Settings.copy(Blocks.CAKE), true);

    public static final Item CUCUMBER_SEEDS = register("cucumber_seeds", setting -> new ModPolymerBlockItem(CUCUMBER_CROP, setting), new Item.Settings());
    public static final Item CORN_KERNELS = register("corn_kernels", setting -> new ModPolymerBlockItem(CORN_CROP, setting), new Item.Settings());
    public static final Item EGGPLANT_SEEDS = register("eggplant_seeds", setting -> new ModPolymerBlockItem(EGGPLANT_CROP, setting), new Item.Settings());


    private static Item.Settings settings() {
        return new Item.Settings();
    }


    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CulturalDelights.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        return Registry.register(Registries.ITEM, itemKey, item);
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new ModPolymerBlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CulturalDelights.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CulturalDelights.MOD_ID, name));
    }

    public static void RegisterItemGroup() {
            ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
                Identifier.tryParse(CulturalDelights.MOD_ID + ":" + "item_group"),
                PolymerItemGroupUtils.builder()
                        .displayName(Text.translatable("itemGroup.culturaldelights"))
                        .icon(() -> new ItemStack(PICKLE))
                        .entries((displayContext, entries) -> {

                            entries.add(AVOCADO);
                            entries.add(CUT_AVOCADO);
                            entries.add(CUCUMBER);
                            entries.add(PICKLE);
                            entries.add(CUT_CUCUMBER);
                            entries.add(CUT_PICKLE);
                            entries.add(EGGPLANT);
                            entries.add(CUT_EGGPLANT);
                            entries.add(SMOKED_EGGPLANT);
                            entries.add(SMOKED_TOMATO);
                            entries.add(SMOKED_CORN);
                            entries.add(SMOKED_CUT_EGGPLANT);
                            entries.add(SMOKED_WHITE_EGGPLANT);
                            entries.add(WHITE_EGGPLANT);
                            entries.add(CORN_COB);
                            entries.add(SQUID);
                            entries.add(COOKED_SQUID);
                            entries.add(GLOW_SQUID);
                            entries.add(RAW_CALAMARI);
                            entries.add(COOKED_CALAMARI);
                            entries.add(POPCORN);
                            entries.add(CORN_DOUGH);
                            entries.add(TORTILLA);
                            entries.add(TORTILLA_CHIPS);
                            entries.add(ELOTE);
                            entries.add(HEARTY_SALAD);
                            entries.add(BEEF_BURRITO);
                            entries.add(MUTTON_SANDWICH);
                            entries.add(FRIED_EGGPLANT_PASTA);
                            entries.add(EGGPLANT_BURGER);
                            entries.add(AVOCADO_TOAST);
                            entries.add(CREAMED_CORN);
                            entries.add(CHICKEN_TACO);
                            entries.add(SPICY_CURRY);
                            entries.add(PORK_WRAP);
                            entries.add(FISH_TACO);
                            entries.add(MIDORI_ROLL);
                            entries.add(MIDORI_ROLL_SLICE);
                            entries.add(EGG_ROLL);
                            entries.add(CHICKEN_ROLL);
                            entries.add(CHICKEN_ROLL_SLICE);
                            entries.add(PUFFERFISH_ROLL);
                            entries.add(TROPICAL_ROLL);
                            entries.add(RICE_BALL);
                            entries.add(CALAMARI_ROLL);

                            entries.add(AVOCADO_BUNDLE.asItem());
                            entries.add(WILD_CUCUMBERS.asItem());
                            entries.add(WILD_CORN.asItem());
                            entries.add(WILD_EGGPLANTS.asItem());

                            entries.add(AVOCADO_LOG.asItem());
                            entries.add(AVOCADO_WOOD.asItem());
                            entries.add(AVOCADO_LEAVES);
                            entries.add(AVOCADO_SAPLING.asItem());
                            entries.add(AVOCADO_PIT.asItem());

                            entries.add(AVOCADO_CRATE.asItem());
                            entries.add(CUCUMBER_CRATE.asItem());
                            entries.add(PICKLE_CRATE.asItem());
                            entries.add(CORN_COB_CRATE.asItem());
                            entries.add(EGGPLANT_CRATE.asItem());

                            entries.add(EXOTIC_ROLL_MEDLEY.asItem());
                            entries.add(CUCUMBER_SEEDS.asItem());
                            entries.add(CORN_KERNELS.asItem());
                            entries.add(EGGPLANT_SEEDS.asItem());



                        })
                        .build());


        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(CulturalDelights.MOD_ID, "items"), ITEM_GROUP);
    }




    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));

        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(AVOCADO_LEAVES, 30, 60);
        flammableRegistry.add(AVOCADO_LOG, 5, 5);
        flammableRegistry.add(AVOCADO_WOOD, 5, 5);

        CompostingChanceRegistry compostRegistry = CompostingChanceRegistry.INSTANCE;
        compostRegistry.add(CUCUMBER_SEEDS, 0.3f);
        compostRegistry.add(CORN_KERNELS, 0.3f);
        compostRegistry.add(EGGPLANT_SEEDS, 0.3f);
        compostRegistry.add(AVOCADO_PIT, 0.3f);

        compostRegistry.add(CUT_CUCUMBER, 0.5f);
        compostRegistry.add(CUT_AVOCADO, 0.5f);
        compostRegistry.add(CUT_EGGPLANT, 0.5f);

        compostRegistry.add(AVOCADO, 0.65f);
        compostRegistry.add(CUCUMBER, 0.65f);
        compostRegistry.add(CORN_COB, 0.65f);
        compostRegistry.add(EGGPLANT, 0.65f);
        compostRegistry.add(WILD_CUCUMBERS, 0.65f);
        compostRegistry.add(WILD_CORN, 0.65f);
        compostRegistry.add(WILD_EGGPLANTS, 0.65f);
        compostRegistry.add(AVOCADO_LEAVES, 0.65f);
        compostRegistry.add(AVOCADO_SAPLING, 0.65f);

        compostRegistry.add(POPCORN, 0.85f);

        compostRegistry.add(AVOCADO_BUNDLE, 1f);

        StrippableBlockRegistry.register(AVOCADO_LOG, Blocks.STRIPPED_JUNGLE_LOG);
        StrippableBlockRegistry.register(AVOCADO_WOOD, Blocks.STRIPPED_JUNGLE_WOOD);

        RegisterItemGroup();
    }


}
