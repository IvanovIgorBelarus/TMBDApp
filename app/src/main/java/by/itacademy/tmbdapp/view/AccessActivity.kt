package by.itacademy.tmbdapp.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import by.itacademy.tmbdapp.api.moviesapi.AUTHENTICATION_URL
import by.itacademy.tmbdapp.databinding.ActivityAccessBinding
import by.itacademy.tmbdapp.presentation.MyWebChromeClient
import by.itacademy.tmbdapp.presentation.AccessActivityPresenter
import by.itacademy.tmbdapp.presentation.AccessActivityPresenterImpl

class AccessActivity : BaseActivity() {
    private lateinit var binding: ActivityAccessBinding
    private val accessActivityPresenter: AccessActivityPresenter by lazy {
        AccessActivityPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        accessActivityPresenter.getRequestTokenFromApi()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    fun showRequestToken(token: String?) {
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                loadsImagesAutomatically = true
            }
            webChromeClient = MyWebChromeClient(this@AccessActivity)
            webViewClient = WebViewClient()
            loadUrl(AUTHENTICATION_URL + token)
        }
    }
}