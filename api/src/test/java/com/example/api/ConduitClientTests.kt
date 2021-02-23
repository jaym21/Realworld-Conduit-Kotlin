package com.example.api

import com.example.api.models.entities.UserCredentials
import com.example.api.models.requests.SignUpRequest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class ConduitClientTests {

    private val conduitClient = ConduitClient()

    //testing signUp user request to api
    @Test
    fun `POST users- signup user`() {

        //creating a user to test
       val userCredentials =  UserCredentials(
            "test${Random.nextInt(99, 999)}@email.com",
            "pass${Random.nextInt(11111, 9999999)}",
            "test_user_${Random.nextInt(9, 999)}"
        )

        //for coroutine functions
        runBlocking {
            val response = conduitClient.api.signupUser(SignUpRequest(userCredentials))
            assertEquals(userCredentials.username, response.body()?.user?.username)
        }
    }

    //testing to get all articles
    @Test
    fun `GET articles`() {
        //for coroutine functions
        runBlocking {
            val articles = conduitClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    //testing to get articles by authors
    @Test
    fun `GET articles by author`() {
        //for coroutine functions
        runBlocking {
            val articles = conduitClient.api.getArticles(author = "ilay")
            assertNotNull(articles.body()?.articles)
        }
    }

    //testing to get articles by tag
    @Test
    fun `GET articles by tag`() {
        //for coroutine functions
        runBlocking {
            val articles = conduitClient.api.getArticles(tag = "dragons")
            assertNotNull(articles.body()?.articles)
        }
    }
}