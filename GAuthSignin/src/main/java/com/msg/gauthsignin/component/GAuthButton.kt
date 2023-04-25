package com.msg.gauthsignin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.msg.gauthsignin.R
import com.msg.gauthsignin.component.ui.GauthBlue
import com.msg.gauthsignin.component.utils.Types

@Composable
fun GAuthButton(
    style: Types.Style = Types.Style.DEFAULT,
    actionType: Types.ActionType = Types.ActionType.SIGNIN,
    colors: Types.Colors = Types.Colors.WHITE,
    horizontalPaddingValue: Dp? = null,
    horizontalPercent: Float? = null,
    horizontalMargin: Dp? = null,
    onClick: () -> Unit
) {
    val pretendard = FontFamily(
        Font(R.font.pretendardsemibold, FontWeight.SemiBold, FontStyle.Normal)
    )

    val modifier = when {
        horizontalMargin != null && horizontalPercent == null && horizontalPaddingValue == null -> Modifier
            .fillMaxWidth()

        horizontalMargin == null && horizontalPercent != null && horizontalPaddingValue == null -> Modifier
            .fillMaxWidth(horizontalPercent)

        horizontalMargin == null && horizontalPercent == null && horizontalPaddingValue != null -> Modifier
            .wrapContentSize()

        else -> Modifier
            .fillMaxWidth(0.9f)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalMargin ?: 0.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
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
                            Types.Colors.OUTLINE -> GauthBlue
                            Types.Colors.COLORED -> GauthBlue
                            Types.Colors.WHITE -> Color.White
                        }
                    ),
                    RoundedCornerShape(if (style == Types.Style.DEFAULT) 6.dp else 26.dp)
                ),
            contentPadding = PaddingValues(
                vertical = 14.dp,
                horizontal = horizontalPaddingValue ?: 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = when (colors) {
                    Types.Colors.COLORED -> GauthBlue
                    else -> Color.White
                },
                contentColor = when (colors) {
                    Types.Colors.WHITE -> GauthBlue
                    Types.Colors.COLORED -> Color.White
                    Types.Colors.OUTLINE -> GauthBlue
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
}