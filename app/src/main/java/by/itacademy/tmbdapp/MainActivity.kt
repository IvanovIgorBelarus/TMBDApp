package by.itacademy.tmbdapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import by.itacademy.tmbdapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
    private fun setViewPager() {
        setContentView(binding.root)
        val viewPager2 = binding.viewPager2
        val fragmentList = arrayListOf<Fragment>(
            PopularFragment.newInstance(),
            TrendingFragment.newInstance(),
            UpcomingFragment.newInstance(),
            TopRatedFragment.newInstance()
        )
        viewPager2.adapter = CategoryPagerAdapter(this, fragmentList)
        val tabs = binding.tabLayout
        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.popular)
                1 -> tab.setText(R.string.trending)
                2 -> tab.setText(R.string.upcoming)
                3 -> tab.setText(R.string.top_rated)
            }
        }.attach()
    }
}