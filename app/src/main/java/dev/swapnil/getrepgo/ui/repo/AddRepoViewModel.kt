package dev.swapnil.getrepgo.ui.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.swapnil.getrepgo.db.RepoDao
import dev.swapnil.getrepgo.networking.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddRepoViewModel @Inject constructor(
    private val db: RepoDao,
    private val api: ApiService
) : ViewModel() {

    val showProgressBar = MutableLiveData(true)

    val message = MutableLiveData<String>()

    fun addRepo(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                val response = api.getRepoDetails(owner, repo)
                db.addRepo(response)
                message.value = "Repo successfully tracked"
            } catch (e: HttpException) {
                // HTTP error, show to user the error with code
                message.value = "There was an error, code: ${e.code()}"
                e.printStackTrace()
            } catch (e: Exception) {
                // Other error, show to user the error
                message.value = "There was an error"
                e.printStackTrace()
            }
        }
    }
}