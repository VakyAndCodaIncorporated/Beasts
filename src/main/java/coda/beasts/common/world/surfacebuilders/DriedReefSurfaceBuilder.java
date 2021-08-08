package coda.beasts.common.world.surfacebuilders;

import coda.beasts.init.BeastsSurfaceBuilders;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class DriedReefSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public DriedReefSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232129_1_) {
        super(p_i232129_1_);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noise >= -0.4 && noise <= 0.4) {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, BeastsSurfaceBuilders.STONE_CONFIG);

            for (int i = startHeight; i < startHeight + 3; i++) {
                BlockPos pos = new BlockPos(x, i, z);
                chunkIn.setBlockState(pos, Blocks.STONE.defaultBlockState(), false);
            }

            if (noise >= -0.2 && noise <= 0.325) {
                for (int i = startHeight; i < startHeight + 4; i++) {
                    BlockPos pos = new BlockPos(x, i, z);
                    chunkIn.setBlockState(pos, Blocks.STONE.defaultBlockState(), false);
                }
            }
        }
        else {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, BeastsSurfaceBuilders.SAND_CONFIG);
        }
    }
}
