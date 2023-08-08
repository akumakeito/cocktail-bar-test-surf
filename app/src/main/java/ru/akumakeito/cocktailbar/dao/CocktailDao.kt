package ru.akumakeito.cocktailbar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverter
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import ru.akumakeito.cocktailbar.dto.Cocktail
import ru.akumakeito.cocktailbar.entity.CocktailEntity


@Dao
interface CocktailDao {
    @Query("SELECT * FROM CocktailEntity")
    fun getAll(): Flow<List<CocktailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cocktail: CocktailEntity)

    @Query("SELECT COUNT(*) == 0 FROM CocktailEntity")
    suspend fun isEmpty() : Boolean

    @Query("SELECT * FROM CocktailEntity WHERE id = :id")
    suspend fun getById(id: Int) : CocktailEntity

}

class Converters {

    @TypeConverter
    fun listToJson(value: MutableList<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}