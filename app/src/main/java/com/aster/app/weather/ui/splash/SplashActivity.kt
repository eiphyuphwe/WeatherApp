package com.aster.app.weather.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.aster.app.weather.R
import com.aster.app.weather.di.component.ActivityComponent
import com.aster.app.weather.ui.base.BaseActivity
import com.aster.app.weather.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {
        const val TAG = "SplashActivity"
    }

    private val SPLASH_TIME_OUT: Long = 3000 // 1 sec
    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }

    override fun setupObservers() {

    }
}