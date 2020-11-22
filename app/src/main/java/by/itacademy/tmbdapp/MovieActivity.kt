package by.itacademy.tmbdapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.tmbdapp.api.MoviesRepository
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.ActivityMovieBinding
import com.bumptech.glide.Glide

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private var id = -1
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
            title.text = movie.title
            overview.text = movie.overview
            rating.rating = movie.popularity.toFloat() / 2
            releaseDate.text = movie.release_date
            Glide.with(poster)
                .load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
                .into(poster)
        }
        Log.d("HM2", "MovieActivity  $movie")
    }

    private fun onError() {
        Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show()
    }

}