package by.itacademy.tmbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.databinding.FragmentPopularBinding

class PopularFragment : Fragment(), ListItemActionListener {
    private lateinit var binding: FragmentPopularBinding
    private val category= mutableListOf<Int>().apply { for (i in 1..50)add(i) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentPopularBinding.bind(view)
        binding.popularRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=CategoryAdapter(category,this@PopularFragment)
        }
    }

    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onItemClick(position: Int) {

    }
}