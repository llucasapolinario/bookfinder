package lucasapolinario.com.bookfinder.views.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.views.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private var searchQuery = "lord of ring"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frame, HomeFragment().newInstance(searchQuery), "Home")
                .commitNow()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        menu.findItem(R.id.action_search)
        val searchView = menu.findItem(R.id.action_search).actionView as? SearchView
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_search -> goToFragment(HomeFragment().newInstance(searchQuery), "Home")
            R.id.nav_categories -> goToFragment(CategoriesFragment(), "Categories")
            R.id.nav_liked -> goToFragment(LikedFragment(), "LikedFragment")
            R.id.nav_about -> goToFragment(AboutFragment(), "About")

        }

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return searchQuery(query)
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return searchQuery(newText)
    }

    private fun searchQuery(query: String): Boolean {
        searchQuery = query
        if (query.isNotEmpty()) {
            goToFragment(HomeFragment().newInstance(searchQuery), "Book")

            return true
        }

        return false
    }

    private fun goToFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frame, fragment, tag)
                .addToBackStack(tag)
                .commit()
    }

}
