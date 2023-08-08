package ru.akumakeito.cocktailbar.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.akumakeito.cocktailbar.entity.CocktailEntity


@Database ( entities = [CocktailEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun cocktailDao() : CocktailDao
}