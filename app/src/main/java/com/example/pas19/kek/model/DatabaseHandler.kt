package com.example.pas19.kek

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), IDatabaseHandler {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ReadBooks"
        private const val TABLE_BOOKS = "books"
        private const val TABLE_AUTHORS = "authors"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_PAGES = "pages"
        private const val KEY_DESC = "description"
        private const val KEY_AUTHOR_ID = "authorId"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_AUTHORS_TABLE = ("CREATE TABLE $TABLE_AUTHORS($KEY_ID INTEGER PRIMARY KEY,$KEY_NAME TEXT)")
        db?.execSQL(CREATE_AUTHORS_TABLE)
        val CREATE_BOOKS_TABLE = ("CREATE TABLE $TABLE_BOOKS($KEY_ID INTEGER PRIMARY KEY,$KEY_NAME TEXT,$KEY_PAGES INTEGER,$KEY_AUTHOR_ID INTEGER, $KEY_DESC TEXT)")
        db?.execSQL(CREATE_BOOKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_AUTHORS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")
        onCreate(db)
    }

    override fun addAuthor(author : Author) {
        val db : SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, author.name)
        db.insert(TABLE_AUTHORS, null, values)
        db.close()
    }

    override fun delAuthor(id : Int) {
        val db = this.writableDatabase
        db.delete(TABLE_AUTHORS, KEY_ID + "=?", arrayOf(id.toString()))
        db.close()
    }

    override fun addBook(book : Book) {
        val db : SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, book.name)
        values.put(KEY_PAGES,book.numOfPages)
        values.put(KEY_AUTHOR_ID, book.author.id)
        values.put(KEY_DESC, book.desc)
        db.insert(TABLE_BOOKS, null, values)
        db.close()
    }

    override fun delBook(id : Int) {
        val db = this.writableDatabase
        db.delete(TABLE_BOOKS, KEY_ID + "=?", arrayOf(id.toString()))
        db.close()
    }

    override fun updBook(description: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorAt(id: Int) : Author {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_AUTHORS, arrayOf(KEY_ID, KEY_NAME), "$KEY_ID=?",
            arrayOf(id.toString()), null, null, null, null
        )
        cursor?.moveToFirst()
        db.close()
        return com.example.pas19.kek.Author(Integer.parseInt(cursor.getString(0)), cursor.getString(1))
    }

    override fun getBookAt(id: Int): Book {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_BOOKS, arrayOf(KEY_ID, KEY_NAME, KEY_PAGES, KEY_AUTHOR_ID, KEY_DESC), "$KEY_ID=?",
            arrayOf(id.toString()), null, null, null, null
        )
        cursor?.moveToFirst()
        val author = getAuthorAt(Integer.parseInt(cursor.getString(3)))
        db.close()
        return Book(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)), author, cursor.getString(4))
    }

    override fun getAuthors(): ArrayList<Author> {
        val authorsList = ArrayList<Author>()
        val selectQuery = "SELECT  * FROM $TABLE_AUTHORS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor!!.getString(0))
                val name = cursor.getString(1)
                val author = Author(id,name)
                authorsList.add(author)
            } while (cursor.moveToNext())
        }
        db.close()
        return authorsList
    }

    override fun getBooks(): ArrayList<Book> {
        val booksList = ArrayList<Book>()
        val selectQuery = "SELECT  * FROM $TABLE_BOOKS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor!!.getString(0))
                val name = cursor.getString(1)
                val nOfPages = Integer.parseInt(cursor.getString(2))
                val author = getAuthorAt(Integer.parseInt(cursor!!.getString(3)))
                val desc = cursor.getString(4)
                val book = Book(id, name,nOfPages, author,desc)
                booksList.add(book)
            } while (cursor.moveToNext())
        }
        db.close()
        return booksList
    }
}