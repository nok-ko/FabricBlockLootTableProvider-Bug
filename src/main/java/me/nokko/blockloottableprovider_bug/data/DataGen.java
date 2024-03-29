package me.nokko.blockloottableprovider_bug.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import static me.nokko.blockloottableprovider_bug.ExampleContent.*;

public class DataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BlockLootTableProvider::new);
    }

    private class BlockLootTableProvider extends FabricBlockLootTableProvider {
        @Override
        public void generate() {
            dropSelf(SIMPLE_BLOCK);
            dropOther(BLOCK_WITHOUT_ITEM, SIMPLE_BLOCK);

            excludeFromStrictValidation(BLOCK_WITHOUT_LOOT_TABLE);
        }
    }
}
