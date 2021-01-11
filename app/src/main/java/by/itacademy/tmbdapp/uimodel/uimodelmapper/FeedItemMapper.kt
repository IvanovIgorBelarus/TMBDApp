package by.itacademy.tmbdapp.uimodel.uimodelmapper

import by.itacademy.tmbdapp.api.data.Genre
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.uimodel.UIMovieModel

class FeedItemMapper : (Movie) -> UIMovieModel.FeedItem {
    override fun invoke(movie: Movie) = UIMovieModel.FeedItem(
        id = movie.id,
        title = movie.title,
        releaseDate = movie.release_date,
        rating = movie.vote_average.toFloat() / 2,
        genres = genresString(movie.genres),
        posterPath = "https://image.tmdb.org/t/p/w185${movie.poster_path}"
    )

    private fun genresString(genres: List<Genre>): String {
        var result = "Genres: "
        genres.forEach { result += it.name + "  " }
        return result
    }
}