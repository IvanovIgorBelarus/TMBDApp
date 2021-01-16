package by.itacademy.tmbdapp.uimodel.uimodelmapper

import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.uimodel.UIMovieModel

class OverViewMapper : (Movie) -> UIMovieModel.OverView {
    override fun invoke(movie: Movie) = UIMovieModel.OverView(
        overView = movie.overview
    )
}