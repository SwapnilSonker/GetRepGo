package dev.swapnil.getrepgo.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View? {
        binding = FragmentAddRepoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            val owner = binding.textFieldOwner.editText?.text.toString()
            val repo = binding.textFieldRepo.editText?.text.toString()

            viewModel.addRepo(owner, repo)
        }
    }

}