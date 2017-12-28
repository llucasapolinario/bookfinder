package lucasapolinario.com.bookfinder.views.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.model.dataClass.Book
import lucasapolinario.com.bookfinder.presenter.Presenter
import lucasapolinario.com.bookfinder.views.BookAdapter
import java.text.ParseException


open class HomeFragment : MyFragment() {

    private val key = "query"
    private lateinit var query : String
    private lateinit var books: ArrayList<Book>

    fun newInstance(query: String): HomeFragment {
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString(key, query)
        fragment.arguments = bundle

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,
                container, false)
    }

    @SuppressLint("ResourceType")
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
        query = arguments!!.getString(key)
        presenter = Presenter()
        presenter.setView(this, activity!!.applicationContext)
        presenter.fetchBooks(query)
        books = presenter.getBooks()

    }

    override fun updateListRecycler() {

        val handler = Handler()
        handler.postDelayed({
            recyclerView.adapter = BookAdapter(books){
                fragmentManager!!.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.frame, BookViewFragment().newInstance(it), tag)
                        .addToBackStack(tag)
                        .commit()
            }
        },1100)
        val lm = LinearLayoutManager(activity!!.applicationContext)
        recyclerView.layoutManager = lm
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun showProgressBar(visibilidade: Int) {
        progressbar(visibilidade)
        updateListRecycler()
    }

}
