package com.msg.gauthsignin

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.gauthsignin.component.ui.GauthBlue
import com.msg.gauthsignin.component.utils.Types

@Composable
fun GAuthButton(
    style: Types.Style,
    actionType: Types.ActionType,
    colors: Types.Colors,
    onClick: () -> Unit
) {
    val pretendard = FontFamily(
        Font(R.font.pretendardsemibold, FontWeight.SemiBold, FontStyle.Normal)
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
            .clip(
                RoundedCornerShape(
                    when (style) {
                        Types.Style.DEFAULT -> 6.dp
                        Types.Style.ROUNDED -> 26.dp
                    }
                )
            )
            .border(
                if (colors == Types.Colors.OUTLINE) 1.dp else 0.dp,
                SolidColor(
                    when (colors) {
                        Types.Colors.OUTLINE -> Color.Black
                        Types.Colors.COLORED -> GauthBlue
                        Types.Colors.WHITE -> Color.White
                    }
                ),
                RoundedCornerShape(if (style == Types.Style.DEFAULT) 6.dp else 26.dp)
            ),
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (colors) {
                Types.Colors.COLORED -> GauthBlue
                else -> Color.White
            },
            contentColor = when (colors) {
                Types.Colors.WHITE -> GauthBlue
                Types.Colors.COLORED -> Color.White
                Types.Colors.OUTLINE -> Color.Black
            }
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gauth_white_ic),
                contentDescription = "GAuthButtonIcon",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
            Text(
                text = "${
                    when (actionType) {
                        Types.ActionType.SIGNIN -> "Sign in"
                        Types.ActionType.SIGNUP -> "Sign up"
                        Types.ActionType.CONTINUE -> "Continue"
                    }
                } with GAuth", style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}