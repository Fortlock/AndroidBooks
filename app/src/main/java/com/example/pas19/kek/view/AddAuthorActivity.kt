package com.example.pas19.kek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_author.*

class AddAuthorActivity : AppCompatActivity(), IAddAuthorView {
    private var presenter : AddAuthorPresenter? = AddAuthorPresenter(this, BookModel(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_author)
    }

    fun onOKClickListener(view : View) {
        if (!nameText.text.isBlank()) {
            presenter?.addAuthor(nameText.text.toString())
            this.finish()
        }
        else
            Toast.makeText(this, "Ошибка добавления", Toast.LENGTH_SHORT).show()

    }

    fun onCancelClickListener(view : View) {
        this.finish()
    }
}
