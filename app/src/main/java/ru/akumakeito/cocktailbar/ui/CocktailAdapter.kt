package ru.akumakeito.cocktailbar.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.akumakeito.cocktailbar.databinding.CardCocktailBinding
import ru.akumakeito.cocktailbar.dto.Cocktail

interface OnInteractionListener{
    fun onShow(cocktail : Cocktail) {}
}

class CocktailAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Cocktail, CocktailViewHolder>(CocktailViewHolder.CocktailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = CardCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.bind(cocktail)
    }
}

class CocktailViewHolder(
    private val binding: CardCocktailBinding,
    private val onInteractionListener : OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cocktail: Cocktail) {
        binding.strCocktailName.text = cocktail.title
        binding.imgCocktail.setOnClickListener{
            onInteractionListener.onShow(cocktail)
        }
    }

    class CocktailDiffCallback : DiffUtil.ItemCallback<Cocktail>() {
        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem == newItem
        }

    }

}


