package com.example.nilanjan.visor.utils;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by nilanjan on 09-Aug-16.
 */
public interface MemoryColumns {
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String ID = "id";
    @DataType(DataType.Type.TEXT)
    @NotNull
    String DATE = "date";
    @DataType(DataType.Type.TEXT)
    String HEADER = "header";
    @DataType(DataType.Type.TEXT)
    String BODY = "body";
    @DataType(DataType.Type.TEXT)
    String LATITUDE = "latitude";
    @DataType(DataType.Type.TEXT)
    String LONGITUDE = "longitude";
}
