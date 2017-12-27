package lucasapolinario.com.bookfinder.model

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.presenter.Book
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class Model(presenterImpl: MVP.PresenterImpl) : MVP.ModelImpl {

    val  URL: String  = "http://openlibrary.org/"

    private var presenter : MVP.PresenterImpl = presenterImpl
    private val asyncHttpClinet = AsyncHttpClient()

    override fun fetchBooks(query: String, handler: JsonHttpRequest) {
        try{
            val url = getApiUrl("search.json?q=")
            val request = RequestParams(URL, url)
            asyncHttpClinet.get(url + URLEncoder.encode(query, "utf-8"), JsonHttpRequest(presenter))
        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }

    }

    override fun fetchBook(query: String) {
        presenter.showProgressBar(true)
        val book : Book
        try{
            val url = getApiUrl("search.json?q=" + query)
            val request = RequestParams(URL, url)
            asyncHttpClinet.get(url + URLEncoder.encode(query, "utf-8"), JsonHttpRequest(presenter))
        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }
    }

    fun getApiUrl(relativeUrl: String): String {
        return URL + relativeUrl
    }

}
