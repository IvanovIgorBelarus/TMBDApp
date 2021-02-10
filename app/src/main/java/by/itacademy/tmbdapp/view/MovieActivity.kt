package by.itacademy.tmbdapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.databinding.ActivityMovieBinding
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MoviePresenter
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
import by.itacademy.tmbdapp.presentation.adapters.MovieAdapter
import by.itacademy.tmbdapp.uimodel.UIMovieModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MovieActivity : YouTubeBaseActivity(), MovieActivityListener,
    YouTubePlayer.OnInitializedListener {
    private lateinit var binding: ActivityMovieBinding
    private val moviePresenter: MoviePresenter by inject<MoviePresenterImpl> { parametersOf(this) }
    private var id = -1
    private var youtubeKey = ""
    private val movieAdapter by inject<MovieAdapter> { parametersOf(moviePresenter) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.adapterRecycler.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = movieAdapter
        }
        binding.videoView.initialize("AIzaSyBGUgorrux750rLbWjEaO5k8bAzDPWZ2LI", this)
        getId()
        with(moviePresenter) {
            getTrailerFromApi(id)
            getMovieFromAPI(id)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun setValue(list: List<UIMovieModel>) {
        movieAdapter.update(list)
    }

    override fun setTrailer(key: String) {
        setVideo(key)
    }

    override fun doRate() {
        Toast.makeText(this, "Rate done", Toast.LENGTH_LONG).show()
    }

    private fun setVideo(key: String) {
        youtubeKey = key
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
        Toast.makeText(this, "Wrong player", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun startMovieActivity(context: Context?, movie: Movie) =
            Intent(context, MovieActivity::class.java).apply {
                putExtra("id", movie.id)
            }
    }
}