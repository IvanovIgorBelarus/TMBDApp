package by.itacademy.tmbdapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.tmbdapp.databinding.ActivityMainBinding
import by.itacademy.tmbdapp.fragments.CategoryPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isGuest = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        setViewPager()
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                isGuest = !isGuest
                setViewPager()
                Log.d("HM", "click optionMenu $isGuest")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewPager() {
        setContentView(binding.root)
        val viewPager2 = binding.viewPager2
        viewPager2.adapter = CategoryPagerAdapter(this, isGuest)
        val tabs = binding.tabLayout
        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.popular)
                1 -> tab.setText(R.string.trending)
                2 -> tab.setText(R.string.upcoming)
                3 -> tab.setText(R.string.top_rated)
                4 -> tab.setText(R.string.watch_list)
            }
        }.attach()
    }
}