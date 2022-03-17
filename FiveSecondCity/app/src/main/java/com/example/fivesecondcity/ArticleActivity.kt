package com.example.fivesecondcity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import io.noties.markwon.Markwon
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.ext.tasklist.TaskListPlugin
import io.noties.markwon.image.ImagesPlugin
import io.noties.markwon.linkify.LinkifyPlugin
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
                    val markwon = Markwon.builder(applicationContext)
                        .usePlugin(StrikethroughPlugin.create())
                        .usePlugin(ImagesPlugin.create())
                        .usePlugin(LinkifyPlugin.create())
                        .usePlugin(TablePlugin.create(applicationContext))
                        .usePlugin(TaskListPlugin.create(applicationContext))
                        .build()
                    markwon.setMarkdown(findViewById<TextView>(R.id.textView), article.content)

                    actionBar?.title = article.title
                    supportActionBar?.title = article.title
                }
            }
        })
    }
}
