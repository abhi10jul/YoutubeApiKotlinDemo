package abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.interfaces

import abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.customClass.YoutubeApiKey.Companion.showMessage
import com.google.android.youtube.player.YouTubePlayer

class MyYoutubePlaybackEventListener : YouTubePlayer.PlaybackEventListener {

    override fun onSeekTo(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBuffering(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPlaying() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showMessage("Playing")
    }

    override fun onStopped() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showMessage("Stop")
    }

    override fun onPaused() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showMessage("Paused")
    }
}