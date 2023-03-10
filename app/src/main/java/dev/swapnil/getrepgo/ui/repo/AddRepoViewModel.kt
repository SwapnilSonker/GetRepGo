package dev.swapnil.getrepgo.ui.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.swapnil.getrepgo.db.RepoDao
import dev.swapnil.getrepgo.networking.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AddRepoViewModel @Inject constructor(
    private val db: RepoDao,
    private val api: ApiService
) : ViewModel() {

    val showProgressBar = MutableLiveData(false)

    val message = MutableLiveData<String>()

    fun addRepo(owner: String, repo: String) {
        showProgressBar.value = true
        viewModelScope.launch {
            try {
                val response = api.getRepoDetails(owner, repo)
                db.addRepo(response)
                message.value = "Repo successfully tracked"
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    message.value = "The repo was not found. Please check the details and try again"
                } else {
                    message.value = "There was an error, code: ${e.code()}"
                }
                e.printStackTrace()
            } catch (e: Exception) {
                message.value = "There was an error"
                e.printStackTrace()
            } finally {
                showProgressBar.value = false
            }
        }
    }
}