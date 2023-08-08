package ru.akumakeito.cocktailbar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.akumakeito.cocktailbar.databinding.FragmentNewCocktailBinding
import ru.akumakeito.cocktailbar.viewModel.ViewModel

@AndroidEntryPoint
class FragmentNewCocktail : Fragment() {
    private val viewModel: ViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = FragmentNewCocktailBinding.inflate(inflater, container, false)

//        binding.btnAddIngredient.setOnClickListener {
//
//        }

        binding.btnSave.setOnClickListener {
            binding.let {

                if (it.edtxtTitle.text.isNullOrBlank()) {
                    it.inplayoutTitle.isErrorEnabled = true
                } else {
                    viewModel.changeTitle(it.edtxtTitle.text.toString())
                    viewModel.changeDescription(it.edtxtCocktailDescrip.text.toString())
                    viewModel.changeRecipe(it.edtxtCocktailRecipe.text.toString())
                    viewModel.save()
                    viewModel.cocktailCreated.observe(viewLifecycleOwner) {
                        findNavController().navigateUp()
                    }
                }
            }


        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}