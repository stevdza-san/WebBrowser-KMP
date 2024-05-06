import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openWebBrowser(url: String): Boolean {
    val website = NSURL(string = url)
    return if (UIApplication.sharedApplication().canOpenURL(website)) {
        UIApplication.sharedApplication().openURL(website)
        true
    } else false
}