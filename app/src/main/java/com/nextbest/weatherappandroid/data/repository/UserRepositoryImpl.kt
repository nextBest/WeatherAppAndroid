package com.nextbest.weatherappandroid.data.repository

import com.nextbest.weatherappandroid.data.local.SharedPreferencesStorage

class UserRepositoryImpl(private val sharedPreferencesStorage: SharedPreferencesStorage): UserRepository {
    override fun canShowTutorial(): Boolean {
        return !sharedPreferencesStorage.getBoolean(TUTORIAL_SHOWN, false)
    }

    override fun setTutorialWasShown() {
        sharedPreferencesStorage.putBoolean(TUTORIAL_SHOWN, true)
    }

    companion object {
        private const val TUTORIAL_SHOWN = "TUTORIAL_SHOWN"
    }
}