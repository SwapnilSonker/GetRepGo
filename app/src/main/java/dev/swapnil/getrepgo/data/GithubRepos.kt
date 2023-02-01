package dev.swapnil.getrepgo.data

import androidx.room.Entity

@Entity(tableName = "ReposLists")
data class GithubRepos (
    val name:String,
    val description:String,
    val owner:String)