package com.lucbeaulieu.mm.Init;

import com.lucbeaulieu.mm.Objects.Blocks.BlockBase;
import com.lucbeaulieu.mm.Objects.Blocks.BlockOres;
import com.lucbeaulieu.mm.Objects.Blocks.Item.CustomBlockLeaf;
import com.lucbeaulieu.mm.Objects.Blocks.Item.CustomBlockLog;
import com.lucbeaulieu.mm.Objects.Blocks.Item.CustomBlockPlank;
import com.lucbeaulieu.mm.Objects.Blocks.Item.CustomBlockSapling;
import net.minecraft.block.*;
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

    //ORES
    public static Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static Block ORE_NETHER = new BlockOres("ore_nether", "nether");
    public static Block ORE_END = new BlockOres("ore_end", "end");


    //TREE STUFF
    public static Block PLANKS_RUBBER = new CustomBlockPlank("planks_rubber");
    public static Block LOGS_RUBBER = new CustomBlockLog("logs_rubber");
    public static Block LEAVES_RUBBER = new CustomBlockLeaf("leaf_rubber");
    public static Block SAPLINGS_RUBBER = new CustomBlockSapling("saplings_rubber");



}
