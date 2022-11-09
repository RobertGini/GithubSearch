package com.example.githubsearch.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.databinding.RepoItemBinding

class SaveAdapter(
    private val onItemClick: (data: RepoDb) -> Unit
): ListAdapter<RepoDb, SaveAdapter.RepoViewHolder>(RepoComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener { onItemClick(currentList[position]) }
    }

    class RepoViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = RepoItemBinding.bind(item)
        fun bind(repoDb: RepoDb) = with(binding) {
            rcForks.text = repoDb.forks
            rcFullName.text = repoDb.full_name
        }
        companion object {
            fun create(parent: ViewGroup) : RepoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.repo_item, parent, false)
                return  RepoViewHolder(view)
            }
        }
    }

    class RepoComparator: DiffUtil.ItemCallback<RepoDb>() {
        override fun areItemsTheSame(oldItem: RepoDb, newItem: RepoDb): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RepoDb, newItem: RepoDb): Boolean {
            return oldItem.full_name == newItem.full_name
        }

    }
}