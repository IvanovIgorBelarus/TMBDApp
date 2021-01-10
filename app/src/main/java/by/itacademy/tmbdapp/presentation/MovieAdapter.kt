package by.itacademy.tmbdapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.uimodel.UIMovieModel
import by.itacademy.tmbdapp.databinding.FeedRecyclerBinding
import by.itacademy.tmbdapp.databinding.OverviewRecyclerBinding
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = mutableListOf<UIMovieModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.feed_recycler -> FeedViewHolder(FeedRecyclerBinding.inflate(inflater,
                parent,
                false))

            else -> OverViewHolder(OverviewRecyclerBinding.inflate(inflater, parent, false))

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        when (holder) {
            is FeedViewHolder -> holder.bind(item as UIMovieModel.FeedItem)
            is OverViewHolder -> holder.bind(item as UIMovieModel.OverView)
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is UIMovieModel.FeedItem -> R.layout.feed_recycler
            else -> R.layout.overview_recycler

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
                releaseDate.text = item.title
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
}