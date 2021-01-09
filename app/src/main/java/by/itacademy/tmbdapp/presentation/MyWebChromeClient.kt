package by.itacademy.tmbdapp.presentation

import android.content.Intent
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.tmbdapp.fragments.TAG
import by.itacademy.tmbdapp.view.AuthenticationActivity

class MyWebChromeClient(private val activity: AppCompatActivity) : WebChromeClient() {
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (view.url.contains("allow") && newProgress == 70) {
            activity.finish()
            activity.startActivity(Intent(activity.baseContext,
                AuthenticationActivity::class.java))
            Log.d(TAG, "page allow true!!! $newProgress")
        }
    }
}