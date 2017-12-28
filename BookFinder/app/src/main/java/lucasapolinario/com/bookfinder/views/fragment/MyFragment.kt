package lucasapolinario.com.bookfinder.views.fragment

import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.R

open class MyFragment : Fragment(), MVP.ViewImpl {

    protected lateinit var presenter: MVP.PresenterImpl
    protected lateinit var recyclerView: RecyclerView

    override fun showToast(mensage: String) {
        Toast.makeText(context, mensage, Toast.LENGTH_SHORT).show()

    }

    override fun showProgressBar(visibilidade: Int) {
        progressbar(visibilidade)
    }

    override fun updateListRecycler() {
    }

    protected fun progressbar(visibilidade: Int){
        if (view != null) {
            val pb: ConstraintLayout = view!!.findViewById(R.id.pb_loading)
            pb.visibility = visibilidade
        }
    }

}