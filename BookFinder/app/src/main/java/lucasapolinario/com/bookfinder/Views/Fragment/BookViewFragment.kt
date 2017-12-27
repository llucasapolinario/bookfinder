package lucasapolinario.com.bookfinder.views.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.presenter.Book
import lucasapolinario.com.bookfinder.presenter.Presenter

class BookViewFragment : Fragment(), MVP.ViewImpl{

    private val DESCRIBABLE_KEY = "describable_key"
    private lateinit var book : Book
    private lateinit var cover: ImageView
    private lateinit var title: TextView
    private lateinit var autor: TextView
    private lateinit var publisher: TextView
    private lateinit var pgCount: TextView
    private lateinit var query: String
    private lateinit var presenter: MVP.PresenterImpl

    fun newInstance(query: String): BookViewFragment {
        val fragment  = BookViewFragment()
        val bundle = Bundle()
        bundle.putString(DESCRIBABLE_KEY, query)
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.book_fragment,
                container, false)
    }

    override fun onResume() {
        super.onResume()
        query = arguments!!.getString(DESCRIBABLE_KEY)
        init()
        setData()
    }

    override fun showToast(mensage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar(visibilidade: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateListRecycler() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateItemRecycler(possition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setData() {
        title.text = "a"
        autor.text = "b"
//        Picasso.with(context).load(Uri.parse(book.getCoverUrl())).error(R.drawable.bookfinder).into(cover)
    }

    private fun init() {
        cover = view!!.findViewById(R.id.ivBookCover)
        title = view!!.findViewById(R.id.tvTitle)
        autor = view!!.findViewById(R.id.tvAuthor)
        publisher = view!!.findViewById(R.id.tvPublisher)
        pgCount = view!!.findViewById(R.id.tvPageCount)

        presenter = Presenter()
        presenter.setView(this, activity!!.applicationContext)
        presenter.fetchBooks(query)
//        book = presenter.fetchBook(query)
    }
}