import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.stevdza_san.webbrowser.AppContext

actual fun openWebBrowser(url: String): Boolean {
    val intent = CustomTabsIntent.Builder().build().apply {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    return try {
        val context = AppContext.get()
        intent.launchUrl(context, Uri.parse(url))
        true
    } catch (e: Exception) {
        println("$TAG: ${e.message}")
        false
    }
}