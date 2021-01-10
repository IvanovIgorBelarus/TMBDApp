package by.itacademy.tmbdapp.uimodelmapper

import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.uimodel.UIMovieModel

class FactsMapper: (Movie)->UIMovieModel.Facts {
    override fun invoke(movie: Movie)= UIMovieModel.Facts(
        originalTitle = movie.original_title,
        originalLanguage = movie.original_language,
        budget = movie.budget,
        revenue = movie.revenue,
        homepage = movie.homepage
    )
}