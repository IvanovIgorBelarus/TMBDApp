package by.itacademy.tmbdapp.view

import android.content.res.Configuration
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


open class BaseActivity : AppCompatActivity() {
    init {
        updateConfig(this)
    }

    private fun updateConfig(wrapper: ContextThemeWrapper) {
        if (dLocale == Locale(""))
            return
        Locale.setDefault(dLocale)
        val config = Configuration().apply { setLocale(dLocale) }
        wrapper.applyOverrideConfiguration(config)
    }

    companion object {
        var dLocale: Locale = Locale("")
    }
}