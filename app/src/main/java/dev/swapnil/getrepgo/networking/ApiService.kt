package dev.swapnil.getrepgo.networking

import dev.swapnil.getrepgo.BuildConfig
import dev.swapnil.getrepgo.data.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("repos/{owner_name}/{repo_name}")
    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer ${BuildConfig.ACCESS_TOKEN}",
        "X-GitHub-Api-Version: 2022-11-28"
    )
    suspend fun getRepoDetails(
        @Path("owner_name") ownerName: String,
        @Path("repo_name") repoName: String
    ): GithubRepo
}