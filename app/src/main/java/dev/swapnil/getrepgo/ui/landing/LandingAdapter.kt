package dev.swapnil.getrepgo.ui.landing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.swapnil.getrepgo.data.GithubRepo
import dev.swapnil.getrepgo.databinding.ListRepoItemBinding

class LandingAdapter(
    private val repos: List<GithubRepo>,
    private val onShareClick: (GithubRepo) -> Unit,
    private val onClick: (GithubRepo) -> Unit
) : RecyclerView.Adapter<LandingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListRepoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListRepoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: LandingAdapter.ViewHolder, position: Int) {
        with(holder.binding) {
            val repo = repos[position]
            textViewRepository.text = repo.full_name
            textViewDescription.text = repo.description
            imageViewShare.setOnClickListener {
                onShareClick(repo)
            }
            root.setOnClickListener {
                onClick(repo)
            }
        }
    }
}