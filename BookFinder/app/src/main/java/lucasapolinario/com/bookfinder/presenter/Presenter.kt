package lucasapolinario.com.bookfinder.presenter

import android.content.Context
import android.view.View
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.model.dataClass.Book
import lucasapolinario.com.bookfinder.model.Model

class Presenter : MVP.PresenterImpl {


    private var model: MVP.ModelImpl = Model(this)
    private lateinit var view: MVP.ViewImpl
    private lateinit var context : Context
    private var books = ArrayList<Book>()
    private var bookInfo = ArrayList<String>()

    override fun fetchBooks(query: String) {
        model.fetchBooks(query)
    }

    override fun fetchBookInfo(query: String) {
        model.fetchBookInfo(query)
    }


    override fun getBooks(): ArrayList<Book> {
        return books
    }

    override fun getBookInfo(): ArrayList<String> {
        return bookInfo
    }


    override fun setView(viewImpl: MVP.ViewImpl, cont: Context){
        view = viewImpl
        context = cont
    }

    override fun getContext(): Context {
        return context
    }


    override fun showToast(mensage: String) {
        view.showToast(mensage)
    }

    override fun showProgressBar(status: Boolean) {
        view.showProgressBar(if (status) View.VISIBLE else View.GONE)
    }

    override fun updateListRecycler(booksArray: ArrayList<Book>) {
        books.clear()
        books.addAll(booksArray)
        view.updateListRecycler()
    }

    override fun getInfoBook(info : ArrayList<String>) {
        bookInfo.clear()
        bookInfo.addAll(info)
        view.updateListRecycler()
    }


}
