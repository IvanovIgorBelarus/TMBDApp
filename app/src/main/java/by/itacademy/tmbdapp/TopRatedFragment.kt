package by.itacademy.tmbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment(),ListItemActionListener {
    private val category = mutableListOf<Int>().apply { for (i in 151..200) add(i) }
    private lateinit var binding: FragmentTopRatedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTopRatedBinding.bind(view)
        binding.topRatedRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=CategoryAdapter(category,this@TopRatedFragment)
        }
    }

    companion object {
        fun newInstance() = TopRatedFragment()
    }

    override fun onItemClick(position: Int) {
    }
}