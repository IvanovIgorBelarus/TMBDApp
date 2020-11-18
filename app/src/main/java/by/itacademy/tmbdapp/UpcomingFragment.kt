package by.itacademy.tmbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment(),ListItemActionListener {
    private val category = mutableListOf<Int>().apply { for (i in 101..150) add(i) }
    private lateinit var binding: FragmentUpcomingBinding
  
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentUpcomingBinding.bind(view)
        binding.upcomingRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=CategoryAdapter(category,this@UpcomingFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }

    override fun onItemClick(position: Int) {

    }
}