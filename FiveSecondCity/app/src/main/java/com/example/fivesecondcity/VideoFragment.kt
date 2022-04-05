package com.example.fivesecondcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import java.util.ArrayList

class VideoFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var videoList: MutableList<Video>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoList = ArrayList()
        viewPager = view.findViewById(R.id.viewPager2)

        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.videotwo,
                "New Llanelli School",
                "We discuss the council's controversial £9.1 plans for a new school in the Llanarch area"
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.videoone,
                "New Housing Development",
                "The council's plans for a new housing development in Trowbridge"
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.videothree,
                "Flood Defence Update",
                "New flood defences to be added to the bank of the river Ely after flood destruction"
            )
        )
        val adapter = VideoAdapter(videoList as ArrayList<Video>)
        viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }
}