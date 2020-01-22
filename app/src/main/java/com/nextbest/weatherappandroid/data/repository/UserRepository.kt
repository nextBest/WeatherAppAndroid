package com.nextbest.weatherappandroid.data.repository

interface UserRepository {
    fun canShowTutorial(): Boolean

    fun setTutorialWasShown()
}