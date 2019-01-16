package com.example.pas19.kek

class AddBookPresenter(private val view: IAddBookView, private val bookModel : BookModel){
    fun addBook(name : String, numOfPages : Int, authorId : Int, desc : String) {
        bookModel.addBook(name, numOfPages, authorId, desc)
    }

    fun getAuthors() : ArrayList<Author> = bookModel.getAuthors()
}