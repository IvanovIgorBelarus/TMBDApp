package by.itacademy.tmbdapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.tmbdapp.databinding.ActivityDashBoardBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewPager()
    }

    private fun setViewPager() {
        setContentView(binding.root)
        val viewPager2 = binding.viewPager2
        val fragmentList = arrayListOf(
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