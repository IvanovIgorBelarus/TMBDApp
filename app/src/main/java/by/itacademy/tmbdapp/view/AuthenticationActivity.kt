package by.itacademy.tmbdapp.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import by.itacademy.tmbdapp.api.moviesapi.AUTHENTICATION_URL
import by.itacademy.tmbdapp.databinding.ActivityAuthenticationBinding
import by.itacademy.tmbdapp.presentation.AuthenticationActivityListener
import by.itacademy.tmbdapp.presentation.AuthenticationActivityPresenterImpl
import by.itacademy.tmbdapp.presentation.MyWebChromeClient

class AuthenticationActivity : BaseActivity(), AuthenticationActivityListener {
    private lateinit var binding: ActivityAuthenticationBinding
    private val authenticationActivityPresenterImpl by lazy {
        AuthenticationActivityPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        authenticationActivityPresenterImpl.getRequestTokenFromApi()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun showRequestToken(token: String) {
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                loadsImagesAutomatically = true
            }
            webChromeClient = MyWebChromeClient(this@AuthenticationActivity)
            webViewClient = WebViewClient()

            loadUrl(AUTHENTICATION_URL + token)
        }
    }

}

