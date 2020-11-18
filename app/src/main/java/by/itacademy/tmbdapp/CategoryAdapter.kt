package by.itacademy.tmbdapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.databinding.CategoryRecyclerBinding

class CategoryAdapter(
    private val category: List<Int>,
    private val listItemActionListener: ListItemActionListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryRecyclerBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            bind(category[position])
            itemView.setOnClickListener { listItemActionListener.onItemClick(position) }
        }
    }

    override fun getItemCount(): Int = category.size

    class CategoryViewHolder(private var binding: CategoryRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Int) {
            binding.textView.text = film.toString()
        }
    }
}