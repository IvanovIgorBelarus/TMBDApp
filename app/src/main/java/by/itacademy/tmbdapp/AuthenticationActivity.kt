package by.itacademy.tmbdapp

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import by.itacademy.tmbdapp.databinding.ActivityAuthenticationBinding
import by.itacademy.tmbdapp.presentation.AuthenticationActivityListener
import by.itacademy.tmbdapp.presentation.AuthenticationActivityPresenterImpl

class AuthenticationActivity : BaseActivity(),AuthenticationActivityListener {
    private lateinit var binding: ActivityAuthenticationBinding
    private val authenticationActivityPresenterImpl by lazy { AuthenticationActivityPresenterImpl(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        authenticationActivityPresenterImpl.getRequestTokenFromApi()
    }

    override fun showRequestToken(token: String) {
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled=true
                loadsImagesAutomatically=true
            }
            webChromeClient= WebChromeClient()
            webViewClient= WebViewClient()
            loadUrl("https://www.themoviedb.org/authenticate/$token")
        }
    }

}