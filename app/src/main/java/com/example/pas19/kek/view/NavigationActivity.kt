package com.example.pas19.kek

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity(), IBookView {
    private val presenter = BookPresenter(this, BookModel(this))
    private var tab = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_authors -> {
                tab = 1
                labelAName.visibility = View.VISIBLE
                labelBName.visibility = View.INVISIBLE
                labelAuthor.visibility= View.INVISIBLE
                items_view.adapter = null
                addButton.show()
                addButton.setOnClickListener { onAddAuthorClickListener() }
                presenter.allTaskCancel()
                presenter.loadAuthors()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_books -> {
                tab = 0
                labelAName.visibility = View.INVISIBLE
                labelBName.visibility = View.VISIBLE
                labelAuthor.visibility= View.VISIBLE
                items_view.adapter = null
                addButton.show()
                addButton.setOnClickListener { onAddBookClickListener() }
                presenter.allTaskCancel()
                presenter.loadBooks()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_random -> {
                tab = 2
                labelAName.visibility = View.INVISIBLE
                labelBName.visibility = View.VISIBLE
                labelAuthor.visibility= View.VISIBLE
                items_view.adapter = null
                addButton.hide()
                addButton.setOnClickListener { onAddBookClickListener() }
                presenter.allTaskCancel()
                presenter.loadBooksFromServer()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addButton.setOnClickListener { onAddBookClickListener() }
        presenter.loadBooks()
        labelAName.visibility = View.INVISIBLE
        labelBName.visibility = View.VISIBLE
        labelAuthor.visibility= View.VISIBLE
    }

    override fun onAuthorsLoad(authors : ArrayList<Author>)  {
        items_view.adapter = AuthorsDataAdapter(this, authors)
    }

    override fun onBooksLoad(books : ArrayList<Book>) {
        items_view.adapter = BooksDataAdapter(this, books, this)
    }

    private fun onAddBookClickListener() {
        val intent = Intent(this,AddBookActivity::class.java)
        startActivity(intent)
    }

    private fun onAddAuthorClickListener() {
        val intent = Intent(this,AddAuthorActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        if (tab==0)
            presenter.loadBooks()
        else if (tab==1)
            presenter.loadBooks()
        else
            presenter.loadBooksFromServer()
        super.onResume()
    }
    companion object {
        fun onAuthorItemClick(id : Int){
        }

    }
    fun onBookItemClick(id : Int) {
        val intent = Intent(this, BookActivity::class.java)
        intent.putExtra("BOOK_ID", id)
        when(tab){
            0->intent.putExtra("TYPE", false)
            2->intent.putExtra("TYPE", true)
        }
        startActivity(intent)
    }

}
