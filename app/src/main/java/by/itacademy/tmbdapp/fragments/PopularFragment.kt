package by.itacademy.tmbdapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.ListItemActionListener
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.MoviesRepository
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.FragmentPopularBinding

class PopularFragment : Fragment(), ListItemActionListener {
    private lateinit var binding: FragmentPopularBinding
    private var category= mutableListOf<Movie>()
    private var popAdapter=CategoryAdapter(category,this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesRepository.getPopularMovies(
            onSuccess = ::getPopularMovie,
            onError = ::onError
        )
        binding= FragmentPopularBinding.bind(view)
        binding.popularRecycler.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= popAdapter
        }
    }

    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onItemClick(position: Int) {

    }
    private fun getPopularMovie(movies: List<Movie>){
        popAdapter.upDateMovies(movies)
    }
    private fun onError(){}
}