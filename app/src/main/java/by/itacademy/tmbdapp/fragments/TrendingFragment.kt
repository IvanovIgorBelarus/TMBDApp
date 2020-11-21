package by.itacademy.tmbdapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.ListItemActionListener
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment(), ListItemActionListener {
    private val category= mutableListOf<Int>().apply { for (i in 51..100)add(i) }
    private lateinit var binding: FragmentTrendingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTrendingBinding.bind(view)
        binding.trendingRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
          //  adapter= CategoryAdapter(category,this@TrendingFragment)
        }
    }

    companion object {
        fun newInstance() = TrendingFragment()
    }

    override fun onItemClick(position: Int) {

    }
}