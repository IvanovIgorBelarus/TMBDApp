package by.itacademy.tmbdapp.presentation

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView

class MyWebChromeClient : WebChromeClient() {
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (view.url.contains("allow")) {
            Log.d("qwe", "page allow true!!!")
        }
    }
}