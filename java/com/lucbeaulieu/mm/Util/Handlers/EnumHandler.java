package com.lucbeaulieu.mm.Util.Handlers;

import net.minecraft.util.IStringSerializable;

@SuppressWarnings("ALL")
public class EnumHandler
{
    public enum EnumType implements IStringSerializable
    {
        COPPER(0, "copper"), TIN(1, "tin"), SILVER(2, "silver"),
        MANOX(3, "manox"), COBALT(4, "cobalt"), MANGANESE(5, "manganese"),
        SURPORDIUM(6, "surpordium"), RUBBER(6, "rubber"), NETHER(6, "NETHER"), END(6, "end");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, unlocalizedName;

        EnumType(int meta, String name)
        {
            this(meta, name, name);
        }

        EnumType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getMeta()
        {
            return this.meta;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        public static EnumType byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for(EnumType enumtype : values())
            {
                META_LOOKUP[enumtype.getMeta()] = enumtype;
            }
        }
    }
}

