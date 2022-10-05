package com.binar.movieapp.data.repository

import com.binar.movieapp.data.local.database.user.UserDataSource
import com.binar.movieapp.data.model.user.UserEntity
import com.binar.movieapp.data.local.preference.UserPreferenceDataSource
import com.binar.movieapp.wrapper.Resource

interface UserRepository {
    fun checkIfUserLoggedIn(): Boolean
    fun setIfUserLogin(userLoggedIn: Boolean)

    suspend fun registerUser(user: UserEntity): Resource<Number>
    suspend fun getUser(username: String): Resource<UserEntity>
}

class UserRepositoryImpl(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val userDataSource: UserDataSource,
): UserRepository {
    override fun checkIfUserLoggedIn(): Boolean {
        return userPreferenceDataSource.getIfUserLogin()
    }

    override fun setIfUserLogin(userLoggedIn: Boolean){
        return userPreferenceDataSource.setIfUserLogin(userLoggedIn)
    }

    override suspend fun registerUser(user: UserEntity): Resource<Number> {
        return proceed {
            userDataSource.registerUser(user)
        }
    }

    override suspend fun getUser(username: String): Resource<UserEntity> {
        return proceed {
            userDataSource.getUser(username)
        }
    }

    private suspend fun <T> proceed(coroutine: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }

}