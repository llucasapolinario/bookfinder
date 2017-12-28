package lucasapolinario.com.bookfinder.model.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import lucasapolinario.com.bookfinder.model.dataClass.Book

class LikedBooksDB (
        private val context: Context,
        private val db : SQLiteDatabase = BookLikedDatabase(context, "likedbook_db").writableDatabase){

    private val tableName = "likedbook_db"
    private lateinit var contentValue : ContentValues
    private lateinit var bookdb: BookLikedDatabase

    fun insert(book: Book){
        contentValue = ContentValues()
        contentValue.put("openLibraryId", book.openLibraryId )
        contentValue.put("author", book.author)
        contentValue.put("title", book.title)

        db.insert(tableName,  null, contentValue)
    }

    fun getLikedBooks(): ArrayList<Book>{
        bookdb = BookLikedDatabase(context, tableName)
        val books = ArrayList<Book>()
        val cursor = bookdb.writableDatabase.query(tableName,
                arrayOf("openLibraryId", "author", "title"),
                null, null, null, null, null)
        while (cursor.moveToNext())
            books.add(Book(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2))
            )
        cursor.close()
        return books
    }

    fun deletLikedBook(book: Book){
        db.delete(tableName, "openLibraryId = ?", arrayOf(""+book.openLibraryId))
    }
}