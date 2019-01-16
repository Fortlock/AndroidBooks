package com.example.pas19.kek

data class Book(val id : Int, val name: String, val numOfPages : Int, val author : Author, val desc : String){
    constructor(_name : String, _numOfPages : Int, _author : Author, _desc : String) : this(0,_name,_numOfPages,_author,_desc)
}