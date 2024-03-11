package com.example.cuadernodedeku.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cuadernodedeku.R

val Bradley = FontFamily(
    Font(R.font.bradley_hand, FontWeight.Normal)
)

val Sriracha = FontFamily(
    Font(R.font.sriracha_regular, FontWeight.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Bradley,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = Sriracha,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )
)