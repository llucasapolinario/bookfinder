package lucasapolinario.com.bookfinder.model

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.presenter.Book
import java.net.URLEncoder


class Model(presenterImpl: MVP.PresenterImpl) : MVP.ModelImpl {

    val  URL: String  = "http://openlibrary.org"

    private var presenter : MVP.PresenterImpl = presenterImpl

    override fun getBooks(query: String, handler: JsonHttpRequest) {
        val asyncHttpClinet = AsyncHttpClient()

        val url = getApiUrl("search.json?q=")
        val request = RequestParams(URL, url)
//        asyncHttpClinet.get(url + URLEncoder.encode(query, "utf-8"), handler)

        asyncHttpClinet.post(presenter.getContext(),
                url,
                request,
                JsonHttpRequest(presenter)
                )
        Log.d("jsom", "model")
    }

    fun getApiUrl(relativeUrl: String): String {
        return URL + relativeUrl
    }

}
