package dev.sterner.culturaldelights.common.registry;

import dev.sterner.culturaldelights.CulturalDelights;
import dev.sterner.culturaldelights.common.block.*;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class CDObjects {
    public static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();



    public static final Item AVOCADO = register("avocado", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.AVOCADO));
    public static final Item CUT_AVOCADO = register("cut_avocado", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CUT_AVOCADO, CDFoodComponents.CUT_AVOCADO_CONSUMABLE));
    public static final Item CUCUMBER = register("cucumber", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CUCUMBER));
    public static final Item PICKLE = register("pickle", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.PICKLE));
    public static final Item CUT_CUCUMBER = register("cut_cucumber", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CUT_CUCUMBER, CDFoodComponents.CUT_CUCUMBER_CONSUMABLE));
    public static final Item CUT_PICKLE = register("cut_pickle", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CUT_PICKLE, CDFoodComponents.CUT_PICKLE_CONSUMABLE));
    public static final Item EGGPLANT = register("eggplant", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.EGGPLANT));
    public static final Item CUT_EGGPLANT = register("cut_eggplant", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CUT_EGGPLANT, CDFoodComponents.CUT_EGGPLANT_CONSUMABLE));
    public static final Item SMOKED_EGGPLANT = register("smoked_eggplant",SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SMOKED_EGGPLANT));
    public static final Item SMOKED_TOMATO = register("smoked_tomato", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SMOKED_TOMATO));
    public static final Item SMOKED_CORN = register("smoked_corn", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SMOKED_CORN));
    public static final Item SMOKED_CUT_EGGPLANT = register("smoked_cut_eggplant", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SMOKED_CUT_EGGPLANT, CDFoodComponents.SMOKED_CUT_EGGPLANT_CONSUMABLE));
    public static final Item SMOKED_WHITE_EGGPLANT = register("smoked_white_eggplant", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SMOKED_WHITE_EGGPLANT));
    public static final Item WHITE_EGGPLANT = register("white_eggplant", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.WHITE_EGGPLANT));
    public static final Item CORN_COB = register("corn_cob", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CORN_COB));
    public static final Item SQUID = register("squid", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SQUID));
    public static final Item COOKED_SQUID = register("cooked_squid", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.COOKED_SQUID));
    public static final Item GLOW_SQUID = register("glow_squid", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.GLOW_SQUID, CDFoodComponents.GLOW_SQUID_CONSUMABLE));
    public static final Item RAW_CALAMARI = register("raw_calamari", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.RAW_CALAMARI));
    public static final Item COOKED_CALAMARI = register("cooked_calamari", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.COOKED_CALAMARI));

    public static final Item POPCORN = register("popcorn", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.POPCORN, CDFoodComponents.POPCORN_CONSUMABLE));
    public static final Item CORN_DOUGH = register("corn_dough", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CORN_DOUGH));
    public static final Item TORTILLA = register("tortilla", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.TORTILLA));
    public static final Item TORTILLA_CHIPS = register("tortilla_chips", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.TORTILLA_CHIPS, CDFoodComponents.TORTILLA_CHIPS_CONSUMABLE));
    public static final Item ELOTE = register("elote", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.ELOTE));
    public static final Item HEARTY_SALAD = register("hearty_salad", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.HEARTY_SALAD, CDFoodComponents.HEARTY_SALAD_CONSUMABLE).usingConvertsTo(Items.BOWL));
    public static final Item BEEF_BURRITO = register("beef_burrito", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.BEEF_BURRITO));
    public static final Item MUTTON_SANDWICH = register("mutton_sandwich", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.MUTTON_SANDWICH));
    public static final Item FRIED_EGGPLANT_PASTA = register("fried_eggplant_pasta", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.FRIED_EGGPLANT_PASTA, CDFoodComponents.FRIED_EGGPLANT_PASTA_CONSUMABLE).usingConvertsTo(Items.BOWL));
    public static final Item EGGPLANT_BURGER = register("eggplant_burger", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.EGGPLANT_BURGER));
    public static final Item AVOCADO_TOAST = register("avocado_toast", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.AVOCADO_TOAST));
    public static final Item CREAMED_CORN = register("creamed_corn", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CREAMED_CORN).craftRemainder(Items.BOWL));
    public static final Item CHICKEN_TACO = register("chicken_taco", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CHICKEN_TACO));
    public static final Item SPICY_CURRY = register("spicy_curry", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.SPICY_CURRY,CDFoodComponents.SPICY_CURRY_CONSUMABLE).craftRemainder(Items.BOWL));
    public static final Item PORK_WRAP = register("pork_wrap", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.PORK_WRAP));
    public static final Item FISH_TACO = register("fish_taco", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.FISH_TACO));
    public static final Item MIDORI_ROLL = register("midori_roll", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.MIDORI_ROLL));
    public static final Item MIDORI_ROLL_SLICE = register("midori_roll_slice", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.MIDORI_ROLL_SLICE, CDFoodComponents.MIDORI_ROLL_SLICE_CONSUMABLE));
    public static final Item EGG_ROLL = register("egg_roll", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.EGG_ROLL, CDFoodComponents.EGG_ROLL_CONSUMABLE));
    public static final Item CHICKEN_ROLL = register("chicken_roll", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CHICKEN_ROLL));
    public static final Item CHICKEN_ROLL_SLICE = register("chicken_roll_slice", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CHICKEN_ROLL_SLICE, CDFoodComponents.CHICKEN_ROLL_SLICE_CONSUMABLE));
    public static final Item PUFFERFISH_ROLL = register("pufferfish_roll", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.PUFFERFISH_ROLL, CDFoodComponents.PUFFERFISH_ROLL_CONSUMABLE));
    public static final Item TROPICAL_ROLL = register("tropical_roll", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.TROPICAL_ROLL, CDFoodComponents.TROPICAL_ROLL_CONSUMABLE));
    public static final Item RICE_BALL = register("rice_ball", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.RICE_BALL, CDFoodComponents.RICE_BALL_CONSUMABLE));
    public static final Item CALAMARI_ROLL = register("calamari_roll", SimplePolymerItem::new, new Item.Properties().food(CDFoodComponents.CALAMARI_ROLL, CDFoodComponents.CALAMARI_ROLL_CONSUMABLE));

    public static final Block AVOCADO_BUNDLE = register("avocado_bundle", settings -> new PolymerBlock(settings, "avocado_bundle"), BlockBehaviour.Properties.ofFullCopy(Blocks.PUMPKIN), true);
    public static final Block WILD_CUCUMBERS = register("wild_cucumbers", settings -> new PolymerWildCucumberBlock(MobEffects.GLOWING, 100, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS), true);
    public static final Block WILD_CORN = register("wild_corn", settings -> new PolymerWildCornBlock(MobEffects.GLOWING, 100, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS), true);
    public static final Block WILD_EGGPLANTS = register("wild_eggplants", settings -> new PolymerWildEggPlantsBlock(MobEffects.GLOWING, 100, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS), true);

    public static final Block AVOCADO_LOG = register("avocado_log", settings -> new PolymerPillarBlock(settings, "avocado_log"), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LOG), true);
    public static final Block AVOCADO_WOOD = register("avocado_wood", settings -> new PolymerPillarBlock(settings, "avocado_wood"), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD), true);
    public static final Block AVOCADO_LEAVES = register("avocado_leaves", settings -> new PolymerLeavesBlock(settings, "avocado_leaves"), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES), true);

     public static final Block AVOCADO_SAPLING = register("avocado_sapling", settings -> new PolymerSaplingBlock(CDWorldGenerators.AVOCADO_SAPLING_GENERATOR, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);


    public static final Block CUCUMBER_CROP = register("cucumbers", CucumbersBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT), false);
    public static final Block EGGPLANT_CROP = register("eggplants", EggplantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT), false);

    public static final Block CORN_CROP = register("corn", CornBlock::new, BlockBehaviour.Properties.of().mapColor((state) -> (Integer)state.getValue(CornBlock.AGE) >= 2 ? MapColor.COLOR_YELLOW : MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY), false);
    public static final Block CORN_UPPER = register("corn_upper", CornUpperBlock::new, BlockBehaviour.Properties.of().mapColor((state) -> (Integer)state.getValue(CornUpperBlock.CORN_AGE) >= 2 ? MapColor.COLOR_YELLOW : MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY), false);

    public static final Block AVOCADO_PIT = register("avocado_pit", settings -> new AvocadoPitBlock(CDWorldGenerators.AVOCADO_PIT_GENERATOR, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);

    public static final Block AVOCADO_CRATE = register("avocado_crate", settings -> new PolymerBlock(settings, "avocado_crate"), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block CUCUMBER_CRATE = register("cucumber_crate", settings -> new PolymerBlock(settings, "cucumber_crate"), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block PICKLE_CRATE = register("pickle_crate", settings -> new PolymerBlock(settings, "pickle_crate"), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block CORN_COB_CRATE = register("corn_cob_crate", settings -> new PolymerBlock(settings, "corn_cob_crate"), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final Block EGGPLANT_CRATE = register("eggplant_crate", settings -> new PolymerBlock(settings, "eggplant_crate"), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);


    public static final Block EXOTIC_ROLL_MEDLEY = register("exotic_roll_medley", settings -> new ExoticRollMedleyBlock(CDObjects.TROPICAL_ROLL, true, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), true);

    public static final Item CUCUMBER_SEEDS = register("cucumber_seeds", setting -> new ModPolymerBlockItem(CUCUMBER_CROP, setting), new Item.Properties());
    public static final Item CORN_KERNELS = register("corn_kernels", setting -> new ModPolymerBlockItem(CORN_CROP, setting), new Item.Properties());
    public static final Item EGGPLANT_SEEDS = register("eggplant_seeds", setting -> new ModPolymerBlockItem(EGGPLANT_CROP, setting), new Item.Properties());

    private static Item.Properties settings() {
        return new Item.Properties();
    }


    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, name));

        Item item = itemFactory.apply(settings.setId(itemKey));

        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new ModPolymerBlockItem(block, new Item.Properties().setId(itemKey));
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, name));
    }

    public static void RegisterItemGroup() {
            CreativeModeTab ITEM_GROUP = PolymerItemGroupUtils.builder()
                        .title(Component.translatable("itemGroup.culturaldelights"))
                        .icon(() -> new ItemStack(PICKLE))
                        .displayItems((displayContext, entries) -> {

                            entries.accept(AVOCADO);
                            entries.accept(CUT_AVOCADO);
                            entries.accept(CUCUMBER);
                            entries.accept(PICKLE);
                            entries.accept(CUT_CUCUMBER);
                            entries.accept(CUT_PICKLE);
                            entries.accept(EGGPLANT);
                            entries.accept(CUT_EGGPLANT);
                            entries.accept(SMOKED_EGGPLANT);
                            entries.accept(SMOKED_TOMATO);
                            entries.accept(SMOKED_CORN);
                            entries.accept(SMOKED_CUT_EGGPLANT);
                            entries.accept(SMOKED_WHITE_EGGPLANT);
                            entries.accept(WHITE_EGGPLANT);
                            entries.accept(CORN_COB);
                            entries.accept(SQUID);
                            entries.accept(COOKED_SQUID);
                            entries.accept(GLOW_SQUID);
                            entries.accept(RAW_CALAMARI);
                            entries.accept(COOKED_CALAMARI);
                            entries.accept(POPCORN);
                            entries.accept(CORN_DOUGH);
                            entries.accept(TORTILLA);
                            entries.accept(TORTILLA_CHIPS);
                            entries.accept(ELOTE);
                            entries.accept(HEARTY_SALAD);
                            entries.accept(BEEF_BURRITO);
                            entries.accept(MUTTON_SANDWICH);
                            entries.accept(FRIED_EGGPLANT_PASTA);
                            entries.accept(EGGPLANT_BURGER);
                            entries.accept(AVOCADO_TOAST);
                            entries.accept(CREAMED_CORN);
                            entries.accept(CHICKEN_TACO);
                            entries.accept(SPICY_CURRY);
                            entries.accept(PORK_WRAP);
                            entries.accept(FISH_TACO);
                            entries.accept(MIDORI_ROLL);
                            entries.accept(MIDORI_ROLL_SLICE);
                            entries.accept(EGG_ROLL);
                            entries.accept(CHICKEN_ROLL);
                            entries.accept(CHICKEN_ROLL_SLICE);
                            entries.accept(PUFFERFISH_ROLL);
                            entries.accept(TROPICAL_ROLL);
                            entries.accept(RICE_BALL);
                            entries.accept(CALAMARI_ROLL);

                            entries.accept(AVOCADO_BUNDLE.asItem());
                            entries.accept(WILD_CUCUMBERS.asItem());
                            entries.accept(WILD_CORN.asItem());
                            entries.accept(WILD_EGGPLANTS.asItem());

                            entries.accept(AVOCADO_LOG.asItem());
                            entries.accept(AVOCADO_WOOD.asItem());
                            entries.accept(AVOCADO_LEAVES);
                            entries.accept(AVOCADO_SAPLING.asItem());
                            entries.accept(AVOCADO_PIT.asItem());

                            entries.accept(AVOCADO_CRATE.asItem());
                            entries.accept(CUCUMBER_CRATE.asItem());
                            entries.accept(PICKLE_CRATE.asItem());
                            entries.accept(CORN_COB_CRATE.asItem());
                            entries.accept(EGGPLANT_CRATE.asItem());

                            entries.accept(EXOTIC_ROLL_MEDLEY.asItem());
                            entries.accept(CUCUMBER_SEEDS.asItem());
                            entries.accept(CORN_KERNELS.asItem());
                            entries.accept(EGGPLANT_SEEDS.asItem());



                        })
                        .build();


        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.fromNamespaceAndPath(CulturalDelights.MOD_ID, "item_group"), ITEM_GROUP);
    }




    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(BuiltInRegistries.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(BuiltInRegistries.ITEM, ITEMS.get(item), item));

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
