package by.itacademy.tmbdapp

import android.app.Application
import android.content.Context
import by.itacademy.tmbdapp.view.BaseActivity
import by.itacademy.tmbdapp.view.MovieActivity
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
        MovieActivity.dLocale=Locale(language)
    }
}