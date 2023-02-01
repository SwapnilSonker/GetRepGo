package dev.swapnil.getrepgo.networking

import dev.swapnil.getrepgo.data.GithubRepos
import retrofit2.http.GET

interface ApiService {
@GET("repositories")
suspend fun getreposfromGithub():List<GithubRepos>
}