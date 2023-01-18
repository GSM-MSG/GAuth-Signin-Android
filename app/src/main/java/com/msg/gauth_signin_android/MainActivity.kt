package com.msg.gauth_signin_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.msg.gauthsignin.GAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GAuth.getCodeInfoRequest("s21024@gsm.hs.kr", "injoo12313@") {
                Log.d("response", it.toString())
            }
        }
    }
}