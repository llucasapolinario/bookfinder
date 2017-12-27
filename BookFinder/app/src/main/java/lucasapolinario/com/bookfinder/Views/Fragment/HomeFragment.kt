package lucasapolinario.com.bookfinder.views.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_home,
                container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        try {
            setUpPresenter()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun setUpPresenter() {
        presenter = Presenter()
        presenter.setView(this, activity!!.applicationContext)
        presenter.fetchBooks()
        books = presenter.getBooks()
        recyclerView = view!!.findViewById(R.id.rv_reciclerview)

        Log.d("jsom", "view")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun showToast(mensage: String) {
        Toast.makeText(activity, mensage, Toast.LENGTH_SHORT).show()
    }
    override fun showProgressBar(visibilidade: Int) {
        if (view != null) {
            val pb: ProgressBar = view!!.findViewById(R.id.pb_loading)
            pb.setVisibility(visibilidade)
        }
    }

    override fun updateListRecycler() {
        if (recyclerView.adapter != null)
               recyclerView.adapter.notifyDataSetChanged()
        else
            recyclerView.adapter = BookAdapter(books)

        if (books.isEmpty()) {
            val image = view!!.findViewById<ImageView>(R.id.imageView2)
            image.setImageResource(R.drawable.bookfinder)
        }

        val lm = LinearLayoutManager(activity!!.applicationContext)
        lm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setLayoutManager(lm)
        recyclerView.setItemAnimator(DefaultItemAnimator())
    }

    override fun updateItemRecycler(possition: Int) {
        recyclerView.adapter.notifyItemChanged( possition )
    }

}