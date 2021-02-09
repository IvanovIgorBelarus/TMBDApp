package by.itacademy.tmbdapp

import android.app.Application
import android.content.Context
import by.itacademy.tmbdapp.di.accountModule
import by.itacademy.tmbdapp.di.authModule
import by.itacademy.tmbdapp.di.movieModule
import by.itacademy.tmbdapp.di.moviesModule
import by.itacademy.tmbdapp.view.BaseActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        var language = baseContext.resources.configuration.locale.toLanguageTag()
        if (getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getBoolean("language", false)
        ) {
            language = "ru"
        }
        BaseActivity.dLocale = Locale(language)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(movieModule, accountModule, authModule, moviesModule)
        }
    }
}