package by.itacademy.tmbdapp.uimodelmapper

import by.itacademy.tmbdapp.api.data.Movie

class MovieMapper: (List<Movie>)->List<String> {
    override fun invoke(movieList: List<Movie>):List<String> = movieList.map { item->  "https://image.tmdb.org/t/p/w185${item.poster_path}" }
}