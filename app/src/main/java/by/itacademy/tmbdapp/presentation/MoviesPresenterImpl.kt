package by.itacademy.tmbdapp.presentation

import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.api.moviesapi.MoviesRepository
import by.itacademy.tmbdapp.presentation.adapters.CategoryAdapter

class MoviesPresenterImpl(
    private val category: String,
    private val recyclerView: RecyclerView,
    private val adapter: CategoryAdapter,
) : MoviesPresenter {
    private var page = 1
    override fun getListMovies() {
        MoviesRepository.getCategoryMovies(
            category,
            page,
            language = recyclerView.context.resources.configuration.locale.toLanguageTag()
        ).subscribe { list ->
            adapter.appendMovies(list.movies)
            attachMoviesOnScrollListener()
        }
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
}