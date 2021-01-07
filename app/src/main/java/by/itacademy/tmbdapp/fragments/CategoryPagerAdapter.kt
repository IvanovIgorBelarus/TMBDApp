package by.itacademy.tmbdapp.fragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import by.itacademy.tmbdapp.view.MainActivity

class CategoryPagerAdapter(fa: MainActivity, private val isGuest: Boolean) :
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = createFragmentList(isGuest).size
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BaseFragment.newInstance(POPULAR)
            1 -> BaseFragment.newInstance(TRENDING)
            2 -> BaseFragment.newInstance(UPCOMING)
            3 -> BaseFragment.newInstance(TOP_RATED)
            else ->
                return BaseFragment.newInstance(FAVORITE)
        }
    }

    private fun createFragmentList(isGuest: Boolean): List<Fragment> {
        val fragmentList = mutableListOf<Fragment>().apply {
            with(BaseFragment) {
                add(newInstance(POPULAR))
                add(newInstance(TRENDING))
                add(newInstance(UPCOMING))
                add(newInstance(TOP_RATED))
            }
        }
        if (!isGuest) {
            fragmentList.add(BaseFragment.newInstance(FAVORITE))
        }
        return fragmentList
    }
}