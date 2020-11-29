package by.itacademy.tmbdapp.fragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import by.itacademy.tmbdapp.MainActivity

class CategoryPagerAdapter(fa: MainActivity, private val isGuest: Boolean) :
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = createFragmentList(isGuest).size
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> TrendingFragment()
            2 -> UpcomingFragment()
            3 -> TopRatedFragment()
            else ->
                return UserListFragment()
        }
    }

    private fun createFragmentList(isGuest: Boolean): List<Fragment> {
        val fragmentList = mutableListOf<Fragment>(
            PopularFragment(),
            TrendingFragment(),
            UpcomingFragment(),
            TopRatedFragment()
        )
        if (!isGuest) {
            fragmentList.add(UserListFragment())
        }
        return fragmentList
    }
}