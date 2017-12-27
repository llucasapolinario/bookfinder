package lucasapolinario.com.bookfinder.presenter

import android.content.Context
import android.util.Log
import android.view.View
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.model.JsonHttpRequest
import lucasapolinario.com.bookfinder.model.Model

class Presenter : MVP.PresenterImpl {

    private var model: MVP.ModelImpl = Model(this)
    private lateinit var view: MVP.ViewImpl
    private var books = ArrayList<Book>()
    private lateinit var context : Context


    override fun fetchBook(query: String) {

    }

    override fun fetchBooks(query: String) {
        Log.d("jsom", "presenter")
        model.fetchBooks(query, JsonHttpRequest(this))

    }

    override fun setView(viewImpl: MVP.ViewImpl, cont: Context){
        view = viewImpl
        context = cont
    }

    override fun showToast(mensage: String) {
        view.showToast(mensage)
    }

    override fun showProgressBar(status: Boolean) {
        view.showProgressBar(if (status) View.VISIBLE else View.GONE)
    }

    override fun getContext(): Context {
       return context
    }

    override fun updateListRecycler(booksArray: ArrayList<Book>) {
        books.clear()
        books.addAll(booksArray)
        view.updateListRecycler()
    }

    override fun updateItemRecycler(b: Book.CREATOR) {
        for (b1 in books){
            if (b1.openLibraryId == b.openLibraryId) {
                view.updateItemRecycler(books.indexOf(b1))
                break
            }
        }
    }

    override fun getBooks(): ArrayList<Book> {
        return books
    }

}
