package by.itacademy.tmbdapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.CategoryRecyclerBinding

class CategoryAdapter(
    private var movies: List<Movie>,
    private val listItemActionListener: ListItemActionListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryRecyclerBinding.inflate(inflater, parent, false)
        Log.d("HM", "bind= ${movies.size}")
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            bind(movies[position])
            itemView.setOnClickListener { listItemActionListener.onItemClick(position) }
        }
    }
    fun upDateMovies(movies: List<Movie>){
        this.movies=movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size
    class CategoryViewHolder(private var binding: CategoryRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textView.text = movie.title
        }
    }
}