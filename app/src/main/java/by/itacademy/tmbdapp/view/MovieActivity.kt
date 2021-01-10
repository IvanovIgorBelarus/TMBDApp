package by.itacademy.tmbdapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.databinding.ActivityMovieBinding
import by.itacademy.tmbdapp.fragments.TAG
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MovieAdapter
import by.itacademy.tmbdapp.presentation.MoviePresenter
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
import by.itacademy.tmbdapp.uimodel.UIMovieModel
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
    private val movieAdapter by lazy { MovieAdapter(this) }

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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onStart() {
        super.onStart()
        with(moviePresenter) {
            getMovieFromAPI(id)
            getTrailerFromApi(id)
        }
        Log.d(TAG, "id=$id")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.authentication -> {
                startActivity(Intent(this, AccessActivity::class.java))
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

    override fun setValue(list: List<UIMovieModel>) {
        movieAdapter.update(list)
    }

    override fun setTrailer(key: String) {
        setVideo(key)
    }

    override fun doRate(rate: Float) {
        Toast.makeText(this, "You get $rate", Toast.LENGTH_LONG).show()
    }

    private fun setVideo(key: String) {
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
        Toast.makeText(this, "Wrong player", Toast.LENGTH_SHORT).show()
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