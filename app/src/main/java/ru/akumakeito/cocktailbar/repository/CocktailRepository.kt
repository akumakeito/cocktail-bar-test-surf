package ru.akumakeito.cocktailbar.repository

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.akumakeito.cocktailbar.dao.CocktailDao
import ru.akumakeito.cocktailbar.dto.Cocktail
import ru.akumakeito.cocktailbar.entity.CocktailEntity
import ru.akumakeito.cocktailbar.entity.toDto
import javax.inject.Inject

interface CocktailRepository {
    val data: Flow<List<Cocktail>>

    suspend fun getCocktailById(id: Int): Cocktail

    suspend fun addCocktail(cocktail: Cocktail)

    suspend fun isEmpty(): Boolean

}

class CocktailRepositoryImpl @Inject constructor(
    private val dao: CocktailDao
) : CocktailRepository {
    override val data: Flow<List<Cocktail>> =
        dao.getAll().map(List<CocktailEntity>::toDto)
            .flowOn(Dispatchers.Default)


    override suspend fun getCocktailById(id: Int): Cocktail {
        val result = dao.getById(id)
        return result.toDto()
    }

    override suspend fun addCocktail(cocktail: Cocktail) {
        dao.insert(CocktailEntity.fromDto(cocktail))
    }

    override suspend fun isEmpty(): Boolean = dao.isEmpty()
}