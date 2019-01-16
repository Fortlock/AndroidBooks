package com.example.pas19.kek

interface IDatabaseHandler {
    fun addAuthor(author : Author)
    fun delAuthor(id : Int)
    fun addBook(book : Book)
    fun delBook(id : Int)
    fun updBook(description: String)
    fun getAuthorAt(id : Int) : Author
    fun getBookAt(id : Int) : Book
    fun getBooks() : ArrayList<Book>
    fun getAuthors(): ArrayList<Author>
}

interface IApiHandler {
    fun getBooks() : ArrayList<Book>
    fun getBook(index : Int) : Book?
}

interface IBookView {
    fun onAuthorsLoad(authors : ArrayList<Author>)
    fun onBooksLoad(books : ArrayList<Book>)
}

interface IAddAuthorView
interface IAddBookView
interface IInfoBookView {
    fun onBookLoad(book : Book)
}