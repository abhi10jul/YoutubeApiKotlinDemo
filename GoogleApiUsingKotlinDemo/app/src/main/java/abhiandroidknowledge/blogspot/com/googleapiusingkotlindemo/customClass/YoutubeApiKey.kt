package abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.customClass

import android.content.Context
import android.widget.Toast

class YoutubeApiKey {

    companion object {
        val context: Context? = null
        val DEVELOPER_KEY = "YOUR_API_KEY"

        fun showMessage(s: String) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show()
        }
    }
}
