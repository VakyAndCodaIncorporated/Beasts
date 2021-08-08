package coda.beasts.init;

import coda.beasts.Beasts;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BeastsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Beasts.MOD_ID);

    public static final RegistryObject<Block> ANEMONE_STALK = register("anemone_stalk", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_BLUE).strength(1.0F).harvestTool(ToolType.AXE).sound(SoundType.WET_GRASS)), new Item.Properties());
    public static final RegistryObject<Block> ANEMONE_TENTACLE = register("anemone_tentacle", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK).strength(1.0F).harvestTool(ToolType.AXE).sound(SoundType.WET_GRASS)), new Item.Properties());

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = BLOCKS.register(name, block);
        if (itemProperties != null) BeastsItems.ITEMS.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties.tab(Beasts.GROUP)));
        return registryObject;
    }
}
