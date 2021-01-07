package by.itacademy.tmbdapp.view

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.databinding.ActivityMovieBinding
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MoviePresenter
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import java.util.*

class MovieActivity : YouTubeBaseActivity(), MovieActivityListener,
    YouTubePlayer.OnInitializedListener {
    private lateinit var binding: ActivityMovieBinding
    private val moviePresenter: MoviePresenter by lazy { MoviePresenterImpl(this) }
    private var id = -1
    private var youtubeKey = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.videoView.initialize("AIzaSyBGUgorrux750rLbWjEaO5k8bAzDPWZ2LI", this)
        getId()
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onStart() {
        super.onStart()
        with(moviePresenter) {
            getMovieFromAPI(id)
            getTrailerFromApi(id)
        }
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

    override fun setTrailer(key: String) {
        setVideo(key)
    }

    private fun setVideo(key: String) {
        Log.d(by.itacademy.tmbdapp.fragments.TAG, "$key")
        youtubeKey = key
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

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        restored: Boolean,
    ) {
        player?.loadVideo(youtubeKey)
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?,
    ) {
        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
    }

    companion object {
        var dLocale: Locale = Locale("")

        @JvmStatic
        fun startMovieActivity(context: Context?, movie: Movie) =
            Intent(context, MovieActivity::class.java).apply {
                putExtra("id", movie.id)
            }
    }
}