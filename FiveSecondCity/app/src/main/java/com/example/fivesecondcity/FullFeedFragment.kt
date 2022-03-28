package com.example.fivesecondcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_full_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FullFeedFragment : Fragment() {

    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutFull)
        refreshLayout.setOnRefreshListener {
            fetchArticles()
        }

//        fetchArticles()
    }

    private fun fetchArticles() {
        refreshLayout.isRefreshing = true

        ArticleAPI().getArticles().enqueue(object : Callback<List<Article>> {
            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                refreshLayout.isRefreshing = false
                val articlePreviews = response.body()

                articlePreviews?.let {
                    showPreviews(it)
                }
            }
        })
    }

    private fun showPreviews(previews: List<Article>) {
        recyclerViewArticles.layoutManager = LinearLayoutManager(activity)
        recyclerViewArticles.adapter = context?.let { ArticleAdapter(previews, it) }
    }
}