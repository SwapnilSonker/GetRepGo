package dev.swapnil.getrepgo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.swapnil.getrepgo.data.GithubRepos

@Database(entities = [GithubRepos::class], version = 1)
abstract class RepoDatabase : RoomDatabase(){

    abstract fun repoDao() : RepoDao
}