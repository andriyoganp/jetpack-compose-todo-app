package com.ayeee.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ayeee.ui.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val OpenSans = GoogleFont("Open Sans")

val OpenSansFamily = FontFamily(
    Font(googleFont = OpenSans, fontProvider = provider),
    Font(googleFont = OpenSans, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = OpenSans, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = OpenSans, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = OpenSans, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = OpenSans, fontProvider = provider, weight = FontWeight.ExtraBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp

    ),
    titleSmall = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp

    ),
    labelLarge = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = OpenSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp

    ),
)

@Preview
@Composable
private fun TypographyPreview() {
    MaterialTheme {
        Surface {
            Column {
                Text("Display Large", style = MaterialTheme.typography.displayLarge)
                Text("Display Medium", style = MaterialTheme.typography.displayMedium)
                Text("Display Small", style = MaterialTheme.typography.displaySmall)
                Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
                Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
                Text("Headline Small", style = MaterialTheme.typography.headlineSmall)
                Text("Title Large", style = MaterialTheme.typography.titleLarge)
                Text("Title Medium", style = MaterialTheme.typography.titleMedium)
                Text("Title Small", style = MaterialTheme.typography.titleSmall)
                Text("Body Large", style = MaterialTheme.typography.bodyLarge)
                Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
                Text("Body Small", style = MaterialTheme.typography.bodySmall)
                Text("Label Large", style = MaterialTheme.typography.labelLarge)
                Text("Label Medium", style = MaterialTheme.typography.labelMedium)
                Text("Label Small", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}