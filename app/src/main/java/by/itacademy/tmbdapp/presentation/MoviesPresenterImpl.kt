package by.itacademy.tmbdapp.presentation

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.api.moviesapi.MoviesRepository
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.presentation.adapters.CategoryAdapter
import by.itacademy.tmbdapp.uimodelmapper.MovieMapper

class MoviesPresenterImpl(
    private val category: String,
    private val recyclerView: RecyclerView,
    private val adapter: CategoryAdapter
) : MoviesPresenter {
    private var page = 1
    override fun getListMovies() {
        MoviesRepository.getCategoryMovies(
            category,
            page,
            language = recyclerView.context.resources.configuration.locale.toLanguageTag(),
            ::getMovies,
            ::onError
        )
    }

    private fun attachMoviesOnScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleCount = recyclerView.layoutManager!!.childCount
                val totalCount = recyclerView.adapter!!.itemCount
                if (visibleCount == 1 || totalCount == 20) {
                    Log.d("HM2","$totalCount")
                    recyclerView.removeOnScrollListener(this)
                    page++
                    getListMovies()
                }
            }
        })
    }

    private fun getMovies(movies: List<Movie>) {
        adapter.appendMovies(movies)
        attachMoviesOnScrollListener()
    }

    private fun onError() {
        Log.d("HM2", "ERROR")
    }
}