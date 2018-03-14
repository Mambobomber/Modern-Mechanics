package com.lucbeaulieu.mm.Objects.Blocks.Item;


import com.lucbeaulieu.mm.Init.BlockInit;
import com.lucbeaulieu.mm.Init.ItemInit;
import com.lucbeaulieu.mm.Main;
import com.lucbeaulieu.mm.Util.Handlers.EnumHandler;
import com.lucbeaulieu.mm.Util.Interfaces.IHasModel;
import com.lucbeaulieu.mm.Util.Interfaces.IMetaName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;

@SuppressWarnings("ALL")
public class CustomBlockLog extends BlockLog implements IHasModel, IMetaName
{
    private String name;
    private static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.create("variant", EnumHandler.EnumType.class, apply -> apply.getMeta() < 3);


    public CustomBlockLog(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.RUBBER).withProperty(LOG_AXIS, EnumAxis.Y));

        this.name = name;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVeriant(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    //OVERRIDE METHODS

    //**********************************//

    @Override
    public int damageDropped(IBlockState state)
    {
        return  state.getValue(VARIANT).getMeta();
    }

    @Override
    public void registerModels()
    {
        if (this.name == null) throw new AssertionError();
        for (int i = 0; i < EnumHandler.EnumType.values().length; i++) {
            Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "log_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
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
        IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta));

        switch (meta & 9)
        {
            case 0: state = state.withProperty(LOG_AXIS, EnumAxis.Y); break;
            case 3: state = state.withProperty(LOG_AXIS, EnumAxis.X); break;
            case 6: state = state.withProperty(LOG_AXIS, EnumAxis.Z); break;
            default: state.withProperty(LOG_AXIS, EnumAxis.NONE); break;
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();

        switch (state.getValue(LOG_AXIS))
        {
            case X: i |= 3; break;
            case Y: i |= 6; break;
            case Z: i |= 9; break;
        }
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
        return new BlockStateContainer(this, VARIANT, LOG_AXIS);
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this, 1, state.getValue(VARIANT).getMeta();
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
    }

    //**********************************//
}
