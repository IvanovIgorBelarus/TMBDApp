package by.itacademy.tmbdapp.uimodel.uimodelmapper

import by.itacademy.tmbdapp.api.data.SimilarResult
import by.itacademy.tmbdapp.uimodel.UIMovieModel

class SimilarMoviesMapper : (MutableList<SimilarResult>?) -> UIMovieModel.SimilarMovies {
    override fun invoke(list: MutableList<SimilarResult>?) = UIMovieModel.SimilarMovies(
        list = list?.map { item ->
            "https://image.tmdb.org/t/p/w185${item.poster_path}"
        }?.toMutableList())
}