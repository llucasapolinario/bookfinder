package lucasapolinario.com.bookfinder.views

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import lucasapolinario.com.bookfinder.presenter.Book
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.squareup.picasso.Picasso
import lucasapolinario.com.bookfinder.R


class BookAdapter(var list: ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_book, parent, false),parent.context)
    }

    class ViewHolder(var view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        private lateinit var cover: ImageView
        private lateinit var title: TextView
        private lateinit var autor: TextView


        fun bind(book: Book) {
            initData()
            setData(book)
        }

        private fun setData(currentObj: Book) {
            title.text = currentObj.title
            autor.text = currentObj.author
            Picasso.with(context).load(Uri.parse(currentObj.getCoverUrl())).error(R.drawable.bookfinder).into(cover)

        }

        private fun initData() {
            cover = itemView.findViewById(R.id.ivBookCover)
            title = itemView.findViewById(R.id.tvTitle)
            autor = itemView.findViewById(R.id.tvAuthor)
        }
    }

}