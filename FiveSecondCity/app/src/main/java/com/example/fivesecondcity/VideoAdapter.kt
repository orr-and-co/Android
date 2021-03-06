package com.example.fivesecondcity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(var videoList: List<Video>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
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
        private var videoView = itemView.findViewById<VideoView>(R.id.VideoView)
        private var title = itemView.findViewById<TextView>(R.id.video_title)
        private var desc = itemView.findViewById<TextView>(R.id.video_desc)
        private var playButton = itemView.findViewById<ImageView>(R.id.playButton)


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
                playButton.visibility = View.GONE
            }

            videoView.setOnClickListener {
                if (videoView.isPlaying) {
                    videoView.pause()
                    playButton.visibility = View.VISIBLE
                } else {
                    videoView.start()
                    playButton.visibility = View.GONE
                }
            }

            videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }
        }

    }
}