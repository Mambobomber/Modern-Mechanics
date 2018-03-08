package com.lucbeaulieu.mm.Init;

import com.lucbeaulieu.mm.Objects.Blocks.BlockBase;
import com.lucbeaulieu.mm.Objects.Blocks.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<>();

    //INGOT BLOCKS
    public static Block BLOCK_COPPER = new BlockBase("block_copper", Material.IRON);
    public static Block BLOCK_TIN = new BlockBase("block_tin", Material.IRON);
    public static Block BLOCK_SILVER = new BlockBase("block_silver", Material.IRON);
    public static Block BLOCK_BRONZE = new BlockBase("block_bronze", Material.IRON);

    public static Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static Block ORE_NETHER = new BlockOres("ore_nether", "nether");
    public static Block ORE_END = new BlockOres("ore_end", "end");

//    //OVER WORLD ORES
//    public static Block ORE_COPPER = new BlockOres("ore_copper", "overworld");
//    public static Block ORE_SILVER = new BlockOres("ore_silver", "overworld");
//    public static Block ORE_TIN = new BlockOres("ore_tin", "overworld");
//    public static Block ORE_COBALT = new BlockOres("ore_cobalt", "overworld");
//    public static Block ORE_MANGANESE = new BlockOres("ore_manganese", "overworld");
//
//    //NETHER & END ORES
//    public static Block END_SUPORDIUM = new BlockOres("end_surpordium", "end");
//    public static Block NETHER_MANOX = new BlockOres("nether_manox", "nether");


}
