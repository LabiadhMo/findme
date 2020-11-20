package com.example.findme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PositionManager {
    Context con;
    private SQLiteDatabase mabase;

    public PositionManager(Context con, String file, int versin) {
        this.con = con;
        ouvrir(file, versin);

    }

    private void ouvrir(String nom_fichier, int version) {
        PositionHelper helper = new PositionHelper(con, nom_fichier, null, version);
        mabase = helper.getWritableDatabase();// ken fichier mawjoud chy7elou , ken mouch mawjoud yasn3ou , ken version mehich hia y3ayet lil upgrade


    }

    public long inserer(int num, String lon, String lat) {
        long a = -1;
        ContentValues v = new ContentValues();
        v.put(PositionHelper.col_num, num);
        v.put(PositionHelper.col_long, lon);
        v.put(PositionHelper.col_lat, lat);
        a = mabase.insert(PositionHelper.table_pos, null, v);
        return a;
    }

    public ArrayList<Position> selectionnerTout() {
        ArrayList<Position> positions = new ArrayList<>();
        Cursor c = mabase.query(
                PositionHelper.table_pos,
                new String[]{
                        PositionHelper.col_id,
                        PositionHelper.col_num,
                        PositionHelper.col_lat,
                        PositionHelper.col_long
                },
                null,
                null,
                PositionHelper.col_num,
                null,
                PositionHelper.col_num
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            positions.add(new Position(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getColumnName(3)
                    )
            );
            c.moveToNext();
        }
        return positions;
    }

    public int supprimer(String num) {
        int a = -1;
        a = mabase.delete(
                PositionHelper.table_pos,
                PositionHelper.col_num + "=" + num,
                null
        );
        return a;
    }
}
