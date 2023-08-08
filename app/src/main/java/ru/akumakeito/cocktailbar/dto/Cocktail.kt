package ru.akumakeito.cocktailbar.dto

data class Cocktail(
    val id : Int,
    val title : String,
    val description : String?,
    val ingredients : MutableList<String>,
    val recipe : String?,
    val photo : String?
)
