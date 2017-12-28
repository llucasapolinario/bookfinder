package lucasapolinario.com.bookfinder.views.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.presenter.Presenter
import lucasapolinario.com.bookfinder.views.BookAdapter
import java.text.ParseException

class LikedFragment : MyFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,
                container, false)
    }

    override fun onResume() {
        super.onResume()

        try {
            recyclerView = view!!.findViewById(R.id.rv_reciclerview)
            updateListRecycler()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun updateListRecycler() {

        presenter = Presenter()
        presenter.setView(this, activity!!.applicationContext)
        val likedbooks = presenter.getLikedBooks()
        val handler = Handler()

        showProgressBar(-1)
        handler.postDelayed({
            recyclerView.adapter = BookAdapter(likedbooks){
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

}