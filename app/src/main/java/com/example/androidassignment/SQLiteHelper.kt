package com.example.androidassignment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.androidassignment.model.UserModel
import java.lang.Exception

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_vERSION) {


    companion object {
        private const val DATABASE_vERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PHONE = "phone"
        private const val ADDRESS = "address"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE " + TBL_USER + " ("
                + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT, "
                + EMAIL + " TEXT, " + PHONE + " TEXT, "
                + ADDRESS + " TEXT " + " )")
        db?.execSQL(createTblUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

    //Create
    fun insertUser(usr: UserModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, usr.id)
        contentValues.put(NAME, usr.name)

        contentValues.put(EMAIL, usr.email)
        contentValues.put(PHONE, usr.phone)
        contentValues.put(ADDRESS, usr.address)

        val success = db.insert(TBL_USER, null, contentValues)
        db.close()
        return success


    }

    //Read
    fun getAllUser(): ArrayList<UserModel> {
        val usrList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String
        var phone: String
        var address: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                phone = cursor.getString(cursor.getColumnIndex("phone"))
                address = cursor.getString(cursor.getColumnIndex("address"))

                val usr =
                    UserModel(id = id, name = name, email = email, phone = phone, address = address)
                usrList.add(usr)
            } while (cursor.moveToNext())
        }
        return usrList
    }

    //Update
    fun updateUser(usr: UserModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, usr.id)
        contentValues.put(NAME, usr.name)
        contentValues.put(EMAIL, usr.email)
        contentValues.put(PHONE, usr.phone)
        contentValues.put(ADDRESS, usr.address)


        val success = db.update(TBL_USER, contentValues, "id" + usr.id, null)
        db.close()
        return success

    }

    //Delete
    fun deleteUserById(id: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        val success = db.delete(TBL_USER, "id=$id", null)
        db.close()
        return success

    }

}