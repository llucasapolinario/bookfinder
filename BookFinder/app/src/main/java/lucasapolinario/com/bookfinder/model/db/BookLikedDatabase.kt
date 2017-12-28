package lucasapolinario.com.bookfinder.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookLikedDatabase(
        val context: Context,
        val name: String) :
        SQLiteOpenHelper(
                context,
                name,
                null,
                1)
{

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE likedbook_db ( " +
                "openLibraryId varchar(45) PRIMARY KEY NOT NULL," +
                "author varchar(45) NOT NULL," +
                "title varchar(45) NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}