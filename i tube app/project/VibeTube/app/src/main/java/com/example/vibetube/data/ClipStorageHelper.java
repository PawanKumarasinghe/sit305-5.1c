package com.example.vibetube.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ClipStorageHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "vibetube_clips.db";
    private static final int VERSION = 1;
    private static final String TABLE = "clips";
    private static final String COL_LABEL = "label";
    private static final String COL_VIDEO_ID = "video_id";

    public ClipStorageHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ("
                + COL_LABEL + " TEXT, "
                + COL_VIDEO_ID + " TEXT PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertClip(ClipItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LABEL, item.getLabel());
        values.put(COL_VIDEO_ID, item.getVideoId());
        db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public List<ClipItem> fetchAll() {
        List<ClipItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new ClipItem(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_LABEL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_VIDEO_ID))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
