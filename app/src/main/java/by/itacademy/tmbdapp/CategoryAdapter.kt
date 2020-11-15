package by.itacademy.tmbdapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val listItemActionListener: ListItemActionListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val category= mutableListOf<Int>().apply { for (i in 1..100)add(i) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.category_recycler,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            bind(category[position])
            itemView.setOnClickListener { listItemActionListener.onItemClick(position) }
        }
    }

    override fun getItemCount(): Int=category.size

    class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private var number=itemView.findViewById<TextView>(R.id.textView)
        fun bind(film: Int){
            number.text=film.toString()
        }
    }
}