package com.example.nilanjan.visor.utils;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by nilanjan on 09-Aug-16.
 */

@Database(version = MemoryDatabase.VERSION,
    packageName = "com.example.nilanjan.visor.provider")
public class MemoryDatabase {
    public static final int VERSION = 1;
    @Table(MemoryColumns.class)
    public static final String MEMORY = "memory";
}
