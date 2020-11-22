package by.itacademy.tmbdapp

import by.itacademy.tmbdapp.api.model.Movie

interface ListItemActionListener {
    fun onItemClick(movie: Movie)
}