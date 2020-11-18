package by.itacademy.tmbdapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.CategoryAdapter
import by.itacademy.tmbdapp.ListItemActionListener
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.databinding.FragmentUserListBinding

class UserListFragment : Fragment(), ListItemActionListener {
    private val category = mutableListOf<Int>().apply { for (i in 201..250) add(i) }
    private lateinit var binding: FragmentUserListBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserListBinding.bind(view)
        binding.usersListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CategoryAdapter(category, this@UserListFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onItemClick(position: Int) {
    }
}