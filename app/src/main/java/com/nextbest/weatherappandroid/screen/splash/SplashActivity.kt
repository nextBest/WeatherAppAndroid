package com.nextbest.weatherappandroid.screen.splash

import android.os.Bundle
import com.nextbest.weatherappandroid.screen.main.MainActivity
import com.nextbest.weatherappandroid.data.repository.UserRepository
import com.nextbest.weatherappandroid.screen.tutorial.TutorialActivity
import com.nextbest.weatherappandroid.utils.openActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openNextScreen()
    }

    private fun openNextScreen() {
        if (userRepository.canShowTutorial()) {
            openTutorialActivity()
        } else {
            openMainActivity()
        }
    }

    private fun openMainActivity() {
        openActivity(MainActivity::class.java)
    }

    private fun openTutorialActivity() {
        openActivity(TutorialActivity::class.java)
    }
}
