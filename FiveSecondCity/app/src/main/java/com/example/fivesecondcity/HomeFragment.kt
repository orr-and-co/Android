package com.example.fivesecondcity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ID_VAL = "com.example.fivesecondcity.ArticleID"

class HomeFragment : Fragment() {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var articlePreviews: List<Article>
    private var isFiltered = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Make list of API interest strings for the currently selected interests
        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
//        if (preferences != null) {
//            interestStrings = interestsMap.filter { (key, _) -> preferences.getBoolean(key, false)}.values as List<String>
//        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFiltered = false

        // Get articles
        viewModel = ViewModelProvider(this)[ArticleViewModel::class.java]
        refreshLayout.setOnRefreshListener {
            fetchArticles()
        }
        fetchArticles()

        // Show only the ones that ought to be shown
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    isFiltered = false
                    showPreviews()
                } else {
                    isFiltered = true
                    showPreviews()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
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
                articlePreviews = response.body()!!

                showPreviews()
            }
        })
    }

    private fun showPreviews() {
        recyclerViewArticles.layoutManager = LinearLayoutManager(activity)
        val interestMessage = view?.findViewById<TextView>(R.id.textView)
        if (isFiltered) {
            if(interestStrings.isEmpty())
            {
                if (interestMessage != null) {
                    interestMessage.visibility = View.VISIBLE
                }
                recyclerViewArticles.adapter = context?.let {
                    ArticleAdapter(emptyList(), it)
                }
            }
            else
            {
                if (interestMessage != null) {
                    interestMessage.visibility = View.GONE
                }
                recyclerViewArticles.adapter = context?.let {
                    ArticleAdapter(articlePreviews.filter { article ->
                        article.interests.any(interestStrings::contains)
                    }, it)
                }
            }
        } else {
            if (interestMessage != null) {
                interestMessage.visibility = View.GONE
            }
            recyclerViewArticles.adapter = context?.let { ArticleAdapter(articlePreviews, it) }
        }
    }
}