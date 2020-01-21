package com.nextbest.weatherappandroid.data.repository

class UserRepositoryImpl: UserRepository {
    override fun canShowTutorial(): Boolean {
        // TODO get value from
        return false
    }

    override fun setTutorialWasShown() {
        // TODO save tutorial showed
    }
}