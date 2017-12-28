package lucasapolinario.com.bookfinder.model

import com.loopj.android.http.AsyncHttpClient
import lucasapolinario.com.bookfinder.MVP
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
            val url = getApiUrl("search.json?q=")
            asyncHttpClient.get(url + URLEncoder.encode(query, "utf-8"), BookLibraryRequest(presenter))

        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }

    }

    override fun fetchBookInfo(query: String) {
        try{
            val url = getApiUrl("books/$query.json")
            asyncHttpClient.get(url , BookInformationRequest(presenter))

        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }
    }

    private fun getApiUrl(relativeUrl: String): String {
        return urlOpenLibrary + relativeUrl
    }

}
