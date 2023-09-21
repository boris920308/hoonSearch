package hoon.example.hoonsearch

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import hoon.example.hoonsearch.databinding.ItemHomeMainBinding
import hoon.example.hoonsearch.network.NaverSearchResponseDetailItem


class HomeAdapter() : ListAdapter<NaverSearchResponseDetailItem, HomeAdapter.HomeAdapterMainViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterMainViewHolder {
        val binding = ItemHomeMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeAdapterMainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterMainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeAdapterMainViewHolder(
        private val binding: ItemHomeMainBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: NaverSearchResponseDetailItem) {
            binding.tvTitle.text = item.title
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<NaverSearchResponseDetailItem>() {
            override fun areItemsTheSame(oldItem: NaverSearchResponseDetailItem, newItem: NaverSearchResponseDetailItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: NaverSearchResponseDetailItem, newItem: NaverSearchResponseDetailItem): Boolean {
                return oldItem == newItem
            }
        }
    }


}