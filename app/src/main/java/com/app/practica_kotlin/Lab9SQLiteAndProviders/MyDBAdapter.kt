package com.app.practica_kotlin.Lab9SQLiteAndProviders

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBAdapter(_context: Context) {

    private val DATABSE_NAME: String = "name"
    private var mContext: Context? = null
    private var mDataHelper: MyDBHelper? = null
    private var mSqLiteDataBase: SQLiteDatabase? = null
    private val DATABASE_VERSION = 1


    init {
        this.mContext = _context
        mDataHelper = MyDBHelper(_context, DATABSE_NAME, null, DATABASE_VERSION)
    }



    public fun insertStudents(name: String, faculty:Int){
        val cv: ContentValues = ContentValues()
        cv.put("name", name)
        cv.put("faculty", faculty)
        mSqLiteDataBase?.insert("students", null, cv)
    }

    public fun open(){
        mSqLiteDataBase = mDataHelper?.writableDatabase
    }

    public fun selectAllStudents(): List<String>{
        var allStudents: MutableList<String> = ArrayList()
        var cursor: Cursor = mSqLiteDataBase?.query("students", null, null, null, null, null, null)!!
        if (cursor.moveToFirst()){
            do {
                allStudents.add(cursor.getString(1))
            }while (cursor.moveToNext())
        }
        return  allStudents
    }

    public fun deleteAllEngineers(){
        mSqLiteDataBase?.delete("students", null, null)
    }











    inner class MyDBHelper(_context: Context?, databsE_NAME: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(_context, databsE_NAME, factory, version) {


        override fun onCreate(p0: SQLiteDatabase?) {
            val query = "CREATE TABLE students(id integer primary key autoincrement, name text, faculty integer);"
            p0?.execSQL(query)
        }

        override fun onUpgrade(_db: SQLiteDatabase?, _oldVersion: Int, _newVersion: Int) {
            val query = "DROP TABLE IF EXISTS students;"
            _db?.execSQL(query)
            onCreate(_db)
        }

    }
}