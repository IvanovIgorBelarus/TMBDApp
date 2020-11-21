package by.itacademy.tmbdapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.ListItemActionListener
import by.itacademy.tmbdapp.MovieActivity
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.MoviesRepository
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment(), ListItemActionListener {
    private var moviesList = mutableListOf<Movie>()
    private var tredAdapter = CategoryAdapter(moviesList, this)
    private lateinit var binding: FragmentTrendingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesRepository.getCategoryMovies(
            "now_playing",
            onSuccess = ::getMovies,
            onError = ::onError
        )
        binding= FragmentTrendingBinding.bind(view)
        binding.trendingRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= tredAdapter
        }
    }

    companion object {
        fun newInstance() = TrendingFragment()
    }

    override fun onItemClick(position: Int) {
        startActivity(Intent(context, MovieActivity::class.java))
    }
    private fun getMovies(movies: List<Movie>) {
        tredAdapter.upDateMovies(movies)
    }

    private fun onError() {}
}