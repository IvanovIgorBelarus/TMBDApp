package by.itacademy.tmbdapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.tmbdapp.api.MoviesRepository
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private var id = -1
    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent != null) {
            id = intent.getIntExtra("id", -1)
        } else {
            finish()
        }
        MoviesRepository.getMovie(
            id,
            ::getMovie,
            ::onError
        )
    }

    private fun getMovie(movie: Movie) {
        with(binding) {
            title.text = movie.original_title
            overview.text = movie.overview
        }

    }

    private fun onError() {
        Log.d("HM2", "Error MovieActivity")
    }

}