package com.example.pas19.kek

import android.widget.TextView
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlin.contracts.contract


internal class AuthorsDataAdapter(val context: Context, private val authors: List<Author>) :
    RecyclerView.Adapter<AuthorsDataAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsDataAdapter.ViewHolder {

        val view = inflater.inflate(R.layout.view_author, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorsDataAdapter.ViewHolder, position: Int) {
        val author = authors[position]

        holder.nameView.text = author.name
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { NavigationActivity.onAuthorItemClick(authors[adapterPosition].id) }
        }
        internal val nameView: TextView = view.findViewById(R.id.authorName)
    }
}

internal class BooksDataAdapter(val context: Context, private val books: List<Book>, val na : NavigationActivity) :
    RecyclerView.Adapter<BooksDataAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksDataAdapter.ViewHolder {

        val view = inflater.inflate(R.layout.view_book, parent, false)
        return ViewHolder(view,na)
    }

    override fun onBindViewHolder(holder: BooksDataAdapter.ViewHolder, position: Int) {
        val book = books[position]
        holder.nameView.text = book.name
        holder.authorView.text = book.author.name
    }

    override fun getItemCount(): Int {
        return books.size
    }


    inner class ViewHolder internal constructor(view: View, na : NavigationActivity) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { na.onBookItemClick(books[adapterPosition].id) }
        }
        internal val nameView: TextView = view.findViewById(R.id.authorText)
        internal val authorView: TextView = view.findViewById(R.id.bookText)
    }
}

