package ru.akumakeito.cocktailbar.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.akumakeito.cocktailbar.dto.Cocktail

@Entity
data class CocktailEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val description : String?,
    val ingredients : MutableList<String>,
    val recipe : String?,
    val photo : String?
) {
    fun toDto() = Cocktail(
        id,
        title,
        description,
        ingredients,
        recipe,
        photo
    )

    companion object {
        fun fromDto( dto : Cocktail) =
            CocktailEntity(
                dto.id,
                dto.title,
                dto.description,
                dto.ingredients,
                dto.recipe,
                dto.photo
            )
    }

}

fun List<CocktailEntity>.toDto() : List<Cocktail> = map(CocktailEntity::toDto)