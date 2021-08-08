package coda.beasts.common.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HoodooFeature extends Feature<NoFeatureConfig> {

    public HoodooFeature() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
        if (seedReader.getBlockState(pos.below()).getBlock() == Blocks.STONE) {
            int height = random.nextInt(4) + 14;
            int width = random.nextInt(5) + 6;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j <= height; j++) {
                    double remove = 4 + Math.sin(j * -(Math.PI / (height + 8 + random.nextInt(2)))) * 2;
                    double remove2 = 2 + Math.cos(j *- (Math.PI / height + 8)) * 2;
                    for (int k = -width; k <= width; k++) {
                        for (int m = -width; m <= width; m++) {
                            for (int l = -5; l < 0; l++) {
                                if (k * k + m * m <= remove2 * remove2) {
                                    if (i == 1) {
                                        seedReader.setBlock(pos.offset(k, l, m), Blocks.STONE.defaultBlockState(), 2);
                                    }
                                }
                            }
                        }
                    }
                    for (int k = -width; k <= width; k++) {
                        for (int m = -width; m <= width; m++) {
                            if (k * k + m * m <= -remove * -remove) {
                                if (i == 1) {
                                    seedReader.setBlock(pos.offset(k, j, m), Blocks.STONE.defaultBlockState(), 2);
                                }
                            }
                        }
                    }
                    int topWidth = random.nextInt(5) + 8;
                    int topHeight = 3;
                    for (int b = j; b <= topHeight; b++) {
                        double remove3 = 2 + Math.sin(b * (Math.PI / topWidth)) * 4;
                        double remove4 = 2 + Math.cos(b * (Math.PI / topWidth)) * 4;
                        for (int l = -topWidth; l < topWidth; l++) {
                            for (int n = -topWidth; n < topWidth; n++) {
                                if (l * l + n * n <= remove3 * remove3) {
                                    if (MathHelper.abs(l) < 6 && MathHelper.abs(n) < 6) {
                                        for (int k = height; k < (height + 5 + random.nextInt(2)); k++) {
                                            if (i == 2) {
                                                seedReader.setBlock(pos.offset(l, b + height, n), Blocks.STONE.defaultBlockState(), 2);
                                            }
                                        }
                                    }
                                }
                                if (l * l + n * n <= remove4 * remove4) {
                                    if (MathHelper.abs(l) < 6 && MathHelper.abs(n) < 6) {
                                        for (int k = height; k < (height + 5 + random.nextInt(2)); k++) {
                                            if (i == 2) {
                                                seedReader.setBlock(pos.offset(l, b + height + topHeight, n), Blocks.STONE.defaultBlockState(), 2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}