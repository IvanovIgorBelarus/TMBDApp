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
import by.itacademy.tmbdapp.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment(), ListItemActionListener {
    private val category = "top_rated"
    private var topAdapter = CategoryAdapter(mutableListOf(), this)
    private lateinit var binding: FragmentTopRatedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopRatedBinding.bind(view)
        binding.topRatedRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = topAdapter
        }
        MoviesUpdater(category, binding.topRatedRecycler, topAdapter).getListMovies()
    }

    companion object {
        fun newInstance() = TopRatedFragment()
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("id",movie.id)
        startActivity(intent)
    }
}