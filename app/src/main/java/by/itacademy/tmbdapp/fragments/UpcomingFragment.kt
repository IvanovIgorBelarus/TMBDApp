package by.itacademy.tmbdapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.ListItemActionListener
import by.itacademy.tmbdapp.MovieActivity
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.MoviesUpdater
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment(), ListItemActionListener {
    private val category = "upcoming"
    private val upcomingAdapter by lazy {  CategoryAdapter(this)}
    private lateinit var binding: FragmentUpcomingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingBinding.bind(view)
        binding.upcomingRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = upcomingAdapter
        }
        MoviesUpdater(category, binding.upcomingRecycler, upcomingAdapter).getListMovies()
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

    override fun onItemClick(movie: Movie) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("id",movie.id)
        startActivity(intent)
    }
}