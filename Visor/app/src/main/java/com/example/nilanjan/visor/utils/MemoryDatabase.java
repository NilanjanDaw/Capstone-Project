package com.example.nilanjan.visor.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.ExecOnCreate;
import net.simonvt.schematic.annotation.OnConfigure;
import net.simonvt.schematic.annotation.OnCreate;
import net.simonvt.schematic.annotation.OnUpgrade;
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
    @ExecOnCreate
    public static final String EXEC_ON_CREATE = "SELECT * FROM " + MEMORY;

    @OnCreate
    public static void onCreate(Context context, SQLiteDatabase db) {
    }

    @OnUpgrade
    public static void onUpgrade(Context context, SQLiteDatabase db, int oldVersion,
                                 int newVersion) {
    }

    @OnConfigure
    public static void onConfigure(SQLiteDatabase db) {
    }

}
