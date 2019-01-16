package com.example.pas19.kek

import android.os.AsyncTask

class BookPresenter(private val bookView: IBookView, val bookModel : BookModel){
    var task : AsyncTask<Void,Void,Boolean>? = null

    fun loadAuthors() {
        loadAuthorsDBTask().execute()
    }

    fun loadBooks() {
        loadBooksDBTask().execute()
    }

    fun loadBooksFromServer() {
        task = loadBooksAPITask().execute()

    }

    fun allTaskCancel() {
        task?.cancel(true)
    }

    inner class loadAuthorsDBTask() : AsyncTask<Void, Void, Boolean>() {
        var res : ArrayList<Author>?=null
        override fun doInBackground(vararg p0: Void?) : Boolean{
            res = bookModel.getAuthors()
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            bookView.onAuthorsLoad(res!!)
            super.onPostExecute(result)
        }
    }

    inner class loadBooksDBTask() : AsyncTask<Void, Void, Boolean>() {
        var res : ArrayList<Book>?=null
        override fun doInBackground(vararg p0: Void?) : Boolean{
            res = bookModel.getBooks()
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            bookView.onBooksLoad(res!!)
            super.onPostExecute(result)
        }
    }

    inner class loadBooksAPITask() : AsyncTask<Void, Void, Boolean>() {
        var res : ArrayList<Book>?=null
        override fun doInBackground(vararg p0: Void?) : Boolean{
            res = bookModel.getBooksFromServer()
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            bookView.onBooksLoad(res!!)
            super.onPostExecute(result)
        }
    }
}