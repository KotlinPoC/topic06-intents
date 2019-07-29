package ca.campbell.bestwebbrowser

import android.content.pm.PackageInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient//import java.net.URI;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main)
        } catch (e: RuntimeException) {
            // most likely to get an InflateException on a WebView
            // later devices  have No WebView installed due to vulnerabilities
            // TODO: seek another option
            Log.d(TAG, "missing package")
            setContentView(R.layout.error)
            return
        }

        var uri = intent.data
        var webViewPackageInfo: PackageInfo? = null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            webViewPackageInfo = WebView.getCurrentWebViewPackage()
            Log.d(TAG, "WebView version: " + webViewPackageInfo!!.versionName)

        }

        val wv = findViewById<android.view.View>(R.id.wv) as WebView
        if (uri == null)
            uri = Uri.parse("https://xkcd.org")
        Log.d(TAG, uri!!.toString())
        wv.webViewClient = WebViewClient()
        wv.loadUrl(uri.toString())
    }

    companion object {
        private val TAG = "BESTB"
    }
}
