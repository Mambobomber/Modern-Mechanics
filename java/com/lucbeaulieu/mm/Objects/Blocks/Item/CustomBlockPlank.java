package com.lucbeaulieu.mm.Objects.Blocks.Item;

import com.lucbeaulieu.mm.Init.BlockInit;
import com.lucbeaulieu.mm.Init.ItemInit;
import com.lucbeaulieu.mm.Main;
import com.lucbeaulieu.mm.Util.Handlers.EnumHandler;
import com.lucbeaulieu.mm.Util.Interfaces.IHasModel;
import com.lucbeaulieu.mm.Util.Interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Stack;

@SuppressWarnings("ALL")
public class CustomBlockPlank extends Block implements IMetaName, IHasModel
{
    private static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.create("variant", EnumHandler.EnumType.class);
    private String name;

    public CustomBlockPlank(String name)
    {
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.RUBBER));

        this.name = name;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVeriant(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    //OVERRIDE METHODS

    //**********************************//

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT).getMeta();
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
        return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, (int)(getMetaFromState(world.getBlockState(pos))));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void registerModels()
    {
        if (this.name == null) throw new AssertionError();
        for (int i = 0; i < EnumHandler.EnumType.values().length; i++) {
            Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "planks_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
        }
    }

    //**********************************//

}
