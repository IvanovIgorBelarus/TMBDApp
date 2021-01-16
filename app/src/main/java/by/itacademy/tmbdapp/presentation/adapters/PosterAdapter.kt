package by.itacademy.tmbdapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.databinding.PosterRecyclerBinding
import com.bumptech.glide.Glide

class PosterAdapter(private var posterList: List<String> = emptyList()) :
    RecyclerView.Adapter<PosterAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(PosterRecyclerBinding.inflate(inflater,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posterList?.get(position))
    }

    fun update(postersPath: List<String>?) {
        posterList = postersPath?.toMutableList()!!
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = posterList!!.size

    class PostViewHolder(private val binding: PosterRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(poster: String?) {
            Glide.with(binding.root)
                .load(poster)
                .fitCenter()
                .into(binding.imageView)
        }
    }
}