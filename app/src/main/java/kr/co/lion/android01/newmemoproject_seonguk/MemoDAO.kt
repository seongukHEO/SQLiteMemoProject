package kr.co.lion.android01.newmemoproject_seonguk

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MemoDAO {
    companion object{

        //select one
        fun selctOneMemo(context: Context, idx: Int){
            //쿼리문
            var sql = """select idx, title, contents, important, dateTime
                |from MemoTable
                |where idx = ?
            """.trimMargin()


            //?에 들어갈 값
            var args = arrayOf(idx.toString())

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            //접근
            cursor.moveToNext()

            //순서값을 추출한다
            var idx1 = cursor.getColumnIndex("idx")
            var idx2 = cursor.getColumnIndex("title")
            var idx3 = cursor.getColumnIndex("contents")
            var idx4 = cursor.getColumnIndex("important")
            var idx5 = cursor.getColumnIndex("dateTime")

            //데이터를 가져온다
            var idx = cursor.getInt(idx1)
            var title = cursor.getString(idx2)
            var contents = cursor.getString(idx3)
            var important = cursor.getInt(idx4)
            var dateTime = cursor.getString(idx5)

            //객체에 데이터를 담아준다
            var memoList = Trip(idx, title, contents, important, dateTime)

            //데이터를 닫아준다
            dbHelper.close()
        }

        //select All
        fun selectAllMemo(context: Context): MutableList<Trip>{
            var sql = """select idx, title, contents, important
                |from MemoTable
                |order by idx desc
            """.trimMargin()

            //쿼리 생성
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            //값을 담아둘 리스트
            var memoList = mutableListOf<Trip>()

            while (cursor.moveToNext()){
                //순서값 받아오기
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("title")
                var idx3 = cursor.getColumnIndex("contents")
                var idx4 = cursor.getColumnIndex("important")
                var idx5 = cursor.getColumnIndex("dateTime")

                var idx = cursor.getInt(idx1)
                var title = cursor.getString(idx2)
                var contents = cursor.getString(idx3)
                var important = cursor.getInt(idx4)
                var dateTime = cursor.getString(idx5)

                var memoModel = Trip(idx, title, contents, important, dateTime)
                memoList.add(memoModel)
            }
            //닫아준다
            dbHelper.close()
            return memoList
        }

        //insert
        fun insertMemo(context: Context, trip: Trip){
            //쿼리 생성
            var sql = """insert into MemoTable
                |(title, contents, important, dateTime)
                |values(?, ?, ?, ?)
            """.trimMargin()

            //시간을 구해준다
            var sdf = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
            var now = sdf.format(Date())

            //?에 들어갈 값을 구한다
            var args = arrayOf(trip.title, trip.contents, trip.important, now)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        //update
        fun updateMemo(context: Context, trip: Trip){
            var sql = """update MemoTable
                |set title = ?, contents = ?, important = ?, dateTime = ?
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값을 구해준다
            var args = arrayOf(trip.title, trip.contents, trip.important, trip.dateTime, trip.type)
            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        //delete
        fun deleteMemo(context: Context, idx: Int){
            var sql = """delete from MemoTable
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값을 구한다
            var args = arrayOf(idx.toString())
            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }
}