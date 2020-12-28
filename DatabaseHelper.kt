package de.sanexio.kotlin.mypresents

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,
        databeseValues.DATABASE_NAME,
        null,
        databeseValues.DATABASE_VERSION){

    val tabelle ="CREATE TABLE gifttable (_id INTEGER PRIMARY KEY, name TEXT, gekauft INTEGER, shop TEXT, beschreibung TEXT, bildid INTEGER, geschenkfuer TEXT);"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(tabelle)
        Log.i("test", "Tabelle angelegt!")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${databeseValues.TABLE_NAME}")
        db.execSQL(tabelle)
        Log.i("test", "Tabelle gelöscht und neu angelegt!")
    }

}
