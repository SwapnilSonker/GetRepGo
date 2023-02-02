package dev.swapnil.getrepgo.ui.landing

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.swapnil.getrepgo.databinding.ListRepoItemBinding
import dev.swapnil.getrepgo.db.RepoDao
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    db: RepoDao
) : ViewModel() {

    private lateinit var binding: ListRepoItemBinding

    val repos = db.getAllRepos()


}