package com.example.pas19.kek

data class Author(val id : Int, val name: String) {
    constructor(_name : String) : this(0,_name)

    override fun toString(): String = name
}