package dev.swapnil.getrepgo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class GithubRepo (
    @PrimaryKey val id: Long,
    val full_name: String,
    val description: String,
    val html_url: String
)