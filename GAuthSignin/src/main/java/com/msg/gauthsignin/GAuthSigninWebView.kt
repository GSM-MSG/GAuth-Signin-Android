package com.msg.gauthsignin

import android.annotation.SuppressLint
import android.net.http.SslError
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GAuthSigninWebView(
    clientId: String,
    redirectUri: String,
    onBackPressed: (() -> Unit)? = null,
    callBack: (String) -> Unit,
) {
    if (onBackPressed != null) {
        BackHandler(onBack = onBackPressed)
    }

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
                        return true
                    }
                    return false
                }

                @SuppressLint("WebViewClientOnReceivedSslError")
                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    super.onReceivedSslError(view, handler, error)
                    handler!!.proceed()
                }
            }
            loadUrl("https://gauth.co.kr/login?client_id=$clientId&redirect_uri=$redirectUri")
        }
    })
}