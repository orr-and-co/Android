package com.example.fivesecondcity
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.VideoView
import android.widget.TextView
import android.view.View

class VideoAdapter(var videoList: List<Video>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setVideoData(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView = itemView.findViewById(R.id.VideoView)
        var title: TextView = itemView.findViewById(R.id.video_title)
        var desc: TextView = itemView.findViewById(R.id.video_desc)
        fun setVideoData(video: Video) {
            title.text = video.title
            desc.text = video.desc
            videoView.setVideoPath(video.videoUrl)
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.start()
                val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight
                    .toFloat()
                val screenRatio = videoView.width / videoView.height.toFloat()
                val scale = videoRatio / screenRatio
                if (scale >= 1f) {
                    videoView.scaleX = scale
                } else {
                    videoView.scaleY = 1f / scale
                }
            }
            videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }
        }

    }
}