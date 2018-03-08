package com.lucbeaulieu.mm.World.gen;

import com.lucbeaulieu.mm.Init.BlockInit;
import com.lucbeaulieu.mm.Objects.Blocks.BlockOres;
import com.lucbeaulieu.mm.Util.Handlers.EnumHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator
{
    private WorldGenerator ore_overwold_copper;
    private WorldGenerator ore_overwold_tin;
    private WorldGenerator ore_overwold_silver;
    private WorldGenerator ore_overwold_cobalt;
    private WorldGenerator ore_overwold_manganese;
    private WorldGenerator ore_nether_manox;
    private WorldGenerator ore_end_surpordium;

    public WorldGenCustomOres()
    {
        ore_overwold_copper = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.COPPER),9, BlockMatcher.forBlock(Blocks.STONE));
        ore_overwold_tin = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.TIN),9, BlockMatcher.forBlock(Blocks.STONE));
        ore_overwold_silver = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.SILVER),9, BlockMatcher.forBlock(Blocks.STONE));
        ore_overwold_cobalt = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.COBALT),9, BlockMatcher.forBlock(Blocks.STONE));
        ore_overwold_manganese = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.MANGANESE),9, BlockMatcher.forBlock(Blocks.STONE));
        ore_nether_manox = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.MANOX),9, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_end_surpordium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.SURPORDIUM),9, BlockMatcher.forBlock(Blocks.END_STONE));


    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.getDimension())
        {
            case -1:
                runGenerator(ore_nether_manox, random, chunkX, chunkZ, world, 50, 0, 100);
            break;

            case 0:
                runGenerator(ore_overwold_copper, random, chunkX, chunkZ, world, 50, 0, 100);
                runGenerator(ore_overwold_tin, random, chunkX, chunkZ, world, 50, 0, 100);
                runGenerator(ore_overwold_silver, random, chunkX, chunkZ, world, 50, 0, 100);
                runGenerator(ore_overwold_manganese, random, chunkX, chunkZ, world, 50, 0, 100);
                runGenerator(ore_overwold_cobalt, random, chunkX, chunkZ, world, 50, 0, 100);
                break;

            case 1:
                runGenerator(ore_end_surpordium, random, chunkX, chunkZ, world, 50, 0, 256);
            break;

        }
    }

    public void runGenerator(WorldGenerator generator, Random random, int chunkX, int chunkZ, World world, int chance, int minHeight, int maxHeight)
    {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException(" ore gen out of bonds");
        int heightDiff = maxHeight - minHeight + 1;

        for (int i = 0; i < chance; i++)
        {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);

            generator.generate(world, random, new BlockPos(x,y,z));
        }
    }


}
