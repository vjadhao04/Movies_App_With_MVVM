package com.example.moviesapp.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R

class LoaderAdapter(val context: Context) :LoadStateAdapter<LoaderAdapter.LoadVH>() {

    class LoadVH(itemView: View) :RecyclerView.ViewHolder(itemView){
        val pbar=itemView.findViewById<ProgressBar>(R.id.pbar)
        fun bind(loadState: LoadState){
            pbar.isVisible=loadState is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoadVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadVH {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.loader_item,parent,false)
        return LoadVH(view)
    }
}