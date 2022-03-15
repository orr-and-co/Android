package com.example.fivesecondcity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_article_preview.view.*

class ArticleAdapter(val articles: List<Article>, val context: Context) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_article_preview, parent, false)
        )
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.view.textViewTitle.text = article.title
        holder.view.textViewContent.text = article.content

        holder.view.button.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(ID_VAL, article.id)
            context.startActivity(intent)
        })
    }

    class ArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}