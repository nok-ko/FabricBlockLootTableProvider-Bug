package me.nokko.blockloottableprovider_bug;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Copied from the datagen testmod and converted to mojmaps
public class ExampleContent implements ModInitializer {
	public static final String MOD_ID = "fabric-data-gen-api-v1-testmod";

	public static Block SIMPLE_BLOCK;
	public static Block BLOCK_WITHOUT_ITEM;
	public static Block BLOCK_WITHOUT_LOOT_TABLE;

	public static final CreativeModeTab SIMPLE_ITEM_GROUP = FabricItemGroup.builder(new ResourceLocation(MOD_ID, "simple"))
			.icon(() -> new ItemStack(Items.DIAMOND_PICKAXE))
			.title(Component.literal("Data gen test"))
			.build();

	@Override
	public void onInitialize() {
		SIMPLE_BLOCK = createBlock("simple_block", true);
		BLOCK_WITHOUT_ITEM = createBlock("block_without_item", false);
		BLOCK_WITHOUT_LOOT_TABLE = createBlock("block_without_loot_table", false);

		ItemGroupEvents.modifyEntriesEvent(SIMPLE_ITEM_GROUP).register(entries -> entries.accept(SIMPLE_BLOCK));
	}

	private static Block createBlock(String name, boolean hasItem) {
		var identifier = new ResourceLocation(MOD_ID, name);
		Block block = Registry.register(BuiltInRegistries.BLOCK, identifier, new Block(BlockBehaviour.Properties.of(Material.STONE)));

		if (hasItem) {
			Registry.register(BuiltInRegistries.ITEM, identifier, new BlockItem(block, new Item.Properties()));
		}

		return block;
	}
}