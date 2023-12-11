package com.example.instagram.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Models.Post
import com.example.instagram.databinding.MyPostRvDesgineBinding
import com.squareup.picasso.Picasso

class MyPostRvAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<MyPostRvAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: MyPostRvDesgineBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MyPostRvDesgineBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.postImage
        Picasso.get().load(postList.get(position).postUrl).into(holder.binding.postImage)
    }

}