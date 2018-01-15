package lucasapolinario.com.bookfinder.model

import android.content.Context
import com.loopj.android.http.AsyncHttpClient
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.model.dataClass.Book
import lucasapolinario.com.bookfinder.model.db.LikedBooksDB
import lucasapolinario.com.bookfinder.model.jsonHttpRequest.BookInformationRequest
import lucasapolinario.com.bookfinder.model.jsonHttpRequest.BookLibraryRequest
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class Model(presenterImpl: MVP.PresenterImpl) : MVP.ModelImpl {

    private val urlOpenLibrary: String  = "http://openlibrary.org/"


    private var presenter : MVP.PresenterImpl = presenterImpl
    private val asyncHttpClient = AsyncHttpClient()

    override fun fetchBooks(query: String) {
        try{
            asyncHttpClient.get(getApiUrl("search.json?q=") +
                    URLEncoder.encode(query, "utf-8"),
                    BookLibraryRequest(presenter))

        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }

    }

    override fun fetchBookInfo(query: String) {
        try{
            asyncHttpClient.get(getApiUrl("books/$query.json")
                    , BookInformationRequest(presenter))

        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }
    }

    override fun likebook(book: Book, context: Context){
        LikedBooksDB(context).new(book)
    }

    override fun dislikebook(book: Book, context: Context){
        LikedBooksDB(context).delete(book)
    }

    override fun getLikedBooks(context: Context): ArrayList<Book>{
        return LikedBooksDB(context).getBooks()
    }

    private fun getApiUrl(relativeUrl: String): String {
        return urlOpenLibrary + relativeUrl
    }

}
