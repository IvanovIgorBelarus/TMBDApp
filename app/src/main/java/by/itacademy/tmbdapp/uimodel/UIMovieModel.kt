package by.itacademy.tmbdapp.uimodel

sealed class UIMovieModel {
    class FeedItem(
        val id: Int,
        val posterPath: String,
        val title: String,
        val releaseDate: String,
        val rating: Float,
        val genres: String,
    ) : UIMovieModel()

    class OverView(val overView: String) : UIMovieModel()
    class SimilarMovies(val list: MutableList<String>?) : UIMovieModel()
}