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
import by.itacademy.tmbdapp.databinding.FragmentPopularBinding

class PopularFragment : Fragment(), ListItemActionListener {
    private val category = "popular"
    private lateinit var binding: FragmentPopularBinding
    private val popularAdapter by lazy { CategoryAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_popular, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPopularBinding.bind(view)
        binding.popularRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = popularAdapter
        }
        MoviesUpdater(category, binding.popularRecycler, popularAdapter).getListMovies()
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("id", movie.id)
        startActivity(intent)
    }
}