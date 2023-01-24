package com.msg.gauthsignin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GAuthSigninWebView(
    clientId: String,
    redirectUri: String,
    context: Context,
    callBack: (String) -> Unit
) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (url.contains("code=")) {
                        callBack(url.substringAfter("code="))
                        context.startActivity(Intent(context, context::class.java))
                        it.stopService(Intent(it, it::class.java))
                        return true
                    }
                    return false
                }
            }
            loadUrl("https://gauth.co.kr/login?client_id=$clientId&redirect_uri=$redirectUri")
        }
    })
}