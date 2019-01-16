package com.example.pas19.kek
import android.os.AsyncTask
import khttp.responses.Response
import java.lang.Exception

class ApiHandler : IApiHandler {
    companion object {
        const val accessURL : String = "https://api.jsonbin.io/b/5c3e973e7b31f426f859843c/1"
    }

    override fun getBooks(): ArrayList<Book> {
        val res = ArrayList<Book>()
        try {
            val response = khttp.get(accessURL)
            val books = response.jsonObject.getJSONArray("books")
            for (i in 0 until books.length()) {
                val jsonBook = books.getJSONObject(i)
                var id = i
                var name = jsonBook.getString("name")
                var nop = jsonBook.getInt("numOfPages")
                var author = jsonBook.getJSONObject("author")
                var aName = author.getString("name")
                var desc = jsonBook.getString("description")
                res.add(Book(id, name, nop, Author(aName), desc))
            }
        }
        catch (e: Exception){

        }
        return res
    }

    override fun getBook(index : Int) : Book? {
        var res : Book?
        try {
            val response = khttp.get(accessURL)
            val books = response.jsonObject.getJSONArray("books")
            val jsonBook = books.getJSONObject(index)
            var name = jsonBook.getString("name")
            var nop = jsonBook.getInt("numOfPages")
            var author = jsonBook.getJSONObject("author")
            var aName = author.getString("name")
            var desc = jsonBook.getString("description")
            res=Book(name, nop, Author(aName), desc)
        }
        catch (e: Exception){
            res=null
        }
        return res
    }
}