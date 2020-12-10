package by.itacademy.tmbdapp.fragments

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
import by.itacademy.tmbdapp.databinding.FragmentBaseBinding

const val POPULAR = "popular"
const val TRENDING = "now_playing"
const val UPCOMING = "upcoming"
const val TOP_RATED = "top_rated"
const val FAVORITE = "favorite"

class BaseFragment : Fragment(), ListItemActionListener {
    private lateinit var categoryFragment: String
    private lateinit var binding: FragmentBaseBinding
    private val popularAdapter by lazy { CategoryAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_base, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBaseBinding.bind(view)
        binding.popularRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = popularAdapter
        }
        MoviesUpdater(categoryFragment, binding.popularRecycler, popularAdapter).getListMovies()
    }

    override fun onItemClick(movie: Movie) {
        startActivity(MovieActivity.startMovieActivity(context, movie))
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String) =
            BaseFragment().apply {
                categoryFragment = category
            }
    }
}
