package lucasapolinario.com.bookfinder.views.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.presenter.Book
import lucasapolinario.com.bookfinder.presenter.Presenter
import lucasapolinario.com.bookfinder.views.BookAdapter
import java.text.ParseException


class HomeFragment : Fragment(), MVP.ViewImpl {

    private lateinit var presenter: MVP.PresenterImpl
    private lateinit var recyclerView: RecyclerView
    private lateinit var books: ArrayList<Book>
    private val DESCRIBLE_kEY = "query"
    private lateinit var query : String

    fun newInstance(query: String): HomeFragment {
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString(DESCRIBLE_kEY, query)
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        try {
            setUpPresenter()
            recyclerView = view!!.findViewById(R.id.rv_reciclerview)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun setUpPresenter() {
        query = arguments!!.getString(DESCRIBLE_kEY)
        presenter = Presenter()
        presenter.setView(this, activity!!.applicationContext)
        presenter.fetchBooks(query)
        books = presenter.getBooks()

    }

    override fun showToast(mensage: String) {
        Toast.makeText(activity, mensage, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar(visibilidade: Int) {
        if (view != null) {
            val pb: ProgressBar = view!!.findViewById(R.id.pb_loading)
            pb.visibility = visibilidade
            updateListRecycler()
        }
    }

    override fun updateListRecycler() {
        if (recyclerView.adapter != null)
            recyclerView.adapter.notifyDataSetChanged()
        else
            recyclerView.adapter = BookAdapter(books)

        val lm = LinearLayoutManager(activity!!.applicationContext)
        recyclerView.layoutManager = lm
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun updateItemRecycler(possition: Int) {
        recyclerView.adapter.notifyItemChanged(possition)
    }

}
