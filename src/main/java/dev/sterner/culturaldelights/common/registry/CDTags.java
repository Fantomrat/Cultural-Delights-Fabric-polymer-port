package dev.sterner.culturaldelights.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CDTags {
    public static final TagKey<Item> CORN = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "corn"));
}
