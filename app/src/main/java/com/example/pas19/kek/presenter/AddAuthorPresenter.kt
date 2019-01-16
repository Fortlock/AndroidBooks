package com.example.pas19.kek

class AddAuthorPresenter(private val view: IAddAuthorView, private val bookModel : BookModel){
    fun addAuthor(name : String){
        bookModel.addAuthor(name)
    }
}

