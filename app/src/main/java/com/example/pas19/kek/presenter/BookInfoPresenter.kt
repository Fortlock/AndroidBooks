package com.example.pas19.kek

import android.os.AsyncTask

class BookInfoPresenter(private val view: IInfoBookView, private val bookModel : BookModel) {
    fun getBook(id : Int) {
        val book = bookModel.getBookAt(id)
        view.onBookLoad(book)
    }

    fun getBookFromServer(index : Int) {
        loadBookAPITask().execute(index)
    }

    inner class loadBookAPITask() : AsyncTask<Int, Void, Boolean>() {
        var res : Book?=null
        override fun doInBackground(vararg p0: Int?) : Boolean{
            res = bookModel.getBookFromServer(p0[0]?: return false)
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            view.onBookLoad(res!!)
            super.onPostExecute(result)
        }
    }

    fun delBook(id : Int) {
        bookModel.delBook(id)
    }


}