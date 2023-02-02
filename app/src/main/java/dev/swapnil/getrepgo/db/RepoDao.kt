package dev.swapnil.getrepgo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.swapnil.getrepgo.data.GithubRepo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepo(repo: GithubRepo)


    @Query("SELECT * FROM repos")
    fun getAllRepos(): LiveData<List<GithubRepo>>
}