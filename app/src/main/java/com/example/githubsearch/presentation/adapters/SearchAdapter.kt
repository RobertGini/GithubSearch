package com.example.githubsearch.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.githubsearch.R
import com.example.githubsearch.databinding.RepoItemBinding
import com.example.githubsearch.domain.RepoItemsEntity

class SearchAdapter(
    private var data: List<RepoItemsEntity>,
    private val onItemClick: (data: RepoItemsEntity) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(data[position], position)
        holder.itemView.setOnClickListener { onItemClick(data[position]) }
    }

    override fun getItemCount(): Int = data.size

    fun addList(response: RepoItemsEntity){
        data += response
        notifyDataSetChanged()
    }

    fun clearList() {
        notifyDataSetChanged()
    }

    class SearchViewHolder(
        item: View
    ) : RecyclerView.ViewHolder(item) {
        val binding = RepoItemBinding.bind(item)
        fun bind(response: RepoItemsEntity, position: Int) = with(binding) {
            rcFullName.text = response.full_name
            rcForks.text = response.forks
            rcOnToggleFavorite.load(R.mipmap.ic_fav)
        }
    }
}