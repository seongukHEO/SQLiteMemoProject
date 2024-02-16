package kr.co.lion.android01.newmemoproject_seonguk

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Test.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //쿼리
        var sql = """create table MemoTable
            |(idx integer primary key autoincrement,
            |title text not null,
            |contents text not null,
            |important integer not null,
            |dateTime text not null)
        """.trimMargin()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}