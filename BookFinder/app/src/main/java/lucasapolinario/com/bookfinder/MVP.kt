package lucasapolinario.com.bookfinder

import android.content.Context
import com.loopj.android.http.JsonHttpResponseHandler
import lucasapolinario.com.bookfinder.model.JsonHttpRequest
import lucasapolinario.com.bookfinder.presenter.Book

interface MVP {

    interface ModelImpl{
        fun getBooks(query: String, handler: JsonHttpRequest)
    }

    interface PresenterImpl{
        fun fetchBook(book : Book)
        fun fetchBooks()

        fun showToast(mensage : String)
        fun showProgressBar(status: Boolean)
        fun setView(viewImpl: MVP.ViewImpl, cont: Context)
        fun getContext() : Context
        fun updateListRecycler(booksArray: ArrayList<Book>)
        fun updateItemRecycler(b: Book.CREATOR)

        fun getBooks(): ArrayList<Book>
    }

    interface ViewImpl{
        fun showToast(mensage : String)
        fun showProgressBar(visibilidade: Int)
        fun updateListRecycler()
        fun updateItemRecycler(possition: Int)
    }

}