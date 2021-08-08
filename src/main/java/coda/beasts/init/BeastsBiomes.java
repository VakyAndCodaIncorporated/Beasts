package coda.beasts.init;

import coda.beasts.Beasts;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BeastsBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Beasts.MOD_ID);
    
    static {
        driedReef("dried_reef", BiomeMaker::theVoidBiome);
    }
    
    public static RegistryObject<Biome> driedReef(String name, Supplier<Biome> biome) {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Beasts.MOD_ID, name)), 1));
        return BIOMES.register(name, biome);
    }
}
