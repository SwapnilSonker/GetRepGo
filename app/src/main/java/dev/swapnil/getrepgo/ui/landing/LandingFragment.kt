package dev.swapnil.getrepgo.ui.landing


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.getrepgo.R
import dev.swapnil.getrepgo.databinding.FragmentLandingBinding

@AndroidEntryPoint
class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding

    private val viewModel by viewModels<LandingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repo_list,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addRepo ->{
                findNavController().navigate(R.id.action_landingScreen_to_repoScreen)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.repos.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.listRepos.visibility = View.GONE
                binding.layoutEmpty.visibility = View.VISIBLE

                binding.buttonAddNow.setOnClickListener {
                    findNavController().navigate(R.id.action_landingScreen_to_repoScreen)
                }
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.listRepos.visibility = View.VISIBLE
            }
        }
    }
}