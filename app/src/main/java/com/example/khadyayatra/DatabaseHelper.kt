package com.example.khadyayatra

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.khadyayatra.model.RegUser


const val DATABASE_NAME = "KhadyaYatra"
const val TABLE_NAME = "Registration"
const val COL_REGID = "id"
const val COL_REGNAME = "name"
const val COL_MOBNO = "mobno"
const val COL_EMAIL = "emailid"
const val COL_DELADD = "delivery_address"
const val COL_PASSWORD = "password"
const val COL_CONPASS = "confirmpaa"

class DatabaseHelper (var context : Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COL_REGID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_REGNAME VARCHAR(256), $COL_MOBNO VARCHAR(11), $COL_EMAIL VARCHAR(100), $COL_DELADD VARCHAR(256), $COL_PASSWORD VARCHAR(10), $COL_CONPASS VARCHAR(10))";
             db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(regUser: RegUser){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_REGNAME, regUser.regname)
        cv.put(COL_MOBNO, regUser.mobno)
        cv.put(COL_EMAIL, regUser.email)
        cv.put(COL_DELADD, regUser.delivery_address)
        cv.put(COL_PASSWORD, regUser.pass)
        cv.put(COL_CONPASS, regUser.conpass)
        val result = db.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong()){
            Toast.makeText(context, "Fail to register", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT).show()
        }
    }

}


