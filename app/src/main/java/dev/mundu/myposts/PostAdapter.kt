package dev.mundu.myposts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.mundu.myposts.databinding.PostsListItemBinding

class PostAdapter (var context: Context, var postsList: List<Post>):
    RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding = PostsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currPost = postsList.get(position)
        holder.binding.tvUserId.text = currPost.UserId.toString()
        holder.binding.tvId.text = currPost.id.toString()
        holder.binding.tvTitle.text = currPost.title
        holder.binding.tvBody.text = currPost.body

    }

    override fun getItemCount(): Int {
        return postsList.size
    }

}

class PostViewHolder(var binding : PostsListItemBinding):
    RecyclerView.ViewHolder(binding.root)