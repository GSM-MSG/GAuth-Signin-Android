package com.msg.gauth_signin_android

import com.msg.gauthsignin.GAuthSigninWebView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GAuthSigninWebView(clientId = "00ce71cc5f774d4191db789d4e6aea40260080b4498947de98f3c7bd7d5ec78d", redirectUri = "https://www.google.com")
        }
    }
}