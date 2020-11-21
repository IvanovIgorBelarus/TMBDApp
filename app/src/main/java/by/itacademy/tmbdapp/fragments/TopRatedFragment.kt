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
import by.itacademy.tmbdapp.api.MoviesRepository
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment(), ListItemActionListener {
    private var moviesList = mutableListOf<Movie>()
    private var topAdapter = CategoryAdapter(moviesList, this)
    private lateinit var binding: FragmentTopRatedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesRepository.getCategoryMovies(
            "top_rated",
            onSuccess = ::getMovies,
            onError = ::onError
        )
        binding= FragmentTopRatedBinding.bind(view)
        binding.topRatedRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= topAdapter
        }
    }

    companion object {
        fun newInstance() = TopRatedFragment()
    }

    override fun onItemClick(position: Int) {
        startActivity(Intent(context,MovieActivity::class.java))
    }
    private fun getMovies(movies: List<Movie>) {
        topAdapter.upDateMovies(movies)
    }

    private fun onError() {}
}