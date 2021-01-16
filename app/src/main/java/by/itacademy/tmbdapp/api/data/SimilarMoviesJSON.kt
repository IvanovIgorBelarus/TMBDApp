package by.itacademy.tmbdapp.api.data

data class SimilarMoviesJSON(
    val page: Int,
    val results: List<SimilarResult>,
    val total_pages: Int,
    val total_results: Int,
)