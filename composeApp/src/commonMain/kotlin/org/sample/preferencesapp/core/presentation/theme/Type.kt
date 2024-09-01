package org.sample.preferencesapp.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import preferencesapp.composeapp.generated.resources.Res
import preferencesapp.composeapp.generated.resources.montserrat_bold
import preferencesapp.composeapp.generated.resources.montserrat_regular

@Composable
fun montserrat(): FontFamily {
    val montserratRegular =
        Font(
            resource = Res.font.montserrat_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal,
        )

    val montserratBold =
        Font(
            resource = Res.font.montserrat_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        )
    return FontFamily(
        montserratRegular,
        montserratBold
    )
}

@Composable
fun getTypography(): Typography {
    val montserrat = montserrat()
    return Typography(
        displayLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 50.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 40.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 30.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 28.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W700,
            fontSize = 14.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 11.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 13.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 11.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 9.sp,
        ),
    )
}