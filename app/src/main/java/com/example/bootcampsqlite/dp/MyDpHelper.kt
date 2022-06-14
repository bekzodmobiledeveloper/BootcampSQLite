package com.example.bootcampsqlite.dp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bootcampsqlite.BootCamp

class MyDpHelper(var context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION), DataBases {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table bootcamp (${Constant.ID} integer not null primary key autoincrement unique,${Constant.NAME} text not null,${Constant.TEXT} text not null,${Constant.BOLIM} text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
    }

    override fun addInformation(bootCamp: BootCamp) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, bootCamp.name)
        contentValues.put(Constant.TEXT, bootCamp.text)
        contentValues.put(Constant.BOLIM, bootCamp.bolim)
        database.insert(Constant.TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllInformation(part: String): ArrayList<BootCamp> {
        val courseList = ArrayList<BootCamp>()
        val query = "select * from ${Constant.TABLE_NAME} where ${Constant.BOLIM} = '$part'"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val bootcamp = BootCamp(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                courseList.add(bootcamp)
            } while (cursor.moveToNext())
        }
        return courseList
    }

    override fun deleteInformation(bootCamp: BootCamp) {
        val database = this.writableDatabase
        database.delete(
            Constant.TABLE_NAME,
            "${Constant.ID}=?",
            arrayOf("${bootCamp.id}")
        )
        database.close()
    }

    override fun updateInformatsion(bootCamp: BootCamp): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.ID, bootCamp.id)
        contentValues.put(Constant.NAME, bootCamp.name)
        contentValues.put(Constant.TEXT, bootCamp.text)
        contentValues.put(Constant.BOLIM, bootCamp.bolim)
        val query = "update bootcamp set name = '${bootCamp.name}',text = '${bootCamp.text}', bolim = '${bootCamp.bolim}' where id=${bootCamp.id}"
//        return database.update(Constant.TABLE_NAME, contentValues, "${Constant.ID}=?", arrayOf("${bootCamp.id}"))
//        database.rawQuery(query, arrayOf("${bootCamp.id}"))
        database.execSQL(query)
        database.close()
        return 0
    }


}