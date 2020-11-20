package com.example.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PositionHelper extends SQLiteOpenHelper {

    public static  final  String table_pos ="position";
    public static  final  String col_id ="position";
    public static  final  String col_num ="position";
    public static  final  String col_long ="position";
    public static  final  String col_lat ="position";


    public PositionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sera lancer lors de creation du fichier ==> creation du tables
        //db parametre , qui permet de creer les requettes

        db.execSQL(" create table  "+table_pos+"(" +
                col_id      +" integer primary Key autoincrement ," +
                col_num     +" Text not null," +
                col_long    +" Text not null," +
                col_lat     +" Text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//mise ajour

        db.execSQL("drop table "+table_pos);
        onCreate(db);

    }
}
