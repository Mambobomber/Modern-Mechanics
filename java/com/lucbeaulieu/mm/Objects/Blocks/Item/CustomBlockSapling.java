package com.lucbeaulieu.mm.Objects.Blocks.Item;

import com.lucbeaulieu.mm.Init.BlockInit;
import com.lucbeaulieu.mm.Init.ItemInit;
import com.lucbeaulieu.mm.Main;
import com.lucbeaulieu.mm.Util.Handlers.EnumHandler;
import com.lucbeaulieu.mm.Util.Interfaces.IHasModel;
import com.lucbeaulieu.mm.Util.Interfaces.IMetaName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;


@SuppressWarnings("ALL")
public class CustomBlockSapling extends BlockBush implements IGrowable, IHasModel, IMetaName
{

    private static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.create("variant", EnumHandler.EnumType.class, apply -> apply.getMeta() < 2);
    private String name;

    private static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    private static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public CustomBlockSapling(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.RUBBER).withProperty(STAGE, 0));

        this.name = name;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVeriant(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    //OVERRIDE METHODS

    //**********************************//


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return SAPLING_AABB; }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) { return NULL_AABB; }

    @Override
    public int damageDropped(IBlockState state)
    {
        return  state.getValue(VARIANT).getMeta();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public boolean isFullCube(IBlockState state) { return false;}

    @Override
    public void registerModels()
    {
        if (this.name == null) throw new AssertionError();
        for (int i = 0; i < EnumHandler.EnumType.values().length; i++) {
            Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "sapling_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(EnumHandler.EnumType enumType : EnumHandler.EnumType.values())
        {
            items.add(new ItemStack(this, 1, enumType.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta & 2)).withProperty(STAGE, (meta & 8) >> 3);

    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();
        i = i | state.getValue(STAGE).intValue() << 3;
        return i;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, (int)(getMetaFromState(world.getBlockState(pos))));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT, STAGE);
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this, 1, state.getValue(VARIANT).getMeta();
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) { return true; }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return worldIn.rand.nextFloat() < 0.45d;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (state.getValue(STAGE).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, rand, pos, state);
        }
    }

    public void generateTree(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (TerrainGen.saplingGrowTree(world, rand, pos)) return;
        WorldGenerator generator = (rand.nextInt(10) == 0) ? new WorldGenBigTree(false) : new WorldGenTrees(false);
        boolean flag = false;
        switch (state.getValue(VARIANT))
        {
            case RUBBER: /* generator = new WorldGenRubberTree(); */ break;
            case NETHER:/* generator = new WorldGenNetherTree();*/  break;
            case END:/*generator = new WorldGenEndTree();*/  break;
        }

        IBlockState iBlockState = Blocks.AIR.getDefaultState();
        if (flag)
        {
            world.setBlockState(pos.add(0, 0, 0), iBlockState, 4);
            world.setBlockState(pos.add(1,0,0), iBlockState, 4);
            world.setBlockState(pos.add(0,0,1), iBlockState, 4);
            world.setBlockState(pos.add(1,0,1), iBlockState, 4);
        }
        else
        {
            world.setBlockState(pos, iBlockState, 4);
        }
        if (!generator.generate(world, rand, pos))
        {
            world.setBlockState(pos.add(0, 0, 0), iBlockState, 4);
            world.setBlockState(pos.add(1,0,0), iBlockState, 4);
            world.setBlockState(pos.add(0,0,1), iBlockState, 4);
            world.setBlockState(pos.add(1,0,1), iBlockState, 4);
        }
        else
        {
            world.setBlockState(pos, iBlockState, 4);
        }
    }
    //**********************************//
}