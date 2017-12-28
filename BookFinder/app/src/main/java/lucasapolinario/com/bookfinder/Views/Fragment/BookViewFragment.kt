package lucasapolinario.com.bookfinder.views.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import lucasapolinario.com.bookfinder.MVP
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.model.dataClass.Book
import lucasapolinario.com.bookfinder.presenter.Presenter

class BookViewFragment : Fragment(), MVP.ViewImpl {

    private val key = "describable_key"
    private lateinit var book: Book
    private lateinit var cover: ImageView
    private lateinit var title: TextView
    private lateinit var autor: TextView
    private lateinit var publisher: TextView
    private lateinit var pgCount: TextView
    private lateinit var query: String
    private lateinit var presenter: MVP.PresenterImpl
    private lateinit var bookInfo: ArrayList<String>

    fun newInstance(b: Book): BookViewFragment {
        val fragment = BookViewFragment()
        val bundle = Bundle()
        bundle.putParcelable(key, b)
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
        book = arguments!!.getParcelable<Book>(key)
        query = book.openLibraryId
        init()
        setData()
    }

    override fun showToast(mensage: String) {
        Toast.makeText(context, mensage, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar(visibilidade: Int) {
        if (view != null) {
            val pb: ProgressBar = view!!.findViewById(R.id.pb_loading)
            pb.visibility = visibilidade
            updateListRecycler()
        }
    }

    private fun setData() {
        title.text = book.title
        autor.text = book.author
        Picasso.with(context).load(Uri.parse(book.getLargeCoverUrl())).error(R.drawable.bookfinder).into(cover);
//        Toast.makeText(context, bookInfo.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        cover = view!!.findViewById(R.id.ivBookCover)
        title = view!!.findViewById(R.id.tvTitle)
        autor = view!!.findViewById(R.id.tvAuthor)
        publisher = view!!.findViewById(R.id.tvPublisher)
        pgCount = view!!.findViewById(R.id.tvPageCount)

        presenter = Presenter()
        presenter.fetchBooks(query)
        presenter.setView(this, activity!!.applicationContext)

    }

    override fun updateListRecycler() {
        bookInfo = presenter.getBookInfo()
    }

}