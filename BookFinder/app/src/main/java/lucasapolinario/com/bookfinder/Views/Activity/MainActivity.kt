package lucasapolinario.com.bookfinder.Views.Activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import lucasapolinario.com.bookfinder.R
import lucasapolinario.com.bookfinder.Views.Fragment.AboutFragment
import lucasapolinario.com.bookfinder.Views.Fragment.CategoriesFragment
import lucasapolinario.com.bookfinder.Views.Fragment.HomeFragment
import lucasapolinario.com.bookfinder.Views.Fragment.LikedFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val fragment = HomeFragment()
        goToFragment(fragment, "Categories")

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var run : Runnable
        when (item.itemId) {
            R.id.nav_search -> {
                val fragment = HomeFragment()
                goToFragment(fragment, "Categories")
            }
            R.id.nav_categories -> {
                val fragment = CategoriesFragment()
                goToFragment(fragment, "Categories")
            }
            R.id.nav_liked -> {
                val fragment = LikedFragment()
                goToFragment(fragment, "LikedFragment")
            }
            R.id.nav_about -> {
                val fragment = AboutFragment()
                goToFragment(fragment, "About")
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun goToFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frame, fragment, tag)
                .commitNow()
    }

}
