package by.itacademy.tmbdapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.ListItemActionListener
import by.itacademy.tmbdapp.MovieActivity
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.MoviesUpdater
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment(), ListItemActionListener {
    private val category = "now_playing"
    private var tredAdapter = CategoryAdapter(mutableListOf(), this)
    private lateinit var binding: FragmentTrendingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTrendingBinding.bind(view)
        binding.trendingRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= tredAdapter
        }
        MoviesUpdater(category, binding.trendingRecycler, tredAdapter).getListMovies()
    }

    companion object {
        fun newInstance() = TrendingFragment()
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("id",movie.id)
        startActivity(intent)
    }
}