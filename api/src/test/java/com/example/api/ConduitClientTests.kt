package com.example.api

import com.example.api.models.entities.LoginData
import com.example.api.models.entities.UserCredentials
import com.example.api.models.requests.LoginRequest
import com.example.api.models.requests.SignUpRequest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class ConduitClientTests {



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
            val response = ConduitClient.publicApi.signupUser(SignUpRequest(userCredentials))
            assertEquals(userCredentials.username, response.body()?.user?.username)
        }
    }

    @Test
    fun `POST users- login user`() {
        //make a loginData that is present in the server
        val loginData = LoginData(
                "test${Random.nextInt(99, 999)}@email.com",
                "pass${Random.nextInt(11111, 9999999)}"
        )

        //for coroutine functions
        runBlocking {
            val response = ConduitClient.publicApi.loginUser(LoginRequest(loginData))
            assertEquals(loginData.email, response.body()?.user?.email)
        }
    }

    //testing to get all articles
    @Test
    fun `GET articles`() {
        //for coroutine functions
        runBlocking {
            val articles = ConduitClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    //testing to get articles by authors
    @Test
    fun `GET articles by author`() {
        //for coroutine functions
        runBlocking {
            val articles = ConduitClient.publicApi.getArticles(author = "ilay")
            assertNotNull(articles.body()?.articles)
        }
    }

    //testing to get articles by tag
    @Test
    fun `GET articles by tag`() {
        //for coroutine functions
        runBlocking {
            val articles = ConduitClient.publicApi.getArticles(tag = "dragons")
            assertNotNull(articles.body()?.articles)
        }
    }

    //testing to get tags
    @Test
    fun `GET Tags`() {
        //for coroutine functions
        runBlocking {
            val tags = ConduitClient.publicApi.getTags()
            assertNotNull(tags)
        }
    }
}