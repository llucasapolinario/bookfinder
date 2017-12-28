package lucasapolinario.com.bookfinder.model.jsonHttpRequest

import cz.msebera.android.httpclient.Header
import lucasapolinario.com.bookfinder.MVP
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class BookInformationRequest(private var presenter: MVP.PresenterImpl): JsonHttpRequest(presenter) {

    override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
        try {
            val publisher: JSONArray
            if (response != null) {
                publisher = response.getJSONArray("publishers")
                val numPublishers = publisher.length()
                val publishers = ArrayList<String>()

                //like for
                (0 until numPublishers).mapTo(publishers) { publisher.getString(it) }

                if (response.has("number_of_pages")){
                    publishers.add(response.getInt("number_of_pages").toString() + " pages")
                }else
                    publishers.add("pages not metion")

                presenter.getInfoBook(publishers)
            }
        } catch (e: JSONException) {
            presenter.showToast("Invalid JSON format")
            e.printStackTrace()
        }
    }
}