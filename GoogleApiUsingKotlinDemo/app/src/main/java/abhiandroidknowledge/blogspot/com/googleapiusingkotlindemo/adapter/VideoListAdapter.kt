package abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.adapter

import abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.R
import abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.model.VideoListModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.youtube.player.YouTubePlayer
import java.util.*

class VideoListAdapter(
    private val mContext: Context,
    private val list: ArrayList<VideoListModel>,
    private val player: YouTubePlayer
) :
    RecyclerView.Adapter<VideoListAdapter.VideoListAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListAdapterHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.video_list_raw, parent, false)
        return VideoListAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: VideoListAdapterHolder, position: Int) {
        holder.tvVideoName.setText(list.get(position).videoName)
        holder.tvVideoId.setText(list.get(position).videoId)
        holder.btnPlay.setOnClickListener({ v ->
            player!!.cueVideo(list.get(position).videoId)
            player?.play()
        })
        holder.btnPause.setOnClickListener({ v ->
            player?.pause()
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VideoListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvVideoName: TextView
        internal var tvVideoId: TextView
        internal var btnPlay: ImageButton
        internal var btnPause: ImageButton

        init {
            tvVideoName = itemView.findViewById(R.id.tvVideoName) as TextView
            tvVideoId = itemView.findViewById(R.id.tvVideoId) as TextView
            btnPlay = itemView.findViewById(R.id.btnPlay) as ImageButton
            btnPause = itemView.findViewById(R.id.btnPause) as ImageButton
        }
    }
}
