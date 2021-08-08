package coda.beasts.init;

import coda.beasts.Beasts;
import coda.beasts.common.world.surfacebuilders.DriedReefSurfaceBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BeastsSurfaceBuilders {
    private static final BlockState SAND = Blocks.SAND.defaultBlockState();
    private static final BlockState STONE = Blocks.STONE.defaultBlockState();
    private static final BlockState SANDSTONE = Blocks.SANDSTONE.defaultBlockState();

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDER = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Beasts.MOD_ID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> REEF = SURFACE_BUILDER.register("reef", () -> new DriedReefSurfaceBuilder(SurfaceBuilderConfig.CODEC));

    public static final SurfaceBuilderConfig SAND_CONFIG = new SurfaceBuilderConfig(SAND, SANDSTONE, SAND);
    public static final SurfaceBuilderConfig STONE_CONFIG = new SurfaceBuilderConfig(STONE, STONE, STONE);
}
