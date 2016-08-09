package com.example.nilanjan.visor.utils;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by nilanjan on 09-Aug-16.
 */
public interface MemoryColumns {
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @AutoIncrement
    String ID = "id";
    @DataType(DataType.Type.INTEGER)
    @NotNull
    String DATE = "date";
    @DataType(DataType.Type.TEXT)
    String HEADER = "header";
    @DataType(DataType.Type.TEXT)
    String BODY = "body";
    @DataType(DataType.Type.TEXT)
    String latitude = "latitude";
    @DataType(DataType.Type.TEXT)
    String longitude = "longitude";
}
