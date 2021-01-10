package by.itacademy.tmbdapp.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.databinding.FeedRecyclerBinding
import by.itacademy.tmbdapp.databinding.OverviewRecyclerBinding
import by.itacademy.tmbdapp.databinding.SimilarRecyclerBinding
import by.itacademy.tmbdapp.fragments.TAG
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
import by.itacademy.tmbdapp.uimodel.UIMovieModel
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = mutableListOf<UIMovieModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.feed_recycler -> FeedViewHolder(FeedRecyclerBinding.inflate(inflater,
                parent,
                false))
            R.layout.overview_recycler -> OverViewHolder(OverviewRecyclerBinding.inflate(inflater,
                parent,
                false))
            else -> SimilarViewHolder(SimilarRecyclerBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        when (holder) {
            is FeedViewHolder -> holder.bind(item as UIMovieModel.FeedItem)
            is OverViewHolder -> holder.bind(item as UIMovieModel.OverView)
            is SimilarViewHolder -> holder.bind(item as UIMovieModel.SimilarMovies)
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is UIMovieModel.FeedItem -> R.layout.feed_recycler
            is UIMovieModel.OverView -> R.layout.overview_recycler
            else -> R.layout.similar_recycler
        }
    }

    fun update(list: List<UIMovieModel>) {
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class FeedViewHolder(private val binding: FeedRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UIMovieModel.FeedItem) {
            with(binding) {
                title.text = item.title
                releaseDate.text = item.releaseDate
                genres.text = item.genres
                rating.rating = item.rating
                Glide.with(poster)
                    .load(item.posterPath)
                    .into(poster)
                rating.setOnRatingBarChangeListener { ratingBar, fl, b ->
                    if (b) {
                        MoviePresenterImpl(context as MovieActivityListener).rateMovie(item.id, fl)
                        ratingBar.setIsIndicator(true)
                    }
                }
            }
        }
    }

    inner class OverViewHolder(private val binding: OverviewRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UIMovieModel.OverView) {
            binding.overview.text = item.overView
        }
    }

    inner class SimilarViewHolder(private val binding: SimilarRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UIMovieModel.SimilarMovies) {
            Log.d(TAG,"SimilarViewHolder size=${item.list?.size}")
            val similarAdapter = PosterAdapter()
            binding.similarRecycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = similarAdapter
            }
            similarAdapter.update(item.list?.toList())
        }
    }
}