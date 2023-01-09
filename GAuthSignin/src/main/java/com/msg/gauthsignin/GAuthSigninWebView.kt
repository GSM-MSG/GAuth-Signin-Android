package com.msg.gauthsignin

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GAuthSigninWebView(clientId: String, redirectUri: String) {
    val code = remember {
        mutableStateOf("")
    }
    val isLoginSuccess = remember {
        mutableStateOf(false)
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
                        code.value = url.substringAfter("code=")
                        isLoginSuccess.value = true
                        this@apply.destroy()
                        return true
                    }
                    return false
                }
            }
            loadUrl("https://gauth.co.kr/login?client_id=$clientId&redirect_uri=$redirectUri")
        }
    })
    if (isLoginSuccess.value) {
        GAuth.setCodeValue(code.value)
    }
}