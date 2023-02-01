package dev.swapnil.getrepgo.Repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.swapnil.getrepgo.databinding.FragmentRepoScreenBinding


class RepoScreen : Fragment() {

    private lateinit var binding: FragmentRepoScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRepoScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

}