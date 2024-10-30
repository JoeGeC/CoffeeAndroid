package joebarker.coffee

import android.app.Application
import joebarker.config.Config

lateinit var config: Config


class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        config = Config(applicationContext)
    }
}