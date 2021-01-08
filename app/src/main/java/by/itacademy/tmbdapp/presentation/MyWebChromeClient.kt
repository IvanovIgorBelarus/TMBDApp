package by.itacademy.tmbdapp.presentation

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MyWebChromeClient(private val activity: AppCompatActivity) : WebChromeClient() {
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (view.url.contains("allow")) {
            activity.finish()
            Log.d("qwe", "page allow true!!!")
        }
    }
}