package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.data.Movie

interface ListItemActionListener {
    fun onItemClick(movie: Movie)
}