package dev.swapnil.getrepgo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.swapnil.getrepgo.data.GithubRepos

@Dao
interface RepoDao {

    @Insert
    suspend fun addRepo(repo :GithubRepos)

    @Query("SELECT * FROM ReposLists")
    fun getAllMessages():LiveData<List<GithubRepos>>
}