package abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo

import abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.adapter.VideoListAdapter
import abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.customClass.YoutubeApiKey.Companion.DEVELOPER_KEY
import abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.model.VideoListModel
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : YouTubeBaseActivity(), View.OnClickListener, YouTubePlayer.OnInitializedListener {

    var telYoutubeUrl: TextInputLayout? = null
    lateinit var etYoutubeUrl: TextInputEditText
    lateinit var youtubePlayerView: YouTubePlayerView
    private val RECOVERY_REQUEST = 1
    var player: YouTubePlayer? = null
    var strYouTubeUrl: String = ""
    var fullScreen: Boolean = false
    var recyclerView: RecyclerView? = null
    var videoList: ArrayList<VideoListModel>? = null
    var adapter: VideoListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPause.setOnClickListener(this)
        btnPlay.setOnClickListener(this)
        btnFullScreen.setOnClickListener(this)
        ibSearch.setOnClickListener(this)

        youtubePlayerView = findViewById(R.id.youtube_view)
        youtubePlayerView.initialize(DEVELOPER_KEY, this)

        telYoutubeUrl = findViewById(R.id.telYoutubeUrl)
        etYoutubeUrl = findViewById(R.id.etYoutubeUrl)

        etYoutubeUrl!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length!! > 0) {
                    telYoutubeUrl!!.isErrorEnabled = false
                    strYouTubeUrl = s.toString().trim()

                } else {
                    telYoutubeUrl!!.error = "Enter your Youtube Url"
                    telYoutubeUrl!!.isErrorEnabled = true
                }
            }
        })
        addVideoListData()
    }

    private fun addVideoListData() {
        videoList = ArrayList<VideoListModel>()
        videoList!!.add(VideoListModel("Video 1: Ache bache school jate", "HfAOxTA7JAc"))
        videoList!!.add(VideoListModel("Video 2: Aj mangalwar h chuhe ko bukaar h", "PaLMgktvztA"))
        videoList!!.add(VideoListModel("Video 3: Cute baby sing a poem", "wDSiFHL3W8I"))
        videoList!!.add(VideoListModel("Video 4: CUTE DOLL DANCE", "xCMc8ntfL1k"))
        recyclerView = findViewById(R.id.recylerView)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false))
        Handler().postDelayed({
            adapter = VideoListAdapter(this@MainActivity, videoList!!, this@MainActivity.player!!)
            recyclerView!!.adapter = adapter
        }, 2000)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnPlay -> {
                if (!player!!.isPlaying)
                    player?.play()
            }

            R.id.btnPause -> {
                if (player!!.isPlaying)
                    player?.pause()
            }

            R.id.btnFullScreen -> {
                player!!.setFullscreen(true)
            }

            R.id.ibSearch -> {
                if (!"".equals(strYouTubeUrl)) {
                    if (strYouTubeUrl.startsWith("https://www.youtube.com/watch?v=", true)
                        || strYouTubeUrl.contains("www.youtube.com")
                        || strYouTubeUrl.contains("youtu.be")
                        || strYouTubeUrl.startsWith("https://youtube.com/watch?v=", true)
                    ) {
                        if (strYouTubeUrl.contains("v=")) {
                            strYouTubeUrl = strYouTubeUrl.split("v=").get(1)
                        } else if (strYouTubeUrl.contains(".be/")) {
                            strYouTubeUrl = strYouTubeUrl.split(".be/").get(1)
                        }
                    }
                    player!!.cueVideo(strYouTubeUrl)
                    player!!.play()
                } else {
                    telYoutubeUrl!!.error = "Enter your Youtube Url"
                    telYoutubeUrl!!.isErrorEnabled = true
                }
            }
        }

    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        this.player = player

        if (!wasRestored) {
            this.player!!.setOnFullscreenListener { _isFullScreen ->
                fullScreen = _isFullScreen
            }
            player?.cueVideo("xCMc8ntfL1k")
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        if (errorReason!!.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = "Error initializing YouTube player"
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this)
        }
    }

    protected fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        return youtubePlayerView
    }

    override fun onBackPressed() {
        if (fullScreen) {
            player!!.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

}
