package by.itacademy.tmbdapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.CategoryRecyclerBinding
import com.bumptech.glide.Glide

class CategoryAdapter(
    private val listItemActionListener: ListItemActionListener,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(CategoryRecyclerBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            bind(movies[position])
            itemView.setOnClickListener { listItemActionListener.onItemClick(movies[position]) }
        }
    }

    fun appendMovies(movies: List<Movie>) {
        val positionStart = this.movies.size
        Log.d("HM2", "positionsStart= $positionStart")
        this.movies.addAll(movies)
        if (positionStart == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(positionStart, itemCount - 1)
        }
    }

    override fun getItemCount(): Int = movies.size
    class CategoryViewHolder(private val binding: CategoryRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
                .fitCenter()
                .into(binding.imageView)
        }
    }
}