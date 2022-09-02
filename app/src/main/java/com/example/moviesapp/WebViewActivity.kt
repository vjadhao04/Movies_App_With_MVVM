package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url=intent.getStringExtra("title")

        var webview=findViewById<WebView>(R.id.webView)
        var pbar=findViewById<ProgressBar>(R.id.progressBar)

        webview.settings.javaScriptEnabled=true
        webview.loadUrl("https://en.wikipedia.org/wiki/"+url)
        webview.webViewClient=object :WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pbar.visibility= View.GONE
                webview.visibility=View.VISIBLE


            }

        }


    }
}