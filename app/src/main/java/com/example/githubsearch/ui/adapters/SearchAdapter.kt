package com.example.githubsearch.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.githubsearch.R
import com.example.githubsearch.data.model.GithubResponse
import com.example.githubsearch.databinding.RepoItemBinding

class SearchAdapter(
    private var repoList: MutableList<GithubResponse>,
    private val onItemClick: (position: Int) -> Unit
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
        holder.bind(
            repoList[position],
            position
        )
        holder.itemView.setOnClickListener { onItemClick(position) }
    }

    override fun getItemCount(): Int = repoList.size

    fun addList(githubResponse: GithubResponse){
        repoList += githubResponse
        notifyDataSetChanged()
    }

    fun clearList() {
        repoList.clear()
        notifyDataSetChanged()
    }

    class SearchViewHolder(
        item: View
    ) : RecyclerView.ViewHolder(item) {
        val binding = RepoItemBinding.bind(item)
        fun bind(githubResponse: GithubResponse, position: Int) = with(binding) {
            rcFullName.text = githubResponse.items[position].full_name
            rcForks.text = githubResponse.items[position].forks
            rcOnToggleFavorite.load(R.mipmap.ic_fav)
        }
    }
}