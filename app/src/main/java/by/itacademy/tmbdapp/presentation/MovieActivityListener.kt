package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.uimodel.UIMovieModel

interface MovieActivityListener {
    fun setValue(list: List<UIMovieModel>)
    fun setTrailer(key: String)
    fun doRate()
}
