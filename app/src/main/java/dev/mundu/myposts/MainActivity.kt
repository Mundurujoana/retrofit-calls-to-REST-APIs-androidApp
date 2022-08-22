package dev.mundu.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mundu.myposts.databinding.ActivityMainBinding
import dev.mundu.myposts.databinding.PostsListItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
    }
    fun fetchPosts() {
        var apiclient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiclient.getPosts()
        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    var posts = response.body()
                    if (posts != null) {
                        display(posts)

                    }

                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }

        })
    }

    fun display(postslist: List<Post>) {
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        var postAdapter = PostAdapter(postslist)
        binding.rvPosts.adapter = postAdapter


    }

}
