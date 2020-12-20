package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.data.Movie

interface MovieActivityListener {
    fun setValue(movie: Movie)
    fun onError()
}
