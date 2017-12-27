package lucasapolinario.com.bookfinder.model

import android.util.Log
import com.google.gson.Gson
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.presenter.Book
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class JsonHttpRequest(presenterImpl: MVP.PresenterImpl) : JsonHttpResponseHandler() {

    private var presenter : MVP.PresenterImpl = presenterImpl

    override fun onStart() {
        super.onStart()
        Log.d("jsom", "HttpRequest")
        presenter.showProgressBar(true)
    }

    override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {

        Log.d("jsom", "JSONObject")
        try {
            val docs: JSONArray
            if (response != null) {
                docs = response.getJSONArray("docs")
                val books = Book("", "", "").fromJson(docs)
                Log.d("jsom", books.toString())
                presenter.updateListRecycler(books!!)
            }
        } catch (e: JSONException) {
            presenter.showToast("Invalid JSON format")
            e.printStackTrace()
        }

    }

    override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONArray?) {

        Log.d("jsom", "JSONArray")

        val books = ArrayList<Book>()
        var book: Book

        books.clear()

        for (i in 0 until response!!.length()) {
            try {
                book = Gson().fromJson( response.getJSONObject(i).toString(), Book::class.java)
                books.add(book)
            } catch (ex: JSONException) {
            }
        }
        presenter.updateListRecycler(books)
    }

    override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {

        Log.d("jsom", "onFailure")

        if (responseString != null) {
            presenter.showToast(responseString)
        }
    }

    override fun onFinish() {

        Log.d("jsom", "onFinish")

        presenter.showProgressBar(false )
    }

}
