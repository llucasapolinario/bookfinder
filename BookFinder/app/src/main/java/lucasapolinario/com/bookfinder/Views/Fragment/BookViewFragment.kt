package lucasapolinario.com.bookfinder.views.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.squareup.picasso.Picasso
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.model.dataClass.Book
import lucasapolinario.com.bookfinder.presenter.Presenter

class BookViewFragment : MyFragment() {

    private val key = "describable_key"

    private lateinit var book: Book
    private lateinit var cover: ImageView
    private lateinit var like: ImageView
    private lateinit var title: TextView
    private lateinit var autor: TextView
    private lateinit var publisher: TextView
    private lateinit var pgCount: TextView
    private lateinit var query: String
    private var bookInfo = ArrayList<String>()

    fun newInstance(b: Book): BookViewFragment {
        val fragment = BookViewFragment()
        val bundle = Bundle()
        bundle.putParcelable(key, b)
        fragment.arguments = bundle

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_book,
                container, false)
    }

    override fun onResume() {
        super.onResume()
        hideView(-1)
        book = arguments!!.getParcelable(key)
        query = book.openLibraryId
        init()
    }

    override fun updateListRecycler() {
        bookInfo = presenter.getBookInfo()

        val pages = bookInfo[bookInfo.size-1]
        pgCount.text = pages

        bookInfo.removeAt(bookInfo.size-1)
        publisher.text = bookInfo.joinToString(",")
        setData()

        hideView(1)
    }

    private fun setData() {

        presenter =  Presenter()
        presenter.setView(this, activity!!.applicationContext)

        val likedbooks = presenter.getLikedBooks()
        var liked = false
        if (likedbooks.contains(book)) {
            like.setImageResource(R.drawable.heart_47_liked)
            liked = true
        }
        title.text = book.title
        autor.text = book.author
        Picasso.with(context).load(Uri.parse(book.getLargeCoverUrl())).error(R.drawable.bookfinder).into(cover)

        like.setOnClickListener{

            if (liked){
                like.setImageResource(R.drawable.heart_48)
                presenter.deslikebook(book)
                liked = false
                showToast("Dislike")
            }
            else{
                like.setImageResource(R.drawable.heart_47_liked)
                presenter.likebook(book)
                showToast("Like")
                liked = true
            }

        }
    }

    private fun init() {
        cover = view!!.findViewById(R.id.ivBookCover)
        like = view!!.findViewById(R.id.btn_like)
        title = view!!.findViewById(R.id.tvTitle)
        autor = view!!.findViewById(R.id.tvAuthor)
        publisher = view!!.findViewById(R.id.tvPublisher)
        pgCount = view!!.findViewById(R.id.tvPageCount)

        presenter = Presenter()
        presenter.setView(this, activity!!.applicationContext)
        presenter.fetchBookInfo(query)
    }

    private fun hideView(hide : Int) {
        if (view != null) {
            val pb: ScrollView = view!!.findViewById(R.id.sv_book)
            pb.visibility = hide
        }
    }
}