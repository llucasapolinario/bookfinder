package lucasapolinario.com.bookfinder

import android.content.Context
import lucasapolinario.com.bookfinder.model.dataClass.Book

interface MVP {

    interface ModelImpl{
        fun fetchBooks(query: String)
        fun fetchBookInfo(query: String)

        fun likebook(book: Book, context: Context)
        fun dislikebook(book: Book, context: Context)
        fun getLikedBooks(context: Context): ArrayList<Book>
    }

    interface PresenterImpl{
        fun fetchBooks(query: String)
        fun fetchBookInfo(query: String)

        fun likebook(book: Book)
        fun deslikebook(book: Book)
        fun getLikedBooks(): ArrayList<Book>

        fun getBooks(): ArrayList<Book>
        fun getBookInfo(): ArrayList<String>

        fun setView(viewImpl: MVP.ViewImpl, cont: Context)
        fun getContext() : Context

        fun showToast(mensage : String)
        fun showProgressBar(status: Boolean)

        fun updateListRecycler(booksArray: ArrayList<Book>)
        fun getInfoBook(info : ArrayList<String>)
    }

    interface ViewImpl{
        fun showToast(mensage : String)
        fun showProgressBar(visibilidade: Int)
        fun updateListRecycler()
    }

}