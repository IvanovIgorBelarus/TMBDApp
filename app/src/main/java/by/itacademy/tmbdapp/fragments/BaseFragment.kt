package by.itacademy.tmbdapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.presentation.CategoryAdapter
import by.itacademy.tmbdapp.presentation.ListItemActionListener
import by.itacademy.tmbdapp.view.MovieActivity
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.presentation.MoviesPresenter
import by.itacademy.tmbdapp.presentation.MoviesPresenterImpl
import by.itacademy.tmbdapp.api.data.Movie
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
    private val moviesPresenter: MoviesPresenter by lazy {
        MoviesPresenterImpl(categoryFragment,
            binding.popularRecycler,
            popularAdapter)
    }

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
        moviesPresenter.getListMovies()
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
