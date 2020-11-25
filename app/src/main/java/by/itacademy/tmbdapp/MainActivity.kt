package by.itacademy.tmbdapp

import android.content.Intent
import android.os.Bundle
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
        setViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.userSetting -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.userInfo -> {
                startActivity(Intent(this, RatingActivity::class.java))
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