package com.example.pas19.kek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity(), IInfoBookView {
    private val presenter = BookInfoPresenter(this, BookModel(this))
    var id : Int = -1
    var type : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        id = intent.getIntExtra("BOOK_ID",-1)
        if (intent.getBooleanExtra("TYPE",false)) {
            presenter.getBookFromServer(id)
            delButton.visibility = View.INVISIBLE
        }
        else {
            presenter.getBook(id)
            delButton.visibility = View.INVISIBLE
        }
    }

    override fun onBookLoad(book: Book) {
        nameBookText.text = book.name
        authorSpinner.text = book.author.name
        numPText.text = book.numOfPages.toString()
        descrText.text = book.desc
    }

    fun onDeleteClickListener(view : View) {
        presenter.delBook(id)
        this.finish()
    }
}
