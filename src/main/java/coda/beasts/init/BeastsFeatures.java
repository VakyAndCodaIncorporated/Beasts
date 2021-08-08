package coda.beasts.init;

import coda.beasts.Beasts;
import coda.beasts.common.world.feature.BeastsNbtStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BeastsFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Beasts.MOD_ID);

    public static final RegistryObject<BeastsNbtStructure> ANEMONE = FEATURES.register("anemone", () -> new BeastsNbtStructure(new ResourceLocation(Beasts.MOD_ID, "anemone")));
}
