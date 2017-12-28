package lucasapolinario.com.bookfinder.model.jsonHttpRequest

import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import lucasapolinario.com.bookfinder.MVP


open class JsonHttpRequest(private var presenter: MVP.PresenterImpl): JsonHttpResponseHandler() {

    override fun onStart() {
        super.onStart()
        presenter.showProgressBar(true)
    }

    override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
        if (responseString != null) {
            presenter.showToast(responseString)
        }
    }

    override fun onFinish() {
        presenter.showProgressBar(false )
    }

}