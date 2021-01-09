package by.itacademy.tmbdapp.presentation

interface MoviePresenter {
    fun getMovieFromAPI(id: Int)
    fun getTrailerFromApi(id:Int)
    fun rateMovie(id: Int, rate:Float)
}