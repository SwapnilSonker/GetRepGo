package dev.swapnil.getrepgo.ui.repo

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.getrepgo.databinding.FragmentAddRepoBinding


@AndroidEntryPoint
class AddRepoFragment : Fragment() {

    private lateinit var binding: FragmentAddRepoBinding
    private val viewModel by viewModels<AddRepoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.message.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.message.value = ""
            }
        }

        binding.buttonAdd.setOnClickListener {
            hideKeyboard()
            val owner = binding.textFieldOwner.editText?.text.toString()

            if (owner.isNotEmpty()) {
                binding.textFieldOwner.error = null
            } else {
                binding.textFieldOwner.error = "Owner cannot be empty"
                return@setOnClickListener
            }

            val repo = binding.textFieldRepo.editText?.text.toString()

            if (repo.isNotEmpty()) {
                binding.textFieldRepo.error = null
            } else {
                binding.textFieldRepo.error = "Repo cannot be empty"
                return@setOnClickListener
            }

            viewModel.addRepo(owner, repo)
        }
    }

    private fun hideKeyboard(): Boolean {
        return (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
    }

}