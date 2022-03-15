package com.example.fivesecondcity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        actionBar?.title = ""
        supportActionBar?.title = ""

        val id = intent.getIntExtra(ID_VAL, 0)
        ArticleAPI().getArticle(id).enqueue(object : Callback<Article> {
            override fun onFailure(call: Call<Article>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                val article = response.body()

                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                progressBar.visibility = View.GONE

                if (article != null) {
                    val textView = findViewById<TextView>(R.id.textView).apply {
                        text = article.content
                    }
                    actionBar?.title = article.title
                    supportActionBar?.title = article.title
                }
            }
        })
    }
}
