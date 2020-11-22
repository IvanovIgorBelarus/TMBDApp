package by.itacademy.tmbdapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.api.model.Movie
import by.itacademy.tmbdapp.databinding.CategoryRecyclerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class CategoryAdapter(
    private var movies: MutableList<Movie>,
    private val listItemActionListener: ListItemActionListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryRecyclerBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            bind(movies[position])
            itemView.setOnClickListener { listItemActionListener.onItemClick(movies[position]) }
        }
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size-1
        )
    }

    override fun getItemCount(): Int = movies.size
    class CategoryViewHolder(binding: CategoryRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val poster: ImageView = itemView.findViewById(R.id.imageView)
        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
                .transform(CenterCrop())
                .into(poster)
        }
    }
}