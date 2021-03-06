package by.itacademy.tmbdapp.view

import android.content.Context
import android.os.Bundle
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.databinding.ActivitySettingsBinding
import by.itacademy.tmbdapp.fragments.SettingsDialog

class SettingsActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            exitButton.setOnClickListener {
                saveSettings()
                createDialog()
            }
            languageSwitch.setOnCheckedChangeListener { b, isChecked ->
                if (isChecked) b.setText(R.string.russian) else b.setText(R.string.english)
            }
            themeSwitch.setOnCheckedChangeListener { b, isChecked ->
                if (isChecked) {
                    b.setText(R.string.light_theme)
                } else b.setText(R.string.dark_theme)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            with(languageSwitch, {
                isChecked = loadLanguage()
                if (isChecked) setText(R.string.russian) else setText(R.string.english)
            })
            with(themeSwitch) {
                isChecked = loadTheme()
                if (isChecked) setText(R.string.light_theme) else setText(R.string.dark_theme)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        saveSettings()
    }

    private fun saveSettings() {
        val pref = getSharedPreferences("settings", Context.MODE_PRIVATE)
        with(pref.edit()) {
            putBoolean("language", binding.languageSwitch.isChecked)
            putBoolean("theme", binding.themeSwitch.isChecked)
            apply()
        }
    }

    private fun loadLanguage() =
        getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("language", false)

    private fun loadTheme() =
        getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("theme", false)

    private fun createDialog() {
        val dialog = SettingsDialog()
        val manager = supportFragmentManager
        dialog.show(manager, "dialog")
    }
}