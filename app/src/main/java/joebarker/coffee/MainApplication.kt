package joebarker.coffee

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import joebarker.config.Config

lateinit var config: Config

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        config = Config(applicationContext)
    }
}