package com.example.pas19.kek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity(), IAddBookView, AdapterView.OnItemSelectedListener {
    private var presenter : AddBookPresenter? = AddBookPresenter(this, BookModel(this))
    private var authors : ArrayList<Author>? = null
    var selectedId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        authors = presenter?.getAuthors()
        authorSpinner.onItemSelectedListener = this
        val aa= ArrayAdapter(this, android.R.layout.simple_spinner_item, authors)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        authorSpinner.adapter = aa
        delButton.isEnabled = false
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        delButton.isEnabled = false
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        delButton.isEnabled = true
        selectedId = authors?.get(position)?.id
    }

    fun onOKClickListener(view : View) {
        val np : Int
        try {
            np=Integer.parseInt(numPText.text.toString())
        }
        catch (e : NumberFormatException) {
            Toast.makeText(this, "Ошибка добавления", Toast.LENGTH_SHORT).show()
            return
        }
        if (!nameBookText.text.isBlank() && selectedId!=null){
            presenter?.addBook(nameBookText.text.toString(), np, selectedId!!, descrText.text.toString())
            this.finish()
        }
        else
            Toast.makeText(this, "Ошибка добавления", Toast.LENGTH_SHORT).show()
    }

    fun onCancelClickListener(view : View) {
        this.finish()
    }


}
