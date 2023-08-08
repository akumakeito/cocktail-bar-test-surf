package ru.akumakeito.cocktailbar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.akumakeito.cocktailbar.SingleLiveEvent
import ru.akumakeito.cocktailbar.dto.Cocktail
import ru.akumakeito.cocktailbar.repository.CocktailRepository
import javax.inject.Inject

private val empty = Cocktail(
    0,
    "",
    null,
    mutableListOf(),
    "",
    ""
)

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: CocktailRepository
): ViewModel() {
    val data : LiveData<List<Cocktail>> = repository.data.asLiveData()

    private val edited = MutableLiveData(empty)
    private val _cocktailCreated = SingleLiveEvent<Unit>()
    val cocktailCreated : LiveData<Unit>
        get() =_cocktailCreated


    fun getCurrentCocktail() = edited.value

    fun save() {
        edited.value?.let {
            _cocktailCreated.value = Unit
            viewModelScope.launch {
                repository.addCocktail(it)
            }
            edited.value = empty

        }
    }



    fun edit(cocktail: Cocktail) {
        edited.value = cocktail
    }

    fun changeTitle(title: String) {
        var text = title.trim()
        if (edited.value?.title == text) {
            return
        }
        edited.value = edited.value?.copy(title = text)
    }

    fun changeDescription(descrip: String) {
        var text = descrip.trim()
        if (edited.value?.description == text) {
            return
        }
        edited.value = edited.value?.copy(description = text)
    }

    fun changeRecipe(recipe: String) {
        var text = recipe.trim()
        if (edited.value?.recipe == text) {
            return
        }
        edited.value = edited.value?.copy(recipe = text)
    }

    fun addIngredient(ingredient: String) {
        edited.value?.ingredients?.add(ingredient)
    }


}