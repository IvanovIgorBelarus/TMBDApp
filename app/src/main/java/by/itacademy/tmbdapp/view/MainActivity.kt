package by.itacademy.tmbdapp.view

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import by.itacademy.tmbdapp.R
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.databinding.ActivityMainBinding
import by.itacademy.tmbdapp.presentation.adapters.CategoryPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isGuest = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        changeConfig()
        createSession()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.authentication -> {
                startActivity(Intent(this, AuthenticationActivity::class.java))
            }
            R.id.userSetting -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.userInfo -> {
                if (AuthenticationRepository.sessionId != null) {
                    startActivity(Intent(this, AccountActivity::class.java))
                } else {
                    Toast.makeText(this, "you must Sign in!!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewPager() {
        setContentView(binding.root)
        val viewPager2 = binding.viewPager2
        viewPager2.adapter = CategoryPagerAdapter(this, isGuest)
        val tabs = binding.tabLayout
        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.popular)
                1 -> tab.setText(R.string.trending)
                2 -> tab.setText(R.string.upcoming)
                3 -> tab.setText(R.string.top_rated)
                4 -> tab.setText(R.string.watch_list)
            }
        }.attach()
    }

    private fun createSession() {
        if (AuthenticationRepository.requestToken == null) {
            AuthenticationRepository.createGuestSession(
                onSuccess = Toast.makeText(this,
                    getString(R.string.main_activity_toast),
                    Toast.LENGTH_LONG).show(),
                onError = ::error
            )
        } else {
            AuthenticationRepository.createSession()
        }
    }

    private fun changeConfig() {
        val theme =
            getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("theme", false)
        if (theme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}