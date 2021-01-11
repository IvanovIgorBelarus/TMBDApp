package by.itacademy.tmbdapp.uimodel.uimodelmapper

import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.uimodel.UIMovieModel
import java.text.NumberFormat

class FactsMapper : (Movie) -> UIMovieModel.Facts {
    override fun invoke(movie: Movie) = UIMovieModel.Facts(
        originalTitle = movie.original_title,
        originalLanguage = movie.original_language,
        budget = NumberFormat.getInstance().format(movie.budget) + " $",
        revenue = NumberFormat.getInstance().format(movie.revenue) + " $",
        homepage = movie.homepage
    )
}