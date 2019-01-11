package abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.model

data class VideoListModel(val videoName: String, val videoId: String) {
    override fun toString(): String {
        return "VideoListModel(videoName='$videoName', videoId='$videoId')"
    }
}