package id.haaweejee.rawgandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import id.haaweejee.rawgandroid.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}