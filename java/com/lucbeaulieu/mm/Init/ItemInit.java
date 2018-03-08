package com.lucbeaulieu.mm.Init;

import com.lucbeaulieu.mm.Objects.Armor.ArmorBase;
import com.lucbeaulieu.mm.Objects.Items.ItemBase;
import com.lucbeaulieu.mm.Objects.Tools.*;
import com.lucbeaulieu.mm.Util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import java.util.*;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<>();

    //MATERIALS
    public static final Item.ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial("tool_bronze",2, 600, 6.5F, 2.5F, 20);
 //   public static final Item.ToolMaterial TOOL_STEEL = EnumHelper.addToolMaterial("tool_steel",3, 2048, 8.0F, 4.0F, 4);
    public static final ItemArmor.ArmorMaterial ARMOR_BRONZE = EnumHelper.addArmorMaterial("armor_bronze",Reference.MODID + ":bronze", 18, new int[]{3, 6, 6, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F  );
 //   public static final ItemArmor.ArmorMaterial ARMOR_STEEL = EnumHelper.addArmorMaterial("armor_steel", Reference.MODID + ":steel", 40, new int[]{4, 7, 8, 3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5F );


    //INGOTS + Materials
    public static Item INGOT_COPPER = new ItemBase("ingot_copper");
    public static Item INGOT_TIN = new ItemBase("ingot_tin");
    public static Item INGOT_SILVER = new ItemBase("ingot_silver");
    public static Item INGOT_BRONZE = new ItemBase("ingot_bronze");
    public static Item ITEM_COBALT = new ItemBase("item_cobalt");
    public static Item ITEM_MANGANESE = new ItemBase("item_manganese");
    public static Item ITEM_SUPORDIUM = new ItemBase("item_surpordium");
    public static Item INGOT_MANOX = new ItemBase("ingot_manox");
    public static Item STICK_STONE = new ItemBase("stick_stone");


    //ARMOR
    public static Item HELMET_BRONZE = new ArmorBase("helmet_bronze", ARMOR_BRONZE, 1, EntityEquipmentSlot.HEAD);
    public static Item CHEST_PLATE_BRONZE = new ArmorBase("chest_plate_bronze", ARMOR_BRONZE, 1, EntityEquipmentSlot.CHEST);
    public static Item LEGGINGS_BRONZE = new ArmorBase("leggings_bronze", ARMOR_BRONZE, 2, EntityEquipmentSlot.LEGS);
    public static Item BOOTS_BRONZE = new ArmorBase("boots_bronze", ARMOR_BRONZE, 1, EntityEquipmentSlot.FEET);


    //TOOLS
    public static Item AXE_BRONZE = new ToolAxe("axe_bronze", TOOL_BRONZE);
    public static Item PICK_AXE_BRONZE = new ToolPickAxe("pick_axe_bronze", TOOL_BRONZE);
    public static Item SHOVEL_BRONZE = new ToolShovel("shovel_bronze", TOOL_BRONZE);
    public static Item SWORD_BRONZE = new ToolSword("sword_bronze", TOOL_BRONZE);
    public static Item HOE_BRONZE = new ToolHoe("hoe_bronze", TOOL_BRONZE);





}
