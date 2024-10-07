package com.example.pollcreator.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern

@Composable
fun YoutubeAppOpener(youtubeLink: String) {
    val context = LocalContext.current
    val youtubeVideoId = extractYoutubeVideoId(youtubeLink)

        youtubeVideoId?.let {
            // Create an intent to open YouTube app or browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$it")).apply {
                putExtra("force_fullscreen", true)
                `package` = "com.google.android.youtube"
            }

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$it"))
                val chooser = Intent.createChooser(browserIntent, "Open with")
                context.startActivity(chooser)
            }
        }


}

fun extractYoutubeVideoId(youtubeLink: String): String? {
    val pattern = Pattern.compile(
        "(?:youtube(?:-nocookie)?\\.com/(?:[^/\\n\\s]+/.+/|(?:v|e(?:mbed)?)/|.*[?&]v=)|youtu\\.be/)([\\w-]{11})",
        Pattern.CASE_INSENSITIVE
    )
    val matcher = pattern.matcher(youtubeLink)
    return if (matcher.find()) {
        matcher.group(1)
    } else {
        null
    }
}
