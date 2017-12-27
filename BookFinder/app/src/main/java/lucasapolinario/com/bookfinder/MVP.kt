package lucasapolinario.com.bookfinder

import android.content.Context
import lucasapolinario.com.bookfinder.model.JsonHttpRequest
import lucasapolinario.com.bookfinder.presenter.Book

interface MVP {

    interface ModelImpl{
        fun fetchBooks(query: String, handler: JsonHttpRequest)
        fun fetchBook(query: String)
    }

    interface PresenterImpl{
        fun fetchBooks(query: String)
        fun fetchBook(query: String)

        fun setView(viewImpl: MVP.ViewImpl, cont: Context)
        fun getContext() : Context
        fun getBooks(): ArrayList<Book>

        fun showToast(mensage : String)
        fun showProgressBar(status: Boolean)
        fun updateListRecycler(booksArray: ArrayList<Book>)
        fun updateItemRecycler(b: Book.CREATOR)
    }

    interface ViewImpl{
        fun showToast(mensage : String)
        fun showProgressBar(visibilidade: Int)
        fun updateListRecycler()
        fun updateItemRecycler(possition: Int)
    }

}