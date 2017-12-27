package lucasapolinario.com.bookfinder.model

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import lucasapolinario.com.bookfinder.MVP
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class Model(presenterImpl: MVP.PresenterImpl) : MVP.ModelImpl {

    val  URL: String  = "http://openlibrary.org/"

    private var presenter : MVP.PresenterImpl = presenterImpl

    override fun getBooks(query: String, handler: JsonHttpRequest) {
        val asyncHttpClinet = AsyncHttpClient()

        try{
            val url = getApiUrl("search.json?q=")
            val request = RequestParams(URL, url)
            asyncHttpClinet.get(url + URLEncoder.encode(query, "utf-8"), JsonHttpRequest(presenter))
        }catch (e : UnsupportedEncodingException){
            presenter.showToast("coneção deu ruim")
        }

    }

    override fun onQueryTextSubmit(query: String): Boolean {

        return true
    }

    fun getApiUrl(relativeUrl: String): String {
        return URL + relativeUrl
    }

}
