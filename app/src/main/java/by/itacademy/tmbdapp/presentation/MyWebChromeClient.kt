package by.itacademy.tmbdapp.presentation

import android.content.Intent
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.tmbdapp.view.MainActivity

class MyWebChromeClient(private val activity: AppCompatActivity) : WebChromeClient() {
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (view.url.contains("allow")) {
            activity.finish()
            activity.startActivity(Intent(activity.baseContext,MainActivity::class.java))
            Log.d("qwe", "page allow true!!!")
        }
    }
}