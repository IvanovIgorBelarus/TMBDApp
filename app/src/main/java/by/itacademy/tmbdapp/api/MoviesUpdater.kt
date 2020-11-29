package by.itacademy.tmbdapp.api

import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.api.model.SpokenLanguage

class MoviesUpdater(
    private val category: String,
    private val recyclerView: RecyclerView,
    private val adapter: CategoryAdapter,
) {
    private var page = 1
    fun getListMovies() {
        MoviesRepository.getCategoryMovies(
            category,
            page,
            language=recyclerView.context.resources.configuration.locale.toLanguageTag(),
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

    private fun onError() {}
}