package by.itacademy.tmbdapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CategoryPagerAdapter(fa: MainActivity, private val fragments:ArrayList<Fragment>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int =fragments.size
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->PopularFragment()
            1->TrendingFragment()
            2->UpcomingFragment()
            else ->
                return TopRatedFragment()
        }
    }
}