import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openWebBrowser(url: String): Boolean {
    val website = NSURL(string = url)
    return if (UIApplication.sharedApplication().canOpenURL(website)) {
        runCatching {
            var completed = false
            UIApplication.sharedApplication()
                .openURL(
                    website,
                    mapOf<Any?, Any>()
                ) { success ->
                    completed = success
                }
            completed
        }.getOrElse {
            println("$TAG: Failed to open the URL. ${it.message}")
            false
        }
    } else {
        println("$TAG: Couldn't open the URL.")
        false
    }
}