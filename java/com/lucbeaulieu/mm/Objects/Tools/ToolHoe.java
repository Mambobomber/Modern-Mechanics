package com.lucbeaulieu.mm.Objects.Tools;

import com.lucbeaulieu.mm.Init.ItemInit;
import com.lucbeaulieu.mm.Util.Interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class ToolHoe extends ItemHoe implements IHasModel
{
    public ToolHoe(String name, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.TOOLS);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {

    }
}
