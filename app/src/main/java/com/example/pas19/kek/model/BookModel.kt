package com.example.pas19.kek

import android.content.Context

class BookModel(context: Context) {
    private val db : IDatabaseHandler = DatabaseHandler(context)
    private val apiHandler : IApiHandler = ApiHandler()

    fun addBook(name : String, numOfPages : Int, authorId : Int, desc : String) {
        val author = db.getAuthorAt(authorId)
        val book = Book(name,numOfPages,author, desc)
        db.addBook(book)
    }
    fun getBookAt(id : Int) : Book = db.getBookAt(id)

    fun getBooks() : ArrayList<Book> = db.getBooks()

    fun getBooksFromServer() : ArrayList<Book> =
        apiHandler.getBooks()

    fun getBookFromServer(index : Int) : Book? = apiHandler.getBook(index)

    fun delBook(id : Int) {
        db.delBook(id)
    }

    fun addAuthor(name: String) {
        val author = Author(name)
        db.addAuthor(author)
    }
    fun getAuthorAt(id : Int) : Author = db.getAuthorAt(id)

    fun getAuthors() : ArrayList<Author> = db.getAuthors()

    fun delAuthor(id : Int) {
        db.delAuthor(id)
    }
}