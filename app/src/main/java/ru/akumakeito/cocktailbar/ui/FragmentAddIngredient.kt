package ru.akumakeito.cocktailbar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.akumakeito.cocktailbar.databinding.FragmentAddIngredientBinding
import ru.akumakeito.cocktailbar.viewModel.ViewModel


@AndroidEntryPoint
class FragmentAddIngredient : Fragment() {
    private val viewModel: ViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         super.onCreateView(inflater, container, savedInstanceState)

        val binding = FragmentAddIngredientBinding.inflate(layoutInflater,container, false)

        binding.btnAdd.setOnClickListener{
            if (binding.edtxtIngredientName.text.isNullOrBlank()) {
                binding.inplayoutTitle.isErrorEnabled = true
            } else {
                val ingredient = binding.edtxtIngredientName.text.toString()
                viewModel.addIngredient(ingredient)
                findNavController().navigateUp()
            }

        }

        binding.btnCancel.setOnClickListener{
            findNavController().navigateUp()
        }

        return binding.root
    }


}