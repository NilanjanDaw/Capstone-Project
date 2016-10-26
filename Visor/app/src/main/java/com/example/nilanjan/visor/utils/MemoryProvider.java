package com.example.nilanjan.visor.utils;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by nilan on 09-Aug-16.
 */
@ContentProvider(authority = MemoryProvider.AUTHORITY,
        database = MemoryDatabase.class,
        packageName = "com.example.nilanjan.visor.provider"
)
public class MemoryProvider {
    public static final String AUTHORITY = "com.example.nilanjan.visor.MemoryProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = MemoryDatabase.MEMORY)
    public static class Memories {
        @ContentUri(path = MemoryDatabase.MEMORY,
                type = "vnd.android.cursor.item/memory",
                defaultSort = MemoryColumns.DATE + " ASC")
        public static final Uri CONTENT_URI = buildUri(MemoryDatabase.MEMORY);

        @InexactContentUri(
                name = "MEMORY_BY_ID",
                path = MemoryDatabase.MEMORY + "/#",
                type = "vnd.android.cursor.item/memory",
                whereColumn = MemoryColumns.ID,
                pathSegment = 1
        )
        public static Uri withId(String id) {
            return buildUri(MemoryDatabase.MEMORY, id);
        }

        @InexactContentUri(
                name = "MEMORY_BY_HEADER",
                path = MemoryDatabase.MEMORY + "/$",
                type = "vnd.android.cursor.item/memory",
                whereColumn = MemoryColumns.HEADER,
                pathSegment = 1
        )
        public static Uri withTitle(String header) {
            return buildUri(MemoryDatabase.MEMORY, header);
        }

    }
}