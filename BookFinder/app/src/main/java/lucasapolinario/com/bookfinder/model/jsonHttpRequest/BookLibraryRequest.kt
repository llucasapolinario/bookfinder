package lucasapolinario.com.bookfinder.model.jsonHttpRequest

import cz.msebera.android.httpclient.Header
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.model.dataClass.Book
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class BookLibraryRequest(private var presenter: MVP.PresenterImpl) : JsonHttpRequest(presenter) {

    override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
        try {
            val docs: JSONArray
            if (response != null) {
                docs = response.getJSONArray("docs")
                val books = Book("", "", "").fromJson(docs)
                presenter.updateListRecycler(books!!)

            }
        } catch (e: JSONException) {
            presenter.showToast("Invalid JSON format")
            e.printStackTrace()
        }
    }
}
