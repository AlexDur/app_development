package de.sanexio.kotlin.mypresents

import android.content.ContentValues
import android.content.Context
import android.util.Log



class GiftTableHelper( context: Context){

    private val helper = DatabaseHelper(context)

    fun speichereNeuenEintrag(daten : GiftObject) : Long {
        val db = helper.writableDatabase
        val datensatz = ContentValues()
        datensatz.put(databeseValues.NAME, daten.name)
        datensatz.put(databeseValues.BESCHREIBUNG, daten.beschreibung)
        datensatz.put(databeseValues.GEKAUFT, daten.gekauft)
        datensatz.put(databeseValues.GESCHENK_FUER, daten.geschenkFuer)
        datensatz.put(databeseValues.BILD_ID,daten.bild)
        datensatz.put(databeseValues.SHOP,daten.shop)

        db.beginTransaction()
        val id : Long = try {
           val id = db.insert(databeseValues.TABLE_NAME,null, datensatz)
            db.setTransactionSuccessful()
            id
        }
        finally {
            db.endTransaction()
            db.close()
        }
        Log.i("test","Datensatz eingefügt. $id")
        return id
    }

    fun alleEintraege(gekauft : Int): ArrayList<GiftObject>{
        val auswahl = arrayOf(databeseValues._ID,databeseValues.NAME,databeseValues.BESCHREIBUNG,databeseValues.BILD_ID,databeseValues.GEKAUFT,databeseValues.GESCHENK_FUER,databeseValues.SHOP)
        val db = helper.readableDatabase
        val cursor = db.query(databeseValues.TABLE_NAME, auswahl,"${databeseValues.GEKAUFT} = $gekauft", null,null,null,null)

        val ergebnissListe = ArrayList<GiftObject>()
        while (cursor.moveToNext()){
            var giftDaten = GiftObject(0,"",0,"","",0,"")
            giftDaten.id = cursor.getInt(cursor.getColumnIndex(databeseValues._ID))
            giftDaten.beschreibung = cursor.getString(cursor.getColumnIndex(databeseValues.BESCHREIBUNG))
            giftDaten.bild = cursor.getInt(cursor.getColumnIndex(databeseValues.BILD_ID))
            giftDaten.gekauft =  cursor.getInt(cursor.getColumnIndex(databeseValues.GEKAUFT))
            giftDaten.geschenkFuer = cursor.getString(cursor.getColumnIndex(databeseValues.GESCHENK_FUER))
            giftDaten.name = cursor.getString(cursor.getColumnIndex(databeseValues.NAME))
            giftDaten.shop = cursor.getString(cursor.getColumnIndex(databeseValues.SHOP))
            Log.i("test","Datensatz abgerufen: ${giftDaten.toString()}")
            ergebnissListe.add(giftDaten)
        }
        Log.i("test", "Ergebniss enthält: ${ergebnissListe.size} Objekte")
        cursor.close()
        return ergebnissListe
    }


    fun ausgeswelterDatensatz(id : Int):GiftObject{
        val db = helper.readableDatabase
        val querry ="SELECT  ${databeseValues.GEKAUFT}, ${databeseValues.SHOP}, ${databeseValues.GESCHENK_FUER}, ${databeseValues.BILD_ID}, ${databeseValues.NAME}, ${databeseValues.BESCHREIBUNG} FROM ${databeseValues.TABLE_NAME} WHERE _id = $id;"

        val cursor = db.rawQuery(querry,null,null)
        var giftDaten = GiftObject(0,"",0,"","",0,"")
        while (cursor.moveToNext()){
            giftDaten.id = id
            giftDaten.beschreibung = cursor.getString(cursor.getColumnIndex(databeseValues.BESCHREIBUNG))
            giftDaten.bild = cursor.getInt(cursor.getColumnIndex(databeseValues.BILD_ID))
            giftDaten.gekauft =  cursor.getInt(cursor.getColumnIndex(databeseValues.GEKAUFT))
            giftDaten.geschenkFuer = cursor.getString(cursor.getColumnIndex(databeseValues.GESCHENK_FUER))
            giftDaten.name = cursor.getString(cursor.getColumnIndex(databeseValues.NAME))
            giftDaten.shop = cursor.getString(cursor.getColumnIndex(databeseValues.SHOP))
            Log.i("test","Datensatz Ein Eintrag: ${giftDaten.toString()}")
        }
//        db.endTransaction()
        db.close()
     return giftDaten
    }


    fun setGekauftTrue(id : Int){
        val db = helper.writableDatabase
        val values = ContentValues()
        values.put(databeseValues.GEKAUFT, 1)
        db.update(databeseValues.TABLE_NAME,values,"${databeseValues._ID}  = $id",null)
        db.close()
    }
    fun loescheDatensatz(id :Int){
        val db = helper.writableDatabase
        db.delete(databeseValues.TABLE_NAME,"${databeseValues._ID}  = $id", null)

    }


}