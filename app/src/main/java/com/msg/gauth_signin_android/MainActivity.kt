package com.msg.gauth_signin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.msg.gauthsignin.GAuthButton
import com.msg.gauthsignin.utils.Types

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GAuthButton(Types.Style.ROUNDED, Types.ActionType.SIGNIN, Types.Colors.OUTLINE){}
        }
    }
}