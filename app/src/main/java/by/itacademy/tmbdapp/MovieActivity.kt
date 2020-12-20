package by.itacademy.tmbdapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.databinding.ActivityMovieBinding
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MoviePresenter
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
import com.bumptech.glide.Glide

class MovieActivity : BaseActivity(), MovieActivityListener {
    private lateinit var binding: ActivityMovieBinding
    private val moviePresenter: MoviePresenter by lazy { MoviePresenterImpl(this) }
    private var id = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getId()
    }

    override fun onStart() {
        super.onStart()
        moviePresenter.getMovieFromAPI(id)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.authentication -> {

            }
            R.id.userSetting -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.userInfo -> {
                startActivity(Intent(this, RatingActivity::class.java))
            }
            R.id.refresh -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setValue(movie: Movie) {
        with(binding) {
            title.text = movie.title
            overview.text = movie.overview
            rating.rating = movie.vote_average.toFloat() / 2
            releaseDate.text = movie.release_date
            Glide.with(poster)
                .load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
                .into(poster)
        }
    }

    override fun onError() {
        Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show()
    }

    private fun getId() {
        if (intent != null) {
            id = intent.getIntExtra("id", -1)
        } else {
            finish()
        }
    }

    companion object {
        @JvmStatic
        fun startMovieActivity(context: Context?, movie: Movie) =
            Intent(context, MovieActivity::class.java).apply {
                putExtra("id", movie.id)
            }
    }
}